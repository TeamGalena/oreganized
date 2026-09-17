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
    private final Supplier<ItemAttributeModifiers> modifiers;

    public ModdedArmorItem(Holder<ArmorMaterial> material, Type slot, Properties properties, Consumer<ArmorAttributeBuilder> attributes) {
        super(material, slot, properties);

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

    @FunctionalInterface
    public interface ArmorAttributeBuilder {
        void add(Holder<Attribute> attribute, double amount, AttributeModifier.Operation operation);
    }

}
