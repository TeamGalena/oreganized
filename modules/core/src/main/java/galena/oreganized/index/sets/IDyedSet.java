package galena.oreganized.index.sets;

import java.util.Map;
import java.util.stream.Stream;

import net.minecraft.world.item.DyeColor;
import net.neoforged.neoforge.registries.DeferredHolder;

public interface IDyedSet<T, R extends T, D extends DeferredHolder<T, R>> extends IHolderSet<T, R, D> {

    Map<DyeColor, D> map();

    default D get(DyeColor dye) {
        return map().get(dye);
    }

    default Stream<Map.Entry<DyeColor, D>> entries() {
        return map().entrySet().stream();
    }

    @Override
    default Stream<D> stream() {
        return entries().map(Map.Entry::getValue);
    }

}
