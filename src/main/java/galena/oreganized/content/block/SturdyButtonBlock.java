package galena.oreganized.content.block;

import galena.oreganized.index.OBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class SturdyButtonBlock extends ButtonBlock implements IMeltableBlock, IPushableBlock {
    protected static final int PRESSED_DEPTH = 2;
    protected static final int UNPRESSED_DEPTH = 4;
    protected static final int HALF_AABB_HEIGHT = 4;
    protected static final int HALF_AABB_WIDTH = 4;
    protected static final VoxelShape CEILING_AABB = Block.box(
            8.0 - HALF_AABB_HEIGHT, 16.0 - UNPRESSED_DEPTH, 8.0 - HALF_AABB_WIDTH,
            8.0 + HALF_AABB_HEIGHT, 16.0, 8.0 + HALF_AABB_WIDTH);
    protected static final VoxelShape FLOOR_AABB = Block.box(
            8.0 - HALF_AABB_HEIGHT, 0, 8.0 - HALF_AABB_WIDTH,
            8.0 + HALF_AABB_HEIGHT, UNPRESSED_DEPTH, 8.0 + HALF_AABB_WIDTH);
    protected static final VoxelShape NORTH_AABB = Block.box(
            8.0 - HALF_AABB_WIDTH, 8.0 - HALF_AABB_HEIGHT, 16.0 - UNPRESSED_DEPTH,
            8.0 + HALF_AABB_WIDTH, 8.0 + HALF_AABB_HEIGHT, 16.0);
    protected static final VoxelShape SOUTH_AABB = Block.box(
            8.0 - HALF_AABB_WIDTH, 8.0 - HALF_AABB_HEIGHT, 0,
            8.0 + HALF_AABB_WIDTH, 8.0 + HALF_AABB_HEIGHT, UNPRESSED_DEPTH);
    protected static final VoxelShape WEST_AABB = Block.box(
            16.0 - UNPRESSED_DEPTH, 8.0 - HALF_AABB_HEIGHT, 8.0 - HALF_AABB_WIDTH,
            16.0, 8.0 + HALF_AABB_HEIGHT, 8.0 + HALF_AABB_WIDTH);
    protected static final VoxelShape EAST_AABB = Block.box(
            0, 8.0 - HALF_AABB_HEIGHT, 8.0 - HALF_AABB_WIDTH,
            UNPRESSED_DEPTH, 8.0 + HALF_AABB_HEIGHT, 8.0 + HALF_AABB_WIDTH);
    protected static final VoxelShape PRESSED_CEILING_AABB = Block.box(
            8.0 - HALF_AABB_HEIGHT, 16.0 - PRESSED_DEPTH, 8.0 - HALF_AABB_WIDTH,
            8.0 + HALF_AABB_HEIGHT, 16.0, 8.0 + HALF_AABB_WIDTH);
    protected static final VoxelShape PRESSED_FLOOR_AABB = Block.box(
            8.0 - HALF_AABB_HEIGHT, 0, 8.0 - HALF_AABB_WIDTH,
            8.0 + HALF_AABB_HEIGHT, PRESSED_DEPTH, 8.0 + HALF_AABB_WIDTH);
    protected static final VoxelShape PRESSED_NORTH_AABB = Block.box(
            8.0 - HALF_AABB_WIDTH, 8.0 - HALF_AABB_HEIGHT, 16.0 - PRESSED_DEPTH,
            8.0 + HALF_AABB_WIDTH, 8.0 + HALF_AABB_HEIGHT, 16.0);
    protected static final VoxelShape PRESSED_SOUTH_AABB = Block.box(
            8.0 - HALF_AABB_WIDTH, 8.0 - HALF_AABB_HEIGHT, 0,
            8.0 + HALF_AABB_WIDTH, 8.0 + HALF_AABB_HEIGHT, PRESSED_DEPTH);
    protected static final VoxelShape PRESSED_WEST_AABB = Block.box(
            16.0 - PRESSED_DEPTH, 8.0 - HALF_AABB_HEIGHT, 8.0 - HALF_AABB_WIDTH,
            16.0, 8.0 + HALF_AABB_HEIGHT, 8.0 + HALF_AABB_WIDTH);
    protected static final VoxelShape PRESSED_EAST_AABB = Block.box(
            0, 8.0 - HALF_AABB_HEIGHT, 8.0 - HALF_AABB_WIDTH,
            PRESSED_DEPTH, 8.0 + HALF_AABB_HEIGHT, 8.0 + HALF_AABB_WIDTH);

    public SturdyButtonBlock(Properties properties) {
        super(OBlocks.LEAD_BLOCK_SET, 30, properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        boolean flag = state.getValue(POWERED);
        return switch (state.getValue(FACE)) {
            case FLOOR -> flag ? PRESSED_FLOOR_AABB : FLOOR_AABB;
            case WALL -> switch (direction) {
                case EAST -> flag ? PRESSED_EAST_AABB : EAST_AABB;
                case WEST -> flag ? PRESSED_WEST_AABB : WEST_AABB;
                case SOUTH -> flag ? PRESSED_SOUTH_AABB : SOUTH_AABB;
                case NORTH, UP, DOWN -> flag ? PRESSED_NORTH_AABB : NORTH_AABB;
            };
            default -> flag ? PRESSED_CEILING_AABB : CEILING_AABB;
        };
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
        if (state.getValue(POWERED)) return;

        state = state.setValue(POWERED, true);
        level.setBlock(pos, state, UPDATE_ALL);
        updateNeighbours(state, level, pos);
        level.gameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
        playSound(null, level, pos, true);
    }

    @Override
    public void reset(Level level, BlockPos pos, BlockState state) {
        if (!state.getValue(POWERED)) return;

        state = state.setValue(POWERED, false);
        level.setBlock(pos, state, UPDATE_ALL);
        updateNeighbours(state, level, pos);
        level.gameEvent(null, GameEvent.BLOCK_DEACTIVATE, pos);
        playSound(null, level, pos, false);
    }
}
