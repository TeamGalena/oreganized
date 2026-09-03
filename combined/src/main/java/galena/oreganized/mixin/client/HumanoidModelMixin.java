package galena.oreganized.mixin.client;

import galena.oreganized.client.OreganizedClient;
import galena.oreganized.plumbum.world.block.PushableBlockEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidModel.class)
public class HumanoidModelMixin<T extends LivingEntity> {

    @Inject(method = "poseRightArm", at = @At(value = "HEAD"), cancellable = true)
    public void poseRightArm(T entity, CallbackInfo ci) {
        if (!PushableBlockEntity.isPushing(entity)) return;

        var model = (HumanoidModel<T>) (Object) this;
        OreganizedClient.renderThirdPersonArm(model.rightArm, true);

        ci.cancel();
    }

    @Inject(method = "poseLeftArm", at = @At(value = "HEAD"), cancellable = true)
    public void poseLeftArm(T entity, CallbackInfo ci) {
        if (!PushableBlockEntity.isPushing(entity)) return;

        var model = (HumanoidModel<T>) (Object) this;
        OreganizedClient.renderThirdPersonArm(model.leftArm, false);

        ci.cancel();
    }

}
