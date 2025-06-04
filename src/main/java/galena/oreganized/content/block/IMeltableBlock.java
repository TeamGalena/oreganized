package galena.oreganized.content.block;

import galena.oreganized.index.OTags;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

/**
 * MeltableBlock check list:
 * - add goopyness block property
 * - override `stepOn` and call `hurt`
 * - override `tick` and call `tickMelting`
 * - override `neighborChanged` and call `scheduleUpdate`
 * - override `onPlace` and call `scheduleUpdate`
 */
public interface IMeltableBlock {

    IntegerProperty GOOPYNESS_3 = IntegerProperty.create("goopyness", 0, 2);

    List<BlockPos> OFFSET = List.of(
            new BlockPos(+1, 0, 0),
            new BlockPos(-1, 0, 0),
            new BlockPos(0, +1, 0),
            new BlockPos(0, -1, 0),
            new BlockPos(0, 0, +1),
            new BlockPos(0, 0, -1)
    );

    default IntegerProperty getGoopynessProperty() {
        return GOOPYNESS_3;
    }

    default int getGoopyness(BlockState state) {
        return state.getValue(getGoopynessProperty());
    }

    static int getLightLevel(BlockState state) {
        return switch (state.getValue(GOOPYNESS_3)) {
            case 2 -> 6;
            case 1 -> 3;
            default -> 0;
        };
    }

    default int getInducedGoopyness(BlockGetter world, BlockState state, BlockPos pos, BlockState selfState, BlockPos selfPos) {
        if (state.is(OTags.Blocks.MELTS_LEAD)) return 2;
        if (state.getBlock() instanceof IMeltableBlock meltable && meltable.getGoopyness(state) == 2) return 1;
        if (state.getLightEmission(world, pos) >= 15) return 1;
        return 0;
    }

    default int goopynessAt(BlockGetter world, BlockState selfState, BlockPos selfPos) {
        var touching = OFFSET.stream()
                .map(selfPos::offset)
                .mapToInt(pos -> getInducedGoopyness(world, world.getBlockState(pos), pos, selfState, selfPos))
                .max();

        return touching.orElse(0);
    }

    default int getNextGoopyness(BlockGetter world, BlockState selfState, BlockPos selfPos) {
        return goopynessAt(world, selfState, selfPos);
    }

    /**
     * @return if the block should be updated
     */
    default boolean onGoopynessChange(Level world, BlockState state, BlockPos pos, RandomSource random, int from, int to) {
        return true;
    }

    default void tickMelting(BlockState state, Level world, BlockPos pos, RandomSource random) {
        int currentGoopyness = state.getValue(getGoopynessProperty());
        int goopyness = getNextGoopyness(world, state, pos);

        if (currentGoopyness != goopyness) {
            if (onGoopynessChange(world, state, pos, random, currentGoopyness, goopyness)) {
                world.setBlockAndUpdate(pos, state.setValue(getGoopynessProperty(), goopyness));
            }
        }

        scheduleUpdate(world, pos, state.getBlock());
        //world.scheduleTick(pos, state.getBlock(), 1);
    }

    default void hurt(BlockState state, Level world, Entity entity) {
        if (getGoopyness(state) < 2) return;
        if (!entity.isSteppingCarefully() && entity instanceof LivingEntity le && !EnchantmentHelper.hasFrostWalker(le)) {
            entity.hurt(world.damageSources().hotFloor(), 1.0F);
        }
    }

    default void scheduleUpdate(Level level, BlockPos pos, Block block) {
        level.scheduleTick(pos, block, level.random.nextInt(30, 60));
    }

}
