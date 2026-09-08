package galena.oreganized.plumbum.world.particle;

import galena.oreganized.plumbum.index.PlumbumParticles;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class LeadFluidParticle extends TextureSheetParticle {
    private final Fluid type;
    protected boolean isGlowing;

    LeadFluidParticle(ClientLevel level, double x, double y, double z, Fluid type) {
        super(level, x, y, z);
        setSize(0.01F, 0.01F);
        this.gravity = 0.06F;
        this.type = type;
    }

    protected Fluid getType() {
        return type;
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public int getLightColor(float partialTick) {
        return isGlowing ? 240 : super.getLightColor(partialTick);
    }

    public void tick() {
        xo = x;
        yo = y;
        zo = z;
        preMoveUpdate();
        if (!removed) {
            yd -= gravity;
            move(xd, yd, zd);
            postMoveUpdate();
            if (!removed) {
                xd *= 0.98F;
                yd *= 0.98F;
                zd *= 0.98F;
                BlockPos blockpos = new BlockPos((int) x, (int) y, (int) z);
                FluidState fluidstate = level.getFluidState(blockpos);
                if (fluidstate.getType() == type && y < (double) ((float) blockpos.getY() + fluidstate.getHeight(level, blockpos))) {
                    remove();
                }

            }
        }
    }

    protected void preMoveUpdate() {
        if (lifetime-- <= 0) {
            remove();
        }

    }

    protected void postMoveUpdate() {
    }

    private static class Hang extends DrihangParticle {
        Hang(ClientLevel p_106068_, double p_106069_, double p_106070_, double p_106071_, Fluid p_106072_, ParticleOptions p_106073_) {
            super(p_106068_, p_106069_, p_106070_, p_106071_, p_106072_, p_106073_);
        }

        protected void preMoveUpdate() {
            rCol = Math.max(0.55F - ((float) (40 - lifetime) / 10.0F) / 8.0F, 0.35F);
            gCol = Math.max(0.44F - ((float) (40 - lifetime) / 10.0F) / 8.0F, 0.24F);
            bCol = Math.max(0.62F - ((float) (40 - lifetime) / 10.0F) / 8.0F, 0.42F);
            super.preMoveUpdate();
        }
    }

    private static class DrihangParticle extends LeadFluidParticle {
        private final ParticleOptions fallingParticle;

        DrihangParticle(ClientLevel level, double x, double y, double z, Fluid type, ParticleOptions fallingParticle) {
            super(level, x, y, z, type);
            this.fallingParticle = fallingParticle;
            this.gravity *= 0.02F;
            this.lifetime = 40;
        }

        protected void preMoveUpdate() {
            if (lifetime-- <= 0) {
                remove();
                level.addParticle(fallingParticle, x, y, z, xd, yd, zd);
            }

        }

        protected void postMoveUpdate() {
            xd *= 0.02D;
            yd *= 0.02D;
            zd *= 0.02D;
        }
    }

    private static class Land extends LeadFluidParticle {
        Land(ClientLevel p_106102_, double p_106103_, double p_106104_, double p_106105_, Fluid p_106106_) {
            super(p_106102_, p_106103_, p_106104_, p_106105_, p_106106_);
            lifetime = (int) (16.0D / (Math.random() * 0.8D + 0.2D));
        }
    }

    private static class Fall extends Falling {
        protected final ParticleOptions landParticle;

        Fall(ClientLevel level, double x, double y, double z, Fluid type, ParticleOptions landParticle) {
            super(level, x, y, z, type);
            this.landParticle = landParticle;
        }

        protected void postMoveUpdate() {
            if (onGround) {
                remove();
                level.addParticle(landParticle, x, y, z, 0.0D, 0.0D, 0.0D);
            }

        }
    }

    private static class Falling extends LeadFluidParticle {
        Falling(ClientLevel p_106132_, double p_106133_, double p_106134_, double p_106135_, Fluid p_106136_) {
            this(p_106132_, p_106133_, p_106134_, p_106135_, p_106136_, (int) (64.0D / (Math.random() * 0.8D + 0.2D)));
        }

        Falling(ClientLevel level, double x, double y, double z, Fluid type, int lifetime) {
            super(level, x, y, z, type);
            this.lifetime = lifetime;
        }

        protected void postMoveUpdate() {
            if (onGround) {
                remove();
            }

        }
    }

    public static class FallProvider implements ParticleProvider<SimpleParticleType> {
        protected final SpriteSet sprite;

        public FallProvider(SpriteSet sprites) {
            this.sprite = sprites;
        }

        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            var particle = new Fall(level, x, y, z, Fluids.LAVA, PlumbumParticles.FALLING_LEAD.get());
            particle.setColor(0.35F, 0.24F, 0.43F);
            particle.pickSprite(sprite);
            return particle;
        }
    }

    public static class HangProvider implements ParticleProvider<SimpleParticleType> {
        protected final SpriteSet sprite;

        public HangProvider(SpriteSet sprites) {
            this.sprite = sprites;
        }

        public Particle createParticle(SimpleParticleType type, ClientLevel level,  double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            var particle = new Hang(level, x, y, z, Fluids.LAVA, PlumbumParticles.FALLING_LEAD.get());
            particle.setColor(0.35F, 0.24F, 0.43F);
            particle.pickSprite(sprite);
            return particle;
        }
    }

    public static class LandProvider implements ParticleProvider<SimpleParticleType> {
        protected final SpriteSet sprite;

        public LandProvider(SpriteSet sprites) {
            this.sprite = sprites;
        }

        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            var particle = new Land(level, x, y, z, Fluids.LAVA);
            particle.setColor(0.35F, 0.24F, 0.43F);
            particle.pickSprite(sprite);
            return particle;
        }
    }


}
