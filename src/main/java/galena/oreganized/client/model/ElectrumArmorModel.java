package galena.oreganized.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import galena.oreganized.Oreganized;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ElectrumArmorModel<T extends LivingEntity> extends HumanoidArmorModel<T> {

    public static final ModelLayerLocation ELECTRUM_ARMOR = new ModelLayerLocation(Oreganized.modLoc( "electrum_armor"), "main");
    private final EquipmentSlot slot;
    private final ModelPart Head;
    private final ModelPart Body;
    private final ModelPart RightArm;
    private final ModelPart LeftArm;
    private final ModelPart RightLeg;
    private final ModelPart LeftLeg;
    private final ModelPart RightBoot;
    private final ModelPart LeftBoot;

    public ElectrumArmorModel(ModelPart root, EquipmentSlot slot) {
        super(root);
        this.slot = slot;
        this.Head = root.getChild("Head");
        this.Body = root.getChild("Body");
        this.RightArm = root.getChild("RightArm");
        this.LeftArm = root.getChild("LeftArm");
        this.RightLeg = root.getChild("RightLeg");
        this.LeftLeg = root.getChild("LeftLeg");
        this.RightBoot = root.getChild("RightBoot");
        this.LeftBoot = root.getChild("LeftBoot");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidArmorModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 48).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.75F)), PartPose.offset(0.0F, -1.0F, 0.0F));

        PartDefinition LeftWing_r1 = Head.addOrReplaceChild("LeftWing_r1", CubeListBuilder.create().texOffs(32, 41).addBox(6.0F, -11.0F, -6.0F, 0.0F, 11.0F, 12.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3054F, 0.0F));

        PartDefinition RightWing_r1 = Head.addOrReplaceChild("RightWing_r1", CubeListBuilder.create().texOffs(32, 41).addBox(-6.0F, -11.0F, -6.0F, 0.0F, 11.0F, 12.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3054F, 0.0F));

        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(40, 21).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.51F))
                .texOffs(40, 14).addBox(-4.0F, 9.0F, -2.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.4F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(48, 37).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

        PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(32, 37).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)), PartPose.offset(5.0F, 2.0F, 0.0F));

        PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.31F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

        PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(16, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(1.9F, 12.0F, 0.0F));

        PartDefinition RightBoot = partdefinition.addOrReplaceChild("RightBoot", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.51F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

        PartDefinition RightFootWing_r1 = RightBoot.addOrReplaceChild("RightFootWing_r1", CubeListBuilder.create().texOffs(16, 12).addBox(-2.8F, 5.0F, -1.0F, 0.0F, 6.0F, 8.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.1745F, 0.0F));

        PartDefinition LeftBoot = partdefinition.addOrReplaceChild("LeftBoot", CubeListBuilder.create().texOffs(0, 24).addBox(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(1.9F, 12.0F, 0.0F));

        PartDefinition LeftFootWing_r1 = LeftBoot.addOrReplaceChild("LeftFootWing_r1", CubeListBuilder.create().texOffs(16, 18).addBox(2.8F, 5.0F, -1.0F, 0.0F, 6.0F, 8.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.1745F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (slot == EquipmentSlot.HEAD) {
            poseStack.pushPose();
            this.Head.copyFrom(this.head);
            if (this.young) {
                poseStack.scale(0.75F, 0.75F, 0.75F);
                this.Head.setPos(0.0F, 15.0F, 0.0F);
            }
            this.Head.render(poseStack, buffer, packedLight, packedOverlay);
            poseStack.popPose();
        }
        if (slot == EquipmentSlot.CHEST) {
            poseStack.pushPose();
            this.Body.copyFrom(this.body);
            this.RightArm.copyFrom(this.rightArm);
            this.LeftArm.copyFrom(this.leftArm);
            if (this.young) {
                poseStack.scale(0.5F, 0.5F, 0.5F);
                this.Body.setPos(0.0F, 24.0F, 0.0F);
                this.RightArm.setPos(-5.0F, 24.0F, 0.0F);
                this.LeftArm.setPos(5.0F, 24.0F, 0.0F);
            }
            this.RightArm.render(poseStack, buffer, packedLight, packedOverlay);
            this.LeftArm.render(poseStack, buffer, packedLight, packedOverlay);
            this.Body.render(poseStack, buffer, packedLight, packedOverlay);
            poseStack.popPose();
        }
        if (slot == EquipmentSlot.LEGS) {
            poseStack.pushPose();
            this.RightLeg.copyFrom(this.rightLeg);
            this.LeftLeg.copyFrom(this.leftLeg);
            if (this.young) {
                poseStack.scale(0.5F, 0.5F, 0.5F);
                this.LeftLeg.setPos(2.0F, 36.0F, 0.0F);
                this.RightLeg.setPos(-2.0F, 36.0F, 0.0F);
            }
            this.RightLeg.render(poseStack, buffer, packedLight, packedOverlay);
            this.LeftLeg.render(poseStack, buffer, packedLight, packedOverlay);
            poseStack.popPose();
        }
        if (slot == EquipmentSlot.FEET) {
            poseStack.pushPose();
            this.RightBoot.copyFrom(this.rightLeg);
            this.LeftBoot.copyFrom(this.leftLeg);
            if (this.young) {
                poseStack.scale(0.5F, 0.5F, 0.5F);
                this.LeftBoot.setPos(2.0F, 37.0F, 0.0F);
                this.RightBoot.setPos(-2.0F, 37.0F, 0.0F);
            }
            this.RightBoot.render(poseStack, buffer, packedLight, packedOverlay);
            this.LeftBoot.render(poseStack, buffer, packedLight, packedOverlay);
            poseStack.popPose();
        }
    }
}