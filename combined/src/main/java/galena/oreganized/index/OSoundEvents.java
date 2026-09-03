package galena.oreganized.index;

import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import galena.oreganized.Oreganized;
import galena.oreganized.gothic.index.GothicSounds;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

public class OSoundEvents {
    private static final SoundSubRegistryHelper HELPER = Oreganized.REGISTRY_HELPER.getSoundSubHelper();

    public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_STRUCTURE = HELPER.createSoundEvent("music.disc.structure");

    public static final DeferredHolder<SoundEvent, SoundEvent> SHRAPNEL_BOMB_PRIMED = HELPER.createSoundEvent("entity.shrapnel_bomb.primed");

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<SoundEvent, SoundEvent> GARGOYLE_GROWL = GothicSounds.GARGOYLE_GROWL;

    public static final DeferredHolder<SoundEvent, SoundEvent> BOLT_HIT = HELPER.createSoundEvent("entity.bolt_hit");
    public static final DeferredHolder<SoundEvent, SoundEvent> BOLT_HIT_ARMOR = HELPER.createSoundEvent("entity.bolt_hit_armor");

    public static final DeferredHolder<SoundEvent, SoundEvent> TARNISH = HELPER.createSoundEvent("block.tarnish");
    public static final DeferredHolder<SoundEvent, SoundEvent> POLISH = HELPER.createSoundEvent("block.polish");
    public static final DeferredHolder<SoundEvent, SoundEvent> POLISH_FINISH = HELPER.createSoundEvent("block.polish_finish");

    public static void register() {
        // Load this class
    }

}
