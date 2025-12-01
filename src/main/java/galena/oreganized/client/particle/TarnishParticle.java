package galena.oreganized.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.GlowParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;

public class TarnishParticle extends GlowParticle{

    protected TarnishParticle(ClientLevel p_172136_, double p_172137_, double p_172138_, double p_172139_, double p_172140_, double p_172141_, double p_172142_, SpriteSet p_172143_) {
        super(p_172136_, p_172137_, p_172138_, p_172139_, p_172140_, p_172141_, p_172142_, p_172143_);
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final double SPEED_FACTOR = 0.01;
        private final SpriteSet sprite;

        public Provider(SpriteSet p_172238_) {
            this.sprite = p_172238_;
        }

        @Override
        public Particle createParticle(SimpleParticleType p_172249_, ClientLevel p_172250_, double p_172251_, double p_172252_, double p_172253_, double p_172254_, double p_172255_, double p_172256_) {
            GlowParticle glowparticle = new TarnishParticle(p_172250_, p_172251_, p_172252_, p_172253_, 0.0, 0.0, 0.0, this.sprite);
            glowparticle.setParticleSpeed(p_172254_ * 0.01 / 2.0, p_172255_ * 0.01, p_172256_ * 0.01 / 2.0);
            glowparticle.setLifetime(p_172250_.random.nextInt(30) + 10);
            return glowparticle;
        }
    }
}
