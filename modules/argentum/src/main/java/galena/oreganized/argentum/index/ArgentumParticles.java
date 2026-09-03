package galena.oreganized.argentum.index;

import galena.oreganized.OConstants;
import galena.oreganized.argentum.world.particle.PolishParticle;
import galena.oreganized.argentum.world.particle.TarnishParticle;
import galena.oreganized.register.ParticleTypeRegistryHelper;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

@EventBusSubscriber(Dist.CLIENT)
@Mod(OConstants.MOD_ID)
public class ArgentumParticles {

    private static final ParticleTypeRegistryHelper PARTICLES = OConstants.REGISTRY_HELPER.getParticleTypeSubHelper();

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> TARNISH = PARTICLES.createSimple("tarnish");

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> POLISH = PARTICLES.createSimple("polish");

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(TARNISH.get(), TarnishParticle.Provider::new);
        event.registerSpriteSet(POLISH.get(), PolishParticle.Provider::new);
    }

}
