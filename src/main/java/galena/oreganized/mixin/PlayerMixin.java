package galena.oreganized.mixin;

import galena.oreganized.index.OItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin {

    @Inject(at = @At("HEAD"), method = "isModelPartShown(Lnet/minecraft/world/entity/player/PlayerModelPart;)Z", cancellable = true)
    public void onEquip(PlayerModelPart part, CallbackInfoReturnable<Boolean> cir) {
        if (part != PlayerModelPart.HAT) return;

        @SuppressWarnings("DataFlowIssue")
        var self = (Player) (Object) this;

        var helmet = self.getItemBySlot(EquipmentSlot.HEAD);
        if (helmet.is(OItems.SILVER_HELMET)) {
            cir.setReturnValue(false);
        }
    }

}
