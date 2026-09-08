package galena.oreganized.gothic.mixin;

import galena.oreganized.gothic.world.block.GargoyleBlock;
import galena.oreganized.index.OTags;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DispenserBlock.class)
public class DispenserBlockMixin {

    @Inject(
            method = "getDispenseMethod(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/core/dispenser/DispenseItemBehavior;",
            at = @At("HEAD"),
            cancellable = true
    )
    private void injectGargoyleBehaviour(Level level, ItemStack stack, CallbackInfoReturnable<DispenseItemBehavior> cir) {
        if (stack.is(OTags.Items.GARGOYLE_SNACK)) cir.setReturnValue(GargoyleBlock.DISPENSE_ITEM_BEHAVIOR);
    }

}
