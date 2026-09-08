package galena.oreganized.plumbum.data;

import static galena.oreganized.data.extensions.OTagExtensions.tagDyed;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.waxed.index.WaxedBlocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class WaxedTags {

    public WaxedTags() {
        ODatagen.addItemTagProvider(this::items);
        ODatagen.addBlockTagProvider(this::blocks);
    }

    private void items(RegistrateItemTagsProvider provider) {
        tagDyed(provider, WaxedBlocks.WAXED_CONCRETE_POWDER);
    }

    private void blocks(RegistrateTagsProvider.IntrinsicImpl<Block> provider) {
        tagDyed(provider, WaxedBlocks.WAXED_CONCRETE_POWDER, BlockTags.MINEABLE_WITH_SHOVEL);
    }

}
