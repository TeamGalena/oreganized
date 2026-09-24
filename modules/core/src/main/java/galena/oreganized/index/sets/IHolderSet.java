package galena.oreganized.index.sets;

import java.util.stream.Stream;

import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.DeferredHolder;

public interface IHolderSet<T, R extends T, D extends DeferredHolder<T, ? extends R>> {

    Stream<D> stream();

    @SuppressWarnings("unchecked")
    default D[] array() {
        return (D[]) stream().toArray();
    }

    default Stream<ResourceKey<T>> keys() {
        return stream().map(DeferredHolder::getKey);
    }

}
