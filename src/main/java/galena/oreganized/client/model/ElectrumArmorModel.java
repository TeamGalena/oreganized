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
                        .texOffs(35, 3)
                        .addBox(-1.0F, -8.5F, -4.75F, 2.0F, 4.0F, 0.0F, new CubeDeformation(0.25F, 0.25F, 0.0F))
                        .texOffs(32, 0)
                        .addBox(-1.0F, -8.75F, -4.5F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.25F, 0.0F, 0.25F))
                        .texOffs(0, 0)
                        .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)),
                PartPose.ZERO
        );

        head.addOrReplaceChild("right_wing_r1", CubeListBuilder.create()
                        .texOffs(48, -8).mirror()
                        .addBox(-3.0F, -3.0F, -4.0F, 0.0F, 7.0F, 8.0F, CubeDeformation.NONE).mirror(false),
                PartPose.offsetAndRotation(-2.25F, -7.0F, 1.5F, 0.0F, -0.1309F, 0.0F)
        );

        head.addOrReplaceChild("left_wing_r1", CubeListBuilder.create()
                        .texOffs(48, -8)
                        .addBox(-3.0F, -3.0F, -4.0F, 0.0F, 7.0F, 8.0F, CubeDeformation.NONE),
                PartPose.offsetAndRotation(8.25F, -7.0F, 1.5F, 0.0F, 0.1309F, 0.0F)
        );

        root.addOrReplaceChild("waist", CubeListBuilder.create()
                        .texOffs(16, 16)
                        .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.3F)),
                PartPose.ZERO
        );

        root.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(16, 16)
                        .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)),
                PartPose.ZERO
        );

        var leftBoot = root.addOrReplaceChild("left_boot", CubeListBuilder.create()
                        .texOffs(0, 16)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)),
                PartPose.ZERO
        );

        var rightBoot = root.addOrReplaceChild("right_boot", CubeListBuilder.create()
                        .texOffs(0, 16).mirror()
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false),
                PartPose.ZERO
        );

        rightBoot.addOrReplaceChild("wing_r1", CubeListBuilder.create()
                        .texOffs(49, 2).mirror()
                        .addBox(-2.7F, 5.0F, -1.5F, 0.0F, 6.0F, 7.0F, CubeDeformation.NONE).mirror(false),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.1309F, 0.0F)
        );

        leftBoot.addOrReplaceChild("wing_r2", CubeListBuilder.create()
                        .texOffs(49, 2)
                        .addBox(2.7F, 5.0F, -1.5F, 0.0F, 6.0F, 7.0F, CubeDeformation.NONE),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.1309F, 0.0F)
        );

        root.addOrReplaceChild("left_arm", CubeListBuilder.create()
                        .texOffs(40, 16)
                        .addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)),
                PartPose.ZERO
        );

        root.addOrReplaceChild("right_arm", CubeListBuilder.create()
                        .texOffs(40, 16).mirror()
                        .addBox(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F)).mirror(false),
                PartPose.ZERO
        );

        root.addOrReplaceChild("left_leg", CubeListBuilder.create()
                        .texOffs(0, 16).mirror()
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false),
                PartPose.ZERO
        );

        root.addOrReplaceChild("right_leg", CubeListBuilder.create()
                        .texOffs(0, 16)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)),
                PartPose.ZERO
        );

        return LayerDefinition.create(mesh, 64, 32);
    }

}
