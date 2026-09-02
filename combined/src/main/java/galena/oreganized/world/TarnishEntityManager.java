package galena.oreganized.world;

import galena.oreganized.index.OAttachmentTypes;
import galena.oreganized.index.OSoundEvents;
import galena.oreganized.index.OTags;
import galena.oreganized.network.packet.TarnishParticlePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.PacketDistributor;

public class TarnishEntityManager {

    public static boolean canTarnish(Entity entity) {
        return entity.getType().is(OTags.Entities.TARNISHABLE) && !entity.getData(OAttachmentTypes.TARNISHED);
    }

    public static boolean canPolish(Entity entity) {
        return entity.getData(OAttachmentTypes.TARNISHED);
    }

    public static boolean tryTarnish(Entity entity) {
        if (!canTarnish(entity)) return false;

        entity.setData(OAttachmentTypes.TARNISHED, true);

        if (entity.level() instanceof ServerLevel serverLevel) {
            serverLevel.playSound(null, entity, OSoundEvents.TARNISH.get(), SoundSource.BLOCKS, 1F, 1F);
            PacketDistributor.sendToPlayersInDimension(serverLevel, new TarnishParticlePacket(entity.blockPosition(), true));
        }

        return true;
    }

    public static boolean tryPolishing(Entity entity) {
        if (!canPolish(entity)) return false;

        entity.setData(OAttachmentTypes.TARNISHED, false);

        if (entity.level() instanceof ServerLevel serverLevel) {
            serverLevel.playSound(null, entity, OSoundEvents.POLISH_FINISH.get(), SoundSource.BLOCKS, 1F, 1F);
            PacketDistributor.sendToPlayersInDimension(serverLevel, new TarnishParticlePacket(entity.blockPosition(), false));
        }

        return true;
    }

}
