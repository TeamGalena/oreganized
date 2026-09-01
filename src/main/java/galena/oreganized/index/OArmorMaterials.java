package galena.oreganized.index;

import galena.oreganized.Oreganized;
import java.util.EnumMap;
import java.util.List;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class OArmorMaterials {

    private static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, Oreganized.MOD_ID);

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> ELECTRUM = ARMOR_MATERIALS.register("electrum", () ->
            new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.BOOTS, 3);
                        map.put(ArmorItem.Type.LEGGINGS, 6);
                        map.put(ArmorItem.Type.CHESTPLATE, 8);
                        map.put(ArmorItem.Type.HELMET, 3);
                    }),
                    20,
                    SoundEvents.ARMOR_EQUIP_CHAIN,
                    () -> Ingredient.of(OTags.Items.INGOTS_ELECTRUM),
                    List.of(new ArmorMaterial.Layer(Oreganized.modLoc("electrum"))),
                    2.0F,
                    0.0F
            )
    );

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SILVER = ARMOR_MATERIALS.register("silver", () ->
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
                    List.of(new ArmorMaterial.Layer(Oreganized.modLoc("silver"))),
                    0.0F,
                    0.0F
            )
    );

    public static void register(IEventBus modBus) {
        ARMOR_MATERIALS.register(modBus);
    }

}
