package galena.oreganized.mixin;

import galena.oreganized.network.packet.DoorPushingPacket;
import galena.oreganized.world.IDoorProgressHolder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerMixin implements IDoorProgressHolder {

    @Unique
    private int oreganised$leadDoorOpeningProgress = 0;

    @Unique
    private long oreganised$lastPress = 0;

    @Unique
    @Override
    public int oreganised$getOpeningProgress() {
        return oreganised$leadDoorOpeningProgress;
    }

    @Unique
    private void oreganized$syncProgress(boolean pushing) {
        var self = (Player) (Object) this;

        if (self instanceof ServerPlayer player) {
            var packet = new DoorPushingPacket(self.getUUID(), pushing);
            PacketDistributor.sendToPlayersInDimension(player.serverLevel(), packet);
        }
    }

    @Unique
    @Override
    public int oreganised$incrementOpeningProgress() {
        var self = (Player) (Object) this;
        oreganised$lastPress = self.level().getGameTime();
        oreganized$syncProgress(true);
        return ++oreganised$leadDoorOpeningProgress;
    }

    @Unique
    @Override
    public void oreganised$resetOpeningProgress() {
        oreganised$leadDoorOpeningProgress = 0;
        oreganized$syncProgress(false);
    }

    @Inject(
            method = "tick",
            at = @At("HEAD")
    )
    private void oreganised$onTick(CallbackInfo ci) {
        if (oreganised$leadDoorOpeningProgress == 0) return;
        var self = (Player) (Object) this;
        if (self.level() == null || self.level().isClientSide) return;
        var time = self.level().getGameTime();
        if (time - oreganised$lastPress > 6) oreganised$resetOpeningProgress();
    }

}
