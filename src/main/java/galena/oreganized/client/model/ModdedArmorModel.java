package galena.oreganized.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SpriteCoordinateExpander;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

public class ModdedArmorModel<T extends LivingEntity> extends HumanoidArmorModel<T> {

    private final EquipmentSlot slot;
    private final ModelPart leftBoot;
    private final ModelPart rightBoot;
    private final ModelPart waist;

    public ModdedArmorModel(ModelPart root, EquipmentSlot slot) {
        super(root);
        this.slot = slot;
        this.leftBoot = root.getChild("left_boot");
        this.rightBoot = root.getChild("right_boot");
        this.waist = root.getChild("waist");
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        // trim rendering
        if (buffer instanceof SpriteCoordinateExpander) {
            return;
        }

        poseStack.pushPose();

        if (young) {
            if (slot == EquipmentSlot.HEAD) {
                poseStack.scale(0.75F, 0.75F, 0.75F);
            } else {
                poseStack.scale(0.5F, 0.5F, 0.5F);
            }
        }

        if (slot == EquipmentSlot.HEAD) {
            if (young) {
                head.setPos(0.0F, 15.0F, 0.0F);
            }
            head.render(poseStack, buffer, packedLight, packedOverlay);
        } else if (slot == EquipmentSlot.CHEST) {
            if (young) {
                poseStack.scale(0.5F, 0.5F, 0.5F);
                body.setPos(0.0F, 24.0F, 0.0F);
                rightArm.setPos(-5.0F, 24.0F, 0.0F);
                leftArm.setPos(5.0F, 24.0F, 0.0F);
            }
            rightArm.render(poseStack, buffer, packedLight, packedOverlay);
            leftArm.render(poseStack, buffer, packedLight, packedOverlay);
            body.render(poseStack, buffer, packedLight, packedOverlay);
        } else if (slot == EquipmentSlot.LEGS) {
            if (young) {
                leftLeg.setPos(2.0F, 36.0F, 0.0F);
                rightLeg.setPos(-2.0F, 36.0F, 0.0F);
            }
            rightLeg.render(poseStack, buffer, packedLight, packedOverlay);
            leftLeg.render(poseStack, buffer, packedLight, packedOverlay);
            waist.render(poseStack, buffer, packedLight, packedOverlay);
        } else if (slot == EquipmentSlot.FEET) {
            rightBoot.copyFrom(rightLeg);
            leftBoot.copyFrom(leftLeg);
            if (young) {
                leftBoot.setPos(2.0F, 37.0F, 0.0F);
                rightBoot.setPos(-2.0F, 37.0F, 0.0F);
            }
            rightBoot.render(poseStack, buffer, packedLight, packedOverlay);
            leftBoot.render(poseStack, buffer, packedLight, packedOverlay);
        }

        poseStack.popPose();
    }
}
