package galena.oreganized.armament.mixin;

import galena.oreganized.armament.index.ArmamentItems;
import java.util.function.Predicate;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CrossbowItem.class)
public class CrossbowMixin {

    @Unique
    private static final Predicate<ItemStack> HELD_PREDICATE = ProjectileWeaponItem.ARROW_OR_FIREWORK.or(it -> it.is(ArmamentItems.LEAD_BOLT.get()));

    @Inject(cancellable = true, at = @At("HEAD"), method = "getSupportedHeldProjectiles()Ljava/util/function/Predicate;")
    public void injectLeadBolt(CallbackInfoReturnable<Predicate<ItemStack>> cir) {
        cir.setReturnValue(HELD_PREDICATE);
    }

}
