package galena.oreganized.index;

import galena.oreganized.Oreganized;
import galena.oreganized.client.particle.CustomDrippingParticle;
import galena.oreganized.client.particle.KineticHitParticle;
import galena.oreganized.client.particle.LeadCloudParticleProvider;
import galena.oreganized.client.particle.LeadShrapnelParticle;
import galena.oreganized.client.particle.VengeanceParticleProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ExplodeParticle;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = Oreganized.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class OParticleTypes {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, Oreganized.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DRIPPING_LEAD = PARTICLES.register("dripping_lead", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_LEAD = PARTICLES.register("falling_lead", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LANDING_LEAD = PARTICLES.register("landing_lead", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LEAD_SHRAPNEL = PARTICLES.register( "lead_shrapnel", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> VENGEANCE = PARTICLES.register( "vengeance", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> KINETIC_HIT = PARTICLES.register( "kinetic_hit", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LEAD_CLOUD = PARTICLES.register( "lead_cloud", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LEAD_BLOW = PARTICLES.register( "lead_blow", () -> new SimpleParticleType(true));

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        ParticleEngine engine = Minecraft.getInstance().particleEngine;

        engine.register(DRIPPING_LEAD.get(), CustomDrippingParticle.LeadHangProvider::new);
        engine.register(FALLING_LEAD.get(), CustomDrippingParticle.LeadFallProvider::new);
        engine.register(LANDING_LEAD.get(), CustomDrippingParticle.LeadLandProvider::new);
        engine.register(LEAD_SHRAPNEL.get(), LeadShrapnelParticle.Provider::new);
        engine.register(VENGEANCE.get(), VengeanceParticleProvider::new);
        engine.register(KINETIC_HIT.get(), KineticHitParticle.Provider::new);
        engine.register(LEAD_CLOUD.get(), LeadCloudParticleProvider::new);
        engine.register(LEAD_BLOW.get(), ExplodeParticle.Provider::new);
    }
}
