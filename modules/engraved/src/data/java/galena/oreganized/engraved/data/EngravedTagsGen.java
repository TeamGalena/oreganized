package galena.oreganized.gothic.data;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.engraved.index.EngravedItems;
import galena.oreganized.engraved.index.EngravedTags;
import galena.oreganized.plumbum.index.PlumbumTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class EngravedTagsGen {

    public EngravedTagsGen() {
        ODatagen.addItemTagProvider(this::items);
        ODatagen.addBlockTagProvider(this::blocks);
    }

    private void items(RegistrateItemTagsProvider provider) {
        provider.addTag(EngravedTags.Items.TOOLS_BUSH_HAMMER).add(EngravedItems.BUSH_HAMMER.getKey());
    }

    private void blocks(RegistrateTagsProvider.IntrinsicImpl<Block> provider) {
        provider.addTag(PlumbumTags.Blocks.INCORRECT_FOR_LEAD_TOOL).addTag(BlockTags.INCORRECT_FOR_IRON_TOOL);
        provider.addTag(EngravedTags.Blocks.MINEABLE_WITH_BUSH_HAMMER).addTag(BlockTags.MINEABLE_WITH_PICKAXE);
    }

}
