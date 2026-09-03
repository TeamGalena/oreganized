package galena.oreganized.index;

import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import galena.oreganized.Oreganized;
import galena.oreganized.armament.index.ArmamentSounds;
import galena.oreganized.gothic.index.GothicSounds;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

public class OSoundEvents {
    private static final SoundSubRegistryHelper HELPER = Oreganized.REGISTRY_HELPER.getSoundSubHelper();

    public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_STRUCTURE = HELPER.createSoundEvent("music.disc.structure");

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<SoundEvent, SoundEvent> GARGOYLE_GROWL = GothicSounds.GARGOYLE_GROWL;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<SoundEvent, SoundEvent> SHRAPNEL_BOMB_PRIMED = ArmamentSounds.SHRAPNEL_BOMB_PRIMED;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<SoundEvent, SoundEvent> BOLT_HIT = ArmamentSounds.BOLT_HIT;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<SoundEvent, SoundEvent> BOLT_HIT_ARMOR = ArmamentSounds.BOLT_HIT_ARMOR;

    public static final DeferredHolder<SoundEvent, SoundEvent> TARNISH = HELPER.createSoundEvent("block.tarnish");
    public static final DeferredHolder<SoundEvent, SoundEvent> POLISH = HELPER.createSoundEvent("block.polish");
    public static final DeferredHolder<SoundEvent, SoundEvent> POLISH_FINISH = HELPER.createSoundEvent("block.polish_finish");

    public static void register() {
        // Load this class
    }

}
