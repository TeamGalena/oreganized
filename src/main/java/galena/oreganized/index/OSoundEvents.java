package galena.oreganized.index;

import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import galena.oreganized.Oreganized;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Oreganized.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OSoundEvents {
    public static SoundSubRegistryHelper HELPER = Oreganized.REGISTRY_HELPER.getSoundSubHelper();

    public static final RegistryObject<SoundEvent> MUSIC_DISC_STRUCTURE = HELPER.createSoundEvent("music.disc.structure");

    public static final RegistryObject<SoundEvent> SHRAPNEL_BOMB_PRIMED = HELPER.createSoundEvent("entity.shrapnel_bomb.primed");

    public static final RegistryObject<SoundEvent> GARGOYLE_GROWL = HELPER.createSoundEvent("block.gargoyle.growl");

    public static final RegistryObject<SoundEvent> BOLT_HIT = HELPER.createSoundEvent("entity.bolt_hit");
    public static final RegistryObject<SoundEvent> BOLT_HIT_ARMOR = HELPER.createSoundEvent("entity.bolt_hit_armor");

    public static void register() {
        // Loads this class
    }
}
