package galena.oreganized.network.packet;

import galena.oreganized.Oreganized;
import galena.oreganized.content.entity.GargoyleBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record GargoyleParticlePacket(BlockPos pos) implements CustomPacketPayload {

    public static final TypeAndCodec<FriendlyByteBuf, GargoyleParticlePacket> TYPE = new TypeAndCodec<>(
            new Type<>(Oreganized.modLoc("gargoyle_particles")),
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC, GargoyleParticlePacket::pos,
                    GargoyleParticlePacket::new
            )
    );

    public void handle(IPayloadContext context) {
        context.enqueueWork(() -> {
            var blockEntity = context.player().level().getBlockEntity(pos);

            if (blockEntity instanceof GargoyleBlockEntity gargoyle) {
                gargoyle.spawnParticles();
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE.type();
    }

}
