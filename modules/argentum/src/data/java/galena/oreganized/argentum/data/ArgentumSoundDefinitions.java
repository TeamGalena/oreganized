package galena.oreganized.argentum.data;

import static net.neoforged.neoforge.common.data.SoundDefinition.Sound.sound;
import static net.neoforged.neoforge.common.data.SoundDefinition.definition;

import com.possible_triangle.multikulti.registrate.provider.RegistrateSoundsProvider;
import galena.oreganized.OConstants;
import galena.oreganized.argentum.index.ArgentumSounds;
import galena.oreganized.data.ODatagen;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.SoundDefinition.SoundType;

@Mod(OConstants.MOD_ID)
public class ArgentumSoundDefinitions {

    public ArgentumSoundDefinitions() {
        ODatagen.addSoundDefinitionProvider(this::generate);
    }

    private void generate(RegistrateSoundsProvider provider) {
        provider.add(ArgentumSounds.TARNISH.value(), definition().with(
                sound(OConstants.modLoc("block/tarnish_1"), SoundType.SOUND),
                sound(OConstants.modLoc("block/tarnish_2"), SoundType.SOUND),
                sound(OConstants.modLoc("block/tarnish_3"), SoundType.SOUND),
                sound(OConstants.modLoc("block/tarnish_4"), SoundType.SOUND),
                sound(OConstants.modLoc("block/tarnish_5"), SoundType.SOUND)
        ).subtitle("subtitles.block.tarnish"));

        provider.add(ArgentumSounds.POLISH.value(), definition().with(
                sound(OConstants.modLoc("block/polish_1"), SoundType.SOUND),
                sound(OConstants.modLoc("block/polish_2"), SoundType.SOUND),
                sound(OConstants.modLoc("block/polish_3"), SoundType.SOUND),
                sound(OConstants.modLoc("block/polish_4"), SoundType.SOUND)
        ).subtitle("subtitles.block.polish"));

        provider.add(ArgentumSounds.POLISH_FINISH.value(), definition().with(
                sound(OConstants.modLoc("block/polish_finish_1"), SoundType.SOUND),
                sound(OConstants.modLoc("block/polish_finish_2"), SoundType.SOUND),
                sound(OConstants.modLoc("block/polish_finish_3"), SoundType.SOUND),
                sound(OConstants.modLoc("block/polish_finish_4"), SoundType.SOUND),
                sound(OConstants.modLoc("block/polish_finish_5"), SoundType.SOUND)
        ));
    }

}
