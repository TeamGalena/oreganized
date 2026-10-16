package galena.oreganized.antiques.index;

import galena.oreganized.antiques.AntiquesModule;
import galena.oreganized.register.SoundRegistryHelper;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(AntiquesModule.MOD_ID)
public class AntiquesSounds {

    private static final SoundRegistryHelper SOUNDS = AntiquesModule.REGISTRY_HELPER.getSoundSubHelper();

    public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_RADIO = SOUNDS.createSoundEvent("music.disc.radio");

}
