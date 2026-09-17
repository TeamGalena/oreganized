package galena.oreganized.register;

import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import galena.oreganized.ModCompat;
import galena.oreganized.compat.FarmersDelightCompat;
import galena.oreganized.world.item.ModdedArmorItem;
import galena.oreganized.world.item.ModdedSmithingTemplateItem;
import java.util.function.Consumer;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;

public class ItemRegistryHelper extends ItemSubRegistryHelper {

    public ItemRegistryHelper(RegistryHelper parent) {
        super(parent);
    }

    public static Item createKnife(Tier tier, Item.Properties properties) {
        if (ModCompat.FARMERS_DELIGHT_LOADED) {
            return FarmersDelightCompat.KNIFE_FACTORY.apply(tier, properties);
        }

        return new Item(properties);
    }

    public DeferredItem<Item> createKnife(String name, Tier tier, float attackDamage, float attackSpeed) {
        var properties = new Item.Properties().attributes(DiggerItem.createAttributes(tier, attackDamage, attackSpeed));
        return createItem(name, () -> createKnife(tier, properties));
    }

    public DeferredItem<SmithingTemplateItem> createUpgradeTemplate(String name, String type) {
        return createItem(name, () -> ModdedSmithingTemplateItem.create(
                ResourceLocation.fromNamespaceAndPath(parent.getModId(), type)
        ));
    }

    public static ArmorItem createArmor(Holder<ArmorMaterial> material, ArmorItem.Type slot, int durabilityFactor, Consumer<ModdedArmorItem.ArmorAttributeBuilder> attributes) {
        return new ModdedArmorItem(material, slot, new Item.Properties().durability(slot.getDurability(durabilityFactor)), attributes);
    }

    public DeferredItem<ArmorItem> createArmor(String name, Holder<ArmorMaterial> material, ArmorItem.Type slot, int durabilityFactor, Consumer<ModdedArmorItem.ArmorAttributeBuilder> attributes) {
        return createItem(name, () -> createArmor(material, slot, durabilityFactor, attributes));
    }

}
