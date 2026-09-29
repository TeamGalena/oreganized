package galena.oreganized.argentum.event;

import galena.oreganized.argentum.config.ArgentumConfigs;
import galena.oreganized.argentum.index.ArgentumTags;
import galena.oreganized.argentum.world.TarnishBlockManager;

import java.util.Arrays;
import java.util.Optional;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber
public class TarnishEvents {

    @SubscribeEvent
    private static void onEntityDie(LivingDeathEvent event) {
        var entity = event.getEntity();

        if (entity.getType().is(ArgentumTags.Entities.TARNISHES)) {
            tarnishArea(entity.level(), entity.blockPosition(), entity.getRandom());
        }
    }

    @SubscribeEvent
    private static void onEntitySpawn(FinalizeSpawnEvent event) {
        var entity = event.getEntity();
        if (!entity.getType().is(ArgentumTags.Entities.TARNISHES)) return;

        Optional.ofNullable(event.getSpawner())
                .flatMap(it -> it.left())
                .ifPresent(spawner -> {
                    tarnishArea(entity.level(), spawner.getBlockPos(), entity.getRandom());
                });
    }

    private static void tarnishArea(Level level, BlockPos center, RandomSource random) {
        int radius = ArgentumConfigs.COMMON.tarnishRadius.get();
        int maxIter = ArgentumConfigs.COMMON.tarnishChecksPerMob.get();
        int maxConversions = ArgentumConfigs.COMMON.maximumTarnishesPerMob.get();

        var mutable = center.mutable();

        for (int x = 0; x < 2; x++)
            for (int y = 0; y < 2; y++)
                for (int z = 0; z < 2; z++) {
                    mutable.set(x, y, z);
                    TarnishBlockManager.tryTarnishing(mutable, level);
                }

        for (int i = 0, conversions = 0; i < maxIter && conversions < maxConversions; i++) {
            var directionA = Direction.getRandom(random);
            var directionB = Util.getRandom(Arrays.stream(Direction.values()).filter(it -> it.getAxis() != directionA.getAxis()).toList(), random);

            var pos = center
                    .relative(directionA, random.nextIntBetweenInclusive(2, radius))
                    .relative(directionB, random.nextIntBetweenInclusive(0, radius));

            if (TarnishBlockManager.tryTarnishing(pos, level)) {
                conversions++;
            }
        }
    }

}
