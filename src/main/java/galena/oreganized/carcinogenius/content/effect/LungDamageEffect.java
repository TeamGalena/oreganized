package galena.oreganized.carcinogenius.content.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class LungDamageEffect extends MobEffect {

    public LungDamageEffect() {
        super(MobEffectCategory.HARMFUL, 0xAAAAAA);
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        int div = 40;
        if (entity instanceof Player player && player.isSprinting()) {
            div = 10;
        }
        if (entity.isUnderWater()) {
            return true;
        }
        if (entity.level().getGameTime() % div == 0) {
            entity.hurt(entity.damageSources().inWall(), amplifier + 1);
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

}
