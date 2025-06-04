package galena.oreganized.network.packet;

import galena.oreganized.world.IDoorProgressHolder;
import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record DoorPushingPacket(UUID player, boolean pushing) {

    public void write(FriendlyByteBuf buffer) {
        buffer.writeUUID(player);
        buffer.writeBoolean(pushing);
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        var context = contextSupplier.get();
        context.enqueueWork(() -> {
            var level = Minecraft.getInstance().level;
            if (level == null) return;

            var player = level.getPlayerByUUID(player());
            if (player instanceof IDoorProgressHolder progressHolder) {
                if (pushing) progressHolder.oreganised$incrementOpeningProgress();
                else progressHolder.oreganised$resetOpeningProgress();
            }
        });

        context.setPacketHandled(true);
    }

    public static DoorPushingPacket from(FriendlyByteBuf buffer) {
        var player = buffer.readUUID();
        var pushing = buffer.readBoolean();
        return new DoorPushingPacket(player, pushing);
    }

}
