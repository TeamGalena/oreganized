package galena.oreganized.content.effect;

import galena.oreganized.Oreganized;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = Oreganized.MOD_ID, value = Dist.CLIENT)
public class LungDamageEffect extends MobEffect {

    private static final String SLOWNESS_UUID = "019150f6-e85e-777f-a566-1eafd7c7e1a5";
    public static final int MAX_AMPLIFIER = 9;

    public LungDamageEffect() {
        super(MobEffectCategory.HARMFUL, 0xAAAAAA);

    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        int div = 40;
        if(entity instanceof Player player && player.isSprinting()){
            div = 10;
        }
        if(entity.isUnderWater()){
            return;
        }
       if(entity.level().getGameTime() % div == 0){
           entity.hurt(entity.damageSources().inWall(),amplifier+1);
       }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
