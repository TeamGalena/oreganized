package galena.oreganized.data;

import galena.oreganized.Oreganized;
import galena.oreganized.index.ODamageSources;
import galena.oreganized.index.OFeatures;
import galena.oreganized.index.OTrimMaterials;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

public class ORegistries extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, OFeatures.Configured::bootstrap)
            .add(Registries.PLACED_FEATURE, OFeatures.Placed::bootstrap)
            .add(Registries.DAMAGE_TYPE, ODamageSources::bootStrap)
            .add(Registries.TRIM_MATERIAL, OTrimMaterials::bootstrap);

    public ORegistries(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, future, BUILDER, Set.of("minecraft", Oreganized.MOD_ID));
    }
}
