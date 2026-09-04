package galena.oreganized.device.data;


import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;
import static galena.oreganized.data.provider.ORecipeProvider.*;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import galena.oreganized.OConstants;
import galena.oreganized.device.index.DeviceItems;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

public class DevicesRecipes {

    static void generate(RegistrateRecipeProvider consumer) {
        compact(Items.NETHERITE_INGOT, DeviceItems.NETHERITE_NUGGET.get()).save(consumer, OConstants.modLoc("netherite_ingot_from_nuggets"));
        unCompact(DeviceItems.NETHERITE_NUGGET.get(), Items.NETHERITE_INGOT).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DeviceItems.UNKNOWN_DEVICE.get())
                .pattern(" O ")
                .pattern("OXO")
                .pattern(" O ")
                .define('X', Items.REDSTONE)
                .define('O', Items.NETHERITE_SCRAP)
                .unlockedBy("has_netherite_scrap", has(Items.NETHERITE_SCRAP))
                .save(consumer);
    }
}
