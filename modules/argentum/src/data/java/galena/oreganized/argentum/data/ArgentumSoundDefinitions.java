package galena.oreganized.argentum.data;

import static galena.oreganized.data.extensions.OSoundExtensions.*;
import static galena.oreganized.data.extensions.OSoundExtensions.blockSound;
import static net.neoforged.neoforge.common.data.SoundDefinition.definition;

import com.possible_triangle.multikulti.registrate.provider.RegistrateSoundsProvider;
import galena.oreganized.OConstants;
import galena.oreganized.argentum.index.ArgentumSounds;
import galena.oreganized.data.ODatagen;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class ArgentumSoundDefinitions {

    public ArgentumSoundDefinitions() {
        ODatagen.addSoundDefinitionProvider(this::generate);
    }

    private void generate(RegistrateSoundsProvider provider) {
        provider.add(ArgentumSounds.TARNISH.value(), withVariants(
                definition().subtitle("subtitles.block.tarnish"),
                OConstants.modLoc("block/tarnish"), 5)
        );

        provider.add(ArgentumSounds.POLISH.value(), withVariants(
                definition().subtitle("subtitles.block.polish"),
                OConstants.modLoc("block/polish"), 4)
        );

        provider.add(ArgentumSounds.POLISH_FINISH.value(), withVariants(
                definition(),
                OConstants.modLoc("block/polish_finish"), 5)
        );

        blockSound(provider, ArgentumSounds.SILVER.getBreakSound(), OConstants.modLoc("silver_dig"), 4);
        blockSound(provider, ArgentumSounds.SILVER.getHitSound(), OConstants.modLoc("silver_dig"), 4);
        blockSound(provider, ArgentumSounds.SILVER.getStepSound(), OConstants.modLoc("silver_step"), 4);
        blockSound(provider, ArgentumSounds.SILVER.getFallSound(), OConstants.modLoc("silver_step"), 4);
        blockSound(provider, ArgentumSounds.SILVER.getPlaceSound(), OConstants.modLoc("silver_dig"), 4);
    }

}
