package galena.oreganized.device.client;

import galena.oreganized.device.index.DeviceDataComponents;
import galena.oreganized.device.index.DeviceItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(Dist.CLIENT)
public class DevicesClient {

    @SubscribeEvent
    private static void registerItemProperties(FMLClientSetupEvent event) {
        ItemProperties.register(DeviceItems.UNKNOWN_DEVICE.get(), DeviceDataComponents.DEVICE_VALUE.getId(), new DevicePropertyFunction());
    }

}
