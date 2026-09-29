package galena.oreganized.plumbum.data;

import static galena.oreganized.data.extensions.OColorExtensions.*;
import static galena.oreganized.data.extensions.OConditionExtensions.dyed;
import static galena.oreganized.data.extensions.ORecipeExtensions.makeWaxed;

import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.waxed.index.WaxedBlocks;
import net.minecraft.data.recipes.RecipeOutput;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class WaxedRecipes {

    public WaxedRecipes() {
        ODatagen.addRecipeProvider(this::generate);
    }

    private void generate(RecipeOutput output) {
        WaxedBlocks.WAXED_CONCRETE_POWDER.map().forEach((color, waxed) -> {
            var unwaxed = getColoredBlock(color, "concrete_powder");
            dyed(color, output, () -> makeWaxed(output, waxed, unwaxed));
        });
    }

}
