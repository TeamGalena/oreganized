package galena.oreganized.device;

import galena.oreganized.CreativeModeTabBuilder;
import galena.oreganized.device.index.DeviceItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@EventBusSubscriber
public class DevicesTabs {

    @SubscribeEvent
    private static void buildTabs(BuildCreativeModeTabContentsEvent event) {
        var builder = new CreativeModeTabBuilder(event);

        builder.add(CreativeModeTabs.INGREDIENTS, tab -> {
            tab.putBefore(Items.IRON_INGOT, DeviceItems.NETHERITE_NUGGET);
        });

        builder.add(CreativeModeTabs.TOOLS_AND_UTILITIES, tab -> {
            tab.putBefore(Items.SPYGLASS, DeviceItems.UNKNOWN_DEVICE);
        });
    }

}
