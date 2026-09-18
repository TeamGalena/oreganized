package galena.oreganized.device.data;


import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;
import static galena.oreganized.data.extensions.ORecipeExtensions.*;

import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.device.index.DeviceItems;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class DevicesRecipes {

    public DevicesRecipes() {
        ODatagen.addRecipeProvider(this::generate);
    }

    private void generate(RecipeOutput provider) {
        compact(Items.NETHERITE_INGOT, DeviceItems.NETHERITE_NUGGET.get()).save(provider, OConstants.modLoc("netherite_ingot_from_nuggets"));
        unCompact(DeviceItems.NETHERITE_NUGGET.get(), Items.NETHERITE_INGOT).save(provider);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DeviceItems.UNKNOWN_DEVICE.get())
                .pattern(" O ")
                .pattern("OXO")
                .pattern(" O ")
                .define('X', Items.REDSTONE)
                .define('O', Items.NETHERITE_SCRAP)
                .unlockedBy("has_netherite_scrap", has(Items.NETHERITE_SCRAP))
                .save(provider);
    }
}
