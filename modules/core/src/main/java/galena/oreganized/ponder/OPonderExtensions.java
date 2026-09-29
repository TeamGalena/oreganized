package galena.oreganized.ponder;

import galena.oreganized.ModCompat;
import net.createmod.ponder.Ponder;
import net.createmod.ponder.api.ParticleEmitter;
import net.createmod.ponder.api.element.ElementLink;
import net.createmod.ponder.api.element.EntityElement;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class OPonderExtensions {

    public static ParticleEmitter cubicParticleEmitter(ParticleOptions data, double size, Vec3 motion) {
        var radius = size / 2;
        return (level, x, y, z) -> level.addParticle(
                data,
                x + Ponder.RANDOM.nextFloat() * size - radius, y + Ponder.RANDOM.nextFloat() * size - radius, z + Ponder.RANDOM.nextFloat() * size - radius,
                motion.x(), motion.y(), motion.z()
        );
    }

    public static void spawnerActivationParticles(SceneBuilder scene, BlockPos pos) {
        scene.effects().emitParticles(
                pos.getCenter(),
                cubicParticleEmitter(ModCompat.createSpawnerFlame(), 1.5, Vec3.ZERO),
                30F, 1
        );
    }

    public static void deathParticles(SceneBuilder scene, Vec3 pos) {
        scene.effects().emitParticles(
                pos,
                scene.effects().simpleParticleEmitter(ParticleTypes.POOF, Vec3.ZERO),
                10F, 1
        );
    }

    public static void killEntity(SceneBuilder scene, Vec3 pos, ElementLink<EntityElement> link) {
        scene.world().modifyEntity(link, Entity::discard);
        deathParticles(scene, pos);
    }

    public static void spinScene(SceneBuilder scene, float speed) {
        scene.addInstruction(new SpinSceneInstruction(speed));
    }

}
