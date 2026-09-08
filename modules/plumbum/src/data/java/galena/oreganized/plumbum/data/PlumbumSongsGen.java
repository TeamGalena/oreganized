package galena.oreganized.plumbum.data;

import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.plumbum.index.PlumbumSongs;
import galena.oreganized.plumbum.index.PlumbumSounds;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class PlumbumSongsGen {

    public PlumbumSongsGen() {
        ODatagen.addDataRegistryEntries(Registries.JUKEBOX_SONG, this::bootstrap);
    }

    private void bootstrap(BootstrapContext<JukeboxSong> context) {
        context.register(PlumbumSongs.STRUCTURE,  new JukeboxSong(PlumbumSounds.MUSIC_DISC_STRUCTURE, Component.translatable("item.oreganized.music_disc_structure.desc"), 2980 / 20, 13));
    }

}
