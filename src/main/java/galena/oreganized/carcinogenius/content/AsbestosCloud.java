package galena.oreganized.carcinogenius.content;

import galena.oreganized.api.PreventableEffectCloud;
import galena.oreganized.carcinogenius.index.OCEffects;
import galena.oreganized.carcinogenius.index.OCParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class AsbestosCloud {

    public static void create(BlockPos pos, Level level, float radius, float shrinkage, int duration) {
        var vec = Vec3.atCenterOf(pos).add(0, 0.05, 0);
        var cloud = new AreaEffectCloud(level, vec.x, vec.y, vec.z);

        if (cloud instanceof PreventableEffectCloud preventable) {
            preventable.setPreventable(true);
        }

        cloud.addEffect(new MobEffectInstance(OCEffects.LUNG_DAMAGE, 50));
        cloud.setParticle(OCParticleTypes.ASBESTOS_CLOUD.get());
        cloud.setRadius(radius);
        cloud.setRadiusPerTick(-shrinkage);
        cloud.setDuration(duration);
        level.addFreshEntity(cloud);
    }

}
