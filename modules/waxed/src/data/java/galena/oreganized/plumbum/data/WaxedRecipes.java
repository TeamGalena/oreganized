package galena.oreganized.plumbum.data;

import static galena.oreganized.data.ConditionalData.dyed;
import static galena.oreganized.data.extensions.ORecipeExtensions.makeWaxed;

import galena.oreganized.OConstants;
import galena.oreganized.data.ColorCompat;
import galena.oreganized.data.ODatagen;
import galena.oreganized.waxed.index.WaxedBlocks;
import net.minecraft.data.recipes.RecipeOutput;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class WaxedRecipes {

    public WaxedRecipes() {
        ODatagen.addRecipeProvider(this::generate);
    }

    private void generate(RecipeOutput provider) {
        WaxedBlocks.WAXED_CONCRETE_POWDER.forEach((color, waxed) -> {
            var unwaxed = ColorCompat.getColoredBlock("concrete_powder", color);
            dyed(color, provider, () -> makeWaxed(provider, waxed, unwaxed));
        });
    }

}
