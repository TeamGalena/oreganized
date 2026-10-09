package galena.oreganized.plumbum.world.particle;

import galena.oreganized.plumbum.index.PlumbumFluids;
import galena.oreganized.plumbum.index.PlumbumParticles;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class LeadFluidParticle {

    public static class FallProvider implements ParticleProvider.Sprite<SimpleParticleType> {
        public TextureSheetParticle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            var particle = new DripParticle.FallAndLandParticle(level, x, y, z, PlumbumFluids.MOLTEN_LEAD.value(), PlumbumParticles.LANDING_LEAD.value());
            particle.setColor(0.35F, 0.24F, 0.43F);
            return particle;
        }
    }

    public static class HangProvider implements ParticleProvider.Sprite<SimpleParticleType> {
        public TextureSheetParticle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            var particle = new DripParticle.DripHangParticle(level, x, y, z, PlumbumFluids.MOLTEN_LEAD.value(), PlumbumParticles.FALLING_LEAD.value());
            particle.setColor(0.35F, 0.24F, 0.43F);
            return particle;
        }
    }

    public static class LandProvider implements ParticleProvider.Sprite<SimpleParticleType> {
        public TextureSheetParticle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            var particle = new DripParticle.DripLandParticle(level, x, y, z, PlumbumFluids.MOLTEN_LEAD.value());
            particle.setColor(0.35F, 0.24F, 0.43F);
            return particle;
        }
    }

    public static class DripstoneDrippingProvider implements ParticleProvider.Sprite<SimpleParticleType> {
        public TextureSheetParticle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            var particle = new DripParticle.DripHangParticle(level, x, y, z, PlumbumFluids.MOLTEN_LEAD.value(), PlumbumParticles.FALLING_DRIPSTONE_LEAD.value());
            particle.setColor(0.35F, 0.24F, 0.43F);
            return particle;
        }
    }

    public static class DripstoneFallingProvider implements ParticleProvider.Sprite<SimpleParticleType> {
        public TextureSheetParticle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            var particle = new DripParticle.DripstoneFallAndLandParticle(level, x, y, z, PlumbumFluids.MOLTEN_LEAD.value(), PlumbumParticles.LANDING_LEAD.value());
            particle.setColor(0.35F, 0.24F, 0.43F);
            return particle;
        }
    }

}
