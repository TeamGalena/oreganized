package galena.oreganized.argentum.world.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.GlowParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class PolishParticle extends GlowParticle {

    protected PolishParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed, sprites);
        pickSprite(sprites);
    }

    @Override
    public void setSpriteFromAge(SpriteSet sprite) {
        // don't
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            var particle = new PolishParticle(level, x, y, z, 0.0F, 0.0F, 0.0F, sprites);
            particle.setColor(1.0F, 0.9F, 1.0F);
            particle.setParticleSpeed(xSpeed * 0.01 / (double) 2.0F, ySpeed * 0.01, zSpeed * 0.01 / (double) 2.0F);
            particle.setLifetime(level.random.nextInt(30) + 10);
            return particle;
        }
    }
}
