package galena.oreganized.content.effect;

import galena.oreganized.Oreganized;
import galena.oreganized.index.OCriteriaTriggers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.jetbrains.annotations.NotNull;

public class StunningEffect extends MobEffect {

    private static final ResourceLocation SLOWNESS_ID = Oreganized.modLoc("stunning_slowness_debuff");
    public static final int MAX_AMPLIFIER = 9;

    public StunningEffect() {
        super(MobEffectCategory.HARMFUL, 0x6e66a4);
        addAttributeModifier(Attributes.MOVEMENT_SPEED, SLOWNESS_ID, -0.075, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (entity.level().getGameTime() % 5 != 0L) return true;

        var health = entity.getHealth() / entity.getMaxHealth();
        var targetAmplifier = (int) Math.ceil((1.0 - health) * MAX_AMPLIFIER);

        if (entity.getHealth() <= 1 && entity instanceof ServerPlayer player) {
            OCriteriaTriggers.PROFOUND_BRAIN_DAMAGE.get().trigger(player);
        }

        if (targetAmplifier == amplifier) return true;
        var holder = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(this);
        var instance = entity.getEffect(holder);
        if (instance == null) return true;

        var step = targetAmplifier > amplifier ? 1 : -1;

        if (instance.update(new MobEffectInstance(instance.getEffect(), instance.getDuration() + 1, amplifier + step, instance.isAmbient(), instance.isVisible(), instance.showIcon()))) {
            addAttributeModifiers(entity.getAttributes(), instance.getAmplifier());
            if (entity.level().isClientSide) {
                entity.level().playSound(entity, entity.blockPosition(), SoundEvents.SCULK_BLOCK_SPREAD, SoundSource.PLAYERS, instance.getAmplifier() * 0.8F / MAX_AMPLIFIER + 0.2F, entity.getRandom().nextFloat() * 0.2F + 0.8F);
            }
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int p_295329_, int p_295167_) {
        return true;
    }
}
