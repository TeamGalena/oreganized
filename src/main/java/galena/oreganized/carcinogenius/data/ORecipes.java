package galena.oreganized.carcinogenius.data;

import galena.oreganized.carcinogenius.OreganizedCarcinogenius;
import galena.oreganized.carcinogenius.data.provider.ORecipeProvider;
import galena.oreganized.carcinogenius.index.OCBlocks;
import galena.oreganized.carcinogenius.index.OCItems;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;

public class ORecipes extends ORecipeProvider {

    public ORecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        smeltingRecipe(OCItems.REFINED_ASBESTOS.get(), OCItems.RAW_ASBESTOS.get(), 0.1F).save(consumer, OreganizedCarcinogenius.modLoc("asbestos_from_raw_asbestos_smelting"));
        blastingRecipe(OCItems.REFINED_ASBESTOS.get(), OCItems.RAW_ASBESTOS.get(), 0.1F).save(consumer, OreganizedCarcinogenius.modLoc("asbestos_from_raw_asbestos_blasting"));

        compact(OCBlocks.ASBESTOS_BLOCK.get().asItem(), OCItems.REFINED_ASBESTOS.get()).save(consumer);
        compact(OCBlocks.RAW_ASBESTOS_BLOCK.get().asItem(), OCItems.RAW_ASBESTOS.get()).save(consumer);

        unCompact(OCItems.RAW_ASBESTOS.get(), OCBlocks.RAW_ASBESTOS_BLOCK.get().asItem()).save(consumer, OreganizedCarcinogenius.modLoc("raw_asbestos_from_block"));
        unCompact(OCItems.REFINED_ASBESTOS.get(), OCBlocks.ASBESTOS_BLOCK.get().asItem()).save(consumer, OreganizedCarcinogenius.modLoc("asbestos_from_block"));
    }
}
