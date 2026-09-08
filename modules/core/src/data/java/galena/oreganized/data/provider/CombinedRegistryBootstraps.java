package galena.oreganized.data.provider;

import java.util.*;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistrySetBuilder.RegistryBootstrap;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class CombinedRegistryBootstraps {

    private final Map<ResourceKey<Registry<?>>, Combined<?>> entries = new HashMap<>();

    public <T> Optional<RegistryBootstrap<T>> combine(ResourceKey<Registry<T>> registry, RegistryBootstrap<T> bootstrap) {
        var castMap = (Map<ResourceKey<Registry<T>>, Combined<T>>) (Object) entries;
        var exists = entries.containsKey(registry);
        var combined = castMap.computeIfAbsent(registry, $ -> new Combined<>());

        combined.add(bootstrap);

        if (exists) return Optional.empty();
        return Optional.of(bootstrap);
    }

    private static class Combined<T> implements RegistryBootstrap<T> {

        private final List<RegistryBootstrap<T>> bootstraps = new ArrayList<>();

        public void add(RegistryBootstrap<T> bootstrap) {
            this.bootstraps.add(bootstrap);
        }

        @Override
        public void run(BootstrapContext<T> context) {
            bootstraps.forEach(it -> it.run(context));
        }
    }

}
