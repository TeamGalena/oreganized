package galena.oreganized.antiques.data;

import static net.neoforged.neoforge.common.data.SoundDefinition.Sound.sound;
import static net.neoforged.neoforge.common.data.SoundDefinition.definition;

import com.possible_triangle.multikulti.registrate.provider.RegistrateSoundsProvider;
import galena.oreganized.OConstants;
import galena.oreganized.antiques.index.AntiquesSongs;
import galena.oreganized.antiques.index.AntiquesSounds;
import galena.oreganized.data.ODatagen;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.SoundDefinition;

@Mod(OConstants.MOD_ID)
public class AntiquesSongsGen {

    public AntiquesSongsGen() {
        ODatagen.addDataRegistryEntries(Registries.JUKEBOX_SONG, this::songs);
        ODatagen.addSoundDefinitionProvider(this::definitions);
    }

    private void songs(BootstrapContext<JukeboxSong> context) {
        context.register(AntiquesSongs.RADIO,  new JukeboxSong(AntiquesSounds.MUSIC_DISC_RADIO, Component.translatable("item.oreganized.music_disc_radio.desc"), 210, 13));
    }

    private void definitions(RegistrateSoundsProvider provider) {
        provider.add(AntiquesSounds.MUSIC_DISC_RADIO.value(), definition().with(
                sound(OConstants.modLoc("music/disc/radio"), SoundDefinition.SoundType.SOUND)
                        .stream()
        ));
    }

}
