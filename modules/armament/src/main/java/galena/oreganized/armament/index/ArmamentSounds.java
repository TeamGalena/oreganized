package galena.oreganized.armament.index;

import galena.oreganized.OConstants;
import galena.oreganized.register.SoundRegistryHelper;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(OConstants.MOD_ID)
public class ArmamentSounds {

    private static final SoundRegistryHelper SOUNDS = OConstants.REGISTRY_HELPER.getSoundSubHelper();

    public static final DeferredHolder<SoundEvent, SoundEvent> SHRAPNEL_BOMB_PRIMED = SOUNDS.createSoundEvent("entity.shrapnel_bomb.primed");

    public static final DeferredHolder<SoundEvent, SoundEvent> BOLT_HIT = SOUNDS.createSoundEvent("entity.bolt_hit");
    public static final DeferredHolder<SoundEvent, SoundEvent> BOLT_HIT_ARMOR = SOUNDS.createSoundEvent("entity.bolt_hit_armor");

}
