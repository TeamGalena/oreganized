package galena.oreganized.content.block;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import galena.oreganized.Oreganized;
import galena.oreganized.OreganizedConfig;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.TarnishedBlocks;
import galena.oreganized.network.packet.TarnishParticlePacket;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

/**
 * This would be a good use case for <a href="https://docs.neoforged.net/docs/resources/server/datamaps/">NeoForge's DataMaps</a>
 * This would enable mod (or even datapack) authors to add tarnished block sets without having to depend on oreganized
 */
@EventBusSubscriber(modid = Oreganized.MOD_ID)
public class TarnishManager {

    private static final BiMap<Block, Block> NEXT_BY_BLOCK = HashBiMap.create();
    private static final List<Block> PRISTINE = new ArrayList<>();

    public static void setup() {
        registerTarnish(OBlocks.SILVER_BLOCKS);
        registerTarnish(OBlocks.CUT_SILVERS);
        registerTarnish(OBlocks.SILVER_LATTICES);
        registerTarnish(OBlocks.SILVER_PILLARS);
        registerTarnish(OBlocks.CHISELED_SILVER);
        registerTarnish(OBlocks.SILVER_BULBS);
        registerTarnish(OBlocks.SILVER_BARS);
        registerTarnish(OBlocks.CUT_SILVER_STAIRS);
        registerTarnish(OBlocks.CUT_SILVER_SLABS);
        registerTarnish(OBlocks.SILVER_DOORS);
        registerTarnish(OBlocks.SILVER_TRAPDOORS);
    }

    public static void registerTarnish(TarnishedBlocks<?> blocks) {
        registerTarnish(blocks.base().get(), blocks.blemished().get(), blocks.tarnished().get());
    }

    public static void registerTarnish(Block... blocks) {
        if (blocks.length < 2) throw new IllegalArgumentException("tarnished block set must be at least 2 blocks");

        PRISTINE.add(blocks[0]);
        for (int i = 0; i < blocks.length - 1; i++) {
            registerTarnish(blocks[i], blocks[i + 1]);
        }
    }

    private static void registerTarnish(Block before, Block after) {
        NEXT_BY_BLOCK.put(before, after);
    }

    public static boolean isPristine(Block b) {
        return PRISTINE.contains(b);
    }

    public static Block first(Block block) {
        Block current = block;
        Block previous;
        do {
            previous = previous(current);
            if (previous != null) {
                current = previous;
            }
        } while (previous != null);
        return current;
    }

    @Nullable
    public static Block next(Block block) {
        return NEXT_BY_BLOCK.get(block);
    }

    @Nullable
    public static BlockState next(BlockState state) {
        Block next = next(state.getBlock());
        if (next != null) {
            return next.withPropertiesOf(state);
        }
        return null;
    }

    @Nullable
    public static Block previous(Block block) {
        return NEXT_BY_BLOCK.inverse().get(block);
    }

    @Nullable
    public static BlockState previous(BlockState state) {
        var prev = previous(state.getBlock());
        if (prev != null) {
            return prev.withPropertiesOf(state);
        }
        return null;
    }

    public static boolean canTarnish(Block block) {
        return NEXT_BY_BLOCK.containsKey(block);
    }

    private static void tryTarnishing(BlockState state, BlockPos pos, Level level, RandomSource random) {
        var tarnished = TarnishManager.next(state);
        if (tarnished != null) {
            boolean isPristine = isPristine(state.getBlock());
            if (!isPristine && hasPristineAround(pos, level)) return;

            level.setBlockAndUpdate(pos, tarnished);
            if (level instanceof ServerLevel sl)
                PacketDistributor.sendToPlayersInDimension(sl, new TarnishParticlePacket(pos, true));
        }
    }

    @SubscribeEvent
    public static void onEntityDie(LivingDeathEvent event) {
        var entity = event.getEntity();

        if (entity.getType().is(EntityTypeTags.UNDEAD)) {
            var center = entity.blockPosition();
            int radius = OreganizedConfig.COMMON.tarnishRadius.get();
            int maxIter = OreganizedConfig.COMMON.tarnishChecksPerMob.get();
            for (int i = 0; i < maxIter; i++) {
                var random = entity.getRandom();
                var randomPos = center.offset(
                        random.nextIntBetweenInclusive(-radius, radius),
                        random.nextIntBetweenInclusive(-radius, radius),
                        random.nextIntBetweenInclusive(-radius, radius)
                );

                var level = entity.level();
                var state = level.getBlockState(randomPos);

                tryTarnishing(state, randomPos, level, random);
            }
        }

    }

    private static boolean hasPristineAround(BlockPos pos, Level level) {
        for (var dir : Direction.values()) {
            var checkPos = pos.relative(dir);
            var checkState = level.getBlockState(checkPos);
            if (isPristine(checkState.getBlock())) {
                return true;
            }
        }
        return false;
    }

}
