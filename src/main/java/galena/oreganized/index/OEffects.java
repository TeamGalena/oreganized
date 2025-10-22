package galena.oreganized.index;

import galena.oreganized.Oreganized;
import galena.oreganized.content.effect.StunningEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OEffects {

    private static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Oreganized.MOD_ID);

    public static final RegistryObject<MobEffect> STUNNING = EFFECTS.register("stunning", StunningEffect::new);

    public static void register(IEventBus modBus) {
        EFFECTS.register(modBus);
    }
}
