package galena.oreganized.client.particle;

import galena.oreganized.index.OParticleTypes;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.api.distmarker.OnlyIn;

public class CustomDrippingParticle extends TextureSheetParticle {
    private final Fluid type;
    protected boolean isGlowing;

    CustomDrippingParticle( ClientLevel pLevel, double pX, double pY, double pZ, Fluid pType) {
        super(pLevel, pX, pY, pZ);
        this.setSize(0.01F, 0.01F);
        this.gravity = 0.06F;
        this.type = pType;
    }

    protected Fluid getType() {
        return this.type;
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public int getLightColor(float pPartialTick) {
        return this.isGlowing ? 240 : super.getLightColor(pPartialTick);
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.preMoveUpdate();
        if (!this.removed) {
            this.yd -= (double)this.gravity;
            this.move(this.xd, this.yd, this.zd);
            this.postMoveUpdate();
            if (!this.removed) {
                this.xd *= (double)0.98F;
                this.yd *= (double)0.98F;
                this.zd *= (double)0.98F;
                BlockPos blockpos = new BlockPos((int) this.x, (int) this.y, (int) this.z);
                FluidState fluidstate = this.level.getFluidState(blockpos);
                if (fluidstate.getType() == this.type && this.y < (double)((float)blockpos.getY() + fluidstate.getHeight(this.level, blockpos))) {
                    this.remove();
                }

            }
        }
    }

    protected void preMoveUpdate() {
        if (this.lifetime-- <= 0) {
            this.remove();
        }

    }

    protected void postMoveUpdate() {
    }

    static class CoolingDripHangParticle extends DripHangParticle {
        CoolingDripHangParticle(ClientLevel p_106068_, double p_106069_, double p_106070_, double p_106071_, Fluid p_106072_, ParticleOptions p_106073_) {
            super(p_106068_, p_106069_, p_106070_, p_106071_, p_106072_, p_106073_);
        }

        protected void preMoveUpdate() {
            this.rCol = Math.max( 0.55F - ((float) (40 - this.lifetime) / 10.0F) / 8.0F , 0.35F );
            this.gCol = Math.max(0.44F - ((float)(40 - this.lifetime) / 10.0F) / 8.0F, 0.24F);
            this.bCol = Math.max(0.62F - ((float)(40 - this.lifetime) / 10.0F) / 8.0F, 0.42F);
            super.preMoveUpdate();
        }
    }

    static class DripHangParticle extends CustomDrippingParticle {
        private final ParticleOptions fallingParticle;

        DripHangParticle(ClientLevel pLevel, double pX, double pY, double pZ, Fluid pType, ParticleOptions pFallingParticle) {
            super(pLevel, pX, pY, pZ, pType);
            this.fallingParticle = pFallingParticle;
            this.gravity *= 0.02F;
            this.lifetime = 40;
        }

        protected void preMoveUpdate() {
            if (this.lifetime-- <= 0) {
                this.remove();
                this.level.addParticle(this.fallingParticle, this.x, this.y, this.z, this.xd, this.yd, this.zd);
            }

        }

        protected void postMoveUpdate() {
            this.xd *= 0.02D;
            this.yd *= 0.02D;
            this.zd *= 0.02D;
        }
    }

    static class DripLandParticle extends CustomDrippingParticle{
         DripLandParticle(ClientLevel p_106102_, double p_106103_, double p_106104_, double p_106105_, Fluid p_106106_) {
            super(p_106102_, p_106103_, p_106104_, p_106105_, p_106106_);
            this.lifetime = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
        }
    }

    static class FallAndLandParticle extends FallingParticle {
        protected final ParticleOptions landParticle;

        FallAndLandParticle(ClientLevel pLevel, double pX, double pY, double pZ, Fluid pType, ParticleOptions pLandParticle) {
            super(pLevel, pX, pY, pZ, pType);
            this.landParticle = pLandParticle;
        }

        protected void postMoveUpdate() {
            if (this.onGround) {
                this.remove();
                this.level.addParticle(this.landParticle, this.x, this.y, this.z, 0.0D, 0.0D, 0.0D);
            }

        }
    }

    static class FallingParticle extends CustomDrippingParticle {
        FallingParticle(ClientLevel p_106132_, double p_106133_, double p_106134_, double p_106135_, Fluid p_106136_) {
            this(p_106132_, p_106133_, p_106134_, p_106135_, p_106136_, (int)(64.0D / (Math.random() * 0.8D + 0.2D)));
        }

        FallingParticle(ClientLevel pLevel, double pX, double pY, double pZ, Fluid pType, int pLifetime) {
            super(pLevel, pX, pY, pZ, pType);
            this.lifetime = pLifetime;
        }

        protected void postMoveUpdate() {
            if (this.onGround) {
                this.remove();
            }

        }
    }

    public static class LeadFallProvider implements ParticleProvider<SimpleParticleType> {
        protected final SpriteSet sprite;

        public LeadFallProvider(SpriteSet pSprites) {
            this.sprite = pSprites;
        }

        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            CustomDrippingParticle.FallAndLandParticle dripparticle = new CustomDrippingParticle.FallAndLandParticle(pLevel, pX, pY, pZ, Fluids.LAVA, OParticleTypes.LANDING_LEAD.get());
            dripparticle.setColor(0.35F, 0.24F, 0.43F);
            dripparticle.pickSprite(this.sprite);
            return dripparticle;
        }
    }

    public static class LeadHangProvider implements ParticleProvider<SimpleParticleType> {
        protected final SpriteSet sprite;

        public LeadHangProvider(SpriteSet pSprites) {
            this.sprite = pSprites;
        }

        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            CustomDrippingParticle.CoolingDripHangParticle dripparticle$coolingdriphangparticle = new CustomDrippingParticle.CoolingDripHangParticle(pLevel, pX, pY, pZ, Fluids.LAVA, OParticleTypes.FALLING_LEAD.get());
            dripparticle$coolingdriphangparticle.setColor(0.35F, 0.24F, 0.43F);
            dripparticle$coolingdriphangparticle.pickSprite(this.sprite);
            return dripparticle$coolingdriphangparticle;
        }
    }

    public static class LeadLandProvider implements ParticleProvider<SimpleParticleType> {
        protected final SpriteSet sprite;

        public LeadLandProvider(SpriteSet pSprites) {
            this.sprite = pSprites;
        }

        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            CustomDrippingParticle dripparticle = new CustomDrippingParticle.DripLandParticle(pLevel, pX, pY, pZ, Fluids.LAVA);
            dripparticle.setColor(0.35F, 0.24F, 0.43F);
            dripparticle.pickSprite(this.sprite);
            return dripparticle;
        }
    }


}