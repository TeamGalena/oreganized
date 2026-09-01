package galena.oreganized.client.model;

import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;

public class SilverArmorModel {

    public static LayerDefinition createBodyLayer() {
        var mesh = HumanoidArmorModel.createMesh(CubeDeformation.NONE, 0.0F);
        var root = mesh.getRoot();

        root.addOrReplaceChild("waist", CubeListBuilder.create()
                        .texOffs(40, 48)
                        .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.51F))
                        .texOffs(56, 43)
                        .addBox(-2.0F, -2.0F, 3.0F, 4.0F, 6.0F, 0.0F, CubeDeformation.NONE),
                PartPose.offset(0.0F, 0.0F, 0.0F)
        );

        root.addOrReplaceChild("left_leg", CubeListBuilder.create()
                        .texOffs(24, 48)
                        .mirror()
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F))
                        .mirror(false),
                PartPose.offset(1.9F, 12.0F, 0.0F)
        );

        root.addOrReplaceChild("right_leg", CubeListBuilder.create()
                        .texOffs(24, 48)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)),
                PartPose.offset(-1.9F, 12.0F, 0.0F)
        );

        root.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4.0F, -7.5F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.75F))
                        .texOffs(0, 32)
                        .addBox(-5.0F, -5.9F, -5.0F, 10.0F, 2.0F, 9.0F, new CubeDeformation(0.5F))
                        .texOffs(32, 0)
                        .addBox(-4.0F, -7.5F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.0F))
                        .texOffs(16, -8)
                        .addBox(-4.0F, -6.5F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.74F)),
                PartPose.offset(0.0F, 0.0F, 0.0F)
        );

        root.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(16, 16)
                        .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)),
                PartPose.offset(0.0F, 0.0F, 0.0F)
        );

        root.addOrReplaceChild("left_boot", CubeListBuilder.create()
                        .texOffs(0, 16)
                        .mirror()
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F))
                        .mirror(false),
                PartPose.offset(1.9F, 12.0F, 0.0F)
        );

        root.addOrReplaceChild("right_boot", CubeListBuilder.create()
                        .texOffs(0, 16)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)),
                PartPose.offset(-1.9F, 12.0F, 0.0F)
        );

        root.addOrReplaceChild("left_arm", CubeListBuilder.create()
                        .texOffs(40, 16)
                        .mirror()
                        .addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F))
                        .mirror(false),
                        // .texOffs(0, 43)
                        // .mirror()
                        // .addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.7F))
                        // .mirror(false),
                PartPose.offset(5.0F, 2.0F, 0.0F)
        );

        root.addOrReplaceChild("right_arm", CubeListBuilder.create()
                        .texOffs(40, 16)
                        .addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)),
                        // .texOffs(0, 43)
                        // .addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.7F)),
                PartPose.offset(-5.0F, 2.0F, 0.0F)
        );

        return LayerDefinition.create(mesh, 64, 64);
    }

}
