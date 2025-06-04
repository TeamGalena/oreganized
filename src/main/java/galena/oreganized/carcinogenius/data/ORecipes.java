package galena.oreganized.carcinogenius.data;

import galena.oreganized.carcinogenius.OreganizedCarcinogenius;
import galena.oreganized.carcinogenius.data.provider.ORecipeProvider;
import galena.oreganized.carcinogenius.index.OBlocks;
import galena.oreganized.carcinogenius.index.OItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class ORecipes extends ORecipeProvider {

    public ORecipes(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        smeltingRecipe(OItems.REFINED_ASBESTOS.get(), OItems.RAW_ASBESTOS.get(), 0.1F).save(consumer, OreganizedCarcinogenius.modLoc("asbestos_from_raw_asbestos_smelting"));
        blastingRecipe(OItems.REFINED_ASBESTOS.get(), OItems.RAW_ASBESTOS.get(), 0.1F).save(consumer, OreganizedCarcinogenius.modLoc("asbestos_from_raw_asbestos_blasting"));

        compact(OBlocks.ASBESTOS_BLOCK.get().asItem(), OItems.REFINED_ASBESTOS.get()).save(consumer);
        compact(OBlocks.RAW_ASBESTOS_BLOCK.get().asItem(), OItems.RAW_ASBESTOS.get()).save(consumer);

        unCompact(OItems.RAW_ASBESTOS.get(), OBlocks.RAW_ASBESTOS_BLOCK.get().asItem()).save(consumer, OreganizedCarcinogenius.modLoc("raw_asbestos_from_block"));
        unCompact(OItems.REFINED_ASBESTOS.get(), OBlocks.ASBESTOS_BLOCK.get().asItem()).save(consumer, OreganizedCarcinogenius.modLoc("asbestos_from_block"));
    }
}
