package galena.oreganized.plumbum.index;

import galena.oreganized.plumbum.PlumbumModule;
import galena.oreganized.register.SoundRegistryHelper;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(PlumbumModule.MOD_ID)
public class PlumbumSounds {

    private static final SoundRegistryHelper SOUNDS = PlumbumModule.REGISTRY_HELPER.getSoundSubHelper();

    public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_STRUCTURE = SOUNDS.createSoundEvent("music.disc.structure");

}
