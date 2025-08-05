package galena.oreganized.network;

import galena.oreganized.network.packet.DoorPushingPacket;
import galena.oreganized.network.packet.GargoyleParticlePacket;
import galena.oreganized.network.packet.KineticHitPacket;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public class OreganizedNetwork {
    private static final String PROTOCOL_VERSION = "2";

    public static void register(RegisterPayloadHandlersEvent event) {
        var registrar = event.registrar(PROTOCOL_VERSION);
        registrar.playToClient(GargoyleParticlePacket.TYPE.type(), GargoyleParticlePacket.TYPE.codec(), GargoyleParticlePacket::handle);
        registrar.playToClient(DoorPushingPacket.TYPE.type(), DoorPushingPacket.TYPE.codec(), DoorPushingPacket::handle);
        registrar.playToClient(KineticHitPacket.TYPE.type(), KineticHitPacket.TYPE.codec(), KineticHitPacket::handle);
    }
}
