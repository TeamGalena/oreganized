package galena.oreganized.plumbum.ponder;

import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import galena.oreganized.plumbum.index.PlumbumBlocks;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;

public class CreateCompatScenes {

    static void registerScenes(PonderSceneRegistrationHelper<Holder<?>> registrar) {
        registrar
                .forComponents(PlumbumBlocks.RAW_LEAD_BLOCK)
                .addStoryBoard("blow_lead_cloud", CreateCompatScenes::leadCloudBlowing);
    }

    private static void leadCloudBlowing(SceneBuilder builder, SceneBuildingUtil util) {
        var scene = new CreateSceneBuilder(builder);

        var leadPos = util.grid().at(2, 1, 2);
        var fanPos = util.grid().at(2, 1, 4);
        var smallCog = util.grid().at(2, 1, 5);
        var largeCog = util.grid().at(1, 0, 5);

        scene.title("blow_lead_cloud", "Blow Lead Dust");
        scene.configureBasePlate(0, 0, 5);

        scene.world().showSection(util.select().position(fanPos), Direction.UP);
        scene.world().showSection(util.select().fromTo(smallCog, largeCog), Direction.UP);
        scene.showBasePlate();
        scene.idle(5);

        scene.world().showSection(util.select().position(leadPos), Direction.DOWN);

        scene.overlay().showText(60)
                .text("Placing Raw Lead or Lead ore in the path of a fan creates a dust cloud")
                .placeNearTarget()
                .pointAt(util.vector().blockSurface(leadPos, Direction.WEST));
        scene.idle(80);

        scene.world().setKineticSpeed(util.select().fromTo(smallCog, fanPos), -16);
        scene.world().setKineticSpeed(util.select().position(largeCog), 8);

        scene.idle(5);
        scene.markAsFinished();
    }

}
