package galena.oreganized.plumbum.world;

import static net.minecraft.core.cauldron.CauldronInteraction.emptyBucket;
import static net.minecraft.world.level.block.Block.popResource;

import galena.oreganized.OConstants;
import galena.oreganized.plumbum.index.PlumbumBlocks;
import java.util.function.Predicate;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class MeltingCauldronInteractions {

    public static final CauldronInteraction.InteractionMap LEAD = CauldronInteraction.newInteractionMap(OConstants.MOD_ID + ":lead");
    public static final CauldronInteraction.InteractionMap MOLTEN_LEAD = CauldronInteraction.newInteractionMap(OConstants.MOD_ID + ":molten_lead");

    public static CauldronInteraction placeLeadBlock() {
        return placeBlock(SoundEvents.METAL_PLACE, PlumbumBlocks.MELTING_LEAD_CAULDRON.get().defaultBlockState());
    }

    public static CauldronInteraction fillMoltenLead() {
        return (state, world, pos, player, hand, stack) ->
                emptyBucket(world, pos, player, hand, stack, PlumbumBlocks.MOLTEN_LEAD_CAULDRON.get().defaultBlockState(), SoundEvents.BUCKET_EMPTY_LAVA);
    }

    public static CauldronInteraction placeBlock(SoundEvent sound, BlockState filledCauldron) {
        return (state, level, pos, player, hand, stack) -> {
            if (!level.isClientSide) {
                var item = stack.getItem();
                player.awardStat(Stats.FILL_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                if (!player.getAbilities().instabuild) stack.shrink(1);
                level.setBlockAndUpdate(pos, filledCauldron);
                level.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent(null, GameEvent.BLOCK_PLACE, pos);
            }

            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        };
    }

    public static CauldronInteraction dropResource(ItemStack droppedStack, Predicate<BlockState> stateCondition, SoundEvent sound) {
        return (state, level, pos, player, hand, stack) -> {
            if (!stateCondition.test(state))
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

            if (!level.isClientSide) {
                var item = stack.getItem();
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                popResource(level, pos, droppedStack);
                level.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                level.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);
            }

            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        };
    }

    public static CauldronInteraction convertItem(ItemStack result) {
        return (state, level, pos, player, hand, stack) -> {
            var newDisc = result.copy();

            player.swing(hand);
            if (!player.isCreative()) stack.shrink(1);

            level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.BLOCKS, 1.0F, 1.0F);
            if (!level.isClientSide()) player.awardStat(Stats.ITEM_USED.get(stack.getItem()));

            if (stack.isEmpty()) {
                player.setItemInHand(hand, newDisc);
            } else {
                if (!player.getInventory().add(newDisc)) {
                    player.drop(newDisc, false);
                }
            }

            level.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());

            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        };
    }

}
