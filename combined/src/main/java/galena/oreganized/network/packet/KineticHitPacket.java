package galena.oreganized.network.packet;

import galena.oreganized.world.KineticDamage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record KineticHitPacket(int target, float factor) implements CustomPacketPayload {

    public static final TypeAndCodec<FriendlyByteBuf, KineticHitPacket> TYPE = new TypeAndCodec<>(
            new Type<>(OConstants.modLoc("kinetic_hit")),
            StreamCodec.composite(
                    ByteBufCodecs.INT, KineticHitPacket::target,
                    ByteBufCodecs.FLOAT, KineticHitPacket::factor,
                    KineticHitPacket::new
            )
    );

    public void handle(IPayloadContext context) {
        context.enqueueWork(() -> {
            var target = context.player().level().getEntity(target());
            if (target == null) return;

            KineticDamage.spawnParticles(target, factor);
        });
    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE.type();
    }

}
