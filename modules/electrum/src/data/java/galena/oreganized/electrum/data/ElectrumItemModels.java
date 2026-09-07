package galena.oreganized.electrum.data;

import static galena.oreganized.data.extensions.OItemModelExtensions.*;

import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.electrum.index.ElectrumItems;
import galena.oreganized.electrum.world.item.SpeedometerItem;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class ElectrumItemModels {

    public ElectrumItemModels() {
        ODatagen.addItemModelProvider(this::generate);
    }

    private void generate(RegistrateItemModelProvider provider) {
        normalItem(provider, ElectrumItems.ELECTRUM_INGOT);
        normalItem(provider, ElectrumItems.ELECTRUM_INGOT);
        normalItem(provider, ElectrumItems.ELECTRUM_NUGGET);

        normalItem(provider, ElectrumItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE);

        ElectrumItems.electrumArmor().forEach(it -> trimmableArmorItem(provider, it));
        ElectrumItems.electrumTools().forEach(it -> toolItem(provider, it));
        shieldItem(provider, ElectrumItems.ELECTRUM_SHIELD);

        leveledDevice(provider, ElectrumItems.SPEEDOMETER, 16, SpeedometerItem.PROPERTY_KEY);
    }

}
