package galena.oreganized.argentum.index;

import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import galena.oreganized.OConstants;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(OConstants.MOD_ID)
public class ArgentumSounds {

    private static final SoundSubRegistryHelper SOUNDS = OConstants.REGISTRY_HELPER.getSoundSubHelper();

    public static final DeferredHolder<SoundEvent, SoundEvent> TARNISH = SOUNDS.createSoundEvent("block.tarnish");
    public static final DeferredHolder<SoundEvent, SoundEvent> POLISH = SOUNDS.createSoundEvent("block.polish");
    public static final DeferredHolder<SoundEvent, SoundEvent> POLISH_FINISH = SOUNDS.createSoundEvent("block.polish_finish");

}
