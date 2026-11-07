package galena.oreganized.gothic.compat;

import galena.oreganized.argentum.index.ArgentumItems;
import galena.oreganized.argentum.index.ArgentumSounds;
import galena.oreganized.argentum.network.TarnishParticlePacket;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.PacketDistributor;

/**
 * this class encapsulated access to classes of the argentum module
 * any call to a method in here should only happen if checked beforehand that the module is loaded
 */
public class ArgentumCompat {

    public static ItemStack getSilverIngot() {
        return ArgentumItems.SILVER_INGOT.toStack();
    }

    public static void sendTarnishParticles(ServerLevel level, BlockPos pos) {
        PacketDistributor.sendToPlayersInDimension(level, new TarnishParticlePacket(pos, true));
    }

    public static void playTarnishSound(ServerLevel level, BlockPos pos) {
        level.playSound(null, pos, ArgentumSounds.TARNISH.get(), SoundSource.BLOCKS);
    }
}
