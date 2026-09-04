package galena.oreganized.plumbum.data;

import static galena.oreganized.data.ConditionalData.dyed;
import static galena.oreganized.data.provider.ORecipeProvider.application;
import static galena.oreganized.data.provider.ORecipeProvider.makeWaxed;

import com.simibubi.create.content.kinetics.deployer.DeployerApplicationRecipe;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import galena.oreganized.data.ColorCompat;
import galena.oreganized.waxed.index.WaxedBlocks;
import net.minecraft.world.level.block.Blocks;

public class WaxedRecipes {

    static void generate(RegistrateRecipeProvider consumer) {
        WaxedBlocks.WAXED_CONCRETE_POWDER.forEach((color, waxed) -> {
            var unwaxed = ColorCompat.getColoredBlock("concrete_powder", color);
            dyed(color, makeWaxed(waxed, unwaxed)).save(consumer);

            dyed(color, consumer, () -> application(DeployerApplicationRecipe::new, waxed.getId().getPath())
                    .output(waxed.get())
                    .require(unwaxed)
                    .require(Blocks.HONEYCOMB_BLOCK)
                    .toolNotConsumed()
                    .build(consumer)
            );
        });
    }

}
