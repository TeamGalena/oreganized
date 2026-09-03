package galena.oreganized.plumbum.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import galena.oreganized.plumbum.index.PlumbumEffects;
import galena.oreganized.plumbum.world.effect.StunningEffect;
import net.minecraft.world.effect.MobEffectInstance;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MobEffectInstance.class)
public class MobEffectInstanceMixin {

    @ModifyExpressionValue(
            method = "update(Lnet/minecraft/world/effect/MobEffectInstance;)Z",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/effect/MobEffectInstance;amplifier:I",
                    ordinal = 0,
                    opcode = Opcodes.GETFIELD
            )
    )
    private int replaceStunning(int original) {
        var self = (MobEffectInstance) (Object) this;
        if (PlumbumEffects.STUNNING.getKey().equals(self.getEffect().getKey())) return StunningEffect.MAX_AMPLIFIER + 1;
        return original;
    }

}
