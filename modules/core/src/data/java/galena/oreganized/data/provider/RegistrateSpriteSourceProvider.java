package galena.oreganized.data.provider;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateProvider;
import java.util.concurrent.CompletableFuture;
import net.minecraft.client.renderer.texture.atlas.SpriteSource;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.LogicalSide;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SpriteSourceProvider;

public final class RegistrateSpriteSourceProvider extends SpriteSourceProvider implements RegistrateProvider {

    public static final ResourceLocation BLOCKS_ATLAS = SpriteSourceProvider.BLOCKS_ATLAS;

    public static final ProviderType<RegistrateSpriteSourceProvider> PROVIDER = ProviderType.registerProvider("sprite_sources", context ->
            new RegistrateSpriteSourceProvider(context.parent(), context.output(), context.provider(), context.fileHelper())
    );

    private final AbstractRegistrate<?> owner;

    private RegistrateSpriteSourceProvider(AbstractRegistrate<?> owner, PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper fileHelper) {
        super(output, provider, owner.getModid(), fileHelper);
        this.owner = owner;
    }

    @Override
    public LogicalSide getSide() {
        return LogicalSide.CLIENT;
    }

    @Override
    protected void gather() {
        owner.genData(PROVIDER, this);
    }

    public SourceList addAtlasSource(ResourceLocation id, SpriteSource source) {
        return atlas(id).addSource(source);
    }

}
