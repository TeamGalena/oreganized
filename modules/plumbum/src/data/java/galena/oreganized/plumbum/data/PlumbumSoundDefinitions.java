package galena.oreganized.plumbum.data;

import static net.neoforged.neoforge.common.data.SoundDefinition.Sound.sound;
import static net.neoforged.neoforge.common.data.SoundDefinition.definition;

import com.possible_triangle.multikulti.registrate.provider.RegistrateSoundsProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.plumbum.index.PlumbumSounds;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.SoundDefinition.SoundType;

@Mod(OConstants.MOD_ID)
public class PlumbumSoundDefinitions {

    public PlumbumSoundDefinitions() {
        ODatagen.addSoundDefinitionProvider(this::generate);
    }

    private void generate(RegistrateSoundsProvider provider) {
        provider.add(PlumbumSounds.MUSIC_DISC_STRUCTURE.value(), definition().with(
                sound(OConstants.modLoc("music/disc/structure"), SoundType.SOUND)
                        .stream()
        ));
    }

}
