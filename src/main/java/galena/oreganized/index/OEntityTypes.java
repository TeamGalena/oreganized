package galena.oreganized.index;

import galena.oreganized.Oreganized;
import galena.oreganized.content.entity.LeadBoltEntity;
import galena.oreganized.content.entity.MinecartShrapnelBomb;
import galena.oreganized.content.entity.ShrapnelBomb;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Oreganized.MOD_ID);

    public static final RegistryObject<EntityType<ShrapnelBomb>> SHRAPNEL_BOMB = ENTITIES.register("shrapnel_bomb", () -> EntityType.Builder.<ShrapnelBomb>of(ShrapnelBomb::new, MobCategory.MISC).fireImmune().sized(1.0F, 1.0F).clientTrackingRange(10).updateInterval(10).build("shrapnel_bomb"));
    public static final RegistryObject<EntityType<MinecartShrapnelBomb>> SHRAPNEL_BOMB_MINECART = ENTITIES.register("shrapnel_bomb_minecart", () -> EntityType.Builder.<MinecartShrapnelBomb>of(MinecartShrapnelBomb::new, MobCategory.MISC).sized(0.98F, 0.7F).clientTrackingRange(8).build("shrapnel_bomb_minecart"));

    public static final RegistryObject<EntityType<LeadBoltEntity>> LEAD_BOLT = ENTITIES.register("lead_bolt", () -> EntityType.Builder.<LeadBoltEntity>of(LeadBoltEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("lead_bolt"));
}
