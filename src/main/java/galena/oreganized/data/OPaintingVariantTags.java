package galena.oreganized.data;

import galena.oreganized.Oreganized;
import galena.oreganized.index.OPaintingVariants;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.common.data.ExistingFileHelper;

public class OPaintingVariantTags extends PaintingVariantTagsProvider {

    public OPaintingVariantTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future, ExistingFileHelper helper) {
        super(output, future, Oreganized.MOD_ID, helper);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        for (Supplier<? extends PaintingVariant> variant : OPaintingVariants.PAINTING_VARIANTS.getEntries()) {
            this.tag(PaintingVariantTags.PLACEABLE).add(ResourceKey.create(Registries.PAINTING_VARIANT, new ResourceLocation(variant.get().toString())));
        }
    }
}
