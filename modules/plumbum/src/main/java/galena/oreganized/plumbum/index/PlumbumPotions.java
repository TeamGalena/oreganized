package galena.oreganized.plumbum.index;

import galena.oreganized.OConstants;
import galena.oreganized.register.PotionRegistryHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(OConstants.MOD_ID)
@EventBusSubscriber
public class PlumbumPotions {

    public static final PotionRegistryHelper POTIONS = OConstants.REGISTRY_HELPER.getPotionSubHelper();

    public static final DeferredHolder<Potion, Potion> STUNNING = POTIONS.createPotion("stunning", () -> new MobEffectInstance(PlumbumEffects.STUNNING, 1800));
    public static final DeferredHolder<Potion, Potion> LONG_STUNNING = POTIONS.createPotion("long_stunning", () -> new MobEffectInstance(PlumbumEffects.STUNNING, 3600));

    @SubscribeEvent
    private static void registerPotionMixes(RegisterBrewingRecipesEvent event) {
        event.getBuilder().addMix(Potions.WATER, PlumbumItems.LEAD_INGOT.value(), STUNNING);
        event.getBuilder().addMix(STUNNING, Items.REDSTONE, LONG_STUNNING);
    }

}
