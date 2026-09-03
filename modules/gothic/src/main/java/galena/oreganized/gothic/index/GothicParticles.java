package galena.oreganized.gothic.index;

import galena.oreganized.OConstants;
import galena.oreganized.gothic.world.particle.VengeanceParticleProvider;
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
public class GothicParticles {

    private static final ParticleTypeRegistryHelper PARTICLES = OConstants.REGISTRY_HELPER.getParticleTypeSubHelper();

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> VENGEANCE = PARTICLES.createSimple("vengeance");

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(VENGEANCE.get(), VengeanceParticleProvider::new);
    }

}
