package galena.oreganized.network.packet;

import galena.oreganized.Oreganized;
import galena.oreganized.world.IDoorProgressHolder;
import java.util.UUID;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record DoorPushingPacket(UUID player, boolean pushing) implements CustomPacketPayload {

    public static final TypeAndCodec<FriendlyByteBuf, DoorPushingPacket> TYPE = new TypeAndCodec<>(
            new Type<>(Oreganized.modLoc("door_pushing")),
            StreamCodec.composite(
                    UUIDUtil.STREAM_CODEC, DoorPushingPacket::player,
                    ByteBufCodecs.BOOL, DoorPushingPacket::pushing,
                    DoorPushingPacket::new
            )
    );

    public void handle(IPayloadContext context) {
        context.enqueueWork(() -> {
            var player = context.player().level().getPlayerByUUID(player());
            if (player instanceof IDoorProgressHolder progressHolder) {
                if (pushing) progressHolder.oreganised$incrementOpeningProgress();
                else progressHolder.oreganised$resetOpeningProgress();
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE.type();
    }

}
