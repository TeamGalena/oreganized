package galena.oreganized.content.effect;

import galena.oreganized.Oreganized;
import galena.oreganized.index.OCriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.api.distmarker.Dist;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = Oreganized.MOD_ID, value = Dist.CLIENT)
public class StunningEffect extends MobEffect {

    private static final String SLOWNESS_UUID = "019150f6-e85e-777f-a566-1eafd7c7e1a5";
    public static final int MAX_AMPLIFIER = 9;

    public StunningEffect() {
        super(MobEffectCategory.HARMFUL, 0x6e66a4);
        addAttributeModifier(Attributes.MOVEMENT_SPEED, SLOWNESS_UUID, -0.075, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (entity.level().getGameTime() % 5 != 0L) return;

        var health = entity.getHealth() / entity.getMaxHealth();
        var targetAmplifier = (int) Math.ceil((1.0 - health) * MAX_AMPLIFIER);

        if (entity.getHealth() <= 1 && entity instanceof ServerPlayer player) {
            OCriteriaTriggers.PROFOUND_BRAIN_DAMAGE.trigger(player);
        }

        if (targetAmplifier == amplifier) return;
        var instance = entity.getEffect(this);
        if (instance == null) return;

        var step = targetAmplifier > amplifier ? 1 : -1;

        if (instance.update(new MobEffectInstance(this, instance.getDuration() + 1, amplifier + step, instance.isAmbient(), instance.isVisible(), instance.showIcon()))) {
            addAttributeModifiers(entity, entity.getAttributes(), instance.getAmplifier());
            if (entity.level().isClientSide) {
                entity.level().playSound(entity, entity.blockPosition(), SoundEvents.SCULK_BLOCK_SPREAD, SoundSource.PLAYERS, instance.getAmplifier() * 0.8F / MAX_AMPLIFIER + 0.2F, entity.getRandom().nextFloat() * 0.2F + 0.8F);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
