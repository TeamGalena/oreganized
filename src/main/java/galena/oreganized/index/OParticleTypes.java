package galena.oreganized.index;

import galena.oreganized.Oreganized;
import galena.oreganized.client.particle.*;
import net.minecraft.client.particle.ExplodeParticle;
import net.minecraft.client.particle.GlowParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = Oreganized.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class OParticleTypes {

    private static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, Oreganized.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DRIPPING_LEAD = PARTICLES.register("dripping_lead", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_LEAD = PARTICLES.register("falling_lead", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LANDING_LEAD = PARTICLES.register("landing_lead", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LEAD_SHRAPNEL = PARTICLES.register( "lead_shrapnel", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> VENGEANCE = PARTICLES.register( "vengeance", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> KINETIC_HIT = PARTICLES.register( "kinetic_hit", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LEAD_CLOUD = PARTICLES.register( "lead_cloud", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LEAD_BLOW = PARTICLES.register( "lead_blow", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> TARNISH = PARTICLES.register( "tarnish", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> POLISH = PARTICLES.register( "polish", () -> new SimpleParticleType(true));

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(DRIPPING_LEAD.get(), CustomDrippingParticle.LeadHangProvider::new);
        event.registerSpriteSet(FALLING_LEAD.get(), CustomDrippingParticle.LeadFallProvider::new);
        event.registerSpriteSet(LANDING_LEAD.get(), CustomDrippingParticle.LeadLandProvider::new);
        event.registerSpriteSet(LEAD_SHRAPNEL.get(), LeadShrapnelParticle.Provider::new);
        event.registerSpriteSet(VENGEANCE.get(), VengeanceParticleProvider::new);
        event.registerSpriteSet(KINETIC_HIT.get(), KineticHitParticle.Provider::new);
        event.registerSpriteSet(LEAD_CLOUD.get(), LeadCloudParticleProvider::new);
        event.registerSpriteSet(LEAD_BLOW.get(), ExplodeParticle.Provider::new);
        event.registerSpriteSet(TARNISH.get(), TarnishParticle.Provider::new);
        event.registerSpriteSet(POLISH.get(), PolishParticleProvider::new);
    }

    public static void register(IEventBus modBus) {
        PARTICLES.register(modBus);
    }

}
