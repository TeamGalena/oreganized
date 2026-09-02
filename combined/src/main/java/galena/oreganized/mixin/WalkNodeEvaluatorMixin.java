package galena.oreganized.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import galena.oreganized.argentum.world.block.SilverDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WalkNodeEvaluator.class)
public class WalkNodeEvaluatorMixin {
    @ModifyExpressionValue(
            method = "getPathTypeFromState",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/properties/BlockSetType;canOpenByHand()Z"
            )
    )
    private static boolean canOpenByHand(boolean original, @Local(name = "blockstate") BlockState blockState) {
        return original || blockState.getBlock() instanceof SilverDoorBlock && blockState.getValue(SilverDoorBlock.POWERED);
    }
}
