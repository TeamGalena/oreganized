package galena.oreganized.world.event;

import galena.oreganized.OConstants;
import galena.oreganized.content.item.ScribeItem;
import galena.oreganized.gothic.world.block.entity.GargoyleBlockEntity;
import galena.oreganized.index.OAttributes;
import galena.oreganized.index.OTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber
public class PlayerEvents {

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
                    new AttributeModifier(OConstants.modLoc("kinetic_damage"), damage / 3, AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.MAINHAND
            );
        }
    }

}
