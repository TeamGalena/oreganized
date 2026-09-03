package galena.oreganized.index;

import galena.oreganized.OConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class OPotions {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, OConstants.MOD_ID);

    public static final DeferredHolder<Potion, Potion> STUNNING = POTIONS.register("stunning", () -> new Potion("stunning", new MobEffectInstance(OEffects.STUNNING, 1800)));
    public static final DeferredHolder<Potion, Potion> LONG_STUNNING = POTIONS.register("long_stunning", () -> new Potion("stunning", new MobEffectInstance(OEffects.STUNNING, 3600)));

    public static void register(IEventBus modBus) {
        POTIONS.register(modBus);
    }

}
