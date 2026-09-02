package galena.oreganized.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import galena.oreganized.content.item.ScribeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(IceBlock.class)
public class IceBlockMixin {

    @ModifyExpressionValue(
            method = "playerDestroy(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/item/ItemStack;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;hasTag(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/tags/TagKey;)Z")
    )
    private boolean modifyLootBuilder(boolean original, @Local ItemStack stack, @Local BlockState state) {
        if (stack.getItem() instanceof ScribeItem scribe && scribe.isCorrectToolForDrops(stack, state)) {
            return true;
        }
        return original;
    }

}
