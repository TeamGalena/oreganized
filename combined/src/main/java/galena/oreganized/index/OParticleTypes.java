package galena.oreganized.index;

import galena.oreganized.client.particle.*;
import galena.oreganized.gothic.index.GothicParticles;
import galena.oreganized.plumbum.index.PlumbumParticles;
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

@EventBusSubscriber(Dist.CLIENT)
public class OParticleTypes {

    private static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, OConstants.MOD_ID);

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DRIPPING_LEAD = PlumbumParticles.DRIPPING_LEAD;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_LEAD = PlumbumParticles.FALLING_LEAD;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LANDING_LEAD = PlumbumParticles.LANDING_LEAD;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LEAD_CLOUD = PlumbumParticles.LEAD_CLOUD;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LEAD_BLOW = PlumbumParticles.LEAD_BLOW;

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LEAD_SHRAPNEL = PARTICLES.register("lead_shrapnel", () -> new SimpleParticleType(true));

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> VENGEANCE = GothicParticles.VENGEANCE;

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> KINETIC_HIT = PARTICLES.register("kinetic_hit", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> TARNISH = PARTICLES.register("tarnish", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> POLISH = PARTICLES.register("polish", () -> new SimpleParticleType(true));

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(LEAD_SHRAPNEL.get(), LeadShrapnelParticle.Provider::new);
        event.registerSpriteSet(KINETIC_HIT.get(), KineticHitParticle.Provider::new);
        event.registerSpriteSet(TARNISH.get(), TarnishParticle.Provider::new);
        event.registerSpriteSet(POLISH.get(), PolishParticle.Provider::new);
    }

    public static void register(IEventBus modBus) {
        PARTICLES.register(modBus);
    }

}
