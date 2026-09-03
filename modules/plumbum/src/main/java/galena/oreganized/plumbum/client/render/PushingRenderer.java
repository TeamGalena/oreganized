package galena.oreganized.plumbum.client.render;

import com.mojang.math.Axis;
import galena.oreganized.plumbum.world.block.PushableBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderHandEvent;

@EventBusSubscriber(Dist.CLIENT)
public class PushingRenderer {

    @SubscribeEvent
    public static void renderHand(RenderHandEvent event) {
        var player = Minecraft.getInstance().player;

        if (!PushableBlockEntity.isPushing(player)) return;
        if (player.isInvisible()) return;

        var poseStack = event.getPoseStack();

        for (var arm : HumanoidArm.values()) {
            poseStack.pushPose();
            boolean rightArm = arm == HumanoidArm.RIGHT;
            float factor = rightArm ? 1.0F : -1.0F;
            poseStack.translate(factor * 0.84000005F, -0.4F, -0.4F);
            poseStack.mulPose(Axis.YP.rotationDegrees(factor * -20F - event.getSwingProgress()));
            poseStack.mulPose(Axis.ZP.rotationDegrees(factor * 45F));
            poseStack.mulPose(Axis.XP.rotationDegrees(-45F));

            float time = player.tickCount + event.getPartialTick();
            float movement = Mth.sin(time * 0.1F) * 0.008F;
            float rotation = factor * (float) Math.toDegrees(Mth.cos(time * 0.09F) * -0.005);
            poseStack.translate(factor * movement, 0, movement);
            poseStack.mulPose(Axis.ZP.rotationDegrees(rotation));

            var renderer = (PlayerRenderer) Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(player);

            if (rightArm) {
                renderer.renderRightHand(poseStack, event.getMultiBufferSource(), event.getPackedLight(), player);
            } else {
                renderer.renderLeftHand(poseStack, event.getMultiBufferSource(), event.getPackedLight(), player);
            }
            poseStack.popPose();
        }

        event.setCanceled(true);
    }
}
