package galena.oreganized.argentum.data;

import com.tterrag.registrate.providers.ProviderType;
import galena.oreganized.OConstants;
import galena.oreganized.argentum.index.ArgentumBlocks;
import galena.oreganized.argentum.index.TarnishedBlocks;
import galena.oreganized.data.provider.ODatagen;
import java.util.stream.Stream;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class ArgentumData {

    public static Stream<TarnishedBlocks<?>> tarnishedBlocks() {
        return Stream.of(
                ArgentumBlocks.SILVER_BLOCKS,
                ArgentumBlocks.SILVER_BULBS,
                ArgentumBlocks.SILVER_BARS,
                ArgentumBlocks.SILVER_DOORS,
                ArgentumBlocks.SILVER_TRAPDOORS,
                ArgentumBlocks.SILVER_LATTICES,
                ArgentumBlocks.SILVER_PILLARS,
                ArgentumBlocks.CHISELED_SILVER,
                ArgentumBlocks.CUT_SILVERS,
                ArgentumBlocks.CUT_SILVER_SLABS,
                ArgentumBlocks.CUT_SILVER_STAIRS
        );
    }

    public ArgentumData() {
        ODatagen.REGISTRATE.addDataGenerator(ProviderType.RECIPE, ArgentumRecipes::generate);
    }

}
