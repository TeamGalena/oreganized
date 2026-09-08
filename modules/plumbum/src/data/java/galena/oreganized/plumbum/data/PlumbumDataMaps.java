package galena.oreganized.plumbum.data;

import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.plumbum.index.PlumbumBlocks;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

@Mod(OConstants.MOD_ID)
public class PlumbumDataMaps {

    public PlumbumDataMaps() {
        ODatagen.addDataMapProvider(this::generate);
    }

    private void generate(DataMapProvider provider) {
        var compostable = provider.builder(NeoForgeDataMaps.COMPOSTABLES);

        compostable.add(PlumbumBlocks.WHITE_DATURA.getId(), new Compostable(0.65F, true), false);
        compostable.add(PlumbumBlocks.PURPLE_DATURA.getId(), new Compostable(0.65F, true), false);
    }

}
