package galena.oreganized.world.event;

import galena.oreganized.Oreganized;
import galena.oreganized.content.block.MoltenLeadCauldronBlock;
import galena.oreganized.content.entity.GargoyleBlockEntity;
import galena.oreganized.content.item.ScribeItem;
import galena.oreganized.content.item.ThermometerItem;
import galena.oreganized.index.OAttributes;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.OItems;
import galena.oreganized.index.OTags;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Oreganized.MOD_ID)
public class PlayerEvents {

    @SubscribeEvent
    public static void blockToolInteractions(final BlockEvent.BlockToolModificationEvent event) {
        var action = event.getItemAbility();
        BlockState state = event.getState();
        if (event.isSimulated()) return;

        // Removing Wax ('Unwaxing' - Using an Axe on a waxed block).
        if (action.equals(ItemAbilities.AXE_WAX_OFF)) {
            Block unWaxedBlock = OBlocks.WAXED_BLOCKS.get(state.getBlock());
            if (unWaxedBlock == null) return;
            event.setFinalState(unWaxedBlock.defaultBlockState());
        }
    }

    /**
     * Use if interaction is not defined in {@link ItemAbilities}
     **/
    @SubscribeEvent
    public static void blockItemInteractions(final PlayerInteractEvent.RightClickBlock event) {
        Level world = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = world.getBlockState(pos);
        ItemStack itemStack = event.getItemStack();
        Player player = event.getEntity();
        InteractionHand hand = event.getHand();

        // Waxing (Using Honeycomb on a waxable block).
        if (itemStack.is(Items.HONEYCOMB) && OBlocks.WAXED_BLOCKS.inverse().get(state.getBlock()) != null) {

            if (player instanceof ServerPlayer)
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, pos, itemStack);

            player.swing(event.getHand());
            if (!player.isCreative()) event.getItemStack().shrink(1);
            Block waxedBlock = OBlocks.WAXED_BLOCKS.inverse().get(state.getBlock());
            if (!world.isClientSide() && waxedBlock != null) world.setBlock(pos, waxedBlock.defaultBlockState(), 11);
            world.levelEvent(player, 3003, pos, 0);
        }

        if (itemStack.is(Items.MUSIC_DISC_11) && state.is(OBlocks.MOLTEN_LEAD_CAULDRON.get())) {
            if (!state.getValue(MoltenLeadCauldronBlock.AGE).equals(3)) return;
            ItemStack newDisc = new ItemStack(OItems.MUSIC_DISC_STRUCTURE.get());

            player.swing(hand);
            if (!player.isCreative()) itemStack.shrink(1);
            world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.BLOCKS, 1.0F, 1.0F);
            if (!world.isClientSide()) player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));

            if (itemStack.isEmpty()) {
                player.setItemInHand(hand, newDisc);
                return;
            }
            if (!player.getInventory().add(newDisc)) {
                player.drop(newDisc, false);
                //return;
            }

            world.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
        }
    }

    @SubscribeEvent
    public static void tickPlayer(final PlayerTickEvent.Post event) {
        var data = event.getEntity().getPersistentData();
        if (data.contains(GargoyleBlockEntity.GROWL_COOLDOWN_TAG, 99)) {
            var cooldown = data.getInt(GargoyleBlockEntity.GROWL_COOLDOWN_TAG);
            if (cooldown > 0) {
                data.putInt(GargoyleBlockEntity.GROWL_COOLDOWN_TAG, cooldown - 1);
            } else {
                data.remove(GargoyleBlockEntity.GROWL_COOLDOWN_TAG);
            }
        }

        var stack = event.getEntity().getItemInHand(InteractionHand.MAIN_HAND);

        if (event.getEntity().level().getGameTime() % 20L != 0) return;
        if (stack.is(OItems.THERMOMETER.get()) && !ThermometerItem.isLocked(stack)) {
            var heatLevel = ThermometerItem.ambientMeasurement(event.getEntity());
            ThermometerItem.setHeatLevel(stack, event.getEntity().level(), heatLevel);
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(final BlockDropsEvent event) {
        var stack = event.getTool();

        if (stack.getItem() instanceof ScribeItem scribe && scribe.dropsLikeSilktouch(stack, event.getState())) {
            event.setDroppedExperience(0);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onItemAttributes(ItemAttributeModifierEvent event) {
        var stack = event.getItemStack();

        if (stack.is(OTags.Items.HAS_KINETIC_DAMAGE)) {
            var damage = event.getModifiers().stream()
                    .filter(it -> it.matches(Attributes.ATTACK_DAMAGE, Item.BASE_ATTACK_DAMAGE_ID))
                    .map(it -> it.modifier().amount())
                    .findFirst()
                    .orElse(2.0);

            event.addModifier(
                    OAttributes.KINETIC_DAMAGE,
                    new AttributeModifier(Oreganized.modLoc("kinetic_damage"), damage / 3, AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.MAINHAND
            );
        }
    }

}
