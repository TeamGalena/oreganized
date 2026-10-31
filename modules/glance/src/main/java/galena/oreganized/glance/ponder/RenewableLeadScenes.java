package galena.oreganized.glance.ponder;

import galena.oreganized.glance.index.GlanceBlocks;
import galena.oreganized.plumbum.index.PlumbumBlocks;
import galena.oreganized.plumbum.index.PlumbumItems;
import galena.oreganized.plumbum.index.PlumbumParticles;
import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.ParticleEmitter;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;

public class RenewableLeadScenes {

    public static void registerScenes(PonderSceneRegistrationHelper<Holder<?>> helper) {
        helper
                .forComponents(GlanceBlocks.SPOTTED_GLANCE, GlanceBlocks.GLANCE.block(), PlumbumItems.MOLTEN_LEAD_BUCKET)
                .addStoryBoard("renewable_lead", RenewableLeadScenes::heatTransfer);
    }

    private static void heatTransfer(SceneBuilder scene, SceneBuildingUtil util) {
        var glance = util.grid().at(2, 0, 2);
        var fluid = glance.above(5);
        var tip = glance.above(2).getBottomCenter().add(0, 0.1, 0);

        scene.title("renewable_lead", "Renewable lead");
        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();
        scene.world().showSection(util.select().fromTo(glance, fluid), Direction.UP);
        scene.idle(20);

        scene.overlay().showControls(fluid.getCenter(), Pointing.LEFT, 50)
                .rightClick()
                .withItem(PlumbumItems.MOLTEN_LEAD_BUCKET.toStack());
        scene.world().setBlock(fluid, PlumbumBlocks.MOLTEN_LEAD.value().defaultBlockState(), false);

        scene.effects().emitParticles(tip, drip(PlumbumParticles.DRIPPING_DRIPSTONE_LEAD.value()), 0.06F, 20 * 20);
        scene.idle(100);

        scene.overlay().showText(80)
                .text("Lead dripping onto glance will slowly turn it into spotted glance")
                .placeNearTarget()
                .pointAt(util.vector().topOf(glance));
        scene.idle(40);

        scene.world().setBlock(glance, GlanceBlocks.SPOTTED_GLANCE.value().defaultBlockState(), false);

        scene.idle(5);
        scene.markAsFinished();
    }

    private static ParticleEmitter drip(ParticleOptions data) {
        return (level, x, y, z) -> level.addParticle(
                data,
                x, y, z,
                0, 0, 0
        );
    }

}
