package galena.oreganized.carcinogenius.index;

import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import galena.oreganized.carcinogenius.OreganizedCarcinogenius;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = OreganizedCarcinogenius.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OCItems {
    public static final ItemSubRegistryHelper HELPER = OreganizedCarcinogenius.REGISTRY_HELPER.getItemSubHelper();

    public static final RegistryObject<Item> RAW_ASBESTOS = HELPER.createItem("raw_asbestos", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> REFINED_ASBESTOS = HELPER.createItem("refined_asbestos", () -> new Item(new Item.Properties()));
}