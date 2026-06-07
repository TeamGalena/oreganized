package galena.oreganized.compat.ponder;

import galena.oreganized.index.OBlocks;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;

public class CreateCompatScenes {

    static void registerScenes(PonderSceneRegistrationHelper<Holder<?>> registrar) {
        registrar
            .forComponents(OBlocks.RAW_LEAD_BLOCK)
            .addStoryBoard("blow_lead_cloud", CreateCompatScenes::leadCloudBlowing);
    }

    private static void leadCloudBlowing(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("blow_lead_cloud", "Blow Lead Dust");
        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();
        scene.idle(5);

        var pos = util.grid().at(2, 1, 2);
        scene.world().showSection(util.select().position(pos), Direction.DOWN);

        scene.idle(5);
    }

}
