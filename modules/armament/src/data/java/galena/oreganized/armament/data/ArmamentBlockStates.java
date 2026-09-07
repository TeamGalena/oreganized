package galena.oreganized.armament.data;

import static galena.oreganized.data.extensions.OBlockStateExtensions.cubeBottomTopBlock;

import galena.oreganized.OConstants;
import galena.oreganized.armament.index.ArmamentBlocks;
import galena.oreganized.data.ODatagen;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;

@Mod(OConstants.MOD_ID)
public class ArmamentBlockStates {

    public ArmamentBlockStates() {
        ODatagen.addBlockStateProvider(this::generate);
    }

    private void generate(BlockStateProvider provider) {
        cubeBottomTopBlock(provider, ArmamentBlocks.SHRAPNEL_BOMB);
        cubeBottomTopBlock(provider, ArmamentBlocks.LEAD_BOLT_CRATE);
    }

}
