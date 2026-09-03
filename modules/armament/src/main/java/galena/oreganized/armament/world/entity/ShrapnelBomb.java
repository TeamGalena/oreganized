package galena.oreganized.armament.world.entity;

import galena.oreganized.api.LeadProtections;
import galena.oreganized.armament.index.ArmamentEntities;
import galena.oreganized.armament.index.ArmamentParticles;
import galena.oreganized.plumbum.config.PlumbumConfigs;
import galena.oreganized.plumbum.index.PlumbumDamageTypes;
import galena.oreganized.plumbum.index.PlumbumEffects;
import javax.annotation.Nullable;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class ShrapnelBomb extends PrimedTnt {

    public ShrapnelBomb(EntityType<? extends PrimedTnt> entity, Level world) {
        super(entity, world);
    }

    public ShrapnelBomb(Level world, double x, double y, double z, @Nullable LivingEntity igniterEntity) {
        this(ArmamentEntities.SHRAPNEL_BOMB.get(), world);
        this.setPos(x, y, z);
        double delta = world.random.nextDouble() * (double) ((float) Math.PI * 2F);
        this.setDeltaMovement(-Math.sin(delta) * 0.02D, 0.2F, -Math.cos(delta) * 0.02D);
        this.setFuse(80);
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.owner = igniterEntity;
    }

    protected void explode() {
        this.level().explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 4.0F, Level.ExplosionInteraction.NONE);
        if (!this.level().isClientSide()) ((ServerLevel) this.level()).sendParticles(ArmamentParticles.LEAD_SHRAPNEL.get(),
                this.getX(), this.getY(0.0625D), this.getZ(), 100, 0.0D, 0.0D, 0.0D, 5);
        for (Entity entity : this.level().getEntities(this, new AABB(this.getX() - 30, this.getY() - 4, this.getZ() - 30,
                this.getX() + 30, this.getY() + 4, this.getZ() + 30))) {
            int random = (int) (Math.random() * 100);
            boolean shouldPoison = false;
            if (entity.distanceToSqr(this) <= 16) {
                shouldPoison = true;
            } else if (entity.distanceToSqr(this) <= 64) {
                if (random < 60) shouldPoison = true;
            } else if (entity.distanceToSqr(this) <= 225) {
                if (random < 30) shouldPoison = true;
            } else if (entity.distanceToSqr(this) <= 900) {
                if (random < 5) shouldPoison = true;
            }
            if (shouldPoison && entity instanceof LivingEntity living) {
                living.hurt(this.damageSources().source(PlumbumDamageTypes.LEAD_POISONING), 2);

                if (LeadProtections.isNotProtected(living)) {
                    living.addEffect(new MobEffectInstance(MobEffects.POISON, 260));
                    if (!PlumbumConfigs.COMMON.poisonInsteadOfStunning.get()) {
                        living.addEffect(new MobEffectInstance(PlumbumEffects.STUNNING, 800));
                    }
                }
            }
        }
    }
}
