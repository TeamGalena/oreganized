package galena.oreganized.glance.data;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.provider.ODatagen;
import galena.oreganized.glance.index.GlanceBlocks;
import galena.oreganized.index.OTags;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class GlanceLang {

    public GlanceLang() {
        ODatagen.addLangProvider(this::generate);
    }

    private void generate(RegistrateLangProvider provider) {
        provider.addBlock(GlanceBlocks.GLANCE::get);
        provider.addBlock(GlanceBlocks.GLANCE_SLAB::get);
        provider.addBlock(GlanceBlocks.GLANCE_STAIRS::get);
        provider.addBlock(GlanceBlocks.GLANCE_WALL::get);

        provider.addBlock(GlanceBlocks.POLISHED_GLANCE::get);
        provider.addBlock(GlanceBlocks.POLISHED_GLANCE_SLAB::get);
        provider.addBlock(GlanceBlocks.POLISHED_GLANCE_STAIRS::get);

        provider.addBlock(GlanceBlocks.CHISELED_GLANCE::get);

        provider.addBlock(GlanceBlocks.GLANCE_BRICKS::get);
        provider.addBlock(GlanceBlocks.GLANCE_BRICK_SLAB::get);
        provider.addBlock(GlanceBlocks.GLANCE_BRICK_STAIRS::get);
        provider.addBlock(GlanceBlocks.GLANCE_BRICK_WALL::get);

        provider.addBlock(GlanceBlocks.SPOTTED_GLANCE::get);
        provider.addBlock(GlanceBlocks.WAXED_SPOTTED_GLANCE::get);

        provider.add(OTags.Items.STONE_TYPES_GLANCE, "Processed Glance");
        provider.add(OTags.Blocks.STONE_TYPES_GLANCE, "Processed Glance");
    }

}
