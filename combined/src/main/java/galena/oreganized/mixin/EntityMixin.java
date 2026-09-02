package galena.oreganized.mixin;

import galena.oreganized.world.IMotionHolder;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin implements IMotionHolder {

    @Unique
    private double oreganized$motion = 0.0;
    @Unique
    private double oreganized$HorizontalMotion = 0.0;

    @Inject(
            method = "setOldPosAndRot",
            at = @At("HEAD")
    )
    private void updateMotion(CallbackInfo ci) {
        var self = (Entity) (Object) this;
        var deltaX = self.getX() - self.xOld;
        var deltaZ = self.getZ() - self.zOld;
        var deltaY = self.getY() - self.yOld;
        oreganized$HorizontalMotion = deltaX * deltaX + deltaZ * deltaZ;
        oreganized$motion = oreganized$HorizontalMotion + deltaY * deltaY;
    }

    @Override
    public double oreganised$getHorizontalMotion() {
        return oreganized$HorizontalMotion;
    }

    @Override
    public double oreganised$getMotion() {
        return oreganized$motion;
    }
}
