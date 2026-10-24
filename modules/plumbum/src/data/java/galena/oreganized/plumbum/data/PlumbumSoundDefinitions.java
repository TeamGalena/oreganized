package galena.oreganized.plumbum.data;

import static galena.oreganized.data.extensions.OSoundExtensions.withVariants;
import static net.neoforged.neoforge.common.data.SoundDefinition.Sound.sound;
import static net.neoforged.neoforge.common.data.SoundDefinition.definition;

import com.possible_triangle.multikulti.registrate.provider.RegistrateSoundsProvider;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.plumbum.PlumbumModule;
import galena.oreganized.plumbum.index.PlumbumSounds;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.SoundDefinition.SoundType;

@Mod(PlumbumModule.MOD_ID)
public class PlumbumSoundDefinitions {

    public PlumbumSoundDefinitions() {
        ODatagen.addSoundDefinitionProvider(this::generate);
    }

    private void generate(RegistrateSoundsProvider provider) {
        provider.add(PlumbumSounds.MUSIC_DISC_STRUCTURE.value(), definition().with(
                sound(OConstants.modLoc("music/disc/structure"), SoundType.SOUND)
                        .stream()
        ));

        provider.add(PlumbumSounds.MOLTEN_LEAD_BUCKET_EMPTY.value(),
                withVariants(definition(), OConstants.modLoc("item/molten_lead_bucket_empty"), 3)
                        .subtitle("subtitles.item.bucket.empty")
        );

        provider.add(PlumbumSounds.MOLTEN_LEAD_BUCKET_FILL.value(),
                withVariants(definition(), OConstants.modLoc("item/molten_lead_bucket_fill"), 3)
                        .subtitle("subtitles.item.bucket.fill")
        );

        provider.add(PlumbumSounds.MOLTEN_LEAD_AMBIENT.value(),
                withVariants(definition(), OConstants.modLoc("block/molten_lead_flow"), 3)
                        .subtitle("subtitles.block.molten_lead.ambient")
        );

        provider.add(PlumbumSounds.MOLTEN_LEAD_EXTINGUISH.value(),
                withVariants(definition(), OConstants.modLoc("block/molten_lead_fizzle"), 3)
                        .subtitle("subtitles.block.molten_lead.extinguish")
        );
    }

}
