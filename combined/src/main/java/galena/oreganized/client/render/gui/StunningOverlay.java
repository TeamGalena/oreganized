package galena.oreganized.client.render.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import galena.oreganized.OConstants;
import galena.oreganized.index.OEffects;
import galena.oreganized.index.OFluids;
import galena.oreganized.plumbum.config.PlumbumConfigs;
import galena.oreganized.plumbum.world.effect.StunningEffect;
import io.netty.util.collection.IntObjectHashMap;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.resources.ResourceLocation;

public class StunningOverlay implements LayeredDraw.Layer {

    protected static final ResourceLocation STUNNING_VIGNETTE_LOCATION = OConstants.modLoc("textures/misc/stunning_overlay.png");

    private static final IntObjectHashMap<ResourceLocation> STUNNING_OVERLAY_LOCATIONS = new IntObjectHashMap<>();

    protected static ResourceLocation getStunningOutline(int amplifier) {
        return STUNNING_OVERLAY_LOCATIONS.computeIfAbsent(amplifier, i ->
                OConstants.modLoc("textures/misc/brain_damage_outline_" + (i + 1) + ".png")
        );
    }

    @Override
    public void render(GuiGraphics graphics, DeltaTracker delta) {
        var minecraft = Minecraft.getInstance();
        var player = Minecraft.getInstance().player;
        if (player == null) return;

        var stunning = minecraft.player.getEffect(OEffects.STUNNING);
        if (stunning != null && PlumbumConfigs.CLIENT.renderStunningOverlay.get()) {
            var opacity = stunning.getAmplifier() * 1F / StunningEffect.MAX_AMPLIFIER;
            renderTextureOverlay(graphics, STUNNING_VIGNETTE_LOCATION, opacity);
            renderTextureOverlay(graphics, getStunningOutline(stunning.getAmplifier()), 1F);
        }

        if (minecraft.player.isEyeInFluidType(OFluids.MOLTEN_LEAD_TYPE.get()))
            renderTextureOverlay(graphics, STUNNING_VIGNETTE_LOCATION, 1F);
    }

    private void renderTextureOverlay(GuiGraphics graphics, ResourceLocation texture, float opacity) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        graphics.setColor(1.0F, 1.0F, 1.0F, opacity);
        graphics.blit(texture, 0, 0, -90, 0.0F, 0.0F, graphics.guiWidth(), graphics.guiHeight(), graphics.guiWidth(), graphics.guiHeight());
        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

}
