package galena.oreganized.carcinogenius.index;

import static galena.oreganized.ModCompat.FARMERS_DELIGHT_ID;

import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import galena.oreganized.carcinogenius.OreganizedCarcinogenius;
import galena.oreganized.compat.farmers_delight.FarmersDelightCompat;
import galena.oreganized.content.item.BushHammerItem;
import galena.oreganized.content.item.ElectrumArmorItem;
import galena.oreganized.content.item.FlintAndPewterItem;
import galena.oreganized.content.item.LeadBoltItem;
import galena.oreganized.content.item.MinecartShrapnelBombItem;
import galena.oreganized.content.item.OSmithingTemplateItem;
import galena.oreganized.content.item.ScribeItem;
import galena.oreganized.content.item.SilverMirrorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = OreganizedCarcinogenius.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OItems {
    public static final ItemSubRegistryHelper HELPER = OreganizedCarcinogenius.REGISTRY_HELPER.getItemSubHelper();

    public static final RegistryObject<Item> RAW_ASBESTOS = HELPER.createItem("raw_asbestos", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> REFINED_ASBESTOS = HELPER.createItem("refined_asbestos", () -> new Item(new Item.Properties()));
}