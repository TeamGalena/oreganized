package galena.oreganized.index;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.JukeboxSong;

public class ORecords {

    public static final ResourceKey<JukeboxSong> STRUCTURE = ResourceKey.create(Registries.JUKEBOX_SONG, OConstants.modLoc("structure"));

    public static void bootstrap(BootstrapContext<JukeboxSong> context) {
        context.register(STRUCTURE,  new JukeboxSong(OSoundEvents.MUSIC_DISC_STRUCTURE, Component.translatable("item.oreganized.music_disc_structure.desc"), 2980 / 20, 13));
    }

}
