package galena.oreganized.index;

import galena.oreganized.armament.index.ArmamentEntities;
import galena.oreganized.armament.world.entity.LeadBoltEntity;
import galena.oreganized.armament.world.entity.MinecartShrapnelBomb;
import galena.oreganized.armament.world.entity.ShrapnelBomb;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class OEntityTypes {

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<EntityType<?>, EntityType<ShrapnelBomb>> SHRAPNEL_BOMB = ArmamentEntities.SHRAPNEL_BOMB;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<EntityType<?>, EntityType<MinecartShrapnelBomb>> SHRAPNEL_BOMB_MINECART = ArmamentEntities.SHRAPNEL_BOMB_MINECART;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<EntityType<?>, EntityType<LeadBoltEntity>> LEAD_BOLT = ArmamentEntities.LEAD_BOLT;

}
