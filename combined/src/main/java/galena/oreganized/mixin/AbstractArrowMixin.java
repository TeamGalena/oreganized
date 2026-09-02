package galena.oreganized.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import galena.oreganized.content.entity.LeadBoltEntity;
import galena.oreganized.index.ODamageSources;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractArrow.class)
public class AbstractArrowMixin {

    @ModifyExpressionValue(
            method = "onHitEntity(Lnet/minecraft/world/phys/EntityHitResult;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/damagesource/DamageSources;arrow(Lnet/minecraft/world/entity/projectile/AbstractArrow;Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/world/damagesource/DamageSource;")
    )
    public DamageSource injectLeadBolt(DamageSource original, @Local(ordinal = 1) Entity user) {
        var self = (AbstractArrow) (Object) this;
        if (self instanceof LeadBoltEntity) {
            return self.damageSources().source(ODamageSources.LEAD_BOLT, self, user);
        }

        return original;
    }

}
