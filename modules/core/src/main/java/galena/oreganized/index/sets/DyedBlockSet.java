package galena.oreganized.index.sets;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public record DyedBlockSet<R extends Block>(
        Map<DyeColor, DeferredBlock<R>> map) implements IDyedSet<Block, R, DeferredBlock<R>> {

    /**
     * uses a LinkedHashMap internally to preserve the order of the stream
     */
    public static <R extends Block> DyedBlockSet<R> create(Stream<DyeColor> dyes, Function<DyeColor, DeferredBlock<R>> factory) {
        return new DyedBlockSet<>(dyes.collect(Collectors.toMap(Function.identity(), factory, (a, b) -> {
            throw new RuntimeException("duplicate key for values %s and %s".formatted(a.getKey().location(), b.getKey().location()));
        }, LinkedHashMap::new)));
    }

}
