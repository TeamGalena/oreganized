package galena.oreganized.register;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.neoforge.common.util.DeferredSoundType;

public class SoundRegistryHelper extends SoundSubRegistryHelper {

    public SoundRegistryHelper(RegistryHelper parent) {
        super(parent);
    }

    public SoundType createBlockSoundType(String name, float volume, float pitch) {
        return new DeferredSoundType(
                volume,
                pitch,
                createSoundEvent("block.%s.break".formatted(name)),
                createSoundEvent("block.%s.step".formatted(name)),
                createSoundEvent("block.%s.place".formatted(name)),
                createSoundEvent("block.%s.hit".formatted(name)),
                createSoundEvent("block.%s.fall".formatted(name))
        );
    }

}
