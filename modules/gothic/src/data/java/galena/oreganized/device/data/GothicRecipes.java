package galena.oreganized.device.data;


import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;
import static galena.oreganized.data.ConditionalData.dyed;
import static galena.oreganized.data.provider.ORecipeProvider.crystalGlass;
import static galena.oreganized.data.provider.ORecipeProvider.makePane;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import galena.oreganized.data.ColorCompat;
import galena.oreganized.gothic.index.GothicBlocks;
import galena.oreganized.index.OTags;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

public class GothicRecipes {

    static void generate(RegistrateRecipeProvider consumer) {
        GothicBlocks.CRYSTAL_GLASS.forEach((color, crystalGlass) -> {
            var glass = ColorCompat.getColoredBlock("stained_glass", color);
            dyed(color, crystalGlass(crystalGlass, glass)).save(consumer);
        });

        GothicBlocks.CRYSTAL_GLASS_PANES.forEach((color, pane) ->
                dyed(color, makePane(pane, GothicBlocks.CRYSTAL_GLASS.get(color))).save(consumer)
        );

        shaped(RecipeCategory.REDSTONE, GothicBlocks.GARGOYLE.get())
                .pattern(" P ")
                .pattern("#S#")
                .pattern("###")
                .define('#', ItemTags.STONE_CRAFTING_MATERIALS)
                .define('P', Items.CARVED_PUMPKIN)
                .define('S', OTags.Items.INGOTS_SILVER)
                .unlockedBy("has_pumpkin", has(Items.CARVED_PUMPKIN))
                .unlockedBy("has_silver_ingot", has(OTags.Items.INGOTS_SILVER))
                .save(consumer);
    }
}
