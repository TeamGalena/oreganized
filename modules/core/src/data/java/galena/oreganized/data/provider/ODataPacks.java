package galena.oreganized.data.provider;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.providers.RegistrateDatapackProvider;
import galena.oreganized.data.ODatagen;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.common.conditions.ICondition;

public class ODataPacks extends RegistrateDatapackProvider implements BiConsumer<ResourceKey<?>, ICondition> {

    private final AbstractRegistrate<?> owner;

    public ODataPacks(AbstractRegistrate<?> parent, PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(parent, output, provider);
        this.owner = parent;
    }

    @Override
    public String getName() {
        return "Data-Driven Registries";
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        owner.genData(ODatagen.DYNAMIC, this);
        return super.run(output);
    }

    @Override
    public void accept(ResourceKey<?> key, ICondition condition) {
        conditions.computeIfAbsent(key, $ -> new ArrayList<>()).add(condition);
    }

}
