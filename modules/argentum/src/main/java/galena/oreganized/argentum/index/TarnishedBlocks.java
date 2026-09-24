package galena.oreganized.argentum.index;

import com.mojang.datafixers.util.Pair;
import galena.oreganized.index.sets.IHolderSet;
import java.util.stream.Stream;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public record TarnishedBlocks<T extends Block>(DeferredBlock<T> base, DeferredBlock<T> blemished,
                                               DeferredBlock<T> tarnished) implements IHolderSet<Block, T, DeferredBlock<T>> {

    public Stream<Pair<DeferredBlock<T>, Integer>> indexed() {
        return Stream.of(new Pair<>(base, 0), new Pair<>(blemished, 1), new Pair<>(tarnished, 2));
    }

    public DeferredBlock<T> get(int index) {
        return stream().toList().get(index);
    }

    @Override
    public Stream<DeferredBlock<T>> stream() {
        return Stream.of(base, blemished, tarnished);
    }

}
