package galena.oreganized.world.item;

import com.google.common.base.Suppliers;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class ModdedArmorItem extends ArmorItem {
    private final ResourceLocation texture;
    private final Supplier<ItemAttributeModifiers> modifiers;

    public static ModdedArmorItem create(Holder<ArmorMaterial> material, Type slot, int durabilityFactor, Consumer<ArmorAttributeBuilder> attributes) {
        return new ModdedArmorItem(material, slot, new Properties().durability(slot.getDurability(durabilityFactor)), attributes);
    }

    public ModdedArmorItem(Holder<ArmorMaterial> material, Type slot, Properties properties, Consumer<ArmorAttributeBuilder> attributes) {
        super(material, slot, properties);

        var index = slot == Type.LEGGINGS ? 2 : 1;
        this.texture = material.getKey().location().withPath(it -> "textures/models/armor/%s_layer_%s.png".formatted(it, index));

        modifiers = Suppliers.memoize(() -> {
            var builder = ItemAttributeModifiers.builder();
            var slotGroup = EquipmentSlotGroup.bySlot(slot.getSlot());
            var id = ResourceLocation.withDefaultNamespace("armor." + slot.getName());
            builder.add(Attributes.ARMOR, new AttributeModifier(id, material.value().getDefense(slot), Operation.ADD_VALUE), slotGroup);
            builder.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(id, material.value().toughness(), Operation.ADD_VALUE), slotGroup);
            attributes.accept((attribute, amount, operation) -> {
                builder.add(attribute, new AttributeModifier(id, amount, operation), slotGroup);
            });
            return builder.build();
        });
    }

    @Override
    public final ItemAttributeModifiers getDefaultAttributeModifiers() {
        return modifiers.get();
    }


    // @Nullable
    // @Override
    // public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
    //     return texture;
    // }

    @FunctionalInterface
    public interface ArmorAttributeBuilder {
        void add(Holder<Attribute> attribute, double amount, AttributeModifier.Operation operation);
    }

}
