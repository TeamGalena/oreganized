package galena.oreganized.client.model;

import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public final class ElectrumArmorModel {

    public static LayerDefinition createBodyLayer() {
        var mesh = HumanoidArmorModel.createMesh(CubeDeformation.NONE, 0.0F);
        var root = mesh.getRoot();

        var head = root.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(0, 48)
                .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.75F)), PartPose.offset(0.0F, -1.0F, 0.0F));

        head.addOrReplaceChild("LeftWing_r1", CubeListBuilder.create()
                .texOffs(32, 41)
                .addBox(6.0F, -11.0F, -6.0F, 0.0F, 11.0F, 12.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3054F, 0.0F));

        head.addOrReplaceChild("RightWing_r1", CubeListBuilder.create()
                .texOffs(32, 41)
                .addBox(-6.0F, -11.0F, -6.0F, 0.0F, 11.0F, 12.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3054F, 0.0F));

        root.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(40, 21)
                .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.51F))
                .texOffs(40, 14)
                .addBox(-4.0F, 9.0F, -2.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.4F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        root.addOrReplaceChild("right_arm", CubeListBuilder.create()
                .texOffs(48, 37)
                .addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

        root.addOrReplaceChild("left_arm", CubeListBuilder.create()
                .texOffs(32, 37)
                .addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)), PartPose.offset(5.0F, 2.0F, 0.0F));

        root.addOrReplaceChild("right_leg", CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.31F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

        root.addOrReplaceChild("left_leg", CubeListBuilder.create()
                .texOffs(16, 0)
                .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offset(1.9F, 12.0F, 0.0F));

        var rightBoot = root.addOrReplaceChild("right_boot", CubeListBuilder.create()
                .texOffs(0, 16)
                .addBox(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.51F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
        rightBoot.addOrReplaceChild("RightFootWing_r1", CubeListBuilder.create()
                .texOffs(16, 12)
                .addBox(-2.8F, 5.0F, -1.0F, 0.0F, 6.0F, 8.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.1745F, 0.0F));

        var leftBoot = root.addOrReplaceChild("left_boot", CubeListBuilder.create()
                .texOffs(0, 24)
                .addBox(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(1.9F, 12.0F, 0.0F));
        leftBoot.addOrReplaceChild("LeftFootWing_r1", CubeListBuilder.create()
                .texOffs(16, 18)
                .addBox(2.8F, 5.0F, -1.0F, 0.0F, 6.0F, 8.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.1745F, 0.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

}
