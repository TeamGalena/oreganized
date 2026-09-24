package galena.oreganized.plumbum.data;

import static galena.oreganized.data.extensions.OLangExtensions.autoTranslate;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.waxed.index.WaxedBlocks;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class WaxedLang {

    public WaxedLang() {
        ODatagen.addLangProvider(this::generate);
    }

    private void generate(RegistrateLangProvider provider) {
        autoTranslate(provider, WaxedBlocks.WAXED_CONCRETE_POWDER.values().stream());
    }

}
