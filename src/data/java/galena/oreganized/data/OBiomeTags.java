package galena.oreganized.data;

import galena.oreganized.Oreganized;
import galena.oreganized.index.OTags;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

public class OBiomeTags extends BiomeTagsProvider {

    public OBiomeTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future, @Nullable ExistingFileHelper helper) {
        super(output, future, Oreganized.MOD_ID, helper);
    }

    @Override
    public @NotNull String getName() {
        return "Oreganized Biome Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(OTags.Biomes.HAS_BOULDER)
                .addTag(OTags.Biomes.RICH_IN_LEAD_ORE)
                .addTag(Tags.Biomes.IS_PLAINS);
        tag(OTags.Biomes.RICH_IN_LEAD_ORE).addTag(BiomeTags.IS_SAVANNA);
        tag(OTags.Biomes.HAS_DATURA).addTags(BiomeTags.IS_SAVANNA);
        tag(OTags.Biomes.HAS_SPARSE_DATURA).add(Biomes.PLAINS);
    }
}
