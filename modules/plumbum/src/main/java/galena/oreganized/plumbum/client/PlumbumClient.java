package galena.oreganized.plumbum.client;

import galena.oreganized.plumbum.index.PlumbumDataComponents;
import galena.oreganized.plumbum.index.PlumbumItems;
import galena.oreganized.plumbum.world.item.ThermometerItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(Dist.CLIENT)
public class PlumbumClient {

    @SubscribeEvent
    private static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(PlumbumClient::registerItemProperties);
    }

    private static void registerItemProperties() {
        ItemProperties.register(PlumbumItems.THERMOMETER.get(), PlumbumDataComponents.HEAT_LEVEL.getId(), (stack, world, entity, seed) ->
                ThermometerItem.getHeatLevel(stack)
        );
    }

}
