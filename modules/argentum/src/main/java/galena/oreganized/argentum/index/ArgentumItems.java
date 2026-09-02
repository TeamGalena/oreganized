package galena.oreganized.argentum.index;

import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import galena.oreganized.OConstants;
import galena.oreganized.index.OTags;
import galena.oreganized.register.SimpleSubRegistryHelper;
import galena.oreganized.world.item.ModdedArmorItem;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.registries.DeferredItem;

public class ArgentumItems {

    private static final ItemSubRegistryHelper ITEMS = OConstants.REGISTRY_HELPER.getItemSubHelper();
    private static final SimpleSubRegistryHelper<ArmorMaterial> ARMOR_MATERIALS = OConstants.REGISTRY_HELPER.getSubHelper(Registries.ARMOR_MATERIAL);

    public static final DeferredItem<Item> RAW_SILVER = ITEMS.createItem("raw_silver",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SILVER_INGOT = ITEMS.createItem("silver_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SILVER_NUGGET = ITEMS.createItem("silver_nugget",
            () -> new Item(new Item.Properties()));

    public static final Holder<ArmorMaterial> SILVER_MATERIAL = ARMOR_MATERIALS.create("silver", id ->
            new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.BOOTS, 2);
                        map.put(ArmorItem.Type.LEGGINGS, 5);
                        map.put(ArmorItem.Type.CHESTPLATE, 6);
                        map.put(ArmorItem.Type.HELMET, 2);
                    }),
                    10,
                    SoundEvents.ARMOR_EQUIP_GOLD,
                    () -> Ingredient.of(OTags.Items.INGOTS_SILVER),
                    List.of(new ArmorMaterial.Layer(id)),
                    0.0F,
                    0.0F
            )
    );

    private static void silverArmorAttributes(ModdedArmorItem.ArmorAttributeBuilder builder) {
        builder.add(ArgentumAttributes.INVINCIBILITY_FRAMES, 0.2, AttributeModifier.Operation.ADD_VALUE);
    }

    public static final DeferredItem<ArmorItem> SILVER_HELMET = ITEMS.createItem("silver_helmet",
            () -> ModdedArmorItem.create(SILVER_MATERIAL, ArmorItem.Type.HELMET, 14, ArgentumItems::silverArmorAttributes));
    public static final DeferredItem<ArmorItem> SILVER_CHESTPLATE = ITEMS.createItem("silver_chestplate",
            () -> ModdedArmorItem.create(SILVER_MATERIAL, ArmorItem.Type.CHESTPLATE, 14, ArgentumItems::silverArmorAttributes));
    public static final DeferredItem<ArmorItem> SILVER_LEGGINGS = ITEMS.createItem("silver_leggings",
            () -> ModdedArmorItem.create(SILVER_MATERIAL, ArmorItem.Type.LEGGINGS, 14, ArgentumItems::silverArmorAttributes));
    public static final DeferredItem<ArmorItem> SILVER_BOOTS = ITEMS.createItem("silver_boots",
            () -> ModdedArmorItem.create(SILVER_MATERIAL, ArmorItem.Type.BOOTS, 14, ArgentumItems::silverArmorAttributes));

    public static final Tier SILVER_TIER = new SimpleTier(OTags.Blocks.INCORRECT_FOR_SILVER_TOOL, 191, 5F, 2.0F, 13, () -> Ingredient.of(OTags.Items.INGOTS_SILVER));

    public static final DeferredItem<Item> SILVER_SWORD = ITEMS.createItem("silver_sword",
            () -> new SwordItem(SILVER_TIER, new Item.Properties().attributes(SwordItem.createAttributes(SILVER_TIER, 3, -2.4F))));
    public static final DeferredItem<Item> SILVER_SHOVEL = ITEMS.createItem("silver_shovel",
            () -> new ShovelItem(SILVER_TIER, new Item.Properties().attributes(DiggerItem.createAttributes(SILVER_TIER, 1.5F, -3.0F))));
    public static final DeferredItem<Item> SILVER_PICKAXE = ITEMS.createItem("silver_pickaxe",
            () -> new PickaxeItem(SILVER_TIER, new Item.Properties().attributes(DiggerItem.createAttributes(SILVER_TIER, 1.0F, -2.8F))));
    public static final DeferredItem<Item> SILVER_AXE = ITEMS.createItem("silver_axe",
            () -> new AxeItem(SILVER_TIER, new Item.Properties().attributes(DiggerItem.createAttributes(SILVER_TIER, 6.0F, -3.1F))));
    public static final DeferredItem<Item> SILVER_HOE = ITEMS.createItem("silver_hoe",
            () -> new HoeItem(SILVER_TIER, new Item.Properties().attributes(DiggerItem.createAttributes(SILVER_TIER, -2.0F, -1.0F))));

    public static Stream<DeferredItem<ArmorItem>> silverArmor() {
        return Stream.of(SILVER_BOOTS, SILVER_LEGGINGS, SILVER_CHESTPLATE, SILVER_HELMET);
    }

    public static Stream<DeferredItem<? extends Item>> silverTools() {
        return Stream.of(SILVER_SWORD, SILVER_SHOVEL, SILVER_PICKAXE, SILVER_AXE, SILVER_HOE);
    }

    public static void register() {
        // Load this class
    }

}
