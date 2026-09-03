package galena.oreganized.plumbum.client.tooltip;

import galena.oreganized.plumbum.world.item.ThermometerItem;
import java.awt.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.resources.language.I18n;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;

@EventBusSubscriber(Dist.CLIENT)
public class ClientThermometerTooltip implements ClientTooltipComponent {

    @SubscribeEvent
    public static void registerClientTooltips(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(ThermometerTooltip.class, ClientThermometerTooltip::new);
    }

    public static String getDescriptionId(int heat) {
        return "tooltip.oreganized.heat_" + heat;
    }

    public static int getColor(int heat) {
        float heatF = ((float) heat) / ThermometerItem.HEAT_LEVELS;
        return new Color(0.5f + heatF / 2, 1 - heatF / 2, 1 - heatF / 2).getRGB();
    }

    private final int heat;

    private ClientThermometerTooltip(ThermometerTooltip tooltip) {
        this.heat = tooltip.heat();
    }

    @Override
    public int getHeight() {
        return 14;
    }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics graphics) {
        int i = 0;
        int offset = 0;
        long time = Minecraft.getInstance().level.getGameTime();
        double delta = Minecraft.getInstance().getTimer().getGameTimeDeltaTicks();
        var text = I18n.get(getDescriptionId(heat));
        for (char a : text.toCharArray()) {
            graphics.pose().pushPose();
            graphics.pose().translate(0, (Math.sin(i * 1.2 + (time + delta) / 24 * heat) * heat / 3), 0);
            graphics.drawString(font, Character.toString(a), x + offset, y, getColor(heat), true);
            i += 1;
            offset += font.width(Character.toString(a));
            graphics.pose().popPose();
        }

    }

    @Override
    public int getWidth(Font font) {
        return 20;
    }
}
