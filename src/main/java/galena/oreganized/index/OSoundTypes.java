package galena.oreganized.index;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.neoforge.common.util.DeferredSoundType;

public class OSoundTypes {
    public static final SoundType MOLTEN_LEAD = new DeferredSoundType(1.0F, 1.0F, () -> SoundEvents.BUCKET_FILL_LAVA, () -> SoundEvents.LAVA_AMBIENT, () -> SoundEvents.BUCKET_EMPTY_LAVA, () -> SoundEvents.LAVA_AMBIENT, () -> SoundEvents.LAVA_POP);
}
