package galena.oreganized.index;

import galena.oreganized.Oreganized;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OPotions {

    private static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, Oreganized.MOD_ID);

    public static final RegistryObject<Potion> STUNNING = POTIONS.register("stunning", () -> new Potion("stunning", new MobEffectInstance(OEffects.STUNNING.get(), 1800)));
    public static final RegistryObject<Potion> LONG_STUNNING = POTIONS.register("long_stunning", () -> new Potion("stunning", new MobEffectInstance(OEffects.STUNNING.get(), 3600)));

    public static void register(IEventBus modBus) {
        POTIONS.register(modBus);
    }
}
