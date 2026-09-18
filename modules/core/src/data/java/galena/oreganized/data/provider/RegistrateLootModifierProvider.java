package galena.oreganized.data.provider;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateProvider;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.fml.LogicalSide;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

public final class RegistrateLootModifierProvider extends GlobalLootModifierProvider implements RegistrateProvider {

    public static final ProviderType<RegistrateLootModifierProvider> PROVIDER = ProviderType.registerServerData("loot_modifier", RegistrateLootModifierProvider::new);

    private final AbstractRegistrate<?> owner;

    private RegistrateLootModifierProvider(AbstractRegistrate<?> owner, PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, owner.getModid());
        this.owner = owner;
    }

    @Override
    public LogicalSide getSide() {
        return LogicalSide.SERVER;
    }

    @Override
    protected void start() {
        owner.genData(PROVIDER, this);
    }

}
