package galena.oreganized.device.index;

import galena.oreganized.OConstants;
import galena.oreganized.device.world.item.DeviceItem;
import galena.oreganized.register.ItemRegistryHelper;
import net.minecraft.world.item.*;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredItem;

@Mod(OConstants.MOD_ID)
public class DeviceItems {

    private static final ItemRegistryHelper ITEMS = OConstants.REGISTRY_HELPER.getItemSubHelper();

    public static final DeferredItem<Item> NETHERITE_NUGGET = ITEMS.createItem("netherite_nugget",
            () -> new Item(new Item.Properties().fireResistant()));

    public static final DeferredItem<Item> UNKNOWN_DEVICE = ITEMS.createItem("unknown_device",
            () -> new DeviceItem(new Item.Properties().rarity(Rarity.UNCOMMON).fireResistant()));


}
