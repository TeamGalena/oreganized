package galena.oreganized.argentum.ponder;

import static galena.oreganized.ponder.OPonderExtensions.*;

import galena.oreganized.argentum.index.ArgentumBlocks;
import galena.oreganized.argentum.index.ArgentumParticles;
import galena.oreganized.argentum.world.TarnishBlockManager;
import java.util.List;
import java.util.stream.Stream;
import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.createmod.ponder.foundation.instruction.RotateSceneInstruction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class TarnishingScenes {

    private static Stream<? extends Holder<?>> silverBlocks() {
        return ArgentumBlocks.SILVER_BLOCKS.stream();
    }

    private static Stream<? extends Holder<?>> spawners() {
        return Stream.of(Blocks.SPAWNER, Blocks.TRIAL_SPAWNER).map(Block::builtInRegistryHolder);
    }

    private static Stream<? extends Holder<?>> spawnersAndBlocks() {
        return Stream.concat(silverBlocks(), spawners());
    }

    private static Stream<? extends Holder<?>> brushes() {
        return Stream.of(Items.BRUSH).map(Item::builtInRegistryHolder);
    }

    static void registerScenes(PonderSceneRegistrationHelper<Holder<?>> helper) {
        helper
                .forComponents(spawnersAndBlocks().toList())
                .addStoryBoard("tarnishing_shape", TarnishingScenes::tarnishingShape);

        helper
                .forComponents(Stream.concat(spawnersAndBlocks(), brushes()).toList())
                .addStoryBoard("tarnishing", TarnishingScenes::tarnishing);
    }

    private static void tarnishing(SceneBuilder scene, SceneBuildingUtil util) {
        var random = RandomSource.create();
        var spawnerPos = util.grid().at(4, 1, 2);
        var silverBlocks = List.of(
                util.select().position(1, 1, 1),
                util.select().position(2, 1, 4),
                util.select().fromTo(5, 1, 5, 5, 2, 5)
        );

        scene.title("tarnishing", "Silver tarnishing");
        scene.configureBasePlate(0, 0, 7);
        scene.showBasePlate();
        silverBlocks.forEach(it -> {
            scene.world().showSection(it, Direction.UP);
        });
        scene.idle(20);

        scene.overlay().showText(80)
                .text("The death of an undead monster causes blocks around to tarnish");
        scene.idleSeconds(4);

        silverBlocks.forEach(it -> {
            killMonster(scene, util, random, spawnerPos);
            tarnish(scene, it);
            scene.idle(20);
        });

        scene.idle(20);

        scene.world().showSection(util.select().position(spawnerPos), Direction.DOWN);
        scene.overlay().showText(80)
                .text("Spawners creating these monster also triggers this effect")
                .pointAt(spawnerPos.getCenter())
                .attachKeyFrame();
        scene.idleSeconds(4);

        silverBlocks.forEach(it -> {
            spawnerActivationParticles(scene, spawnerPos);
            tarnish(scene, it);
            scene.idle(20);
        });

        scene.idle(20);

        var polished = silverBlocks.getFirst();
        scene.overlay().showText(80)
                .text("Tarnished blocks can be polished into their pristine variants again")
                .pointAt(polished.getCenter())
                .placeNearTarget()
                .attachKeyFrame();
        scene.idleSeconds(4);

        scene.overlay().showControls(polished.getCenter().add(0, 0.5, 0), Pointing.DOWN, 50)
                .rightClick()
                .withItem(Items.BRUSH.getDefaultInstance());

        polish(scene, polished);
        scene.idle(20);
        polish(scene, polished);

        scene.idle(30);
    }

    private static void tarnishingShape(SceneBuilder scene, SceneBuildingUtil util) {
        var center = util.grid().at(4, 4, 4);
        var radius = 3;
        var inner = util.select().fromTo(center.offset(-1, -1, -1), center.offset(1, 1, 1));
        var outer = util.select().fromTo(center.offset(-radius, 0, -radius), center.offset(radius, 0, radius))
                .add(util.select().fromTo(center.offset(0, -radius, -radius), center.offset(0, radius, radius)))
                .add(util.select().fromTo(center.offset(-radius, -radius, 0), center.offset(radius, radius, 0)));

        scene.scaleSceneView(0.5F);

        scene.title("tarnishing_shape", "Tarnishing area");
        scene.configureBasePlate(0, 0, 9);
        scene.showBasePlate();
        scene.world().showSection(util.select().position(center), Direction.UP);
        scene.idle(20);

        scene.addInstruction(new RotateSceneInstruction(-10, 180, false));
        spinScene(scene, 0.15F);
        scene.idle(20);

        scene.overlay().showOutline(PonderPalette.RED, inner, inner, 6000);
        scene.overlay().showText(80)
                .text("Blocks close to the source will always tarnish")
                .pointAt(inner.getCenter())
                .attachKeyFrame();
        scene.idleSeconds(5);

        scene.overlay().showOutline(PonderPalette.OUTPUT, outer, outer, 6000);
        scene.overlay().showText(80)
                .text("Within this broader shapes, blocks have a smaller chance to tarnish")
                .pointAt(inner.getCenter())
                .attachKeyFrame();
        scene.idleSeconds(5);
    }

    private static void tarnish(SceneBuilder scene, Selection selection) {
        selection.forEach(pos -> {
            scene.world().modifyBlock(
                    pos,
                    state -> TarnishBlockManager.next(state).orElse(state),
                    false
            );

            scene.effects().emitParticles(
                    pos.getCenter(),
                    cubicParticleEmitter(ArgentumParticles.TARNISH.value(), 1.2, Vec3.ZERO),
                    10F, 1
            );
        });
    }

    private static void polish(SceneBuilder scene, Selection selection) {
        selection.forEach(pos -> {
            scene.world().modifyBlock(
                    pos,
                    state -> TarnishBlockManager.previous(state).orElse(state),
                    false
            );

            scene.effects().emitParticles(
                    pos.getCenter(),
                    cubicParticleEmitter(ArgentumParticles.POLISH.value(), 1.2, Vec3.ZERO),
                    20F, 2
            );
        });
    }

    private static void killMonster(SceneBuilder scene, SceneBuildingUtil util, RandomSource random, BlockPos spawnPos) {
        var link = scene.world().createEntity(level -> {
            var type = random.nextBoolean() ? EntityType.ZOMBIE : EntityType.SKELETON;
            var monster = type.create(level);

            var vec = util.vector().topOf(spawnPos.below());
            monster.moveTo(vec);

            return monster;
        });

        scene.idle(10);

        killEntity(scene, spawnPos.getCenter(), link);
    }

}
