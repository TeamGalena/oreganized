package galena.oreganized.plumbum.data;

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
        WaxedBlocks.WAXED_CONCRETE_POWDER.values().forEach(it -> provider.addBlock(it::get));
    }

}
