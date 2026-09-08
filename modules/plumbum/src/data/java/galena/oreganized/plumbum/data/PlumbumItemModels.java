package galena.oreganized.plumbum.data;

import static galena.oreganized.data.extensions.OItemModelExtensions.*;

import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.plumbum.index.PlumbumDataComponents;
import galena.oreganized.plumbum.index.PlumbumItems;
import galena.oreganized.plumbum.world.item.ThermometerItem;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class PlumbumItemModels {

    public PlumbumItemModels() {
        ODatagen.addItemModelProvider(this::generate);
    }

    private void generate(RegistrateItemModelProvider provider) {
        normalItem(provider, PlumbumItems.RAW_LEAD);
        normalItem(provider, PlumbumItems.LEAD_INGOT);
        normalItem(provider, PlumbumItems.LEAD_NUGGET);

        normalItem(provider, PlumbumItems.MOLTEN_LEAD_BUCKET);

        normalItem(provider, PlumbumItems.MUSIC_DISC_STRUCTURE);

        leveledDevice(provider, PlumbumItems.THERMOMETER, ThermometerItem.HEAT_LEVELS, PlumbumDataComponents.HEAT_LEVEL.getId());
    }

}
