package galena.oreganized.device.data;

import static galena.oreganized.data.provider.OLangProvider.addSubtitle;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.provider.ODatagen;
import galena.oreganized.gothic.index.GothicBlocks;
import galena.oreganized.index.OTags;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class GothicLang {

    public GothicLang() {
        ODatagen.addLangProvider(this::generate);
    }

    private void generate(RegistrateLangProvider provider) {
        provider.addBlock(GothicBlocks.GARGOYLE::get);
        GothicBlocks.CRYSTAL_GLASS.values().forEach(it -> provider.addBlock(it::get));
        GothicBlocks.CRYSTAL_GLASS_PANES.values().forEach(it -> provider.addBlock(it::get));

        addSubtitle(provider, "block", "gargoyle.growl", "Gargoyle growls");

        provider.add(OTags.Items.CRYSTAL_GLASS, "Crystal Glass");
        provider.add(OTags.Items.CRYSTAL_GLASS_PANES, "Crystal Glass Panes");
        provider.add(OTags.Items.GARGOYLE_SNACK, "Gargoyle Snacks");

        provider.add(OTags.Blocks.CRYSTAL_GLASS, "Crystal Glass");
        provider.add(OTags.Blocks.CRYSTAL_GLASS_PANES, "Crystal Glass Panes");

        provider.add(OTags.Entities.SCARED_OF_GARGOYLE, "Scared of Gargoyles");
    }

}
