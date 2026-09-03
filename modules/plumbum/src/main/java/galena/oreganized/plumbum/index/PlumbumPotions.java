package galena.oreganized.plumbum.index;

import galena.oreganized.OConstants;
import galena.oreganized.register.PotionRegistryHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(OConstants.MOD_ID)
public class PlumbumPotions {

    public static final PotionRegistryHelper POTIONS = OConstants.REGISTRY_HELPER.getPotionSubHelper();

    public static final DeferredHolder<Potion, Potion> STUNNING = POTIONS.createPotion("stunning",() -> new MobEffectInstance(PlumbumEffects.STUNNING, 1800));
    public static final DeferredHolder<Potion, Potion> LONG_STUNNING = POTIONS.createPotion("long_stunning", () -> new MobEffectInstance(PlumbumEffects.STUNNING, 3600));


}
