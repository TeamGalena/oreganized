package galena.oreganized.device.client;

import galena.oreganized.device.world.item.DeviceItem;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;

@EventBusSubscriber(Dist.CLIENT)
public class ClientDeviceTooltip implements ClientTooltipComponent {

    @SubscribeEvent
    public static void registerClientTooltips(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(DeviceTooltip.class, ClientDeviceTooltip::new);
    }

    private final DeviceTooltip tooltip;

    private ClientDeviceTooltip(DeviceTooltip tooltip) {
        this.tooltip = tooltip;
    }

    @Override
    public int getHeight() {
        return 14;
    }

    public void renderImage(Font font, int x, int y, GuiGraphics graphics) {
        graphics.drawString(font, String.format("%s", tooltip.number()), x, y, DeviceItem.TOOLTIP_COLOR);
    }

    @Override
    public int getWidth(Font font) {
        return 20;
    }
}
