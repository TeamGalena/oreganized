package galena.oreganized.gothic.world.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class ConnectedSpyreFenceBlock extends SpyreFenceBlock {

    public static final BooleanProperty CONNECTED = BooleanProperty.create("connected");

    public ConnectedSpyreFenceBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(CONNECTED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CONNECTED);
    }

    private boolean connectsTo(LevelAccessor level, BlockPos pos) {
        var state = level.getBlockState(pos);
        return connectsTo(state);
    }

    private boolean connectsTo(BlockState state) {
        return state.is(this);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        var updated = super.updateShape(state, facing, facingState, level, currentPos, facingPos);
        if (facing != Direction.UP) return updated;
        var connected = connectsTo(facingState);
        return updated.setValue(CONNECTED, connected);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context)
                .setValue(CONNECTED, connectsTo(context.getLevel(), context.getClickedPos().above()));
    }
}
