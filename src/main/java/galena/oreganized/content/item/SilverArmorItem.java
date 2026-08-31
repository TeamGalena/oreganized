package galena.oreganized.content.item;

import com.google.common.base.Suppliers;
import galena.oreganized.Oreganized;
import galena.oreganized.index.OArmorMaterials;
import galena.oreganized.index.OAttributes;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class SilverArmorItem extends ArmorItem {
    private static final ResourceLocation TEXTURE = Oreganized.modLoc("textures/models/armor/silver.png");
    private final Supplier<ItemAttributeModifiers> modifiers;

    public SilverArmorItem(Type slot) {
        super(OArmorMaterials.SILVER, slot, new Properties().durability(slot.getDurability(14)));

        modifiers = Suppliers.memoize(() -> {
            var builder = ItemAttributeModifiers.builder();
            var material = OArmorMaterials.SILVER.value();
            var slotGroup = EquipmentSlotGroup.bySlot(slot.getSlot());
            var id = ResourceLocation.withDefaultNamespace("armor." + slot.getName());
            builder.add(Attributes.ARMOR, new AttributeModifier(id, material.getDefense(slot), Operation.ADD_VALUE), slotGroup);
            builder.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(id, material.toughness(), Operation.ADD_VALUE), slotGroup);
            builder.add(OAttributes.INVINCIBILITY_FRAMES, new AttributeModifier(id, 0.2, Operation.ADD_VALUE), slotGroup);
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
        return TEXTURE;
    }

}
