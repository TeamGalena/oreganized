package galena.oreganized.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.GlowParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class PolishParticleProvider implements ParticleProvider<SimpleParticleType> {

    private final ParticleProvider<SimpleParticleType> inner;

    public PolishParticleProvider(SpriteSet sprites) {
        inner = new GlowParticle.WaxOffProvider(sprites);
    }

    @Override
    public @Nullable Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        var particle = (GlowParticle) inner.createParticle(type, level, x, y, z, xSpeed, ySpeed, zSpeed);
        particle.setColor(1F, 1F, 1F);
        return particle;
    }
}
