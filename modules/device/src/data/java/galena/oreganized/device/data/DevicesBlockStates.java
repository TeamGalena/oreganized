package galena.oreganized.device.data;

import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;

@Mod(OConstants.MOD_ID)
public class DevicesBlockStates {

    public DevicesBlockStates() {
        ODatagen.addBlockStateProvider(this::generate);
    }

    private void generate(BlockStateProvider provider) {
    }

}
