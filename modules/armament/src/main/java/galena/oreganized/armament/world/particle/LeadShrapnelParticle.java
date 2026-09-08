package galena.oreganized.armament.world.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ExplodeParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;

public class LeadShrapnelParticle extends ExplodeParticle {

    LeadShrapnelParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed, sprites);
        this.gravity = 0.3F;
    }

    @Override
    public void tick() {
        xo = x;
        yo = y;
        zo = z;
        if (age++ >= lifetime) {
            remove();
        } else {
            yd -= 0.04D * (double) gravity;
            move(xd, yd, zd);
            if (speedUpWhenYMotionIsBlocked && y == yo) {
                xd *= 1.1D;
                zd *= 1.1D;
            }
            xd *= friction;
            yd *= friction;
            zd *= friction;
            if (onGround) {
                xd *= 0.7F;
                zd *= 0.7F;
            }
        }
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet pSprites) {
            this.sprites = pSprites;
        }

        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            var particle = new LeadShrapnelParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, sprites);
            particle.pickSprite(sprites);
            return particle;
        }
    }
}
