package galena.oreganized.electrum.index;

import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import galena.oreganized.OConstants;
import galena.oreganized.index.OTags;
import galena.oreganized.register.SimpleSubRegistryHelper;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.EnumMap;
import java.util.List;
import java.util.stream.Stream;

import static galena.oreganized.ModCompat.FARMERS_DELIGHT_ID;

public class ElectrumItems {

    private static final ItemSubRegistryHelper ITEMS = OConstants.REGISTRY_HELPER.getItemSubHelper();
    private static final SimpleSubRegistryHelper<ArmorMaterial> ARMOR_MATERIALS = OConstants.REGISTRY_HELPER.getSubHelper(Registries.ARMOR_MATERIAL);

    public static final DeferredItem<Item> ELECTRUM_INGOT = ITEMS.createItem("electrum_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ELECTRUM_NUGGET = ITEMS.createItem("electrum_nugget",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ELECTRUM_UPGRADE_SMITHING_TEMPLATE = ITEMS.createItem("electrum_upgrade_smithing_template",
            OSmithingTemplateItem::createElectrumUpgradeTemplate);

    public static final Holder<ArmorMaterial> ELECTRUM_MATERIAL = ARMOR_MATERIALS.create("electrum", id -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.HELMET, 3);
            }),
            20,
            SoundEvents.ARMOR_EQUIP_CHAIN,
            () -> Ingredient.of(OTags.Items.INGOTS_ELECTRUM),
            List.of(new ArmorMaterial.Layer(id)),
            2.0F,
            0.0F
    ));

    public static final DeferredItem<ArmorItem> ELECTRUM_HELMET = ITEMS.createItem("electrum_helmet",
            () -> new ElectrumArmorItem(ArmorItem.Type.HELMET));
    public static final DeferredItem<ArmorItem> ELECTRUM_CHESTPLATE = ITEMS.createItem("electrum_chestplate",
            () -> new ElectrumArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final DeferredItem<ArmorItem> ELECTRUM_LEGGINGS = ITEMS.createItem("electrum_leggings",
            () -> new ElectrumArmorItem(ArmorItem.Type.LEGGINGS));
    public static final DeferredItem<ArmorItem> ELECTRUM_BOOTS = ITEMS.createItem("electrum_boots",
            () -> new ElectrumArmorItem(ArmorItem.Type.BOOTS));

    // TODO modules deprecate too and move to data?
    public static Stream<DeferredItem<ArmorItem>> electrumArmor() {
        return Stream.of(ELECTRUM_BOOTS, ELECTRUM_LEGGINGS, ELECTRUM_CHESTPLATE, ELECTRUM_HELMET);
    }

    public static final Tier ELECTRUM_TIER = new SimpleTier(OTags.Blocks.INCORRECT_FOR_ELECTRUM_TOOL, 1561, 8F, 3.0F, 14, () -> Ingredient.of(OTags.Items.INGOTS_ELECTRUM));

    public static final DeferredItem<Item> ELECTRUM_SWORD = ITEMS.createItem("electrum_sword",
            () -> new SwordItem(ELECTRUM_TIER, new Item.Properties().attributes(SwordItem.createAttributes(ELECTRUM_TIER, 3, -2.4F))));
    public static final DeferredItem<Item> ELECTRUM_SHOVEL = ITEMS.createItem("electrum_shovel",
            () -> new ShovelItem(ELECTRUM_TIER, new Item.Properties().attributes(DiggerItem.createAttributes(ELECTRUM_TIER, 1.5F, -3.0F))));
    public static final DeferredItem<Item> ELECTRUM_PICKAXE = ITEMS.createItem("electrum_pickaxe",
            () -> new PickaxeItem(ELECTRUM_TIER, new Item.Properties().attributes(DiggerItem.createAttributes(ELECTRUM_TIER, 1.0F, -2.8F))));
    public static final DeferredItem<Item> ELECTRUM_AXE = ITEMS.createItem("electrum_axe",
            () -> new AxeItem(ELECTRUM_TIER, new Item.Properties().attributes(DiggerItem.createAttributes(ELECTRUM_TIER, 6.0F, -3.1F))));
    public static final DeferredItem<Item> ELECTRUM_HOE = ITEMS.createItem("electrum_hoe",
            () -> new HoeItem(ELECTRUM_TIER, new Item.Properties().attributes(DiggerItem.createAttributes(ELECTRUM_TIER, -2.0F, -1.0F))));

    @SuppressWarnings({"Convert2MethodRef", "FunctionalExpressionCanBeFolded"})
    public static final DeferredItem<Item> ELECTRUM_KNIFE = ITEMS.createItem("electrum_knife",
            compat(FARMERS_DELIGHT_ID, it -> FarmersDelightCompat.KNIFE_FACTORY.apply(it), new Item.Properties().attributes(DiggerItem.createAttributes(ELECTRUM_TIER, 0.5F, -2.0F))));
    public static final DeferredItem<Item> ELECTRUM_SHIELD = ITEMS.createItem("electrum_shield",
            () -> new ShieldItem(new Item.Properties().durability(363)));
    public static final DeferredItem<Item> ELECTRUM_MACHETE = ITEMS.createItem("electrum_machete",
            () -> new SwordItem(ELECTRUM_TIER, new Item.Properties()));

    public static Stream<DeferredItem<? extends Item>> electrumTools() {
        return Stream.of(ELECTRUM_SWORD, ELECTRUM_SHOVEL, ELECTRUM_PICKAXE, ELECTRUM_AXE, ELECTRUM_HOE);
    }

    public static void register() {
        // Load this class
    }

}
