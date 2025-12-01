package galena.oreganized.network;

import galena.oreganized.network.packet.DoorPushingPacket;
import galena.oreganized.network.packet.KineticHitPacket;
import galena.oreganized.network.packet.TarnishParticlePacket;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public class OreganizedNetwork {
    private static final String PROTOCOL_VERSION = "3";

    public static void register(RegisterPayloadHandlersEvent event) {
        var registrar = event.registrar(PROTOCOL_VERSION);
        registrar.playToClient(TarnishParticlePacket.TYPE.type(), TarnishParticlePacket.TYPE.codec(), TarnishParticlePacket::handle);
        registrar.playToClient(DoorPushingPacket.TYPE.type(), DoorPushingPacket.TYPE.codec(), DoorPushingPacket::handle);
        registrar.playToClient(KineticHitPacket.TYPE.type(), KineticHitPacket.TYPE.codec(), KineticHitPacket::handle);
    }
}
