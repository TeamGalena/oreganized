package galena.oreganized.gothic.world.block;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.DOWN;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.UP;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.pathfinder.PathComputationType;

public class SpyreFenceBlock extends IronBarsBlock {

    public SpyreFenceBlock(Properties properties) {
        super(properties);
    }

    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(UP, DOWN);
    }

    private boolean attachesTo(@Nullable BlockState state, LevelAccessor level, BlockPos pos, Direction facing) {
        if (state == null) state = level.getBlockState(pos);
        return state.is(this);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        var updated = super.updateShape(state, facing, facingState, level, currentPos, facingPos);
        if (facing.getAxis().isHorizontal()) return updated;
        var connected = attachesTo(facingState, level, facingPos, facing.getOpposite());
        var property = facing == Direction.UP ? UP : DOWN;
        return updated.setValue(property, connected);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context)
                .setValue(DOWN, attachesTo(null, context.getLevel(), context.getClickedPos().below(), Direction.UP))
                .setValue(UP, attachesTo(null, context.getLevel(), context.getClickedPos().above(), Direction.DOWN));
    }
}
