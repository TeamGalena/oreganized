package galena.oreganized.data.provider;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateDatapackProvider;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.common.conditions.ICondition;
import org.jetbrains.annotations.ApiStatus;

// currently needed because of https://github.com/tterrag1098/Registrate/issues/96
// re-running data can lead to ProviderType.DYNAMIC & this PROVIDER to run in the wrong order, run again until working
// please god I hope Registrate somehow adds a better solution for this
@ApiStatus.Internal
public final class ODataPacks extends RegistrateDatapackProvider implements BiConsumer<ResourceKey<?>, ICondition> {

    public static final ProviderType<ODataPacks> PROVIDER = ProviderType.registerServerData("conditional_dynamic", ODataPacks::new);

    private final AbstractRegistrate<?> owner;

    private ODataPacks(AbstractRegistrate<?> parent, PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(parent, output, provider);
        this.owner = parent;
    }

    @Override
    public String getName() {
        return "Data-Driven Registries";
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        owner.genData(PROVIDER, this);
        return super.run(output);
    }

    @Override
    public void accept(ResourceKey<?> key, ICondition condition) {
        conditions.computeIfAbsent(key, $ -> new ArrayList<>()).add(condition);
    }

}
