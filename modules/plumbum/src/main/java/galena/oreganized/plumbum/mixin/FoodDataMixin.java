package galena.oreganized.plumbum.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import galena.oreganized.plumbum.index.PlumbumEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FoodData.class)
public class FoodDataMixin {

    @ModifyExpressionValue(
            method = "tick(Lnet/minecraft/world/entity/player/Player;)V",
            at = @At(value = "CONSTANT", args = "floatValue=6F", ordinal = 1)
    )
    private float modifyHealthAmount(float value, @Local Player player) {
        if (player.hasEffect(PlumbumEffects.STUNNING)) return value * 2;
        return value;
    }

    @ModifyExpressionValue(
            method = "tick(Lnet/minecraft/world/entity/player/Player;)V",
            at = @At(value = "CONSTANT", args = "floatValue=1F", ordinal = 1)
    )
    private float modifyHealthAmount2(float value, @Local Player player) {
        if (player.hasEffect(PlumbumEffects.STUNNING)) return value / 2;
        return value;
    }

}
