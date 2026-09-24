package galena.oreganized.device.data;

import static galena.oreganized.data.extensions.OLangExtensions.autoTranslate;

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
        autoTranslate(provider, DeviceItems.UNKNOWN_DEVICE);
        autoTranslate(provider, DeviceItems.NETHERITE_NUGGET);
    }

}
