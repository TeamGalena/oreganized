package galena.oreganized.carcinogenius.index;

import galena.oreganized.carcinogenius.OreganizedCarcinogenius;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class OCPotions {

    private static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(BuiltInRegistries.POTION, OreganizedCarcinogenius.NAMESPACE);

    public static final DeferredHolder<Potion, Potion> LUNG_DAMAGE = POTIONS.register("lung_damage", () -> new Potion("lung_damage", new MobEffectInstance(OCEffects.LUNG_DAMAGE, 360)));

    public static void register(IEventBus modBus) {
        POTIONS.register(modBus);
    }

}
