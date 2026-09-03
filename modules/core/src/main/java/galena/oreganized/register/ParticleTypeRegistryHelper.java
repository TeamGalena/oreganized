package galena.oreganized.register;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ParticleTypeRegistryHelper extends SimpleRegistryHelper<ParticleType<?>> {

    public ParticleTypeRegistryHelper(RegistryHelper parent) {
        super(parent, Registries.PARTICLE_TYPE);
    }

    public DeferredHolder<ParticleType<?>, SimpleParticleType> createSimple(String name) {
        return create(name, $ -> new SimpleParticleType(true));
    }

}
