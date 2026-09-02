package galena.oreganized.world;

import galena.oreganized.index.OAttributes;
import galena.oreganized.index.OParticleTypes;
import galena.oreganized.network.packet.KineticHitPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.PacketDistributor;

public class KineticDamage {

    public static void apply(LivingEntity cause, Entity target) {
        if (!(cause instanceof IMotionHolder motionHolder)) return;

        var motion = Math.sqrt(motionHolder.oreganised$getHorizontalMotion()) - 0.15;

        var factor = Math.min(motion / 0.12, 1F);
        if (factor <= 0.0) return;

        if (!cause.getAttributes().hasAttribute(OAttributes.KINETIC_DAMAGE)) return;
        var kineticDamage = factor * cause.getAttributeValue(OAttributes.KINETIC_DAMAGE);
        var source = target.level().damageSources().generic();

        if (kineticDamage == 0.0) return;

        target.invulnerableTime = 0;
        target.hurt(source, (float) kineticDamage);
        if (target.level() instanceof ServerLevel level) {
            var packet = new KineticHitPacket(target.getId(), (float) factor);
            PacketDistributor.sendToPlayersNear(level, null, target.getX(), target.getY(), target.getZ(), 16.0, packet);
        }
    }

    public static void spawnParticles(Entity target, float factor) {
        var level = target.level();
        var count = (int) (1 + Math.floor(4 * factor));

        for (int i = 0; i < count; i++) {
            level.addParticle(
                    OParticleTypes.KINETIC_HIT.get(),
                    target.getRandomX(0.75), target.getRandomY(), target.getRandomZ(0.75),
                    level.random.nextGaussian() * 0.02D, level.random.nextGaussian() * 0.02D, level.random.nextGaussian() * 0.02D
            );
        }
    }

}
