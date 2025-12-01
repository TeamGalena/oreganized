package galena.oreganized.content.block;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import galena.oreganized.Oreganized;
import galena.oreganized.OreganizedConfig;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.TarnishedBlocks;
import galena.oreganized.network.packet.TarnishParticlePacket;
import java.util.ArrayList;
import java.util.Collection;
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

@EventBusSubscriber(modid = Oreganized.MOD_ID)
public class TarnishManager {

    private static final List<Block> HEADS = new ArrayList<>();
    private static final BiMap<Block, Block> NEXT_BY_BLOCK = HashBiMap.create();
    private static final BiMap<Block, Block> PREVIOUS_BY_BLOCK = HashBiMap.create();


    public static void setup() {
        registerTarnish(OBlocks.SILVER_BLOCKS);
        registerTarnish(OBlocks.CUT_SILVERS);
        registerTarnish(OBlocks.SILVER_PILLARS);
        registerTarnish(OBlocks.SILVER_BULBS);
        registerTarnish(OBlocks.SILVER_BARS);
    }

    public static void registerTarnish(TarnishedBlocks<?> blocks) {
        registerTarnish(blocks.base().get(), blocks.blemished().get(), blocks.tarnished().get());
    }

    public static void registerTarnish(Block... blocks) {
        for (int i = 0; i < blocks.length - 1; i++) {
            registerTarnish(blocks[i], blocks[i + 1]);
        }
    }

    public static void registerTarnish(Block before, Block after) {
        NEXT_BY_BLOCK.put(before, after);
        PREVIOUS_BY_BLOCK.put(after, before);
        var newHead = first(before);
        if (!HEADS.contains(newHead)) {
            HEADS.add(newHead);
        } else if (HEADS.contains(after)) {
            HEADS.set(HEADS.indexOf(before), after);
        }
    }

    public static boolean isPristine(Block b) {
        return first(b) == b;
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

    public static Block last(Block block) {
        Block current = block;
        Block next;
        do {
            next = next(current);
            if (next != null) {
                current = next;
            }
        } while (next != null);
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
        return PREVIOUS_BY_BLOCK.get(block);
    }

    @Nullable
    public static BlockState previous(BlockState state) {
        Block prev = previous(state.getBlock());
        if (prev != null) {
            return prev.withPropertiesOf(state);
        }
        return null;
    }

    //could be replaced with tag check
    public static boolean canTarnish(Block block) {
        return NEXT_BY_BLOCK.containsKey(block);
    }


    @SubscribeEvent
    public static void onEntityDie(LivingDeathEvent e) {

        var entity = e.getEntity();
        //replace with tag in 1.21
        if (entity.getType().is(EntityTypeTags.UNDEAD)) {
            BlockPos center = entity.blockPosition();
            int radius = OreganizedConfig.COMMON.tarnishRadius.get();
            int maxIter = OreganizedConfig.COMMON.tarnishChecksPerMob.get();
            for (int i = 0; i < maxIter; i++) {
                RandomSource random = entity.getRandom();
                BlockPos randomPos = center.offset(
                        random.nextIntBetweenInclusive(-radius, radius),
                        random.nextIntBetweenInclusive(-radius, radius),
                        random.nextIntBetweenInclusive(-radius, radius)
                );
                Level level = entity.level();
                BlockState state = level.getBlockState(randomPos);
                var tarnished = TarnishManager.next(state);
                if (tarnished != null) {
                    double chance = OreganizedConfig.COMMON.tarnishChance.get();
                    boolean isPristine = isPristine(state.getBlock());
                    if (!isPristine) {
                        chance /= 2.0D;
                    }
                    if (random.nextDouble() > chance) {
                        continue;
                    }
                    if (!isPristine && hasPristineAround(randomPos, level)) {
                        continue;
                    }
                    level.setBlockAndUpdate(randomPos, tarnished);
                    if (level instanceof ServerLevel sl)
                        PacketDistributor.sendToPlayersInDimension(sl, new TarnishParticlePacket(randomPos, true));
                    break;
                }
            }
        }

    }

    private static boolean hasPristineAround(BlockPos pos, Level level) {
        for (Direction dir : Direction.values()) {
            BlockPos checkPos = pos.relative(dir);
            BlockState checkState = level.getBlockState(checkPos);
            if (isPristine(checkState.getBlock())) {
                return true;
            }
        }
        return false;
    }


    public static Collection<Block> getAllTarnishables() {
        List<Block> blocks = new ArrayList<>();
        //start from head , add all in order
        for (Block head : HEADS) {
            Block current = head;
            blocks.add(current);
            Block next;
            do {
                next = next(current);
                if (next != null) {
                    blocks.add(next);
                    current = next;
                }
            } while (next != null);
        }
        return blocks;
    }

}
