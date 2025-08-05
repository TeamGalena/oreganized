package galena.oreganized.client.render.entity;

import galena.oreganized.Oreganized;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class LeadBoltRender  extends ArrowRenderer {

    public static final ResourceLocation TEXTURE = Oreganized.modLoc("textures/item/lead_bolt_projectile.png");

    public LeadBoltRender(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(Entity entity) {
        return TEXTURE;
    }

}
