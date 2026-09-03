package galena.oreganized.register;

import com.teamabnormals.blueprint.core.util.registry.AbstractSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SimpleRegistryHelper<T> extends AbstractSubRegistryHelper<T, DeferredRegister<T>> {

    public SimpleRegistryHelper(RegistryHelper parent, ResourceKey<Registry<T>> registry) {
        super(parent, DeferredRegister.create(registry, parent.getModId()));
    }

    public final <R extends T> DeferredHolder<T, R> create(String name, Function<ResourceLocation, R> factory) {
        return deferredRegister.register(name, factory);
    }
}
