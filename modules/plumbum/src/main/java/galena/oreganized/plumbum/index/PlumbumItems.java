package galena.oreganized.plumbum.index;

import galena.oreganized.OConstants;
import galena.oreganized.plumbum.world.item.ThermometerItem;
import galena.oreganized.register.ItemRegistryHelper;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;

public class PlumbumItems {

    private static final ItemRegistryHelper ITEMS = OConstants.REGISTRY_HELPER.getItemSubHelper();

    public static final DeferredItem<Item> RAW_LEAD = ITEMS.createItem("raw_lead",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> LEAD_INGOT = ITEMS.createItem("lead_ingot",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> LEAD_NUGGET = ITEMS.createItem("lead_nugget",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> THERMOMETER = ITEMS.createItem("thermometer",
            () -> new ThermometerItem(new Item.Properties().component(PlumbumDataComponents.LOCKED, false).component(PlumbumDataComponents.HEAT_LEVEL, 2)));

    public static final DeferredItem<Item> MOLTEN_LEAD_BUCKET = ITEMS.createItem("molten_lead_bucket",
            () -> new BucketItem(PlumbumFluids.MOLTEN_LEAD.get(), new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));

    public static final DeferredItem<Item> MUSIC_DISC_STRUCTURE = ITEMS.createItem("music_disc_structure",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(PlumbumSongs.STRUCTURE)));

    public static void register() {
        // Load this class
    }

}
