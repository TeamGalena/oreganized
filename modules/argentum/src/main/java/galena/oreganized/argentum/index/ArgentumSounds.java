package galena.oreganized.argentum.index;

import galena.oreganized.OConstants;
import galena.oreganized.register.SoundRegistryHelper;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(OConstants.MOD_ID)
public class ArgentumSounds {

    private static final SoundRegistryHelper SOUNDS = OConstants.REGISTRY_HELPER.getSoundSubHelper();

    public static final SoundType SILVER = SOUNDS.createBlockSoundType("silver", 1.0F, 1.25F);

    public static final DeferredHolder<SoundEvent, SoundEvent> TARNISH = SOUNDS.createSoundEvent("block.tarnish");
    public static final DeferredHolder<SoundEvent, SoundEvent> POLISH = SOUNDS.createSoundEvent("block.polish");
    public static final DeferredHolder<SoundEvent, SoundEvent> POLISH_FINISH = SOUNDS.createSoundEvent("block.polish_finish");

}
