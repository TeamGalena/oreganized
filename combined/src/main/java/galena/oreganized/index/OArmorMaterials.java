package galena.oreganized.index;

import galena.oreganized.Oreganized;
import java.util.EnumMap;
import java.util.List;

import galena.oreganized.argentum.index.ArgentumItems;
import galena.oreganized.electrum.index.ElectrumItems;
import net.minecraft.Util;
import net.minecraft.core.Holder;
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

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final Holder<ArmorMaterial> SILVER = ArgentumItems.SILVER_MATERIAL;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final Holder<ArmorMaterial> ELECTRUM = ElectrumItems.ELECTRUM_MATERIAL;

    public static void register(IEventBus modBus) {
        ARMOR_MATERIALS.register(modBus);
    }

}
