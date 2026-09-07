package galena.oreganized.device.data;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.engraved.index.EngravedItems;
import galena.oreganized.index.OTags;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class EngravedLang {

    public EngravedLang() {
        ODatagen.addLangProvider(this::generate);
    }

    private void generate(RegistrateLangProvider provider) {
        provider.addItem(EngravedItems.BUSH_HAMMER::get);

        provider.add(OTags.Items.TOOLS_BUSH_HAMMER, "Bush Hammers");

        provider.add(OTags.Blocks.INCORRECT_FOR_LEAD_TOOL, "Incorrect for Lead Tools");
        provider.add(OTags.Blocks.MINEABLE_WITH_BUSH_HAMMER, "Mineable with Bush Hammer");
    }

}
