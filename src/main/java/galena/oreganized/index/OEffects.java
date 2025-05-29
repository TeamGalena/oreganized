package galena.oreganized.index;

import galena.oreganized.Oreganized;
import galena.oreganized.content.effect.LungDamageEffect;
import galena.oreganized.content.effect.StunningEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OEffects {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Oreganized.MOD_ID);

    public static final RegistryObject<MobEffect> STUNNING = EFFECTS.register("stunning", StunningEffect::new);
    public static final RegistryObject<MobEffect> LUNG_DAMAGE = EFFECTS.register("lung_damage", LungDamageEffect::new);
}
