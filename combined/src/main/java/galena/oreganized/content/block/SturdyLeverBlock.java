package galena.oreganized.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class SturdyLeverBlock extends LeverBlock implements IMeltableBlock, IPushableBlock {
    protected static final int DEPTH = 4;
    protected static final int WIDTH = 12;
    protected static final int HEIGHT = 12;
    protected static final VoxelShape NORTH_AABB = Block.box(
            (16 - WIDTH) / 2.0F, (16 - HEIGHT) / 2.0F, 16 - DEPTH,
            (16 + WIDTH) / 2.0F, (16 + HEIGHT) / 2.0F, 16.0F);
    protected static final VoxelShape SOUTH_AABB = Block.box(
            (16 - WIDTH) / 2.0F, (16 - HEIGHT) / 2.0F, 0.0F,
            (16 + WIDTH) / 2.0F, (16 + HEIGHT) / 2.0F, DEPTH);
    protected static final VoxelShape WEST_AABB = Block.box(
            16 - DEPTH, (16 - HEIGHT) / 2.0F, (16 - WIDTH) / 2.0F,
            16.0F, (16 + HEIGHT) / 2.0F, (16 + WIDTH) / 2.0F);
    protected static final VoxelShape EAST_AABB = Block.box(
            0.0F, (16 - HEIGHT) / 2.0F, (16 - WIDTH) / 2.0F,
            DEPTH, (16 + HEIGHT) / 2.0F, (16 + WIDTH) / 2.0F);
    protected static final VoxelShape UP_AABB_Z = Block.box(
            (16 - WIDTH) / 2.0F, 0.0F, (16 - HEIGHT) / 2.0F,
            (16 + WIDTH) / 2.0F, DEPTH, (16 + HEIGHT) / 2.0F);
    protected static final VoxelShape UP_AABB_X = Block.box(
            (16 - HEIGHT) / 2.0F, 0.0F, (16 - WIDTH) / 2.0F,
            (16 + HEIGHT) / 2.0F, DEPTH, (16 + WIDTH) / 2.0F);
    protected static final VoxelShape DOWN_AABB_Z = Block.box(
            (16 - WIDTH) / 2.0F, 16 - DEPTH, (16 - HEIGHT) / 2.0F,
            (16 + WIDTH) / 2.0F, 16.0F, (16 + HEIGHT) / 2.0F);
    protected static final VoxelShape DOWN_AABB_X = Block.box(
            (16 - HEIGHT) / 2.0F, 16 - DEPTH, (16 - WIDTH) / 2.0F,
            (16 + HEIGHT) / 2.0F, 16.0F, (16 + WIDTH) / 2.0F);

    public SturdyLeverBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        return switch (state.getValue(FACE)) {
            case FLOOR -> facing.getAxis() == Direction.Axis.X ? UP_AABB_X : UP_AABB_Z;
            case WALL -> switch (facing) {
                case EAST -> EAST_AABB;
                case WEST -> WEST_AABB;
                case SOUTH -> SOUTH_AABB;
                case NORTH, UP, DOWN -> NORTH_AABB;
            };
            case CEILING -> facing.getAxis() == Direction.Axis.X ? DOWN_AABB_X : DOWN_AABB_Z;
        };
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        return InteractionResult.FAIL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(getGoopynessProperty());
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        var state = super.getStateForPlacement(context);
        if (state == null) return null;
        var connectedState = context.getLevel().getBlockState(context.getClickedPos().relative(getConnectedDirection(state).getOpposite()));
        if (connectedState.hasProperty(getGoopynessProperty())) {
            return state.setValue(getGoopynessProperty(), connectedState.getValue(getGoopynessProperty()));
        }
        return state;
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        BlockState connectedState = level.getBlockState(pos.relative(getConnectedDirection(state).getOpposite()));
        if (connectedState.hasProperty(getGoopynessProperty())) {
            level.setBlock(pos, state.setValue(getGoopynessProperty(), connectedState.getValue(getGoopynessProperty())), 2);
        }
        super.neighborChanged(state, level, pos, block, fromPos, isMoving);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        return PushableBlockEntity.getAt(level, pos)
                .map(it -> it.use(state, level, pos, player))
                .orElse(ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION);
    }

    @Override
    public void onFullyPushed(Player player, Level level, BlockPos pos, BlockState state) {
        state = state.cycle(POWERED);
        level.setBlock(pos, state, 3);
        updateNeighbours(state, level, pos);
        playSound(null, level, pos, state);
        level.gameEvent(player, state.getValue(POWERED) ? GameEvent.BLOCK_ACTIVATE : GameEvent.BLOCK_DEACTIVATE, pos);
    }

    @Override
    public boolean isToggleable(BlockState state) {
        return true;
    }
}
