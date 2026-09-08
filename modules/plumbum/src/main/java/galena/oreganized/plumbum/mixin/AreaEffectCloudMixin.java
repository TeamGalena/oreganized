package galena.oreganized.plumbum.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import galena.oreganized.OConstants;
import galena.oreganized.api.LeadProtections;
import galena.oreganized.plumbum.accessor.PreventableEffectCloud;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AreaEffectCloud.class)
public class AreaEffectCloudMixin implements PreventableEffectCloud {

    @Unique
    private static final String oreganized$KEY = OConstants.modLoc("preventable").toString();

    @ModifyExpressionValue(
            method = "tick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isAffectedByPotions()Z")
    )
    public boolean testProtection(boolean original, @Local LivingEntity target) {
        if (!oreganized$isPreventable()) return original;
        return original && LeadProtections.isNotProtected(target);
    }

    @Override
    public void oreganized$setPreventable(boolean value) {
        var self = (AreaEffectCloud) (Object) this;
        self.getPersistentData().putBoolean(oreganized$KEY, value);
    }

    @Override
    public boolean oreganized$isPreventable() {
        var self = (AreaEffectCloud) (Object) this;
        return self.getPersistentData().getBoolean(oreganized$KEY);
    }
}
