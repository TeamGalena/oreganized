package galena.oreganized.data;

import galena.oreganized.Oreganized;
import galena.oreganized.index.OPaintingVariants;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class OPaintingVariantTags extends PaintingVariantTagsProvider {

    public OPaintingVariantTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future, ExistingFileHelper helper) {
        super(output, future, Oreganized.MOD_ID, helper);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        OPaintingVariants.PAINTING_VARIANTS.getEntries().forEach(it -> {
            tag(PaintingVariantTags.PLACEABLE).add(ResourceKey.create(Registries.PAINTING_VARIANT, it.getId()));
        });
    }
}
