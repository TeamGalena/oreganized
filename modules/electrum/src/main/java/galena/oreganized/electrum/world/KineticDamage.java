package galena.oreganized.electrum.world;

import galena.oreganized.OConstants;
import galena.oreganized.electrum.accessor.IMotionHolder;
import galena.oreganized.electrum.index.ElectrumAttributes;
import galena.oreganized.electrum.index.ElectrumParticles;
import galena.oreganized.electrum.network.KineticHitPacket;
import galena.oreganized.index.OTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber
public class KineticDamage {

    public static void apply(LivingEntity cause, Entity target) {
        if (!(cause instanceof IMotionHolder motionHolder)) return;
        var motion = Math.sqrt(motionHolder.oreganised$getHorizontalMotion()) - 0.15;

        var factor = Math.min(motion / 0.12, 1F);
        if (factor <= 0.0) return;

        if (!cause.getAttributes().hasAttribute(ElectrumAttributes.KINETIC_DAMAGE)) return;
        var kineticDamage = factor * cause.getAttributeValue(ElectrumAttributes.KINETIC_DAMAGE);
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
                    ElectrumParticles.KINETIC_HIT.get(),
                    target.getRandomX(0.75), target.getRandomY(), target.getRandomZ(0.75),
                    level.random.nextGaussian() * 0.02D, level.random.nextGaussian() * 0.02D, level.random.nextGaussian() * 0.02D
            );
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onItemAttributes(ItemAttributeModifierEvent event) {
        var stack = event.getItemStack();

        if (stack.is(OTags.Items.HAS_KINETIC_DAMAGE)) {
            var damage = event.getModifiers().stream()
                    .filter(it -> it.matches(Attributes.ATTACK_DAMAGE, Item.BASE_ATTACK_DAMAGE_ID))
                    .map(it -> it.modifier().amount())
                    .findFirst()
                    .orElse(2.0);

            event.addModifier(
                    ElectrumAttributes.KINETIC_DAMAGE,
                    new AttributeModifier(OConstants.modLoc("kinetic_damage"), damage / 3, AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.MAINHAND
            );
        }
    }

}
