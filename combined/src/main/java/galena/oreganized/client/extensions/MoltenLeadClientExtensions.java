package galena.oreganized.client.extensions;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

public class MoltenLeadClientExtensions implements IClientFluidTypeExtensions {

    @Override
    public ResourceLocation getStillTexture() {
        return OConstants.modLoc("block/fluid/molten_lead");
    }

    @Override
    public ResourceLocation getFlowingTexture() {
        return OConstants.modLoc("block/fluid/molten_lead_flowing");
    }

    @Override
    public ResourceLocation getOverlayTexture() {
        return OConstants.modLoc("block/fluid/molten_lead_flowing");
    }

    @Override
    public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
        return new Vector3f(57 / 255F, 25 / 255F, 80 / 255F);
    }

    @Override
    public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
        RenderSystem.setShaderFogStart(0.0F);
        RenderSystem.setShaderFogEnd(3.0F);
    }

}
