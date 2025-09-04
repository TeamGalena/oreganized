package galena.oreganized.carcinogenius.index;

import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import galena.oreganized.carcinogenius.OreganizedCarcinogenius;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public class OCItems {
    public static final ItemSubRegistryHelper HELPER = OreganizedCarcinogenius.REGISTRY_HELPER.getItemSubHelper();

    public static final DeferredItem<Item> RAW_ASBESTOS = HELPER.createItem("raw_asbestos", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> REFINED_ASBESTOS = HELPER.createItem("refined_asbestos", () -> new Item(new Item.Properties()));

    public static void register() {
        // Load this class
    }

}