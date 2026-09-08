package galena.oreganized.plumbum.world.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class LeadDoorBlock extends DoorBlock implements IMeltableBlock, IPushableBlock {

    public LeadDoorBlock(BlockSetType type, Properties properties) {
        super(type, properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return state.getValue(HALF) == DoubleBlockHalf.LOWER
                ? IPushableBlock.super.newBlockEntity(pos, state)
                : null;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        var controller = state.getValue(HALF) == DoubleBlockHalf.UPPER ? pos.below() : pos;
        return PushableBlockEntity.getAt(level, controller)
                .map(it -> it.use(state, level, pos, player))
                .orElse(ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(getGoopynessProperty());
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
                    || selfHalf == DoubleBlockHalf.LOWER && pos.getY() > selfPos.getY()) {
                return 0;
            }
        }
        return IMeltableBlock.super.getInducedGoopyness(world, state, pos, selfState, selfPos);
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
        scheduleUpdate(level, pos, block);
        boolean flag = level.hasNeighborSignal(pos) || level.hasNeighborSignal(pos.relative(state.getValue(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN));
        if (!defaultBlockState().is(block) && flag != state.getValue(POWERED)) {
            level.setBlock(pos, state.setValue(POWERED, flag), UPDATE_CLIENTS);
        }
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        var state = super.getStateForPlacement(context);
        if (state == null) return null;
        return state.setValue(OPEN, false);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        scheduleUpdate(level, pos, state.getBlock());
    }

    @Override
    public void onFullyPushed(Player player, Level level, BlockPos pos, BlockState state) {
        boolean isOpen = state.getValue(OPEN);
        if (isOpen && !isToggleable(state)) return;

        level.setBlock(pos, state.setValue(OPEN, !isOpen), UPDATE_CLIENTS | UPDATE_IMMEDIATE);
        playSound(null, level, pos, !isOpen);
        level.gameEvent(player, !isOpen ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
    }

    @Override
    public void reset(Level level, BlockPos pos, BlockState state) {
        if (!state.getValue(OPEN)) return;

        setOpen(null, level, state, pos, false);
    }

    @Override
    public boolean isToggleable(BlockState state) {
        return state.getValue(POWERED);
    }
}
