package galena.oreganized.device.data;

import static galena.oreganized.data.extensions.OTagExtensions.tagDyed;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.gothic.index.GothicBlocks;
import galena.oreganized.gothic.index.GothicTags;
import galena.oreganized.index.CoreTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;

@Mod(OConstants.MOD_ID)
public class GothicTagsGen {

    public GothicTagsGen() {
        ODatagen.addItemTagProvider(this::items);
        ODatagen.addBlockTagProvider(this::blocks);
        ODatagen.addEntityTagProvider(this::entities);
    }

    private void items(RegistrateItemTagsProvider provider) {
        provider.addTag(GothicTags.Items.GARGOYLE_SNACK).addTags(CoreTags.Items.INGOTS_SILVER);

        tagDyed(provider, GothicBlocks.CRYSTAL_GLASS, GothicTags.Items.CRYSTAL_GLASS);
        tagDyed(provider, GothicBlocks.CRYSTAL_GLASS_PANES, GothicTags.Items.CRYSTAL_GLASS_PANES);

        provider.copy(Tags.Blocks.GLASS_BLOCKS, Tags.Items.GLASS_BLOCKS);
        provider.copy(Tags.Blocks.GLASS_PANES, Tags.Items.GLASS_PANES);
    }

    private void blocks(RegistrateTagsProvider.IntrinsicImpl<Block> provider) {
        tagDyed(provider, GothicBlocks.CRYSTAL_GLASS, GothicTags.Blocks.CRYSTAL_GLASS);
        tagDyed(provider, GothicBlocks.CRYSTAL_GLASS_PANES, GothicTags.Blocks.CRYSTAL_GLASS_PANES);

        provider.addTag(BlockTags.MINEABLE_WITH_PICKAXE).add(GothicBlocks.GARGOYLE.getKey());

        provider.addTag(Tags.Blocks.GLASS_BLOCKS).addTag(GothicTags.Blocks.CRYSTAL_GLASS);
        provider.addTag(Tags.Blocks.GLASS_PANES).addTag(GothicTags.Blocks.CRYSTAL_GLASS_PANES);
        provider.addTag(BlockTags.IMPERMEABLE).addTag(GothicTags.Blocks.CRYSTAL_GLASS);
    }

    private void entities(RegistrateTagsProvider.IntrinsicImpl<EntityType<?>> provider) {
        provider.addTag(GothicTags.Entities.SCARED_OF_GARGOYLE).addTags(EntityTypeTags.UNDEAD);
    }

}
