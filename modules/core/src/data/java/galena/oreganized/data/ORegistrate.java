package galena.oreganized.data;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import galena.oreganized.OConstants;
import java.util.Collection;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ORegistrate extends AbstractRegistrate<ORegistrate> {

    private ORegistrate(String modid) {
        super(modid);
    }

    static ORegistrate create(String modid) {
        var registrate = new ORegistrate(modid);
        var modBus = ModList.get()
                .getModContainerById(modid)
                .map(ModContainer::getEventBus)
                .orElseThrow();

        registrate.registerEventListeners(modBus);

        return registrate;
    }

    private <R, T extends R> Collection<RegistryEntry<R, T>> getFromHelper(ResourceKey<Registry<R>> type) {
        var deferred = OConstants.REGISTRY_HELPER.getSubHelper(type).getDeferredRegister();
        return deferred.getEntries().stream()
                .map(it -> it.getKey())
                .map(it -> DeferredHolder.<R, T>create(it))
                .map(it -> new RegistryEntry<R, T>(this, it))
                .toList();
    }

    @Override
    public <R, T extends R> Collection<RegistryEntry<R, T>> getAll(ResourceKey<? extends Registry<R>> type) {
        var key = (ResourceKey<Registry<R>>) type;

        if (key.equals(Registries.BLOCK)) {
            return getFromHelper(key);
        }

        return super.getAll(type);
    }
}
