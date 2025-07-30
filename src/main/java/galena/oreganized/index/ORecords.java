package galena.oreganized.index;

import galena.oreganized.Oreganized;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = Oreganized.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ORecords {

    private static final DeferredRegister<JukeboxSong> JUKEBOX_SONGS = DeferredRegister.create(Registries.JUKEBOX_SONG, Oreganized.MOD_ID);

    public static final DeferredHolder<JukeboxSong, JukeboxSong> STRUCTURE = JUKEBOX_SONGS.register("structure", () -> new JukeboxSong(OSoundEvents.MUSIC_DISC_STRUCTURE, Component.translateable(), 2980 / 20, 13));

}
