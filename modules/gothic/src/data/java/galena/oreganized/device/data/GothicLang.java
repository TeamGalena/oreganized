package galena.oreganized.device.data;

import static galena.oreganized.data.provider.OLangProvider.addSubtitle;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.gothic.index.GothicBlocks;
import galena.oreganized.gothic.index.GothicTags;
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

        provider.add(GothicTags.Items.CRYSTAL_GLASS, "Crystal Glass");
        provider.add(GothicTags.Items.CRYSTAL_GLASS_PANES, "Crystal Glass Panes");
        provider.add(GothicTags.Items.GARGOYLE_SNACK, "Gargoyle Snacks");

        provider.add(GothicTags.Blocks.CRYSTAL_GLASS, "Crystal Glass");
        provider.add(GothicTags.Blocks.CRYSTAL_GLASS_PANES, "Crystal Glass Panes");

        provider.add(GothicTags.Entities.SCARED_OF_GARGOYLE, "Scared of Gargoyles");
    }

}
