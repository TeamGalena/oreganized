package galena.oreganized.armament.data;

import static net.minecraft.resources.ResourceLocation.withDefaultNamespace;
import static net.neoforged.neoforge.common.data.SoundDefinition.Sound.sound;
import static net.neoforged.neoforge.common.data.SoundDefinition.definition;

import com.possible_triangle.multikulti.registrate.provider.RegistrateSoundsProvider;
import galena.oreganized.OConstants;
import galena.oreganized.armament.index.ArmamentSounds;
import galena.oreganized.data.ODatagen;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.SoundDefinition.SoundType;

@Mod(OConstants.MOD_ID)
public class ArmamentSoundDefinitions {

    public ArmamentSoundDefinitions() {
        ODatagen.addSoundDefinitionProvider(this::generate);
    }

    private void generate(RegistrateSoundsProvider provider) {
        provider.add(ArmamentSounds.SHRAPNEL_BOMB_PRIMED.value(), definition().with(
                sound(withDefaultNamespace("random/fuse"), SoundType.SOUND)
        ).subtitle("subtitles.entity.shrapnel_bomb.primed"));

        provider.add(ArmamentSounds.BOLT_HIT.value(), definition().with(
                sound(OConstants.modLoc("entity/bolt_hit"), SoundType.SOUND)
        ).subtitle("subtitles.entity.bolt_hit"));

        provider.add(ArmamentSounds.BOLT_HIT_ARMOR.value(), definition().with(
                sound(OConstants.modLoc("entity/bolt_hit_armor"), SoundType.SOUND)
        ).subtitle("subtitles.entity.bolt_hit_armor"));
    }

}
