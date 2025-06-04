package galena.oreganized.content.block;

import galena.oreganized.index.OEffects;
import galena.oreganized.index.OParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class AsbestosBlock extends Block {
    public AsbestosBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {

        var vec = Vec3.atCenterOf(pPos).add(0,0.05,0);
        var cloud = new AreaEffectCloud(pLevel, vec.x, vec.y, vec.z);
        cloud.addEffect(new MobEffectInstance(OEffects.LUNG_DAMAGE.get(),50));
        cloud.setParticle(OParticleTypes.ASBESTOS_CLOUD.get());
        cloud.setRadius(2F);
        cloud.setRadiusPerTick(-0.01F);
        cloud.setDuration((int) (80));
        pLevel.addFreshEntity(cloud);
        super.randomTick(pState, pLevel, pPos, pRandom);
    }

    @Override
    public void fallOn(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, float pFallDistance) {
        var vec = Vec3.atCenterOf(pPos).add(0,0.2,0);
        var cloud = new AreaEffectCloud(pLevel, vec.x, vec.y, vec.z);

        cloud.addEffect(new MobEffectInstance(OEffects.LUNG_DAMAGE.get(),50));
        cloud.setParticle(OParticleTypes.ASBESTOS_CLOUD.get());
        cloud.setRadius(4F);
        cloud.setRadiusPerTick(-0.02F);
        cloud.setDuration((int) (120));

        pLevel.addFreshEntity(cloud);
        super.fallOn(pLevel, pState, pPos, pEntity, pFallDistance);
    }
}
