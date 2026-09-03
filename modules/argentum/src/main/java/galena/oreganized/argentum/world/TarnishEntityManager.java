package galena.oreganized.argentum.world;

import galena.oreganized.argentum.index.ArgentumAttachmentTypes;
import galena.oreganized.argentum.index.ArgentumSounds;
import galena.oreganized.argentum.network.TarnishParticlePacket;
import galena.oreganized.index.OTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.PacketDistributor;

public class TarnishEntityManager {

    public static boolean canTarnish(Entity entity) {
        return entity.getType().is(OTags.Entities.TARNISHABLE) && !entity.getData(ArgentumAttachmentTypes.TARNISHED);
    }

    public static boolean canPolish(Entity entity) {
        return entity.getData(ArgentumAttachmentTypes.TARNISHED);
    }

    public static boolean tryTarnish(Entity entity) {
        if (!canTarnish(entity)) return false;

        entity.setData(ArgentumAttachmentTypes.TARNISHED, true);

        if (entity.level() instanceof ServerLevel serverLevel) {
            serverLevel.playSound(null, entity, ArgentumSounds.TARNISH.get(), SoundSource.BLOCKS, 1F, 1F);
            PacketDistributor.sendToPlayersInDimension(serverLevel, new TarnishParticlePacket(entity.blockPosition(), true));
        }

        return true;
    }

    public static boolean tryPolishing(Entity entity) {
        if (!canPolish(entity)) return false;

        entity.setData(ArgentumAttachmentTypes.TARNISHED, false);

        if (entity.level() instanceof ServerLevel serverLevel) {
            serverLevel.playSound(null, entity, ArgentumSounds.POLISH_FINISH.get(), SoundSource.BLOCKS, 1F, 1F);
            PacketDistributor.sendToPlayersInDimension(serverLevel, new TarnishParticlePacket(entity.blockPosition(), false));
        }

        return true;
    }

}
