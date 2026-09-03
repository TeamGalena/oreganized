package galena.oreganized.index;


import galena.oreganized.argentum.index.ArgentumItems;
import galena.oreganized.armament.index.ArmamentItems;
import galena.oreganized.device.index.DeviceItems;
import galena.oreganized.electrum.index.ElectrumItems;
import galena.oreganized.engraved.index.EngravedItems;
import galena.oreganized.plumbum.index.PlumbumItems;

import java.util.stream.Stream;

import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;

public class OItems {

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> RAW_LEAD = PlumbumItems.RAW_LEAD;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> LEAD_INGOT = PlumbumItems.LEAD_INGOT;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> LEAD_NUGGET = PlumbumItems.LEAD_NUGGET;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> THERMOMETER = PlumbumItems.THERMOMETER;

    public static final DeferredItem<Item> MUSIC_DISC_STRUCTURE = PlumbumItems.MUSIC_DISC_STRUCTURE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> MOLTEN_LEAD_BUCKET = PlumbumItems.MOLTEN_LEAD_BUCKET;

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
    public static final DeferredItem<Item> SCRIBE = ArgentumItems.SCRIBE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> SILVER_MIRROR = ArgentumItems.SILVER_MIRROR;

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

    public static final DeferredItem<Item> SPEEDOMETER = ElectrumItems.SPEEDOMETER;

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

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static Stream<DeferredItem<? extends Item>> electrumTools() {
        return ElectrumItems.electrumTools();
    }

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> LEAD_BOLT = ArmamentItems.LEAD_BOLT;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> FLINT_AND_PEWTER = ArmamentItems.FLINT_AND_PEWTER;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> SHRAPNEL_BOMB_MINECART = ArmamentItems.SHRAPNEL_BOMB_MINECART;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> BUSH_HAMMER = EngravedItems.BUSH_HAMMER;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> NETHERITE_NUGGET = DeviceItems.NETHERITE_NUGGET;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredItem<Item> UNKNOWN_DEVICE = DeviceItems.UNKNOWN_DEVICE;


}
