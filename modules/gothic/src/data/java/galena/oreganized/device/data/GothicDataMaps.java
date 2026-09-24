package galena.oreganized.device.data;

import galena.oreganized.OConstants;
import galena.oreganized.argentum.index.ArgentumDataMapTypes;
import galena.oreganized.argentum.world.ScribePermutation;
import galena.oreganized.data.ODatagen;
import galena.oreganized.gothic.index.GothicTags;
import galena.oreganized.gothic.world.block.ICrystalGlass;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.DataMapProvider;

@Mod(OConstants.MOD_ID)
public class GothicDataMaps {

    public GothicDataMaps() {
        ODatagen.addDataMapProvider(this::generate);
    }

    private void generate(DataMapProvider provider) {
        var scribePermutations = provider.builder(ArgentumDataMapTypes.SCRIBE_PERMUTATIONS);
        scribePermutations.add(GothicTags.Blocks.CRYSTAL_GLASS, new ScribePermutation(ICrystalGlass.TYPE), false);
        scribePermutations.add(GothicTags.Blocks.CRYSTAL_GLASS_PANES, new ScribePermutation(ICrystalGlass.TYPE), false);
    }

}
