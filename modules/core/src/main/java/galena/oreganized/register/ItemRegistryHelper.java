package galena.oreganized.register;

import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import galena.oreganized.ModCompat;
import galena.oreganized.compat.FarmersDelightCompat;
import galena.oreganized.world.item.ModdedSmithingTemplateItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.item.Tier;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredItem;

public class ItemRegistryHelper extends ItemSubRegistryHelper {

    public ItemRegistryHelper(RegistryHelper parent) {
        super(parent);
    }

    public static Item createKnife(Tier tier, Item.Properties properties) {
        if (ModList.get().isLoaded(ModCompat.FARMERS_DELIGHT_ID)) {
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

}
