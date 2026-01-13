package galena.oreganized.index;

import java.util.stream.Stream;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public record TarnishedBlocks<T extends Block>(DeferredBlock<T> base, DeferredBlock<T> blemished,
                                               DeferredBlock<T> tarnished) {

    public Stream<DeferredBlock<T>> all() {
        return Stream.of(base, blemished, tarnished);
    }

    @SuppressWarnings("unchecked")
    public DeferredBlock<T>[] array() {
        return new DeferredBlock[]{base, blemished, tarnished};
    }

}
