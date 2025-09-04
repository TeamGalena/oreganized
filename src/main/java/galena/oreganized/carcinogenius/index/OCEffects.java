package galena.oreganized.carcinogenius.index;

import galena.oreganized.carcinogenius.OreganizedCarcinogenius;
import galena.oreganized.carcinogenius.content.effect.LungDamageEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class OCEffects {

    private static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, OreganizedCarcinogenius.NAMESPACE);

    public static final DeferredHolder<MobEffect, MobEffect> LUNG_DAMAGE = EFFECTS.register("lung_damage", LungDamageEffect::new);

    public static void register(IEventBus modBus) {
        EFFECTS.register(modBus);
    }

}
