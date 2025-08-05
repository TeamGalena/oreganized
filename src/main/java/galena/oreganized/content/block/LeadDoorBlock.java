package galena.oreganized.content.block;

import galena.oreganized.index.OBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class LeadDoorBlock extends DoorBlock implements IMeltableBlock, EntityBlock, IHeavyDoor {

    /**
     * Not fully implemented yet
     */
    public static final BooleanProperty ANIMATED = BooleanProperty.create("animated");


    public LeadDoorBlock(Properties properties) {
        super(OBlocks.LEAD_BLOCK_SET, properties);
        //registerDefaultState(defaultBlockState().setValue(ANIMATED, false));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        if (state.getValue(HALF) == DoubleBlockHalf.UPPER) return null;
        return new HeavyDoorBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return HeavyDoorBlockEntity.getTicker(level, state, type);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        var controller = state.getValue(HALF) == DoubleBlockHalf.UPPER ? pos.below() : pos;
        return HeavyDoorBlockEntity.getAt(level, controller)
                .map(it -> it.use(state, level, pos, player))
                .orElse(ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(getGoopynessProperty());
        //builder.add(ANIMATED);
    }

    @Override
    public int getNextGoopyness(BlockGetter world, BlockState selfState, BlockPos selfPos) {
        var self = goopynessAt(world, selfState, selfPos);

        var half = selfState.getValue(HALF);
        var otherPos = half == DoubleBlockHalf.LOWER ? selfPos.above() : selfPos.below();
        var otherState = world.getBlockState(otherPos);
        var other = goopynessAt(world, otherState, otherPos);

        return Math.max(self, other);
    }

    @Override
    public int getInducedGoopyness(BlockGetter world, BlockState state, BlockPos pos, BlockState selfState, BlockPos selfPos) {
        if (state.is(this)) {
            var selfHalf = selfState.getValue(HALF);
            if (selfHalf == DoubleBlockHalf.UPPER && pos.getY() < selfPos.getY()
                    || selfHalf == DoubleBlockHalf.LOWER && pos.getY() > selfPos.getY()
            ) {
                return 0;
            }
        }
        return IMeltableBlock.super.getInducedGoopyness(world, state, pos, selfState, selfPos);
    }

    /*
    Only required for `ANIMATABLE` property

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState changedState, LevelAccessor level, BlockPos pos, BlockPos changedPos) {
        var updated = super.updateShape(state, direction, changedState, level, pos, changedPos);
        var half = state.getValue(HALF);
        if (changedState.is(this) && ((direction == Direction.UP && half == DoubleBlockHalf.LOWER) || (direction == Direction.DOWN && half == DoubleBlockHalf.UPPER))) {
            return updated.setValue(ANIMATED, changedState.getValue(ANIMATED));
        }
        return updated;
    }
    */

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
        scheduleUpdate(level, pos, block);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(POWERED, false);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        scheduleUpdate(level, pos, state.getBlock());
    }

    @Override
    public void sound(@Nullable Player player, Level level, BlockPos pos, boolean open) {
        playSound(player, level, pos, open);
    }
}
