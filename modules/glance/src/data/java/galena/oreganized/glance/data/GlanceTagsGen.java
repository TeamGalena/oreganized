package galena.oreganized.glance.data;

import static galena.oreganized.data.extensions.OTagExtensions.tagStoneSet;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.glance.index.GlanceBlocks;
import galena.oreganized.glance.index.GlanceTags;
import galena.oreganized.plumbum.index.PlumbumTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;

@Mod(OConstants.MOD_ID)
public class GlanceTagsGen {

    public GlanceTagsGen() {
        ODatagen.addItemTagProvider(this::items);
        ODatagen.addBlockTagProvider(this::blocks);
        ODatagen.addBiomeTagProvider(this::biomes);
    }

    private void items(RegistrateItemTagsProvider provider) {
        provider.copy(GlanceTags.Blocks.STONE_TYPES_GLANCE, GlanceTags.Items.STONE_TYPES_GLANCE);
    }

    private void blocks(RegistrateTagsProvider.IntrinsicImpl<Block> provider) {
        var glanceBlocks = provider.addTag(GlanceTags.Blocks.STONE_TYPES_GLANCE);

        glanceBlocks.add(GlanceBlocks.CHISELED_GLANCE.getKey());
        GlanceBlocks.GLANCE.keys().forEach(glanceBlocks::add);
        GlanceBlocks.GLANCE_BRICKS.keys().forEach(glanceBlocks::add);
        GlanceBlocks.POLISHED_GLANCE.keys().forEach(glanceBlocks::add);

        tagStoneSet(provider, GlanceBlocks.GLANCE);
        tagStoneSet(provider, GlanceBlocks.GLANCE_BRICKS);
        tagStoneSet(provider, GlanceBlocks.POLISHED_GLANCE);

        provider.addTag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                GlanceBlocks.SPOTTED_GLANCE.getKey(),
                GlanceBlocks.WAXED_SPOTTED_GLANCE.getKey()
        ).addTag(
                GlanceTags.Blocks.STONE_TYPES_GLANCE
        );
    }

    private void biomes(RegistrateTagsProvider.Impl<Biome> provider) {
        provider.addTag(GlanceTags.Biomes.HAS_BOULDER)
                .addOptionalTag(PlumbumTags.Biomes.RICH_IN_LEAD_ORE)
                .addTag(Tags.Biomes.IS_PLAINS);
    }

}
