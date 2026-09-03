package galena.oreganized.world;

import com.google.common.collect.HashBiMap;
import galena.oreganized.OConstants;
import galena.oreganized.api.Tarnishable;
import galena.oreganized.index.ODataMapTypes;
import galena.oreganized.index.OSoundEvents;
import galena.oreganized.network.packet.TarnishParticlePacket;
import java.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.registries.datamaps.DataMapsUpdatedEvent;

@EventBusSubscriber
public class TarnishBlockManager {

    private static final Map<Block, Block> INVERSE = HashBiMap.create();
    private static final Set<Block> PRISTINE = new HashSet<>();

    @SubscribeEvent
    public static void reload(DataMapsUpdatedEvent event) {
        event.ifRegistry(Registries.BLOCK, registry -> {
            OConstants.LOGGER.debug("reloading tarnishables");

            var map = registry.getDataMap(ODataMapTypes.TARNISHABLES);

            PRISTINE.clear();
            var stages = new HashSet<Block>();

            map.forEach((key, tarnishable) -> {
                var block = BuiltInRegistries.BLOCK.get(key);
                stages.add(tarnishable.nextStage());
                INVERSE.put(tarnishable.nextStage(), block);
                PRISTINE.add(block);
            });

            PRISTINE.removeAll(stages);
        });
    }

    public static boolean isPristine(Block b) {
        return PRISTINE.contains(b);
    }

    public static Optional<Block> next(Holder<Block> block) {
        return Optional.ofNullable(block.getData(ODataMapTypes.TARNISHABLES))
                .map(Tarnishable::nextStage);
    }

    public static Optional<BlockState> next(BlockState state) {
        return next(state.getBlockHolder())
                .map(it -> it.withPropertiesOf(state));
    }

    public static Optional<Block> previous(Holder<Block> block) {
        return Optional.ofNullable(INVERSE.get(block.value()));
    }

    public static Optional<BlockState> previous(BlockState state) {
        return previous(state.getBlockHolder())
                .map(it -> it.withPropertiesOf(state));
    }

    public static boolean canTarnish(Holder<Block> block) {
        return block.getData(ODataMapTypes.TARNISHABLES) != null;
    }

    public static boolean canPolish(Holder<Block> block) {
        return previous(block).isPresent();
    }

    public static boolean tryTarnishing(BlockPos pos, Level level) {
        var state = level.getBlockState(pos);

        return TarnishBlockManager.next(state).filter(tarnished -> {
            boolean isPristine = TarnishBlockManager.isPristine(state.getBlock());
            if (!isPristine && hasPristineAround(pos, level)) return false;

            level.setBlockAndUpdate(pos, tarnished);
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.playSound(null, pos, OSoundEvents.TARNISH.get(), SoundSource.BLOCKS);
                PacketDistributor.sendToPlayersInDimension(serverLevel, new TarnishParticlePacket(pos, true));
            }

            return true;
        }).isPresent();
    }

    public static boolean tryPolishing(BlockPos pos, Level level) {
        var state = level.getBlockState(pos);

        return TarnishBlockManager.previous(state).filter(previous -> {
            level.setBlockAndUpdate(pos, previous);
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.playSound(null, pos, OSoundEvents.POLISH_FINISH.get(), SoundSource.BLOCKS);
                PacketDistributor.sendToPlayersInDimension(serverLevel, new TarnishParticlePacket(pos, false));
            }

            return true;
        }).isPresent();
    }

    private static boolean hasPristineAround(BlockPos pos, Level level) {
        for (var dir : Direction.values()) {
            var checkPos = pos.relative(dir);
            var checkState = level.getBlockState(checkPos);
            if (TarnishBlockManager.isPristine(checkState.getBlock())) {
                return true;
            }
        }
        return false;
    }

}
