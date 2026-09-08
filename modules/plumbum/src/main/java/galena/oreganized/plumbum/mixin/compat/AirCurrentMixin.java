package galena.oreganized.plumbum.mixin.compat;

import com.simibubi.create.content.kinetics.fan.AirCurrent;
import galena.oreganized.plumbum.world.block.LeadOreBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = AirCurrent.class, remap = false)
public class AirCurrentMixin {

    @Inject(
            method = "tick()V",
            require = 0,
            at = @At("HEAD")
    )
    private void spawnLeadDustClouds(CallbackInfo ci) {
        var self = (AirCurrent) (Object) this;
        if (self.direction == null) return;
        var pos = self.source.getAirCurrentPos();
        var level = self.source.getAirCurrentWorld();
        var facing = self.pushing ? self.direction : self.direction.getOpposite();
        var maxDistance = (int) self.maxDistance + 2;
        var start = self.pushing ? pos : pos.relative(self.direction, maxDistance);
        LeadOreBlock.blowParticles(level, start, facing, maxDistance);
    }

}
