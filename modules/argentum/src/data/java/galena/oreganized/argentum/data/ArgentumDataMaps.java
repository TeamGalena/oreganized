package galena.oreganized.argentum.data;

import galena.oreganized.argentum.ArgentumModule;
import galena.oreganized.argentum.index.ArgentumDataMapTypes;
import galena.oreganized.argentum.index.ArgentumSets;
import galena.oreganized.argentum.world.Tarnishable;
import galena.oreganized.data.ODatagen;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.DataMapProvider;

@Mod(ArgentumModule.MOD_ID)
public class ArgentumDataMaps {

    public ArgentumDataMaps() {
        ODatagen.addDataMapProvider(this::generate);
    }

    private void generate(DataMapProvider provider) {
        var tarnishables = provider.builder(ArgentumDataMapTypes.TARNISHABLES);
        ArgentumSets.tarnishedBlocks().forEach(blocks -> {
            tarnishables.add(blocks.base(), new Tarnishable(blocks.blemished().get()), false);
            tarnishables.add(blocks.blemished(), new Tarnishable(blocks.tarnished().get()), false);
        });
    }

}
