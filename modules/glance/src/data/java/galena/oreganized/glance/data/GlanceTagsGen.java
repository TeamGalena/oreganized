package galena.oreganized.glance.data;

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
        provider.addTag(GlanceTags.Blocks.STONE_TYPES_GLANCE).add(
                GlanceBlocks.POLISHED_GLANCE.getKey(),
                GlanceBlocks.GLANCE_BRICKS.getKey(),
                GlanceBlocks.CHISELED_GLANCE.getKey(),
                GlanceBlocks.GLANCE_BRICK_STAIRS.getKey(),
                GlanceBlocks.GLANCE_BRICK_WALL.getKey()
        );

        provider.addTag(BlockTags.WALLS).add(GlanceBlocks.GLANCE_WALL.getKey(), GlanceBlocks.GLANCE_BRICK_WALL.getKey());
        provider.addTag(BlockTags.STAIRS).add(GlanceBlocks.GLANCE_STAIRS.getKey(), GlanceBlocks.POLISHED_GLANCE_STAIRS.getKey(), GlanceBlocks.GLANCE_BRICK_STAIRS.getKey());
        provider.addTag(BlockTags.SLABS).add(GlanceBlocks.GLANCE_SLAB.getKey(), GlanceBlocks.POLISHED_GLANCE_SLAB.getKey(), GlanceBlocks.GLANCE_BRICK_SLAB.getKey());

        provider.addTag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                GlanceBlocks.GLANCE.getKey(),
                GlanceBlocks.GLANCE_STAIRS.getKey(),
                GlanceBlocks.GLANCE_SLAB.getKey(),
                GlanceBlocks.POLISHED_GLANCE.getKey(),
                GlanceBlocks.POLISHED_GLANCE_STAIRS.getKey(),
                GlanceBlocks.POLISHED_GLANCE_SLAB.getKey(),
                GlanceBlocks.GLANCE_WALL.getKey(),
                GlanceBlocks.GLANCE_BRICKS.getKey(),
                GlanceBlocks.GLANCE_BRICK_STAIRS.getKey(),
                GlanceBlocks.GLANCE_BRICK_SLAB.getKey(),
                GlanceBlocks.GLANCE_BRICK_WALL.getKey(),
                GlanceBlocks.CHISELED_GLANCE.getKey(),
                GlanceBlocks.SPOTTED_GLANCE.getKey(),
                GlanceBlocks.WAXED_SPOTTED_GLANCE.getKey()
        );
    }

    private void biomes(RegistrateTagsProvider.Impl<Biome> provider) {
        provider.addTag(GlanceTags.Biomes.HAS_BOULDER)
                .addOptionalTag(PlumbumTags.Biomes.RICH_IN_LEAD_ORE)
                .addTag(Tags.Biomes.IS_PLAINS);
    }

}
