package galena.oreganized.electrum.data;

import static galena.oreganized.data.extensions.OBlockStateExtensions.cubeAll;

import galena.oreganized.data.ODatagen;
import galena.oreganized.electrum.ElectrumModule;
import galena.oreganized.electrum.index.ElectrumBlocks;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;

@Mod(ElectrumModule.MOD_ID)
public class ElectrumBlockStates {

    public ElectrumBlockStates() {
        ODatagen.addBlockStateProvider(this::generate);
    }

    private void generate(BlockStateProvider provider) {
        cubeAll(provider, ElectrumBlocks.ELECTRUM_BLOCK);
    }

}
