package galena.oreganized.index;

import galena.oreganized.plumbum.index.PlumbumSongs;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.JukeboxSong;

public class ORecords {

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final ResourceKey<JukeboxSong> STRUCTURE = PlumbumSongs.STRUCTURE;

    public static void bootstrap(BootstrapContext<JukeboxSong> context) {
        context.register(STRUCTURE,  new JukeboxSong(OSoundEvents.MUSIC_DISC_STRUCTURE, Component.translatable("item.oreganized.music_disc_structure.desc"), 2980 / 20, 13));
    }

}
