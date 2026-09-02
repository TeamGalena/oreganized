package galena.oreganized.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class LeadBarsBlock extends IronBarsBlock implements IMeltableBlock {

    public LeadBarsBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean onGoopynessChange(Level world, BlockState state, BlockPos pos, RandomSource random, int from, int to) {
        if(from < 2) return true;
        world.destroyBlock(pos, true);
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(getGoopynessProperty());
    }

    @Override
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
}
