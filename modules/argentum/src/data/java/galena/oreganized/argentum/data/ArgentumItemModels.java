package galena.oreganized.argentum.data;

import static galena.oreganized.data.extensions.OItemModelExtensions.*;

import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import galena.oreganized.OConstants;
import galena.oreganized.argentum.index.ArgentumDataComponents;
import galena.oreganized.argentum.index.ArgentumItems;
import galena.oreganized.argentum.world.item.SilverMirrorItem;
import galena.oreganized.data.ODatagen;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class ArgentumItemModels {

    public ArgentumItemModels() {
        ODatagen.addItemModelProvider(this::generate);
    }

    private void generate(RegistrateItemModelProvider provider) {
        normalItem(provider, ArgentumItems.RAW_SILVER);
        normalItem(provider, ArgentumItems.SILVER_INGOT);
        normalItem(provider, ArgentumItems.SILVER_NUGGET);

        toolItem(provider, ArgentumItems.SCRIBE);

        ArgentumItems.silverArmor().forEach(it -> trimmableArmorItem(provider, it));
        ArgentumItems.silverTools().forEach(it -> toolItem(provider, it));

        leveledDevice(provider, ArgentumItems.SILVER_MIRROR, SilverMirrorItem.FRAMES, ArgentumDataComponents.MIRROR_LEVEL.getId());
    }

}
