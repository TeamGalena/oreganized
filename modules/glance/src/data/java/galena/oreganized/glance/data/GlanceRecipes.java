package galena.oreganized.glance.data;


import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;
import static galena.oreganized.data.provider.ORecipeProvider.*;
import static galena.oreganized.data.provider.ORecipeProvider.makeChiseledStonecutting;
import static galena.oreganized.data.provider.ORecipeProvider.makeStairsStonecutting;
import static galena.oreganized.data.provider.ORecipeProvider.makeWallStonecutting;
import static galena.oreganized.data.provider.ORecipeProvider.makeWaxed;
import static galena.oreganized.data.provider.ORecipeProvider.stonecutting;
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

    private void generate(RecipeOutput provider) {
        quadTransform(GlanceBlocks.POLISHED_GLANCE, GlanceBlocks.GLANCE).save(provider);
        quadTransform(GlanceBlocks.GLANCE_BRICKS, GlanceBlocks.POLISHED_GLANCE).save(provider);

        makeSlabStonecutting(GlanceBlocks.GLANCE_SLAB, GlanceBlocks.GLANCE, provider);
        makeSlabStonecutting(GlanceBlocks.GLANCE_BRICK_SLAB, GlanceBlocks.GLANCE_BRICKS, provider);
        makeSlabStonecutting(GlanceBlocks.POLISHED_GLANCE_SLAB, GlanceBlocks.POLISHED_GLANCE, provider);

        makeStairsStonecutting(GlanceBlocks.GLANCE_STAIRS, GlanceBlocks.GLANCE, provider);
        makeStairsStonecutting(GlanceBlocks.GLANCE_BRICK_STAIRS, GlanceBlocks.GLANCE_BRICKS, provider);
        makeStairsStonecutting(GlanceBlocks.POLISHED_GLANCE_STAIRS, GlanceBlocks.POLISHED_GLANCE, provider);

        makeWallStonecutting(GlanceBlocks.GLANCE_WALL, GlanceBlocks.GLANCE, provider);
        makeWallStonecutting(GlanceBlocks.GLANCE_BRICK_WALL, GlanceBlocks.GLANCE_BRICKS, provider);

        makeChiseledStonecutting(GlanceBlocks.CHISELED_GLANCE, GlanceBlocks.GLANCE, GlanceBlocks.GLANCE_SLAB, provider);

        stonecutting(GlanceBlocks.GLANCE, GlanceBlocks.POLISHED_GLANCE.get()).save(provider, OConstants.modLoc("stonecutting/polished_glance"));
        stonecutting(GlanceBlocks.GLANCE, GlanceBlocks.GLANCE_BRICKS.get()).save(provider, OConstants.modLoc("stonecutting/glance_bricks_from_glance"));
        stonecutting(GlanceBlocks.GLANCE, GlanceBlocks.GLANCE_BRICK_STAIRS.get()).save(provider, OConstants.modLoc("stonecutting/glance_brick_stairs_from_glance"));
        stonecutting(GlanceBlocks.GLANCE, GlanceBlocks.GLANCE_BRICK_SLAB.get(), 2).save(provider, OConstants.modLoc("stonecutting/glance_brick_slab_from_glance"));
        stonecutting(GlanceBlocks.GLANCE, GlanceBlocks.GLANCE_BRICK_WALL.get()).save(provider, OConstants.modLoc("stonecutting/glance_brick_wall_from_glance"));

        stonecutting(GlanceBlocks.POLISHED_GLANCE, GlanceBlocks.GLANCE_BRICKS.get()).save(provider, OConstants.modLoc("stonecutting/glance_bricks_from_polished"));
        stonecutting(GlanceBlocks.POLISHED_GLANCE, GlanceBlocks.GLANCE_BRICK_STAIRS.get()).save(provider, OConstants.modLoc("stonecutting/glance_brick_stairs_from_polished"));
        stonecutting(GlanceBlocks.POLISHED_GLANCE, GlanceBlocks.GLANCE_BRICK_SLAB.get(), 2).save(provider, OConstants.modLoc("stonecutting/glance_brick_slab_from_polished"));
        stonecutting(GlanceBlocks.POLISHED_GLANCE, GlanceBlocks.GLANCE_BRICK_WALL.get()).save(provider, OConstants.modLoc("stonecutting/glance_brick_wall_from_polished"));

        makeWaxed(provider, GlanceBlocks.WAXED_SPOTTED_GLANCE, GlanceBlocks.SPOTTED_GLANCE);

        shaped(RecipeCategory.BUILDING_BLOCKS, GlanceBlocks.SPOTTED_GLANCE.get())
                .pattern(" X ")
                .pattern("XOX")
                .pattern(" X ")
                .define('X', CoreTags.Items.NUGGETS_LEAD)
                .define('O', GlanceBlocks.GLANCE.get())
                .unlockedBy("has_glance", has(GlanceBlocks.GLANCE.get()))
                .save(provider);

        shaped(RecipeCategory.BUILDING_BLOCKS, GlanceBlocks.GLANCE.get(), 2)
                .pattern("AB")
                .pattern("BA")
                .define('A', CoreTags.Items.NUGGETS_LEAD)
                .define('B', Items.DIORITE)
                .unlockedBy("has_lead_ingot", has(CoreTags.Items.INGOTS_LEAD))
                .save(provider);

        // TODO modular will need to be conditional in the future
        processing(CrushingRecipe::new, "glance")
                .output(0.8F, AllItems.CRUSHED_LEAD, 1)
                .output(0.8F, PlumbumItems.LEAD_NUGGET.get())
                .require(GlanceBlocks.GLANCE.get())
                .duration(250)
                .build(provider);

        // TODO modular will need to be conditional in the future
        processing(CrushingRecipe::new, "glance_recycling")
                .output(0.8F, AllItems.CRUSHED_LEAD, 1)
                .output(0.8F, PlumbumItems.LEAD_NUGGET.get())
                .require(GlanceTags.Items.STONE_TYPES_GLANCE)
                .duration(250)
                .build(provider);

        // TODO modular will need to be conditional in the future
        processing(FillingRecipe::new, "spotted_glance")
                .output(GlanceBlocks.SPOTTED_GLANCE.get())
                .require(GlanceBlocks.GLANCE.get())
                .require(PlumbumTags.Fluids.MOLTEN_LEAD, 250)
                .build(provider);

        // TODO modular will need to be conditional in the future
        processing(MixingRecipe::new, "glance")
                .output(GlanceBlocks.GLANCE.get())
                .require(Items.DIORITE)
                .require(CoreTags.Items.NUGGETS_LEAD)
                .build(provider);
    }
}
