package galena.oreganized.index;


import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import galena.oreganized.Oreganized;
import galena.oreganized.argentum.index.ArgentumItems;
import galena.oreganized.content.item.*;
import galena.oreganized.electrum.index.ElectrumItems;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredItem;

public class OItems {
    static final ItemSubRegistryHelper HELPER = Oreganized.REGISTRY_HELPER.getItemSubHelper();

    public static Supplier<? extends Item> compat(String modid, Function<Item.Properties, ? extends Item> supplier, Item.Properties properties) {
        if (ModList.get().isLoaded(modid)) return () -> supplier.apply(properties);
        return () -> new Item(properties);
    }

    // Discs
    public static final DeferredItem<Item> MUSIC_DISC_STRUCTURE = HELPER.createItem("music_disc_structure",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(ORecords.STRUCTURE)));

    // Crafting Materials

    public static final DeferredItem<Item> RAW_LEAD = HELPER.createItem("raw_lead",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LEAD_INGOT = HELPER.createItem("lead_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LEAD_NUGGET = HELPER.createItem("lead_nugget",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NETHERITE_NUGGET = HELPER.createItem("netherite_nugget",
            () -> new Item(new Item.Properties().fireResistant()));

    // Tools
    public static final DeferredItem<Item> BUSH_HAMMER = HELPER.createItem("bush_hammer",
            () -> new BushHammerItem(OItemTiers.LEAD, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> SCRIBE = HELPER.createItem("scribe",
            () -> new ScribeItem(new Item.Properties().durability(250)));

    // Misc Tools
    public static final DeferredItem<Item> THERMOMETER = HELPER.createItem("thermometer",
            () -> new ThermometerItem(new Item.Properties().component(ODataComponents.LOCKED, false).component(ODataComponents.HEAT_LEVEL, 2)));
    public static final DeferredItem<Item> SPEEDOMETER = HELPER.createItem("speedometer",
            () -> new SpeedometerItem(new Item.Properties()));
    public static final DeferredItem<Item> UNKNOWN_DEVICE = HELPER.createItem("unknown_device",
            () -> new DeviceItem(new Item.Properties().rarity(Rarity.UNCOMMON).fireResistant()));
    public static final DeferredItem<Item> SILVER_MIRROR = HELPER.createItem("silver_mirror",
            () -> new SilverMirrorItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> MOLTEN_LEAD_BUCKET = HELPER.createItem("molten_lead_bucket",
            () -> new BucketItem(OFluids.MOLTEN_LEAD.get(), new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));

    public static final DeferredItem<Item> LEAD_BOLT = HELPER.createItem("lead_bolt",
            () -> new LeadBoltItem(new Item.Properties()));

    public static final DeferredItem<Item> FLINT_AND_PEWTER = HELPER.createItem("flint_and_pewter",
            () -> new FlintAndPewterItem(new Item.Properties().durability(64)));

    // Transportation
    public static final DeferredItem<Item> SHRAPNEL_BOMB_MINECART = HELPER.createItem("shrapnel_bomb_minecart",
            () -> new MinecartShrapnelBombItem(AbstractMinecart.Type.TNT, OEntityTypes.SHRAPNEL_BOMB_MINECART));

    public static void register() {
        // Load this class
    }

    // deprecated

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> RAW_SILVER = ArgentumItems.RAW_SILVER;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> SILVER_INGOT = ArgentumItems.SILVER_INGOT;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> SILVER_NUGGET = ArgentumItems.SILVER_NUGGET;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<ArmorItem> SILVER_HELMET = ArgentumItems.SILVER_HELMET;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<ArmorItem> SILVER_CHESTPLATE = ArgentumItems.SILVER_CHESTPLATE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<ArmorItem> SILVER_LEGGINGS = ArgentumItems.SILVER_LEGGINGS;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<ArmorItem> SILVER_BOOTS = ArgentumItems.SILVER_BOOTS;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> SILVER_SWORD = ArgentumItems.SILVER_SWORD;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> SILVER_SHOVEL = ArgentumItems.SILVER_SHOVEL;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> SILVER_PICKAXE = ArgentumItems.SILVER_PICKAXE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> SILVER_AXE = ArgentumItems.SILVER_AXE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> SILVER_HOE = ArgentumItems.SILVER_HOE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static Stream<DeferredItem<ArmorItem>> silverArmor() {
        return ArgentumItems.silverArmor();
    }

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static Stream<DeferredItem<? extends Item>> silverTools() {
        return ArgentumItems.silverTools();
    }

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> ELECTRUM_INGOT = ElectrumItems.ELECTRUM_INGOT;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> ELECTRUM_NUGGET = ElectrumItems.ELECTRUM_NUGGET;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<SmithingTemplateItem> ELECTRUM_UPGRADE_SMITHING_TEMPLATE = ElectrumItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<ArmorItem> ELECTRUM_HELMET = ElectrumItems.ELECTRUM_HELMET;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<ArmorItem> ELECTRUM_CHESTPLATE = ElectrumItems.ELECTRUM_CHESTPLATE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<ArmorItem> ELECTRUM_LEGGINGS = ElectrumItems.ELECTRUM_LEGGINGS;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<ArmorItem> ELECTRUM_BOOTS = ElectrumItems.ELECTRUM_BOOTS;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static Stream<DeferredItem<ArmorItem>> electrumArmor() {
        return ElectrumItems.electrumArmor();
    }

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> ELECTRUM_SWORD = ElectrumItems.ELECTRUM_SWORD;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> ELECTRUM_SHOVEL = ElectrumItems.ELECTRUM_SHOVEL;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> ELECTRUM_PICKAXE = ElectrumItems.ELECTRUM_PICKAXE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> ELECTRUM_AXE = ElectrumItems.ELECTRUM_AXE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> ELECTRUM_HOE = ElectrumItems.ELECTRUM_HOE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> ELECTRUM_KNIFE = ElectrumItems.ELECTRUM_KNIFE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> ELECTRUM_SHIELD = ElectrumItems.ELECTRUM_SHIELD;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> ELECTRUM_MACHETE = ElectrumItems.ELECTRUM_MACHETE;

    public static Stream<DeferredItem<? extends Item>> electrumTools() {
        return ElectrumItems.electrumTools();
    }

}
