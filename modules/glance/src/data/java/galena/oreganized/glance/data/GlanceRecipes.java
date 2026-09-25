package galena.oreganized.glance.data;


import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;
import static galena.oreganized.data.extensions.ORecipeExtensions.*;
import static galena.oreganized.data.extensions.ORecipeExtensions.makeChiseledStonecutting;
import static galena.oreganized.data.extensions.ORecipeExtensions.makeStairsStonecutting;
import static galena.oreganized.data.extensions.ORecipeExtensions.makeWallStonecutting;
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
        quadTransform(GlanceBlocks.POLISHED_GLANCE, GlanceBlocks.GLANCE).save(output);
        quadTransform(GlanceBlocks.GLANCE_BRICKS, GlanceBlocks.POLISHED_GLANCE).save(output);

        makeSlabStonecutting(output, GlanceBlocks.GLANCE_SLAB, GlanceBlocks.GLANCE);
        makeSlabStonecutting(output, GlanceBlocks.GLANCE_BRICK_SLAB, GlanceBlocks.GLANCE_BRICKS);
        makeSlabStonecutting(output, GlanceBlocks.POLISHED_GLANCE_SLAB, GlanceBlocks.POLISHED_GLANCE);

        makeStairsStonecutting(output, GlanceBlocks.GLANCE_STAIRS, GlanceBlocks.GLANCE);
        makeStairsStonecutting(output, GlanceBlocks.GLANCE_BRICK_STAIRS, GlanceBlocks.GLANCE_BRICKS);
        makeStairsStonecutting(output, GlanceBlocks.POLISHED_GLANCE_STAIRS, GlanceBlocks.POLISHED_GLANCE);

        makeWallStonecutting(output, GlanceBlocks.GLANCE_WALL, GlanceBlocks.GLANCE);
        makeWallStonecutting(output, GlanceBlocks.GLANCE_BRICK_WALL, GlanceBlocks.GLANCE_BRICKS);

        makeChiseledStonecutting(output, GlanceBlocks.CHISELED_GLANCE, GlanceBlocks.GLANCE, GlanceBlocks.GLANCE_SLAB);

        makeStoneCutting(output, GlanceBlocks.GLANCE, GlanceBlocks.POLISHED_GLANCE.get());
        makeStoneCutting(output, GlanceBlocks.GLANCE, GlanceBlocks.GLANCE_BRICKS.get());
        makeStoneCutting(output, GlanceBlocks.GLANCE, GlanceBlocks.GLANCE_BRICK_STAIRS.get());
        makeStoneCutting(output, GlanceBlocks.GLANCE, GlanceBlocks.GLANCE_BRICK_SLAB.get(), 2);
        makeStoneCutting(output, GlanceBlocks.GLANCE, GlanceBlocks.GLANCE_BRICK_WALL.get());

        makeStoneCutting(output, GlanceBlocks.POLISHED_GLANCE, GlanceBlocks.GLANCE_BRICKS.get());
        makeStoneCutting(output, GlanceBlocks.POLISHED_GLANCE, GlanceBlocks.GLANCE_BRICK_STAIRS.get());
        makeStoneCutting(output, GlanceBlocks.POLISHED_GLANCE, GlanceBlocks.GLANCE_BRICK_SLAB.get(), 2);
        makeStoneCutting(output, GlanceBlocks.POLISHED_GLANCE, GlanceBlocks.GLANCE_BRICK_WALL.get());

        makeWaxed(output, GlanceBlocks.WAXED_SPOTTED_GLANCE, GlanceBlocks.SPOTTED_GLANCE);

        shaped(RecipeCategory.BUILDING_BLOCKS, GlanceBlocks.SPOTTED_GLANCE.get())
                .pattern(" X ")
                .pattern("XOX")
                .pattern(" X ")
                .define('X', CoreTags.Items.NUGGETS_LEAD)
                .define('O', GlanceBlocks.GLANCE.get())
                .unlockedBy("has_glance", has(GlanceBlocks.GLANCE.get()))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, GlanceBlocks.GLANCE.get(), 2)
                .pattern("AB")
                .pattern("BA")
                .define('A', CoreTags.Items.NUGGETS_LEAD)
                .define('B', Items.DIORITE)
                .unlockedBy("has_lead_ingot", has(CoreTags.Items.INGOTS_LEAD))
                .save(output);

        // TODO modular will need to be conditional in the future
        processing(CrushingRecipe::new, "glance")
                .output(0.8F, AllItems.CRUSHED_LEAD, 1)
                .output(0.8F, PlumbumItems.LEAD_NUGGET.get())
                .require(GlanceBlocks.GLANCE.get())
                .duration(250)
                .build(output);

        // TODO modular will need to be conditional in the future
        processing(CrushingRecipe::new, "glance_recycling")
                .output(0.8F, AllItems.CRUSHED_LEAD, 1)
                .output(0.8F, PlumbumItems.LEAD_NUGGET.get())
                .require(GlanceTags.Items.STONE_TYPES_GLANCE)
                .duration(250)
                .build(output);

        // TODO modular will need to be conditional in the future
        processing(FillingRecipe::new, "spotted_glance")
                .output(GlanceBlocks.SPOTTED_GLANCE.get())
                .require(GlanceBlocks.GLANCE.get())
                .require(PlumbumTags.Fluids.MOLTEN_LEAD, 250)
                .build(output);

        // TODO modular will need to be conditional in the future
        processing(MixingRecipe::new, "glance")
                .output(GlanceBlocks.GLANCE.get())
                .require(Items.DIORITE)
                .require(CoreTags.Items.NUGGETS_LEAD)
                .build(output);
    }
}
