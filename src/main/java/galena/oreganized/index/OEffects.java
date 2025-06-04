package galena.oreganized.index;

import galena.oreganized.OreganizedCarcinogenius;
import galena.oreganized.content.effect.LungDamageEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OEffects {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, OreganizedCarcinogenius.MOD_ID);

    public static final RegistryObject<MobEffect> LUNG_DAMAGE = EFFECTS.register("lung_damage", LungDamageEffect::new);
}
