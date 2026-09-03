package galena.oreganized.index;

import galena.oreganized.plumbum.index.PlumbumEffects;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;

public class OEffects {

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<MobEffect, MobEffect> STUNNING = PlumbumEffects.STUNNING;

}
