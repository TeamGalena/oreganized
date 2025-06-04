package galena.oreganized.network.packet;

import galena.oreganized.world.KineticDamage;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public record KineticHitPacket(int target, double factor) {

    public void write(FriendlyByteBuf buffer) {
        buffer.writeInt(target);
        buffer.writeDouble(factor);
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        var context = contextSupplier.get();
        context.enqueueWork(() -> {
            var level = Minecraft.getInstance().level;
            if (level == null) return;

            var target = level.getEntity(target());
            if (target == null) return;

            KineticDamage.spawnParticles(target, factor);
        });

        context.setPacketHandled(true);
    }

    public static KineticHitPacket from(FriendlyByteBuf buffer) {
        var target = buffer.readInt();
        var factor = buffer.readDouble();
        return new KineticHitPacket(target, factor);
    }

}
