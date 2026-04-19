package galena.oreganized.compat.ponder;

import galena.oreganized.Oreganized;
import galena.oreganized.index.OBlocks;
import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;

public class OreganizedPonderPlugin implements PonderPlugin {

    @Override
    public String getModId() {
        return Oreganized.MOD_ID;
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        var registrar = helper.<Holder<?>>withKeyFunction(it -> it.getKey().location());

        registrar
                .forComponents(OBlocks.GARGOYLE)
                .addStoryBoard("gargoyle", this::gargoyleScene);
    }

    private void gargoyleScene(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("gargoyle", "Gargoyle");
        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();
        scene.idle(5);

        var pos = util.grid().at(2, 1, 2);
        scene.world().showSection(util.select().position(pos), Direction.DOWN);

        scene.idle(5);
    }

}
