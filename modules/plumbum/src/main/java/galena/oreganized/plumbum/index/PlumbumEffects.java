package galena.oreganized.plumbum.index;

import galena.oreganized.OConstants;
import galena.oreganized.plumbum.world.effect.StunningEffect;
import galena.oreganized.register.SimpleRegistryHelper;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;

public class PlumbumEffects {

    private static final SimpleRegistryHelper<MobEffect> EFFECTS = OConstants.REGISTRY_HELPER.getEffectSubHelper();

    public static final DeferredHolder<MobEffect, MobEffect> STUNNING = EFFECTS.create("stunning", $ -> new StunningEffect());

    public static void register() {
        // Load this class
    }

}
