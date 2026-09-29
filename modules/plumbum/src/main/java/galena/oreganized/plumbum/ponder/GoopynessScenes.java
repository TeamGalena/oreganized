package galena.oreganized.plumbum.ponder;

import static galena.oreganized.ponder.OPonderExtensions.cubicParticleEmitter;

import galena.oreganized.plumbum.index.PlumbumBlocks;
import galena.oreganized.plumbum.index.PlumbumParticles;
import galena.oreganized.plumbum.world.block.IMeltableBlock;
import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.phys.Vec3;

public class GoopynessScenes {

    public static void registerScenes(PonderSceneRegistrationHelper<Holder<?>> helper) {
        helper
                .forComponents(PlumbumBlocks.LEAD_BLOCK)
                .addStoryBoard("red_hot_lead", GoopynessScenes::heatTransfer);
        helper
                .forComponents(PlumbumBlocks.LEAD_ORE, PlumbumBlocks.DEEPSLATE_LEAD_ORE)
                .addStoryBoard("lead_mining", GoopynessScenes::leadMining);
    }

    private static void heatTransfer(SceneBuilder scene, SceneBuildingUtil util) {
        var heatSource = util.grid().at(1, 1, 1);
        var leadBlocks = util.select().fromTo(1, 1, 1, 3, 1, 3).substract(util.select().position(heatSource));

        scene.title("heat_transfer", "Lead heat transfer");
        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();
        scene.world().showSection(leadBlocks, Direction.DOWN);
        scene.idle(20);

        scene.overlay().showText(80)
                .text("Lead blocks heat up when touching a hot block")
                .pointAt(leadBlocks.getCenter());

        scene.world().showSection(util.select().position(heatSource), Direction.DOWN);
        scene.idle(40);

        leadBlocks.forEach(pos -> {
            var distance = pos.distManhattan(heatSource);
            var goopyness = Math.max(0, 3 - distance);
            scene.world().modifyBlock(pos, state -> state.setValue(IMeltableBlock.GOOPYNESS_3, goopyness), false);
            if (pos.getX() == heatSource.getX()) scene.idle(20);
        });

        scene.overlay().showText(80)
                .text("Removing the heat source will cause them to cool down again")
                .pointAt(heatSource.getCenter())
                .attachKeyFrame()
                .placeNearTarget();
        scene.idleSeconds(4);

        scene.world().destroyBlock(heatSource);
        scene.idle(20);

        leadBlocks.forEach(pos -> {
            scene.world().modifyBlock(pos, state -> state.setValue(IMeltableBlock.GOOPYNESS_3, 0), false);
            if (pos.getX() == heatSource.getX()) scene.idle(20);
        });

        scene.idle(5);
        scene.markAsFinished();
    }

    private static void leadMining(SceneBuilder scene, SceneBuildingUtil util) {
        var unsafeOre = util.grid().at(3, 1, 2);
        var safeOre = util.grid().at(1, 0, 2);
        var waterHole = util.grid().at(2, 0, 2);

        scene.title("lead_mining", "Lead mining");
        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();
        scene.world().showSection(util.select().everywhere(), Direction.UP);
        scene.idle(20);

        scene.overlay().showText(80)
                .text("Mining lead ore causes lead dust clouds to be released")
                .pointAt(unsafeOre.getCenter())
                .placeNearTarget();
        scene.idleSeconds(4);

        scene.world().destroyBlock(unsafeOre);
        scene.effects().emitParticles(
                unsafeOre.below().getCenter(),
                cubicParticleEmitter(PlumbumParticles.LEAD_CLOUD.value(), 3.0, Vec3.ZERO),
                20, 60
        );

        scene.idleSeconds(4);

        scene.overlay().showControls(util.vector().topOf(waterHole), Pointing.DOWN, 50)
                .rightClick()
                .withItem(Items.WATER_BUCKET.getDefaultInstance());
        scene.world().setBlock(waterHole, Blocks.WATER.defaultBlockState(), false);

        scene.overlay().showText(80)
                .text("Lead ore that is touching water will not create any dust clouds")
                .pointAt(safeOre.getCenter())
                .attachKeyFrame()
                .placeNearTarget();
        scene.idleSeconds(4);

        scene.world().destroyBlock(safeOre);

        scene.idle(20);
        scene.world().setBlock(waterHole.west(), Blocks.WATER.defaultBlockState().setValue(LiquidBlock.LEVEL, 1), false);

        scene.idle(5);
        scene.markAsFinished();
    }

}
