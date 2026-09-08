package galena.oreganized.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import galena.oreganized.accessor.GuiAccessor;
import galena.oreganized.client.render.AdditionalHighlightEvent;
import galena.oreganized.client.render.ComponentRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.NeoForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Gui.class)
public abstract class GuiMixin implements GuiAccessor {

    @Shadow
    private int toolHighlightTimer;

    @Shadow
    private ItemStack lastToolHighlight;

    @Override
    public void oreganized$setToolHighlightTimer(int value) {
        this.toolHighlightTimer = value;
    }

    @WrapOperation(
            method = "renderSelectedItemName(Lnet/minecraft/client/gui/GuiGraphics;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawStringWithBackdrop(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIII)I")
    )
    public int renderTooltips(GuiGraphics graphics, Font font, Component text, int centeredX, int y, int originalWidth, int color, Operation<Integer> original) {
        var screenWidth = graphics.guiWidth();
        var mc = Minecraft.getInstance();

        var event = new AdditionalHighlightEvent(mc.level, mc.player, lastToolHighlight, new ComponentRenderer() {
            @Override
            public void draw(Component component, int x) {
                graphics.drawString(font, component, x, y - 12, color);
            }

            @Override
            public int offsetCentered(Component component) {
                return (screenWidth - font.width(component)) / 2;
            }
        });

        NeoForge.EVENT_BUS.post(event);

    /*

        if (lastToolHighlight.is(OItems.UNKNOWN_DEVICE.get())) {
            DeviceItem.getValue(lastToolHighlight).ifPresent(value -> {
                var tooltip = Component.literal(String.format("%s", value))
                        .withStyle(style -> style.withColor(DeviceItem.TOOLTIP_COLOR));
                var x = (screenWidth - font.width(tooltip)) / 2;
                graphics.drawString(font, tooltip, x, y - 12, color);
            });
        }
     */

        return original.call(graphics, font, text, centeredX, y, originalWidth, color);
    }

}
