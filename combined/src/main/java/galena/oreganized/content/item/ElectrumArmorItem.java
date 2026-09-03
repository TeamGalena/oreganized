package galena.oreganized.content.item;

import com.google.common.base.Suppliers;
import galena.oreganized.Oreganized;
import galena.oreganized.electrum.config.ElectrumConfigs;
import galena.oreganized.index.OArmorMaterials;

import java.util.function.Supplier;
import javax.annotation.Nullable;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class ElectrumArmorItem extends ArmorItem {
    private final ResourceLocation texture;
    private final Supplier<ItemAttributeModifiers> modifiers;

    public ElectrumArmorItem(ArmorItem.Type slot) {
        super(OArmorMaterials.ELECTRUM, slot, new Properties().durability(slot.getDurability(33)));

        var index = slot == Type.LEGGINGS ? 2 : 1;
        this.texture = Oreganized.modLoc("textures/models/armor/electrum_layer_%s.png".formatted(index));

        modifiers = Suppliers.memoize(() -> {
            var builder = ItemAttributeModifiers.builder();
            var material = OArmorMaterials.ELECTRUM.value();
            var slotGroup = EquipmentSlotGroup.bySlot(slot.getSlot());
            var id = ResourceLocation.withDefaultNamespace("armor." + slot.getName());
            builder.add(Attributes.ARMOR, new AttributeModifier(id, material.getDefense(slot), Operation.ADD_VALUE), slotGroup);
            builder.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(id, material.toughness(), Operation.ADD_VALUE), slotGroup);

            double speedBoost = ElectrumConfigs.COMMON.electrumSpeedBoost.get();
            if (speedBoost > 0) {
                builder.add(Attributes.MOVEMENT_SPEED, new AttributeModifier(id, speedBoost, Operation.ADD_MULTIPLIED_BASE), slotGroup);
            }
            return builder.build();
        });
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers() {
        return modifiers.get();
    }


    @Nullable
    @Override
    public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        return texture;
    }

}
