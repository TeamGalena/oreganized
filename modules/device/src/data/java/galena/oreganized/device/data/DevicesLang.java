package galena.oreganized.device.data;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.device.index.DeviceItems;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class DevicesLang {

    public DevicesLang() {
        ODatagen.addLangProvider(this::generate);
    }

    private void generate(RegistrateLangProvider provider) {
        provider.addItem(DeviceItems.UNKNOWN_DEVICE::get);
        provider.addItem(DeviceItems.NETHERITE_NUGGET::get);
    }

}
