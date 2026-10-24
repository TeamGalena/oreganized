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

    public static final DeferredHolder<SoundEvent, SoundEvent> MOLTEN_LEAD_BUCKET_FILL = SOUNDS.createSoundEvent("item.molten_lead_bucket.fill");
    public static final DeferredHolder<SoundEvent, SoundEvent> MOLTEN_LEAD_BUCKET_EMPTY = SOUNDS.createSoundEvent("item.molten_lead_bucket.empty");

    public static final DeferredHolder<SoundEvent, SoundEvent> MOLTEN_LEAD_AMBIENT = SOUNDS.createSoundEvent("block.molten_lead.ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> MOLTEN_LEAD_EXTINGUISH = SOUNDS.createSoundEvent("block.molten_lead.extinguish");

}
