package galena.oreganized.glance.data;

import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.glance.index.GlanceBlocks;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.registries.datamaps.builtin.Waxable;

@Mod(OConstants.MOD_ID)
public class GlanceDataMaps {

    public GlanceDataMaps() {
        ODatagen.addDataMapProvider(this::generate);
    }

    private void generate(DataMapProvider provider) {
        var waxables = provider.builder(NeoForgeDataMaps.WAXABLES);

        waxables.add(GlanceBlocks.SPOTTED_GLANCE, new Waxable(GlanceBlocks.WAXED_SPOTTED_GLANCE.get()), false);
    }

}
