package galena.oreganized.world.event;

import galena.oreganized.Oreganized;
import galena.oreganized.OreganizedConfig;
import galena.oreganized.network.packet.TarnishParticlePacket;
import galena.oreganized.world.TarnishManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.network.PacketDistributor;

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
                var state = level.getBlockState(randomPos);

                tryTarnishing(state, randomPos, level, random);
            }
        }
    }

    private static void tryTarnishing(BlockState state, BlockPos pos, Level level, RandomSource random) {
        TarnishManager.next(state).ifPresent(tarnished -> {
            boolean isPristine = TarnishManager.isPristine(state.getBlock());
            if (!isPristine && hasPristineAround(pos, level)) return;

            level.setBlockAndUpdate(pos, tarnished);
            if (level instanceof ServerLevel sl)
                PacketDistributor.sendToPlayersInDimension(sl, new TarnishParticlePacket(pos, true));
        });
    }

    private static boolean hasPristineAround(BlockPos pos, Level level) {
        for (var dir : Direction.values()) {
            var checkPos = pos.relative(dir);
            var checkState = level.getBlockState(checkPos);
            if (TarnishManager.isPristine(checkState.getBlock())) {
                return true;
            }
        }
        return false;
    }

}
