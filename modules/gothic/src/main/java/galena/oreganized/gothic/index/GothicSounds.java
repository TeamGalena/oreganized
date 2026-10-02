package galena.oreganized.gothic.index;

import galena.oreganized.OConstants;
import galena.oreganized.register.SoundRegistryHelper;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(OConstants.MOD_ID)
public class GothicSounds {

    private static final SoundRegistryHelper SOUNDS = OConstants.REGISTRY_HELPER.getSoundSubHelper();

    public static final DeferredHolder<SoundEvent, SoundEvent> GARGOYLE_GROWL = SOUNDS.createSoundEvent("block.gargoyle.growl");

    public static final SoundType GRIMSTONE = SOUNDS.createBlockSoundType("grimstone", 1.0F, 1.25F);


}
