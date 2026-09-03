package galena.oreganized.plumbum.index;

import galena.oreganized.OConstants;
import galena.oreganized.plumbum.world.particle.LeadCloudParticleProvider;
import galena.oreganized.plumbum.world.particle.LeadFluidParticle;
import galena.oreganized.register.ParticleTypeRegistryHelper;
import net.minecraft.client.particle.ExplodeParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

@EventBusSubscriber(Dist.CLIENT)
public class PlumbumParticles {

    private static final ParticleTypeRegistryHelper PARTICLES = OConstants.REGISTRY_HELPER.getParticleTypeSubHelper();

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DRIPPING_LEAD = PARTICLES.createSimple("dripping_lead");

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_LEAD = PARTICLES.createSimple("falling_lead");

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LANDING_LEAD = PARTICLES.createSimple("landing_lead");

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LEAD_CLOUD = PARTICLES.createSimple("lead_cloud");

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LEAD_BLOW = PARTICLES.createSimple("lead_blow");

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(DRIPPING_LEAD.get(), LeadFluidParticle.HangProvider::new);
        event.registerSpriteSet(FALLING_LEAD.get(), LeadFluidParticle.FallProvider::new);
        event.registerSpriteSet(LANDING_LEAD.get(), LeadFluidParticle.LandProvider::new);
        event.registerSpriteSet(LEAD_CLOUD.get(), LeadCloudParticleProvider::new);
        event.registerSpriteSet(LEAD_BLOW.get(), ExplodeParticle.Provider::new);
    }

    public static void register() {
        // Load this class
    }

}
