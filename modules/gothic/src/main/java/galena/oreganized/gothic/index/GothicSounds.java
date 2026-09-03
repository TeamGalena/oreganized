package galena.oreganized.gothic.index;

import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import galena.oreganized.OConstants;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(OConstants.MOD_ID)
public class GothicSounds {

    private static final SoundSubRegistryHelper SOUNDS = OConstants.REGISTRY_HELPER.getSoundSubHelper();

    public static final DeferredHolder<SoundEvent, SoundEvent> GARGOYLE_GROWL = SOUNDS.createSoundEvent("block.gargoyle.growl");


}
