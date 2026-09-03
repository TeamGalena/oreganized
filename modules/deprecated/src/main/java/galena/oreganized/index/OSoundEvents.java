package galena.oreganized.index;

import galena.oreganized.argentum.index.ArgentumSounds;
import galena.oreganized.armament.index.ArmamentSounds;
import galena.oreganized.gothic.index.GothicSounds;
import galena.oreganized.plumbum.index.PlumbumSounds;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

public class OSoundEvents {

    public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_STRUCTURE = PlumbumSounds.MUSIC_DISC_STRUCTURE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<SoundEvent, SoundEvent> GARGOYLE_GROWL = GothicSounds.GARGOYLE_GROWL;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<SoundEvent, SoundEvent> SHRAPNEL_BOMB_PRIMED = ArmamentSounds.SHRAPNEL_BOMB_PRIMED;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<SoundEvent, SoundEvent> BOLT_HIT = ArmamentSounds.BOLT_HIT;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<SoundEvent, SoundEvent> BOLT_HIT_ARMOR = ArmamentSounds.BOLT_HIT_ARMOR;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<SoundEvent, SoundEvent> TARNISH = ArgentumSounds.TARNISH;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<SoundEvent, SoundEvent> POLISH = ArgentumSounds.POLISH;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<SoundEvent, SoundEvent> POLISH_FINISH = ArgentumSounds.POLISH_FINISH;

}
