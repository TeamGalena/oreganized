package galena.oreganized.client;

import galena.oreganized.content.item.DeviceItem;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class DevicePropertyFunction implements ItemPropertyFunction {

    private double current;
    private double motion;
    private long lastUpdateTick;

    @Override
    public float call(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int i) {
        if (level == null) return 0;
        return Mth.clamp(wobble(level, Math.random()), 0F, 1F) * DeviceItem.FRAMES;
    }

    private float wobble(Level level, double target) {
        if (level.getGameTime() != lastUpdateTick) {
            lastUpdateTick = level.getGameTime();
            double diff = target - current;
            motion += diff * 0.1;
            motion *= 0.9;
            current = current + motion;
        }

        return (float) current;
    }

}
