package galena.oreganized.glance.data;


import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;
import static galena.oreganized.data.extensions.ORecipeExtensions.*;
import static galena.oreganized.data.extensions.ORecipeExtensions.makeChiseledStonecutting;
import static galena.oreganized.data.extensions.ORecipeExtensions.makeWaxed;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;

import com.simibubi.create.AllItems;
import com.simibubi.create.content.fluids.transfer.FillingRecipe;
import com.simibubi.create.content.kinetics.crusher.CrushingRecipe;
import com.simibubi.create.content.kinetics.mixer.MixingRecipe;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.glance.index.GlanceBlocks;
import galena.oreganized.glance.index.GlanceTags;
import galena.oreganized.index.CoreTags;
import galena.oreganized.plumbum.index.PlumbumItems;
import galena.oreganized.plumbum.index.PlumbumTags;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class GlanceRecipes {

    public GlanceRecipes() {
        ODatagen.addRecipeProvider(this::generate);
    }

    private void generate(RecipeOutput output) {
        makeQuadTransformStonecutting(output, GlanceBlocks.POLISHED_GLANCE.block(), GlanceBlocks.GLANCE.block());
        makeQuadTransformStonecutting(output, GlanceBlocks.GLANCE_BRICKS.block(), GlanceBlocks.POLISHED_GLANCE.block());

        makeStoneSetRecipes(output, GlanceBlocks.GLANCE);
        makeStoneSetRecipes(output, GlanceBlocks.GLANCE_BRICKS);
        makeStoneSetRecipes(output, GlanceBlocks.POLISHED_GLANCE);

        makeStoneCutting(output, GlanceBlocks.GLANCE_BRICKS.block(), GlanceBlocks.GLANCE.block());
        makeStoneSetRecipes(output, GlanceBlocks.GLANCE_BRICKS, GlanceBlocks.POLISHED_GLANCE.block());
        makeStoneSetRecipes(output, GlanceBlocks.POLISHED_GLANCE, GlanceBlocks.GLANCE.block());
        makeStoneSetRecipes(output, GlanceBlocks.GLANCE_BRICKS, GlanceBlocks.GLANCE.block());

        makeChiseledStonecutting(output, GlanceBlocks.CHISELED_GLANCE, GlanceBlocks.GLANCE.block(), GlanceBlocks.GLANCE.slab());

        makeWaxed(output, GlanceBlocks.WAXED_SPOTTED_GLANCE, GlanceBlocks.SPOTTED_GLANCE);

        shaped(RecipeCategory.BUILDING_BLOCKS, GlanceBlocks.SPOTTED_GLANCE)
                .pattern(" X ")
                .pattern("XOX")
                .pattern(" X ")
                .define('X', CoreTags.Items.NUGGETS_LEAD)
                .define('O', GlanceBlocks.GLANCE.block())
                .unlockedBy("has_glance", has(GlanceBlocks.GLANCE.block()))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, GlanceBlocks.GLANCE.block(), 2)
                .pattern("AB")
                .pattern("BA")
                .define('A', CoreTags.Items.NUGGETS_LEAD)
                .define('B', Items.DIORITE)
                .unlockedBy("has_lead_ingot", has(CoreTags.Items.INGOTS_LEAD))
                .save(output);

        // TODO modular will need to be conditional in the future
        processing(CrushingRecipe::new, "glance")
                .output(0.8F, AllItems.CRUSHED_LEAD, 1)
                .output(0.8F, PlumbumItems.LEAD_NUGGET)
                .require(GlanceBlocks.GLANCE.block())
                .duration(250)
                .build(output);

        // TODO modular will need to be conditional in the future
        processing(CrushingRecipe::new, "glance_recycling")
                .output(0.8F, AllItems.CRUSHED_LEAD, 1)
                .output(0.8F, PlumbumItems.LEAD_NUGGET)
                .require(GlanceTags.Items.STONE_TYPES_GLANCE)
                .duration(250)
                .build(output);

        // TODO modular will need to be conditional in the future
        processing(FillingRecipe::new, "spotted_glance")
                .output(GlanceBlocks.SPOTTED_GLANCE)
                .require(GlanceBlocks.GLANCE.block())
                .require(PlumbumTags.Fluids.MOLTEN_LEAD, 250)
                .build(output);

        // TODO modular will need to be conditional in the future
        processing(MixingRecipe::new, "glance")
                .output(GlanceBlocks.GLANCE.block())
                .require(Items.DIORITE)
                .require(CoreTags.Items.NUGGETS_LEAD)
                .build(output);
    }
}
