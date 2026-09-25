package galena.oreganized.gothic.data;

import static galena.oreganized.data.extensions.OLangExtensions.autoTranslate;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.engraved.index.EngravedItems;
import galena.oreganized.engraved.index.EngravedTags;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class EngravedLang {

    public EngravedLang() {
        ODatagen.addLangProvider(this::generate);
    }

    private void generate(RegistrateLangProvider provider) {
        autoTranslate(provider, EngravedItems.BUSH_HAMMER);

        provider.add(EngravedTags.Items.TOOLS_BUSH_HAMMER, "Bush Hammers");
        provider.add(EngravedTags.Blocks.MINEABLE_WITH_BUSH_HAMMER, "Mineable with Bush Hammer");
    }

}
