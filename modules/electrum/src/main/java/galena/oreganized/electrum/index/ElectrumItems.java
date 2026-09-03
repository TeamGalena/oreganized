package galena.oreganized.electrum.index;

import galena.oreganized.OConstants;
import galena.oreganized.electrum.config.ElectrumConfigs;
import galena.oreganized.index.OTags;
import galena.oreganized.register.ItemRegistryHelper;
import galena.oreganized.register.SimpleRegistryHelper;
import galena.oreganized.world.item.ModdedArmorItem;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.registries.DeferredItem;

@Mod(OConstants.MOD_ID)
public class ElectrumItems {

    private static final ItemRegistryHelper ITEMS = OConstants.REGISTRY_HELPER.getItemSubHelper();
    private static final SimpleRegistryHelper<ArmorMaterial> ARMOR_MATERIALS = OConstants.REGISTRY_HELPER.getArmorMaterialSubHelper();

    public static final DeferredItem<Item> ELECTRUM_INGOT = ITEMS.createItem("electrum_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ELECTRUM_NUGGET = ITEMS.createItem("electrum_nugget",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<SmithingTemplateItem> ELECTRUM_UPGRADE_SMITHING_TEMPLATE = ITEMS.createUpgradeTemplate("electrum_upgrade_smithing_template", "electrum");

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

    private static void electrumArmorAttributes(ModdedArmorItem.ArmorAttributeBuilder builder) {
        double speedBoost = ElectrumConfigs.COMMON.electrumSpeedBoost.get();
        if (speedBoost > 0) {
            builder.add(Attributes.MOVEMENT_SPEED, speedBoost, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        }
    }

    public static final DeferredItem<ArmorItem> ELECTRUM_HELMET = ITEMS.createItem("electrum_helmet",
            () -> ModdedArmorItem.create(ELECTRUM_MATERIAL, ArmorItem.Type.HELMET, 33, ElectrumItems::electrumArmorAttributes));
    public static final DeferredItem<ArmorItem> ELECTRUM_CHESTPLATE = ITEMS.createItem("electrum_chestplate",
            () -> ModdedArmorItem.create(ELECTRUM_MATERIAL, ArmorItem.Type.CHESTPLATE, 33, ElectrumItems::electrumArmorAttributes));
    public static final DeferredItem<ArmorItem> ELECTRUM_LEGGINGS = ITEMS.createItem("electrum_leggings",
            () -> ModdedArmorItem.create(ELECTRUM_MATERIAL, ArmorItem.Type.LEGGINGS, 33, ElectrumItems::electrumArmorAttributes));
    public static final DeferredItem<ArmorItem> ELECTRUM_BOOTS = ITEMS.createItem("electrum_boots",
            () -> ModdedArmorItem.create(ELECTRUM_MATERIAL, ArmorItem.Type.BOOTS, 33, ElectrumItems::electrumArmorAttributes));

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

    public static final DeferredItem<Item> ELECTRUM_KNIFE = ITEMS.createKnife("electrum_knife",
            ELECTRUM_TIER, 0.5F, -2.0F);
    public static final DeferredItem<Item> ELECTRUM_SHIELD = ITEMS.createItem("electrum_shield",
            () -> new ShieldItem(new Item.Properties().durability(363)));
    public static final DeferredItem<Item> ELECTRUM_MACHETE = ITEMS.createItem("electrum_machete",
            () -> new SwordItem(ELECTRUM_TIER, new Item.Properties()));

    public static Stream<DeferredItem<? extends Item>> electrumTools() {
        return Stream.of(ELECTRUM_SWORD, ELECTRUM_SHOVEL, ELECTRUM_PICKAXE, ELECTRUM_AXE, ELECTRUM_HOE);
    }


}
