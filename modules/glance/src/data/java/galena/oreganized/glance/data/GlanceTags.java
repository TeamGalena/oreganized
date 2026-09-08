package galena.oreganized.glance.data;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.glance.index.GlanceBlocks;
import galena.oreganized.index.OTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class GlanceTags {

    public GlanceTags() {
        ODatagen.addItemTagProvider(this::items);
        ODatagen.addBlockTagProvider(this::blocks);
    }

    private void items(RegistrateItemTagsProvider provider) {
        provider.copy(OTags.Blocks.STONE_TYPES_GLANCE, OTags.Items.STONE_TYPES_GLANCE);
    }

    private void blocks(RegistrateTagsProvider.IntrinsicImpl<Block> provider) {
        provider.addTag(OTags.Blocks.STONE_TYPES_GLANCE).add(
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

}
