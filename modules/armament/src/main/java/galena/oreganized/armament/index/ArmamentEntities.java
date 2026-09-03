package galena.oreganized.armament.index;

import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import galena.oreganized.OConstants;
import galena.oreganized.armament.client.render.entity.LeadBoltRender;
import galena.oreganized.armament.client.render.entity.ShrapnelBombMinecartRender;
import galena.oreganized.armament.client.render.entity.ShrapnelBombRender;
import galena.oreganized.armament.world.entity.LeadBoltEntity;
import galena.oreganized.armament.world.entity.MinecartShrapnelBomb;
import galena.oreganized.armament.world.entity.ShrapnelBomb;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(OConstants.MOD_ID)
@EventBusSubscriber(Dist.CLIENT)
public class ArmamentEntities {

    private static final EntitySubRegistryHelper ENTITIES = OConstants.REGISTRY_HELPER.getEntitySubHelper();

    public static final DeferredHolder<EntityType<?>, EntityType<ShrapnelBomb>> SHRAPNEL_BOMB = ENTITIES.createEntity(
            "shrapnel_bomb",
            ShrapnelBomb::new,
            MobCategory.MISC,
            builder -> builder.fireImmune().sized(1.0F, 1.0F).clientTrackingRange(10).updateInterval(10)
    );

    public static final DeferredHolder<EntityType<?>, EntityType<MinecartShrapnelBomb>> SHRAPNEL_BOMB_MINECART = ENTITIES.createEntity(
            "shrapnel_bomb_minecart",
            MinecartShrapnelBomb::new,
            MobCategory.MISC,
            builder -> builder.sized(0.98F, 0.7F).clientTrackingRange(8)
    );

    public static final DeferredHolder<EntityType<?>, EntityType<LeadBoltEntity>> LEAD_BOLT = ENTITIES.createEntity(
            "lead_bolt",
            LeadBoltEntity::new,
            MobCategory.MISC,
            builder -> builder.sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20)
    );

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SHRAPNEL_BOMB.value(), ShrapnelBombRender::new);
        event.registerEntityRenderer(SHRAPNEL_BOMB_MINECART.get(), ShrapnelBombMinecartRender::new);
        event.registerEntityRenderer(LEAD_BOLT.get(), LeadBoltRender::new);
    }

}
