package galena.oreganized.plumbum.data;

import static galena.oreganized.data.extensions.OBlockStateExtensions.waxed;

import galena.oreganized.OConstants;
import galena.oreganized.data.ColorCompat;
import galena.oreganized.data.ODatagen;
import galena.oreganized.waxed.index.WaxedBlocks;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;

@Mod(OConstants.MOD_ID)
public class WaxedBlockStates {

    public WaxedBlockStates() {
        ODatagen.addBlockStateProvider(this::generate);
    }

    private void generate(BlockStateProvider provider) {
        WaxedBlocks.WAXED_CONCRETE_POWDER.forEach((color, block) -> {
            var unwaxed = ColorCompat.getColoredBlock("concrete_powder", color);
            waxed(provider, block, unwaxed);
        });

    }

}
