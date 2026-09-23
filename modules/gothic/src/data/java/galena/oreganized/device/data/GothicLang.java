package galena.oreganized.device.data;

import static galena.oreganized.data.extensions.OLangExtensions.addSubtitle;

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
        provider.addBlock(GothicBlocks.GARGOYLE::value);
        GothicBlocks.CRYSTAL_GLASS.values().forEach(it -> provider.addBlock(it::value));
        GothicBlocks.CRYSTAL_GLASS_PANES.values().forEach(it -> provider.addBlock(it::value));

        provider.addBlock(GothicBlocks.DARK_GRIMSTONE_BRICKS::value);
        provider.addBlock(GothicBlocks.PALE_GRIMSTONE_BRICKS::value);
        provider.addBlock(GothicBlocks.POLISHED_DARK_GRIMSTONE::value);
        provider.addBlock(GothicBlocks.POLISHED_PALE_GRIMSTONE::value);
        provider.addBlock(GothicBlocks.DARK_GRIMSTONE_PILLAR::value);
        provider.addBlock(GothicBlocks.PALE_GRIMSTONE_PILLAR::value);
        provider.addBlock(GothicBlocks.CHISELED_DARK_GRIMSTONE::value);
        provider.addBlock(GothicBlocks.CHISELED_PALE_GRIMSTONE::value);
        provider.addBlock(GothicBlocks.DARK_GRIMSTONE_SLAB::value);
        provider.addBlock(GothicBlocks.PALE_GRIMSTONE_SLAB::value);
        provider.addBlock(GothicBlocks.PALE_GRIMSTONE_SPYRE::value);
        provider.addBlock(GothicBlocks.DARK_GRIMSTONE_SPYRE::value);
        provider.addBlock(GothicBlocks.PALE_GRIMSTONE_SPYRE_BLOCK::value);
        provider.addBlock(GothicBlocks.DARK_GRIMSTONE_SPYRE_BLOCK::value);
        provider.addBlock(GothicBlocks.DARK_GRIMSTONE_SPYRE_FENCE::value);
        provider.addBlock(GothicBlocks.PALE_GRIMSTONE_SPYRE_FENCE::value);

        addSubtitle(provider, "block", "gargoyle.growl", "Gargoyle growls");

        provider.add(GothicTags.Items.CRYSTAL_GLASS, "Crystal Glass");
        provider.add(GothicTags.Items.CRYSTAL_GLASS_PANES, "Crystal Glass Panes");
        provider.add(GothicTags.Items.GARGOYLE_SNACK, "Gargoyle Snacks");

        provider.add(GothicTags.Blocks.CRYSTAL_GLASS, "Crystal Glass");
        provider.add(GothicTags.Blocks.CRYSTAL_GLASS_PANES, "Crystal Glass Panes");

        provider.add(GothicTags.Entities.SCARED_OF_GARGOYLE, "Scared of Gargoyles");
    }

}
