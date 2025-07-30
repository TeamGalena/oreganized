package galena.oreganized.client.render.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import galena.oreganized.Oreganized;
import galena.oreganized.OreganizedConfig;
import galena.oreganized.content.effect.StunningEffect;
import galena.oreganized.index.OEffects;
import galena.oreganized.index.OFluids;
import io.netty.util.collection.IntObjectHashMap;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.resources.ResourceLocation;

public class StunningOverlay implements LayeredDraw.Layer {

    protected static final ResourceLocation STUNNING_VIGNETTE_LOCATION = Oreganized.modLoc( "textures/misc/stunning_overlay.png");
    protected static final ResourceLocation STUNNED_HEARTS = Oreganized.modLoc( "textures/gui/stunned_hearts.png");

    private static final IntObjectHashMap<ResourceLocation> STUNNING_OVERLAY_LOCATIONS = new IntObjectHashMap<>();

    protected static ResourceLocation getStunningOutline(int amplifier) {
        return STUNNING_OVERLAY_LOCATIONS.computeIfAbsent(amplifier, i -> {
            return Oreganized.modLoc("textures/misc/brain_damage_outline_" + (i + 1) + ".png");
        });
    }

    @Override
    public void render(GuiGraphics graphics, DeltaTracker delta) {
        var minecraft = Minecraft.getInstance();
        var player = Minecraft.getInstance().player;
        if (player == null) return;

        var screenWidth = minecraft.getWindow().getGuiScaledWidth();
        var screenHeight = minecraft.getWindow().getGuiScaledHeight();
        RenderSystem.enableBlend();

        var stunning = minecraft.player.getEffect(OEffects.STUNNING);
        if (stunning != null && OreganizedConfig.CLIENT.renderStunningOverlay.get()) {
            var opacity = stunning.getAmplifier() * 1F / StunningEffect.MAX_AMPLIFIER;
            renderTextureOverlay(graphics, STUNNING_VIGNETTE_LOCATION, opacity);
            renderTextureOverlay(graphics, getStunningOutline(stunning.getAmplifier()), 1F);
        }

        if (minecraft.player.isEyeInFluidType(OFluids.MOLTEN_LEAD_TYPE.get()))
            renderTextureOverlay(graphics, STUNNING_VIGNETTE_LOCATION, 1F);
    }

    public static void renderStunnedHeart(GuiGraphics graphics, int u, int x, int y, int v) {
        graphics.blit(STUNNED_HEARTS, x, y, u, v, 9, 9, 72, 18);
    }

}
