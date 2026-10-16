package galena.oreganized.data;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import galena.oreganized.register.ORegistryHelper;

import java.util.Collection;
import java.util.Objects;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ORegistrate extends AbstractRegistrate<ORegistrate> {

    private final String moduleId;

    private ORegistrate(String modid, String moduleId) {
        super(modid);
        this.moduleId = moduleId;
    }

    static ORegistrate create(String modid) {
        var active = ModLoadingContext.get().getActiveContainer();
        Objects.requireNonNull(active.getEventBus(), "no active mod found");

        var registrate = new ORegistrate(modid, active.getModId());
        registrate.registerEventListeners(active.getEventBus());

        // TODO modular this is only necessary to load `extraLang` once
        registrate.addRawLang("dummy." + active.getModId(), "Dummy Entry");

        return registrate;
    }

    private <R, T extends R> Collection<RegistryEntry<R, T>> getFromHelper(ResourceKey<Registry<R>> type) {
        var registryHelper = ORegistryHelper.forModule(moduleId)
                .orElseThrow(() -> new NullPointerException("no registry helper found for module"));

        var deferred = registryHelper.getSubHelper(type).getDeferredRegister();
        return deferred.getEntries().stream()
                .map(DeferredHolder::getKey)
                .map(DeferredHolder::<R, T>create)
                .map(it -> new RegistryEntry<>(this, it))
                .toList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <R, T extends R> Collection<RegistryEntry<R, T>> getAll(ResourceKey<? extends Registry<R>> type) {
        var key = (ResourceKey<Registry<R>>) type;

        if (key.equals(Registries.BLOCK)) {
            return getFromHelper(key);
        }

        return super.getAll(type);
    }
}
