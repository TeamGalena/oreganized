package galena.oreganized.index;

import static galena.oreganized.ModCompat.FARMERS_DELIGHT_ID;

import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import galena.oreganized.Oreganized;
import galena.oreganized.compat.farmers_delight.FarmersDelightCompat;
import galena.oreganized.content.item.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
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
    public static final DeferredItem<Item> RAW_SILVER = HELPER.createItem("raw_silver",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SILVER_INGOT = HELPER.createItem("silver_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SILVER_NUGGET = HELPER.createItem("silver_nugget",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RAW_LEAD = HELPER.createItem("raw_lead",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LEAD_INGOT = HELPER.createItem("lead_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LEAD_NUGGET = HELPER.createItem("lead_nugget",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ELECTRUM_INGOT = HELPER.createItem("electrum_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NETHERITE_NUGGET = HELPER.createItem("netherite_nugget",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> ELECTRUM_NUGGET = HELPER.createItem("electrum_nugget",
            () -> new Item(new Item.Properties()));

    // Tools
    public static final DeferredItem<Item> BUSH_HAMMER = HELPER.createItem("bush_hammer",
            () -> new BushHammerItem(OItemTiers.LEAD, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> SCRIBE = HELPER.createItem("scribe",
            () -> new ScribeItem(new Item.Properties().durability(250)));

    public static final DeferredItem<Item> ELECTRUM_SWORD = HELPER.createItem("electrum_sword",
            () -> new SwordItem(OItemTiers.ELECTRUM, new Item.Properties().attributes(DiggerItem.createAttributes(OItemTiers.ELECTRUM, 3, -2.4F))));
    public static final DeferredItem<Item> ELECTRUM_SHOVEL = HELPER.createItem("electrum_shovel",
            () -> new ShovelItem(OItemTiers.ELECTRUM, new Item.Properties().attributes(DiggerItem.createAttributes(OItemTiers.ELECTRUM, 1.5F, -3.0F))));
    public static final DeferredItem<Item> ELECTRUM_PICKAXE = HELPER.createItem("electrum_pickaxe",
            () -> new PickaxeItem(OItemTiers.ELECTRUM, new Item.Properties().attributes(DiggerItem.createAttributes(OItemTiers.ELECTRUM, 1.0F, -2.8F))));
    public static final DeferredItem<Item> ELECTRUM_AXE = HELPER.createItem("electrum_axe",
            () -> new AxeItem(OItemTiers.ELECTRUM, new Item.Properties().attributes(DiggerItem.createAttributes(OItemTiers.ELECTRUM, 6.0F, -3.1F))));
    public static final DeferredItem<Item> ELECTRUM_HOE = HELPER.createItem("electrum_hoe",
            () -> new HoeItem(OItemTiers.ELECTRUM, new Item.Properties().attributes(DiggerItem.createAttributes(OItemTiers.ELECTRUM, -2.0F, -1.0F))));

    @SuppressWarnings({"Convert2MethodRef", "FunctionalExpressionCanBeFolded"})
    public static final DeferredItem<Item> ELECTRUM_KNIFE = HELPER.createItem("electrum_knife",
            compat(FARMERS_DELIGHT_ID, it -> FarmersDelightCompat.KNIFE_FACTORY.apply(it), new Item.Properties().attributes(DiggerItem.createAttributes(OItemTiers.ELECTRUM, 0.5F, -2.0F))));
    public static final DeferredItem<Item> ELECTRUM_SHIELD = HELPER.createItem("electrum_shield",
            () -> new ShieldItem(new Item.Properties().durability(363)));
    public static final DeferredItem<Item> ELECTRUM_MACHETE = HELPER.createItem("electrum_machete",
            () -> new SwordItem(OItemTiers.ELECTRUM, new Item.Properties()));


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

    // Armor
    public static final DeferredItem<Item> ELECTRUM_UPGRADE_SMITHING_TEMPLATE = HELPER.createItem("electrum_upgrade_smithing_template",
            OSmithingTemplateItem::createElectrumUpgradeTemplate);
    public static final DeferredItem<ArmorItem> ELECTRUM_HELMET = HELPER.createItem("electrum_helmet",
            () -> new ElectrumArmorItem(ArmorItem.Type.HELMET));
    public static final DeferredItem<ArmorItem> ELECTRUM_CHESTPLATE = HELPER.createItem("electrum_chestplate",
            () -> new ElectrumArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final DeferredItem<ArmorItem> ELECTRUM_LEGGINGS = HELPER.createItem("electrum_leggings",
            () -> new ElectrumArmorItem(ArmorItem.Type.LEGGINGS));
    public static final DeferredItem<ArmorItem> ELECTRUM_BOOTS = HELPER.createItem("electrum_boots",
            () -> new ElectrumArmorItem(ArmorItem.Type.BOOTS));

    public static Stream<DeferredItem<ArmorItem>> electrumArmor() {
        return Stream.of(ELECTRUM_BOOTS, ELECTRUM_LEGGINGS, ELECTRUM_CHESTPLATE, ELECTRUM_HELMET);
    }

    public static final DeferredItem<ArmorItem> SILVER_HELMET = HELPER.createItem("silver_helmet",
            () -> new SilverArmorItem(ArmorItem.Type.HELMET));
    public static final DeferredItem<ArmorItem> SILVER_CHESTPLATE = HELPER.createItem("silver_chestplate",
            () -> new SilverArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final DeferredItem<ArmorItem> SILVER_LEGGINGS = HELPER.createItem("silver_leggings",
            () -> new SilverArmorItem(ArmorItem.Type.LEGGINGS));
    public static final DeferredItem<ArmorItem> SILVER_BOOTS = HELPER.createItem("silver_boots",
            () -> new SilverArmorItem(ArmorItem.Type.BOOTS));

    public static Stream<DeferredItem<ArmorItem>> silverArmor() {
        return Stream.of(SILVER_BOOTS, SILVER_LEGGINGS, SILVER_CHESTPLATE, SILVER_HELMET);
    }

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

}
