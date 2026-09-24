package galena.oreganized.index.sets;

import java.util.stream.Stream;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;

public interface IBlockSet {

    // TODO sets use DeferredHolder instead everywhere
    Stream<DeferredBlock<?>> stream();

    default DeferredBlock<?>[] array() {
        return stream().toArray(DeferredBlock[]::new);
    }

    default Stream<ResourceKey<Block>> keys() {
        return stream().map(DeferredHolder::getKey);
    }

}
