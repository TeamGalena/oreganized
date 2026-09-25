package galena.oreganized.glance.data;

import static galena.oreganized.data.extensions.OBlockStateExtensions.*;

import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.glance.index.GlanceBlocks;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class GlanceBlockStates {

    public GlanceBlockStates() {
        ODatagen.addBlockStateProvider(this::generate);
    }

    private void generate(RegistrateBlockstateProvider provider) {
        stoneSet(provider, GlanceBlocks.GLANCE_SET);
        stoneSet(provider, GlanceBlocks.GLANCE_BRICKS_SET);
        stoneSet(provider, GlanceBlocks.POLISHED_GLANCE_SET);
        cubeAll(provider, GlanceBlocks.CHISELED_GLANCE);
        cubeAll(provider, GlanceBlocks.SPOTTED_GLANCE);
        waxed(provider, GlanceBlocks.WAXED_SPOTTED_GLANCE, GlanceBlocks.SPOTTED_GLANCE.value());
    }

}
