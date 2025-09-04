package galena.oreganized.carcinogenius.index;

import galena.oreganized.carcinogenius.OreganizedCarcinogenius;
import galena.oreganized.client.particle.LeadCloudParticleProvider;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = OreganizedCarcinogenius.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class OCParticleTypes {

    private static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, OreganizedCarcinogenius.NAMESPACE);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ASBESTOS_CLOUD = PARTICLES.register( "asbestos_cloud", () -> new SimpleParticleType(true));

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ASBESTOS_CLOUD.get(), LeadCloudParticleProvider::new);
    }

    public static void register(IEventBus modBus) {
        PARTICLES.register(modBus);
    }
}
