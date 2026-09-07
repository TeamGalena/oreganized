package galena.oreganized.plumbum.data;

import static galena.oreganized.data.ConditionalData.dyed;
import static galena.oreganized.data.provider.ORecipeProvider.application;
import static galena.oreganized.data.provider.ORecipeProvider.makeWaxed;

import com.simibubi.create.content.kinetics.deployer.DeployerApplicationRecipe;
import galena.oreganized.OConstants;
import galena.oreganized.data.ColorCompat;
import galena.oreganized.data.provider.ODatagen;
import galena.oreganized.waxed.index.WaxedBlocks;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class WaxedRecipes {

    public WaxedRecipes() {
        ODatagen.addRecipeProvider(this::generate);
    }

    private void generate(RecipeOutput provider) {
        WaxedBlocks.WAXED_CONCRETE_POWDER.forEach((color, waxed) -> {
            var unwaxed = ColorCompat.getColoredBlock("concrete_powder", color);
            dyed(color, makeWaxed(waxed, unwaxed)).save(provider);

            dyed(color, provider, () -> application(DeployerApplicationRecipe::new, waxed.getId().getPath())
                    .output(waxed.get())
                    .require(unwaxed)
                    .require(Blocks.HONEYCOMB_BLOCK)
                    .toolNotConsumed()
                    .build(provider)
            );
        });
    }

}
