package galena.oreganized.argentum.index;

import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import galena.oreganized.OConstants;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(OConstants.MOD_ID)
public class ArgentumSounds {

    private static final SoundSubRegistryHelper SOUNDS = OConstants.REGISTRY_HELPER.getSoundSubHelper();

    public static final DeferredHolder<SoundEvent, SoundEvent> SILVER_BREAK = SOUNDS.createSoundEvent("block.silver.break");
    public static final DeferredHolder<SoundEvent, SoundEvent> SILVER_FALL = SOUNDS.createSoundEvent("block.silver.fall");
    public static final DeferredHolder<SoundEvent, SoundEvent> SILVER_HIT = SOUNDS.createSoundEvent("block.silver.hit");
    public static final DeferredHolder<SoundEvent, SoundEvent> SILVER_PLACE = SOUNDS.createSoundEvent("block.silver.place");
    public static final DeferredHolder<SoundEvent, SoundEvent> SILVER_STEP = SOUNDS.createSoundEvent("block.silver.step");

    public static final SoundType SILVER = new DeferredSoundType(
            1.0F,
            1.25F,
            SILVER_BREAK,
            SILVER_STEP,
            SILVER_HIT,
            SILVER_PLACE,
            SILVER_FALL
    );

    public static final DeferredHolder<SoundEvent, SoundEvent> TARNISH = SOUNDS.createSoundEvent("block.tarnish");
    public static final DeferredHolder<SoundEvent, SoundEvent> POLISH = SOUNDS.createSoundEvent("block.polish");
    public static final DeferredHolder<SoundEvent, SoundEvent> POLISH_FINISH = SOUNDS.createSoundEvent("block.polish_finish");

}
