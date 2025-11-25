package galena.oreganized.content.block;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import galena.oreganized.Oreganized;
import galena.oreganized.OreganizedConfig;
import galena.oreganized.index.OBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import org.jetbrains.annotations.Nullable;

@EventBusSubscriber(modid = Oreganized.MOD_ID)
public class TarnishManager {

    private static final BiMap<Block, Block> NEXT_BY_BLOCK = HashBiMap.create();
    private static final BiMap<Block, Block> PREVIOUS_BY_BLOCK = HashBiMap.create();


    public static void setup() {
        registerTarnish(OBlocks.SILVER_BLOCK.get(),
                OBlocks.BLEMISHED_SILVER_BLOCK.get(),
                OBlocks.TARNISHED_SILVER_BLOCK.get());

        registerTarnish(OBlocks.SILVER_BULB.get(),
                OBlocks.BLEMISHED_SILVER_BULB.get(),
                OBlocks.TARNISHED_SILVER_BULB.get());
    }


    public static void registerTarnish(Block ... blocks) {
        for (int i = 0; i < blocks.length - 1; i++) {
            registerTarnish(blocks[i], blocks[i + 1]);
        }
    }

    public static void registerTarnish(Block prev, Block second) {
        NEXT_BY_BLOCK.put(prev, second);
        PREVIOUS_BY_BLOCK.put(second, prev);
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
                    level.setBlockAndUpdate(randomPos, tarnished);
                    //TODO: particle/sound
                    break;
                }
            }
        }

    }
}
