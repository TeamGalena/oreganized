package galena.oreganized.plumbum.world.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class LeadBulbBlock extends Block implements IMeltableBlock {

    public static final IntegerProperty GOOPYNESS_4 = IntegerProperty.create("goopyness", 0, 3);

    public static int getLightLevel(BlockState state) {
        return switch (state.getValue(GOOPYNESS_4)) {
            case 0 -> 13;
            case 1 -> 8;
            case 2 -> 3;
            default -> 6;
        };
    }

    public LeadBulbBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(GOOPYNESS_4, 0));
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> definition) {
        super.createBlockStateDefinition(definition);
        definition.add(GOOPYNESS_4);
    }

    @Override
    public int getGoopyness(BlockState state) {
        return Math.max(0, state.getValue(GOOPYNESS_4) - 1);
    }

    @Override
    public IntegerProperty getGoopynessProperty() {
        return GOOPYNESS_4;
    }

    @Override
    public int getInducedGoopyness(BlockGetter world, BlockState state, BlockPos pos, BlockState selfState, BlockPos selfPos) {
        if (state.getBlock() instanceof IMeltableBlock meltable && meltable.getGoopyness(state) == 1) return 1;
        var defaultGoopyness = IMeltableBlock.super.getInducedGoopyness(world, state, pos, selfState, selfPos);
        if(defaultGoopyness == 0) return 0;
        return defaultGoopyness + 1;
    }

    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        tickMelting(state, world, pos, random);
    }

    @Override
    public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
        hurt(state, world, entity);
        super.stepOn(world, pos, state, entity);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, level, pos, block, fromPos, isMoving);
        scheduleUpdate(level, pos, block);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        scheduleUpdate(level, pos, state.getBlock());
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        var goopyness = state.getValue(GOOPYNESS_4);
        return (3 - goopyness) * 5;
    }

}
