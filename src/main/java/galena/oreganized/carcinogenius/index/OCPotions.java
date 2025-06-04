package galena.oreganized.carcinogenius.index;

import galena.oreganized.carcinogenius.OreganizedCarcinogenius;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OCPotions {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, OreganizedCarcinogenius.NAMESPACE);

    public static final RegistryObject<Potion> LUNG_DAMAGE = POTIONS.register("lung_damage", () -> new Potion("lung_damage", new MobEffectInstance(OCEffects.LUNG_DAMAGE.get(), 360)));
}
