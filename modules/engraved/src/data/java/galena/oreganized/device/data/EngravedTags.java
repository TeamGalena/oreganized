package galena.oreganized.device.data;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.engraved.index.EngravedItems;
import galena.oreganized.index.OTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class EngravedTags {

    public EngravedTags() {
        ODatagen.addItemTagProvider(this::items);
        ODatagen.addBlockTagProvider(this::blocks);
    }

    private void items(RegistrateItemTagsProvider provider) {
        provider.addTag(OTags.Items.TOOLS_BUSH_HAMMER).add(EngravedItems.BUSH_HAMMER.getKey());
    }

    private void blocks(RegistrateTagsProvider.IntrinsicImpl<Block> provider) {
        provider.addTag(OTags.Blocks.INCORRECT_FOR_LEAD_TOOL).addTag(BlockTags.INCORRECT_FOR_IRON_TOOL);
    }

}
