package galena.oreganized.gothic.data;

import static galena.oreganized.data.extensions.OLangExtensions.addSubtitle;
import static galena.oreganized.data.extensions.OLangExtensions.autoTranslate;

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
        autoTranslate(provider, GothicBlocks.GARGOYLE);
        autoTranslate(provider, GothicBlocks.CRYSTAL_GLASS.stream());
        autoTranslate(provider, GothicBlocks.CRYSTAL_GLASS_PANES.stream());

        autoTranslate(provider, GothicBlocks.DARK_GRIMSTONE_BRICKS);
        autoTranslate(provider, GothicBlocks.PALE_GRIMSTONE_BRICKS);
        autoTranslate(provider, GothicBlocks.POLISHED_DARK_GRIMSTONE);
        autoTranslate(provider, GothicBlocks.POLISHED_PALE_GRIMSTONE);
        autoTranslate(provider, GothicBlocks.DARK_GRIMSTONE_PILLAR);
        autoTranslate(provider, GothicBlocks.PALE_GRIMSTONE_PILLAR);
        autoTranslate(provider, GothicBlocks.CHISELED_DARK_GRIMSTONE);
        autoTranslate(provider, GothicBlocks.CHISELED_PALE_GRIMSTONE);
        autoTranslate(provider, GothicBlocks.PALE_GRIMSTONE_SPYRE);
        autoTranslate(provider, GothicBlocks.DARK_GRIMSTONE_SPYRE);
        autoTranslate(provider, GothicBlocks.PALE_GRIMSTONE_SPYRE_BLOCK);
        autoTranslate(provider, GothicBlocks.DARK_GRIMSTONE_SPYRE_BLOCK);
        autoTranslate(provider, GothicBlocks.DARK_GRIMSTONE_SPYRE_FENCE);
        autoTranslate(provider, GothicBlocks.PALE_GRIMSTONE_SPYRE_FENCE);

        addSubtitle(provider, "block", "gargoyle.growl", "Gargoyle growls");

        provider.add(GothicTags.Items.CRYSTAL_GLASS, "Crystal Glass");
        provider.add(GothicTags.Items.CRYSTAL_GLASS_PANES, "Crystal Glass Panes");
        provider.add(GothicTags.Items.GARGOYLE_SNACK, "Gargoyle Snacks");

        provider.add(GothicTags.Blocks.CRYSTAL_GLASS, "Crystal Glass");
        provider.add(GothicTags.Blocks.CRYSTAL_GLASS_PANES, "Crystal Glass Panes");
        provider.add(GothicTags.Blocks.DARK_SPYRE_CATALYST, "Converts Dripstone to Dark Spyres");
        provider.add(GothicTags.Blocks.PALE_SPYRE_CATALYST, "Converts Dripstone to Pale Spyres");

        provider.add(GothicTags.Entities.SCARED_OF_GARGOYLE, "Scared of Gargoyles");

        provider.add(GothicTags.Fluids.SPYRE_CONVERSION_FLUID, "Converts Dripstone to Spyres");
    }

}
