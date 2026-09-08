package galena.oreganized.device.data;

import static galena.oreganized.data.extensions.OTagExtensions.tagDyed;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.gothic.index.GothicBlocks;
import galena.oreganized.index.OTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;

@Mod(OConstants.MOD_ID)
public class GothicTags {

    public GothicTags() {
        ODatagen.addItemTagProvider(this::items);
        ODatagen.addBlockTagProvider(this::blocks);
        ODatagen.addEntityTagProvider(this::entities);
    }

    private void items(RegistrateItemTagsProvider provider) {
        tagDyed(provider, GothicBlocks.CRYSTAL_GLASS, OTags.Items.CRYSTAL_GLASS);
        tagDyed(provider, GothicBlocks.CRYSTAL_GLASS_PANES, OTags.Items.CRYSTAL_GLASS_PANES);

        provider.copy(Tags.Blocks.GLASS_BLOCKS, Tags.Items.GLASS_BLOCKS);
        provider.copy(Tags.Blocks.GLASS_PANES, Tags.Items.GLASS_PANES);
    }

    private void blocks(RegistrateTagsProvider.IntrinsicImpl<Block> provider) {
        tagDyed(provider, GothicBlocks.CRYSTAL_GLASS, OTags.Blocks.CRYSTAL_GLASS);
        tagDyed(provider, GothicBlocks.CRYSTAL_GLASS_PANES, OTags.Blocks.CRYSTAL_GLASS_PANES);

        provider.addTag(BlockTags.MINEABLE_WITH_PICKAXE).add(GothicBlocks.GARGOYLE.getKey());

        provider.addTag(Tags.Blocks.GLASS_BLOCKS).addTag(OTags.Blocks.CRYSTAL_GLASS);
        provider.addTag(Tags.Blocks.GLASS_PANES).addTag(OTags.Blocks.CRYSTAL_GLASS_PANES);
        provider.addTag(BlockTags.IMPERMEABLE).addTag(OTags.Blocks.CRYSTAL_GLASS);
    }

    private void entities(RegistrateTagsProvider.IntrinsicImpl<EntityType<?>> provider) {
        provider.addTag(OTags.Entities.SCARED_OF_GARGOYLE).addTags(EntityTypeTags.UNDEAD);
    }

}
