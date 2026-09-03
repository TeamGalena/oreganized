package galena.oreganized.network.packet;

import galena.oreganized.index.OParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record TarnishParticlePacket(BlockPos pos, Boolean tarnished) implements CustomPacketPayload {

    public static final TypeAndCodec<FriendlyByteBuf, TarnishParticlePacket> TYPE = new TypeAndCodec<>(
            new Type<>(OConstants.modLoc("tarnish_particles")),
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC, TarnishParticlePacket::pos,
                    ByteBufCodecs.BOOL, TarnishParticlePacket::tarnished,
                    TarnishParticlePacket::new
            )
    );

    public void handle(IPayloadContext context) {
        context.enqueueWork(() -> {
            var type = tarnished ? OParticleTypes.TARNISH : OParticleTypes.POLISH;
            var amount = tarnished ? UniformInt.of(4, 6) : UniformInt.of(4, 8);
            var level = context.player().level();
            ParticleUtils.spawnParticlesOnBlockFaces(level, pos, type.get(), amount);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE.type();
    }

}
