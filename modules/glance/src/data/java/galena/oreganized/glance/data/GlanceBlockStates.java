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
        cubeAll(provider, GlanceBlocks.GLANCE);
        cubeAll(provider, GlanceBlocks.POLISHED_GLANCE);
        cubeAll(provider, GlanceBlocks.GLANCE_BRICKS);
        cubeAll(provider, GlanceBlocks.CHISELED_GLANCE);
        slab(provider, GlanceBlocks.GLANCE, GlanceBlocks.GLANCE_SLAB);
        slab(provider, GlanceBlocks.POLISHED_GLANCE, GlanceBlocks.POLISHED_GLANCE_SLAB);
        slab(provider, GlanceBlocks.GLANCE_BRICKS, GlanceBlocks.GLANCE_BRICK_SLAB);
        stairs(provider, GlanceBlocks.GLANCE, GlanceBlocks.GLANCE_STAIRS);
        stairs(provider, GlanceBlocks.POLISHED_GLANCE, GlanceBlocks.POLISHED_GLANCE_STAIRS);
        stairs(provider, GlanceBlocks.GLANCE_BRICKS, GlanceBlocks.GLANCE_BRICK_STAIRS);
        wall(provider, GlanceBlocks.GLANCE, GlanceBlocks.GLANCE_WALL);
        wall(provider, GlanceBlocks.GLANCE_BRICKS, GlanceBlocks.GLANCE_BRICK_WALL);
        cubeAll(provider, GlanceBlocks.SPOTTED_GLANCE);
        waxed(provider, GlanceBlocks.WAXED_SPOTTED_GLANCE, GlanceBlocks.SPOTTED_GLANCE.value());
    }

}
