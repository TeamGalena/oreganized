package galena.oreganized.compat.ponder;

import galena.oreganized.index.OBlocks;
import galena.oreganized.index.OItems;
import galena.oreganized.index.OParticleTypes;
import java.util.List;
import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.element.ElementLink;
import net.createmod.ponder.api.element.EntityElement;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.phys.Vec3;

public class GargoyleScenes {

    static void registerScenes(PonderSceneRegistrationHelper<Holder<?>> registrar) {
        registrar
                .forComponents(OBlocks.GARGOYLE)
                .addStoryBoard("gargoyle", GargoyleScenes::scareScene)
                .addStoryBoard("gargoyle", GargoyleScenes::automateGargoyle);
    }

    private static void scareScene(SceneBuilder scene, SceneBuildingUtil util) {
        var gargoylePos = util.grid().at(3, 1, 3);

        scene.title("gargoyle_scare_monsters", "Scaring Monsters with the Gargoyle");
        scene.configureBasePlate(0, 0, 7);
        scene.showBasePlate();
        scene.idle(5);

        scene.world().showSection(util.select().position(gargoylePos), Direction.DOWN);

        scene.idle(20);

        var spawns = List.of(util.grid().at(1, 0, 2), util.grid().at(5, 0, 3), util.grid().at(4, 0, 5));

        var random = RandomSource.create();
        var monsters = spawns.stream().map(spawn -> {
            var monster = createMonster(scene, util, random, spawn, gargoylePos);
            scene.idle(10);
            return monster;
        }).toList();

        scene.overlay().showText(70)
                .text("Feeding a silver ingot to a gargoyle will make it scare away nearby monsters")
                .placeNearTarget()
                .pointAt(util.vector().blockSurface(gargoylePos, Direction.WEST));
        scene.idle(90);

        scene.overlay().showControls(util.vector().topOf(gargoylePos), Pointing.DOWN, 50)
                .rightClick()
                .withItem(OItems.SILVER_INGOT.toStack());
        scene.idle(30);

        activateGargoyle(scene, gargoylePos);

        for (int i = 0; i < monsters.size(); i++) {
            scareMonster(scene, monsters.get(i), spawns.get(i));
            scene.idle(10);
        }

        scene.markAsFinished();
    }

    static void activateGargoyle(SceneBuilder scene, BlockPos gargoylePos) {
        scene.effects().emitParticles(
                gargoylePos.getCenter(),
                scene.effects().particleEmitterWithinBlockSpace(OParticleTypes.VENGEANCE.get(), new Vec3(0.1, 0.2, 0.1)),
                1.5F, 10
        );
    }

    private static void automateGargoyle(SceneBuilder scene, SceneBuildingUtil util) {
        var gargoylePos = util.grid().at(3, 1, 3);
        var dispenserPos = gargoylePos.east();
        var leverPos = dispenserPos.above();

        scene.title("gargoyle_automate", "Automate the Gargoyle");
        scene.configureBasePlate(0, 0, 7);
        scene.showBasePlate();
        scene.world().showSection(util.select().position(gargoylePos), Direction.UP);

        if (OreganizedPonderPlugin.CREATED_LOADED) {
            CreateCompatScenes.useArmOnGargoyle(scene, util, gargoylePos);
            scene.idle(20);
            scene.addKeyframe();
        }

        scene.world().showSection(util.select().position(dispenserPos), Direction.WEST);
        scene.idle(5);
        scene.world().showSection(util.select().position(leverPos), Direction.DOWN);
        scene.idle(10);

        scene.overlay().showText(70)
                .text("You can also feed it using a dispenser")
                .placeNearTarget()
                .pointAt(util.vector().blockSurface(dispenserPos, Direction.SOUTH));
        scene.idle(90);

        scene.world().modifyBlock(leverPos, state -> state.setValue(LeverBlock.POWERED, true), false);
        scene.effects().indicateRedstone(leverPos);
        activateGargoyle(scene, gargoylePos);

        scene.markAsFinished();
    }

    private static void scareMonster(SceneBuilder scene, ElementLink<EntityElement> link, BlockPos pos) {
        scene.world().modifyEntity(link, Entity::discard);
        scene.effects().emitParticles(
                pos.above().getCenter(),
                scene.effects().particleEmitterWithinBlockSpace(ParticleTypes.LARGE_SMOKE, Vec3.ZERO),
                1.5F, 10
        );
        scene.idle(10);
    }

    private static ElementLink<EntityElement> createMonster(SceneBuilder scene, SceneBuildingUtil util, RandomSource random, BlockPos spawnPos, BlockPos gargoylePos) {
        return scene.world().createEntity(level -> {
            var type = random.nextBoolean() ? EntityType.ZOMBIE : EntityType.SKELETON;
            var monster = type.create(level);

            var vec = util.vector().topOf(spawnPos);
            monster.moveTo(vec);

            var target = util.vector().topOf(gargoylePos);

            monster.lookAt(EntityAnchorArgument.Anchor.EYES, target);

            return monster;
        });
    }

}
