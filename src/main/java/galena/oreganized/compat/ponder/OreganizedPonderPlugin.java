package galena.oreganized.compat.ponder;

import galena.oreganized.Oreganized;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.OItems;
import galena.oreganized.world.ScaredOfGargoyleGoal;
import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.ParticleEmitter;
import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.fml.ModList;

import java.util.List;
import java.util.stream.Stream;

public class OreganizedPonderPlugin implements PonderPlugin {

    @Override
    public String getModId() {
        return Oreganized.MOD_ID;
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        var registrar = helper.<Holder<?>>withKeyFunction(it -> it.getKey().location());

        if (ModList.get().isLoaded("create")) {
            CreateCompatScenes.registerScenes(registrar);
        }

        registrar
            .forComponents(OBlocks.GARGOYLE)
            .addStoryBoard("gargoyle", OreganizedPonderPlugin::gargoyleScene);
    }

    private static void gargoyleScene(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("gargoyle", "Gargoyle");
        scene.configureBasePlate(0, 0, 7);
        scene.showBasePlate();
        scene.idle(5);

        var pos = util.grid().at(3, 1, 3);
        scene.world().showSection(util.select().position(pos), Direction.DOWN);

        scene.idle(10);

        var spawns = Stream.of(util.grid().at(1, 0, 2), util.grid().at(5, 0, 3));

        var zombies = spawns.map(spawn ->
            scene.world().createEntity(level -> {
                var monster = EntityType.ZOMBIE.create(level);

                var vec = util.vector().topOf(spawn);
                monster.moveTo(vec);

                var target = util.vector().topOf(pos);

                monster.lookAt(EntityAnchorArgument.Anchor.EYES, target);

                return monster;
            })
        ).toList();

        scene.idle(10);

        scene.overlay().showControls(util.vector().topOf(pos), Pointing.DOWN, 50)
            .rightClick()
            .withItem(OItems.SILVER_INGOT.toStack());

        scene.world().modifyEntities(Monster.class, monster -> {
            scene.effects().emitParticles(
                monster.position().add(0, 1, 0),
                scene.effects().particleEmitterWithinBlockSpace(ParticleTypes.ANGRY_VILLAGER, new Vec3(0.1, 0.1, 0.1)),
                10, 4
            );
        });

    }

}
