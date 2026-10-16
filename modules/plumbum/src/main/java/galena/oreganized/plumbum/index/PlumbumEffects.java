package galena.oreganized.plumbum.index;

import galena.oreganized.plumbum.PlumbumModule;
import galena.oreganized.plumbum.world.effect.StunningEffect;
import galena.oreganized.register.SimpleRegistryHelper;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(PlumbumModule.MOD_ID)
public class PlumbumEffects {

    private static final SimpleRegistryHelper<MobEffect> EFFECTS = PlumbumModule.REGISTRY_HELPER.getEffectSubHelper();

    public static final DeferredHolder<MobEffect, MobEffect> STUNNING = EFFECTS.create("stunning", $ -> new StunningEffect());


}
