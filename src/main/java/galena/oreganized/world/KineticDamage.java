package galena.oreganized.world;

import galena.oreganized.index.OAttributes;
import galena.oreganized.index.OParticleTypes;
import galena.oreganized.network.packet.KineticHitPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.PacketDistributor;
import org.apache.commons.lang3.mutable.MutableDouble;

public class KineticDamage {

    public static void apply(LivingEntity cause, Entity target) {
        var stack = cause.getMainHandItem();

        // ignores modifier operation, since only addition is used by oreganized this works, but may be adapted in the future
        var bonus = new MutableDouble(0.0);
        stack.forEachModifier(EquipmentSlot.MAINHAND, (attribute, modifier) -> {
            if (!attribute.is(OAttributes.KINETIC_DAMAGE.getKey())) return;
            bonus.add(modifier.amount());
        });

        if (bonus.getValue() <= 0.0) return;
        if (!(cause instanceof IMotionHolder motionHolder)) return;

        var motion = Math.sqrt(motionHolder.oreganised$getHorizontalMotion()) - 0.15;

        var factor = Math.min(motion / 0.12, 1F);
        if (factor <= 0.0) return;

        var kineticDamage = factor * bonus.getValue();
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
