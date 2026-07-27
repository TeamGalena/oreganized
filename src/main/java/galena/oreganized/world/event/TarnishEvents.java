package galena.oreganized.world.event;

import galena.oreganized.Oreganized;
import galena.oreganized.OreganizedConfig;
import galena.oreganized.world.TarnishManager;
import net.minecraft.tags.EntityTypeTags;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber(modid = Oreganized.MOD_ID)
public class TarnishEvents {

    @SubscribeEvent
    public static void onEntityDie(LivingDeathEvent event) {
        var entity = event.getEntity();

        if (entity.getType().is(EntityTypeTags.UNDEAD)) {
            var center = entity.blockPosition();
            int radius = OreganizedConfig.COMMON.tarnishRadius.get();
            int maxIter = OreganizedConfig.COMMON.tarnishChecksPerMob.get();
            for (int i = 0; i < maxIter; i++) {
                var random = entity.getRandom();
                var randomPos = center.offset(
                        random.nextIntBetweenInclusive(-radius, radius),
                        random.nextIntBetweenInclusive(-radius, radius),
                        random.nextIntBetweenInclusive(-radius, radius)
                );

                var level = entity.level();

                TarnishManager.tryTarnishing(randomPos, level);
            }
        }
    }

}
