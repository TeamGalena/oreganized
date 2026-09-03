package galena.oreganized.plumbum.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import galena.oreganized.index.OTags;
import galena.oreganized.plumbum.world.block.MoltenLeadBlock;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @ModifyReturnValue(
            method = "canStandOnFluid(Lnet/minecraft/world/level/material/FluidState;)Z",
            at = @At("RETURN")
    )
    private boolean canStandOnLead(boolean original, @Local FluidState fluid) {
        var self = (LivingEntity) (Object) this;
        if (original) return original;
        if (!fluid.is(OTags.Fluids.MOLTEN_LEAD)) return original;
        return MoltenLeadBlock.isEntityLighterThanLead(self);
    }

}
