package galena.oreganized.index;

import galena.oreganized.Oreganized;
import galena.oreganized.content.effect.StunningEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class OEffects {

    private static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, Oreganized.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> STUNNING = EFFECTS.register("stunning", StunningEffect::new);

    public static void register(IEventBus modBus) {
        EFFECTS.register(modBus);
    }

}
