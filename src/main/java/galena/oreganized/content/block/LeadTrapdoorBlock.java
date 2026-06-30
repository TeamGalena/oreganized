package galena.oreganized.content.block;

import galena.oreganized.index.OBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class LeadTrapdoorBlock extends TrapDoorBlock implements IMeltableBlock, IPushableBlock {

    public LeadTrapdoorBlock(Properties properties) {
        super(OBlocks.LEAD_BLOCK_SET, properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        return PushableBlockEntity.getAt(level, pos)
                .map(it -> it.use(state, level, pos, player))
                .orElse(ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION);
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
        scheduleUpdate(level, pos, block);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(OPEN, false);
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

        level.setBlock(pos, state.setValue(OPEN, !isOpen), UPDATE_CLIENTS);
        level.playSound(
                null, pos,
                isOpen ? getType().trapdoorClose() : getType().trapdoorOpen(),
                SoundSource.BLOCKS,
                1.0F,
                level.getRandom().nextFloat() * 0.1F + 0.9F
        );
        level.gameEvent(player, !isOpen ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
    }

    @Override
    public void reset(Level level, BlockPos pos, BlockState state) {
        if (!state.getValue(OPEN)) return;

        state = state.setValue(OPEN, false);
        level.setBlock(pos, state, UPDATE_CLIENTS);
        // Not calling level.gameEvent here because TrapDoorBlock's playSound already does that
        playSound(null, level, pos, false);
    }

    @Override
    public boolean isToggleable(BlockState state) {
        return state.getValue(POWERED);
    }
}
