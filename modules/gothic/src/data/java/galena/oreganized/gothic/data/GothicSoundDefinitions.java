package galena.oreganized.gothic.data;

import static galena.oreganized.data.extensions.OSoundExtensions.blockSound;
import static net.neoforged.neoforge.common.data.SoundDefinition.Sound.sound;
import static net.neoforged.neoforge.common.data.SoundDefinition.definition;

import com.possible_triangle.multikulti.registrate.provider.RegistrateSoundsProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.gothic.index.GothicSounds;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.SoundDefinition.SoundType;

@Mod(OConstants.MOD_ID)
public class GothicSoundDefinitions {

    public GothicSoundDefinitions() {
        ODatagen.addSoundDefinitionProvider(this::generate);
    }

    private void generate(RegistrateSoundsProvider provider) {
        provider.add(GothicSounds.GARGOYLE_GROWL.value(), definition().with(
                sound(OConstants.modLoc("block/gargoyle_growl_1"), SoundType.SOUND),
                sound(OConstants.modLoc("block/gargoyle_growl_2"), SoundType.SOUND),
                sound(OConstants.modLoc("block/gargoyle_growl_3"), SoundType.SOUND)
        ).subtitle("subtitles.block.gargoyle.growl"));

        blockSound(provider, GothicSounds.GRIMSTONE.getBreakSound(), OConstants.modLoc("grimstone_dig"), 4);
        blockSound(provider, GothicSounds.GRIMSTONE.getHitSound(), OConstants.modLoc("grimstone_dig"), 4);
        blockSound(provider, GothicSounds.GRIMSTONE.getStepSound(), OConstants.modLoc("grimstone_step"), 4);
        blockSound(provider, GothicSounds.GRIMSTONE.getFallSound(), OConstants.modLoc("grimstone_step"), 4);
        blockSound(provider, GothicSounds.GRIMSTONE.getPlaceSound(), OConstants.modLoc("grimstone_place"), 4);
    }

}
