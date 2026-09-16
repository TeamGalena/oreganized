package galena.oreganized.device.data;

import galena.oreganized.OConstants;
import galena.oreganized.argentum.index.ArgentumDataMapTypes;
import galena.oreganized.argentum.world.ScribePermutation;
import galena.oreganized.data.ODatagen;
import galena.oreganized.gothic.index.GothicBlocks;
import galena.oreganized.gothic.world.block.ICrystalGlass;
import galena.oreganized.index.OTags;
import java.util.stream.Stream;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.DataMapProvider;

@Mod(OConstants.MOD_ID)
public class GothicDataMaps {

    public GothicDataMaps() {
        ODatagen.addDataMapProvider(this::generate);
    }

    private void generate(DataMapProvider provider) {
        var crystalGlass = Stream.concat(
                GothicBlocks.CRYSTAL_GLASS.values().stream(),
                GothicBlocks.CRYSTAL_GLASS_PANES.values().stream()
        );

        var scribePermutations = provider.builder(ArgentumDataMapTypes.SCRIBE_PERMUTATIONS);
        scribePermutations.add(OTags.Blocks.CRYSTAL_GLASS, new ScribePermutation(ICrystalGlass.TYPE), false);
        scribePermutations.add(OTags.Blocks.CRYSTAL_GLASS_PANES, new ScribePermutation(ICrystalGlass.TYPE), false);
    }

}
