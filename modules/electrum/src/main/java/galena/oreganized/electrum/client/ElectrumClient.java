package galena.oreganized.electrum.client;

import galena.oreganized.electrum.accessor.IMotionHolder;
import galena.oreganized.electrum.index.ElectrumItems;
import galena.oreganized.electrum.world.item.SpeedometerItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(Dist.CLIENT)
public class ElectrumClient {

    @SubscribeEvent
    private static void registerItemProperties(FMLClientSetupEvent event) {
        ItemProperties.register(ElectrumItems.SPEEDOMETER.get(), SpeedometerItem.PROPERTY_KEY, (stack, world, entity, seed) -> {
            if (entity == null) return 0;
            var vehicle = entity.getRootVehicle();
            if (!(vehicle instanceof IMotionHolder motionHolder)) return 0;
            return Mth.clamp(Math.round(motionHolder.oreganised$getMotion() * 100), 0, 16);
        });

        ItemProperties.register(ElectrumItems.ELECTRUM_SHIELD.get(), ResourceLocation.withDefaultNamespace("blocking"), (stack, level, user, i) ->
                user != null && user.isUsingItem() && user.getUseItem() == stack ? 1.0F : 0.0F
        );
    }

}
