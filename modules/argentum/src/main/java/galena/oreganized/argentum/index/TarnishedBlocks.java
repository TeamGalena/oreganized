package galena.oreganized.argentum.index;

import com.mojang.datafixers.util.Pair;
import java.util.stream.Stream;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public record TarnishedBlocks<T extends Block>(DeferredBlock<T> base, DeferredBlock<T> blemished,
                                               DeferredBlock<T> tarnished) {

    public Stream<DeferredBlock<T>> all() {
        return Stream.of(base, blemished, tarnished);
    }

    public Stream<Pair<DeferredBlock<T>, Integer>> indexed() {
        return Stream.of(new Pair<>(base, 0), new Pair<>(blemished, 1), new Pair<>(tarnished, 2));
    }

    public DeferredBlock<T> get(int index) {
        return array()[index];
    }

    @SuppressWarnings("unchecked")
    public DeferredBlock<T>[] array() {
        return new DeferredBlock[]{base, blemished, tarnished};
    }

}
