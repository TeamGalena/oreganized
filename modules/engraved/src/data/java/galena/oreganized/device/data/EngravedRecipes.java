package galena.oreganized.device.data;



import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;
import static galena.oreganized.data.provider.ORecipeProvider.metalRecycling;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import galena.oreganized.engraved.index.EngravedItems;
import galena.oreganized.index.OTags;
import galena.oreganized.plumbum.index.PlumbumItems;
import java.util.List;
import net.minecraft.data.recipes.RecipeCategory;
import net.neoforged.neoforge.common.Tags;

public class EngravedRecipes {

    static void generate(RegistrateRecipeProvider consumer) {
        shaped(RecipeCategory.TOOLS, EngravedItems.BUSH_HAMMER.get())
                .pattern("AA")
                .pattern("B ")
                .define('A', OTags.Items.INGOTS_LEAD)
                .define('B', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_lead_ingot", has(OTags.Items.INGOTS_LEAD))
                .unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
                .save(consumer);

        metalRecycling(consumer, PlumbumItems.LEAD_NUGGET.get(), List.of(EngravedItems.BUSH_HAMMER));
    }
}
