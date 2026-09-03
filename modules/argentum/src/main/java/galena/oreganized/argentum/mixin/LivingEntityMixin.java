package galena.oreganized.argentum.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import galena.oreganized.argentum.index.ArgentumAttributes;
import net.minecraft.world.entity.LivingEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @WrapOperation(
            method = {"hurt", "handleDamageEvent"},
            at = @At(value = "FIELD", opcode = Opcodes.PUTFIELD, target = "Lnet/minecraft/world/entity/LivingEntity;invulnerableTime:I")
    )
    private void adjustInvulnerabilityTime(LivingEntity instance, int value, Operation<Void> original) {
        var attribute = instance.getAttribute(ArgentumAttributes.INVINCIBILITY_FRAMES);
        if (attribute == null) {
            original.call(instance, value);
            return;
        }
        var seconds = attribute.getValue();
        var ticks = (int) (seconds * 20);
        original.call(instance, ticks);
    }

}
