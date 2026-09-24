package galena.oreganized.glance.data;

import static galena.oreganized.data.extensions.OLangExtensions.autoTranslate;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.glance.index.GlanceBlocks;
import galena.oreganized.glance.index.GlanceTags;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class GlanceLang {

    public GlanceLang() {
        ODatagen.addLangProvider(this::generate);
    }

    private void generate(RegistrateLangProvider provider) {
        autoTranslate(provider, GlanceBlocks.GLANCE);
        autoTranslate(provider, GlanceBlocks.GLANCE_SLAB);
        autoTranslate(provider, GlanceBlocks.GLANCE_STAIRS);
        autoTranslate(provider, GlanceBlocks.GLANCE_WALL);

        autoTranslate(provider, GlanceBlocks.POLISHED_GLANCE);
        autoTranslate(provider, GlanceBlocks.POLISHED_GLANCE_SLAB);
        autoTranslate(provider, GlanceBlocks.POLISHED_GLANCE_STAIRS);

        autoTranslate(provider, GlanceBlocks.CHISELED_GLANCE);

        autoTranslate(provider, GlanceBlocks.GLANCE_BRICKS);
        autoTranslate(provider, GlanceBlocks.GLANCE_BRICK_SLAB);
        autoTranslate(provider, GlanceBlocks.GLANCE_BRICK_STAIRS);
        autoTranslate(provider, GlanceBlocks.GLANCE_BRICK_WALL);

        autoTranslate(provider, GlanceBlocks.SPOTTED_GLANCE);
        autoTranslate(provider, GlanceBlocks.WAXED_SPOTTED_GLANCE);

        provider.add(GlanceTags.Items.STONE_TYPES_GLANCE, "Processed Glance");
        provider.add(GlanceTags.Blocks.STONE_TYPES_GLANCE, "Processed Glance");
    }

}
