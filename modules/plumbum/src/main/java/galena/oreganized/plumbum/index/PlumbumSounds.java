package galena.oreganized.plumbum.index;

import galena.oreganized.OConstants;
import galena.oreganized.register.SoundRegistryHelper;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(OConstants.MOD_ID)
public class PlumbumSounds {

    private static final SoundRegistryHelper SOUNDS = OConstants.REGISTRY_HELPER.getSoundSubHelper();

    public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_STRUCTURE = SOUNDS.createSoundEvent("music.disc.structure");

}
