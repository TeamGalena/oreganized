package galena.oreganized.client.tooltips;

import galena.oreganized.content.item.DeviceItem;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;

public class ClientDeviceTooltip implements ClientTooltipComponent {

    private final DeviceTooltip tooltip;

    public ClientDeviceTooltip(DeviceTooltip tooltip) {
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
