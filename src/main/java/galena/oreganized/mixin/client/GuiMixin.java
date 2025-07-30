package galena.oreganized.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import galena.oreganized.client.accessors.GuiAccessor;
import galena.oreganized.client.render.gui.StunningOverlay;
import galena.oreganized.client.tooltips.ClientThermometerTooltip;
import galena.oreganized.content.item.DeviceItem;
import galena.oreganized.content.item.ThermometerItem;
import galena.oreganized.index.OEffects;
import galena.oreganized.index.OItems;
import galena.oreganized.world.IMotionHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Gui.class)
public abstract class GuiMixin implements GuiAccessor {

    @Shadow
    protected int toolHighlightTimer;

    @Shadow
    protected ItemStack lastToolHighlight;

    @Shadow
    public abstract Font getFont();

    @Shadow
    protected int screenWidth;

    @Shadow
    protected int screenHeight;

    @Shadow
    @Final
    protected Minecraft minecraft;

    @WrapOperation(
            method = "renderHearts(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/world/entity/player/Player;IIIIFIIIZ)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderHeart(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/gui/Gui$HeartType;IIIZZ)V"),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderHeart(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/gui/Gui$HeartType;IIIZZ)V", ordinal = 1),
                    to = @At("TAIL")
            )
    )
    private void renderStunnedHeart(Gui instance, GuiGraphics graphics, Gui.HeartType type, int x, int y, int v, boolean blinking, boolean half, Operation<Void> original, @Local Player player) {
        if (player.hasEffect(OEffects.STUNNING.get()) && (type == Gui.HeartType.NORMAL || type == Gui.HeartType.POISIONED)) {
            var u = type.getX(half, blinking);
            StunningOverlay.renderStunnedHeart(graphics, u - 52, x, y, v / 5);
        } else {
            original.call(instance, graphics, type, x, y, v, blinking, half);
        }
    }

    @Override
    public void oreganized$setToolHighlightTimer(int value) {
        this.toolHighlightTimer = value;
    }

    @WrapOperation(
            method = "renderSelectedItemName(Lnet/minecraft/client/gui/GuiGraphics;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;III)I")
    )
    public int renderTooltips(GuiGraphics graphics, Font font, Component text, int centeredX, int y, int color, Operation<Integer> original, @Local(ordinal = 4) int opacity) {
        if (lastToolHighlight.is(OItems.THERMOMETER.get())) {
            var heatLevel = ThermometerItem.getHeatLevel(lastToolHighlight);
            var tooltip = Component.translatable(ClientThermometerTooltip.getDescriptionId(heatLevel))
                    .withStyle(style -> style.withColor(ClientThermometerTooltip.getColor(heatLevel)));

            var x = (screenWidth - font.width(tooltip)) / 2;
            graphics.drawString(font, tooltip, x, y - 12, color);
        }

        if (lastToolHighlight.is(OItems.SPEEDOMETER.get()) && Minecraft.getInstance().player.getRootVehicle() instanceof IMotionHolder motionHolder) {
            long time = Minecraft.getInstance().level.getGameTime();
            double speed = motionHolder.oreganised$getMotion();
            var formattedSpeed = String.format("%.2f", speed);
            var tooltip = Component.empty().append(Component.translatable("tooltip.oreganized.speed", formattedSpeed))
                    .withStyle((style) -> style.withColor(0xc7bf81));
            var arrow = Component.empty().append("->")
                    .withStyle((style) -> style.withColor(0xebe198));

            var x = (screenWidth - font.width(tooltip)) / 2;
            graphics.drawString(font, tooltip, x, y - 12, color);
            graphics.drawString(font, arrow, x - 20 + (int) (Math.cos(time / 10f) * 5), y - 12, color);
        }

        if (lastToolHighlight.is(OItems.UNKNOWN_DEVICE.get())) {
            DeviceItem.getValue(lastToolHighlight).ifPresent(value -> {
                var tooltip = Component.literal(String.format("%s", value))
                        .withStyle(style -> style.withColor(DeviceItem.TOOLTIP_COLOR));
                var x = (screenWidth - font.width(tooltip)) / 2;
                graphics.drawString(font, tooltip, x, y - 12, color);
            });
        }

        return original.call(graphics, font, text, centeredX, y, color);
    }
}
