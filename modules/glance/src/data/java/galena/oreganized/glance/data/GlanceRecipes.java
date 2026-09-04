package galena.oreganized.glance.data;


import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;
import static galena.oreganized.data.provider.ORecipeProvider.*;
import static galena.oreganized.data.provider.ORecipeProvider.application;
import static galena.oreganized.data.provider.ORecipeProvider.makeChiseledStonecutting;
import static galena.oreganized.data.provider.ORecipeProvider.makeStairsStonecutting;
import static galena.oreganized.data.provider.ORecipeProvider.makeWallStonecutting;
import static galena.oreganized.data.provider.ORecipeProvider.makeWaxed;
import static galena.oreganized.data.provider.ORecipeProvider.stonecutting;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;

import com.simibubi.create.AllItems;
import com.simibubi.create.content.fluids.transfer.FillingRecipe;
import com.simibubi.create.content.kinetics.crusher.CrushingRecipe;
import com.simibubi.create.content.kinetics.deployer.DeployerApplicationRecipe;
import com.simibubi.create.content.kinetics.mixer.MixingRecipe;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import galena.oreganized.OConstants;
import galena.oreganized.glance.index.GlanceBlocks;
import galena.oreganized.index.OTags;
import galena.oreganized.plumbum.index.PlumbumItems;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class GlanceRecipes {

    static void generate(RegistrateRecipeProvider consumer) {
        quadTransform(GlanceBlocks.POLISHED_GLANCE, GlanceBlocks.GLANCE).save(consumer);
        quadTransform(GlanceBlocks.GLANCE_BRICKS, GlanceBlocks.POLISHED_GLANCE).save(consumer);

        makeSlabStonecutting(GlanceBlocks.GLANCE_SLAB, GlanceBlocks.GLANCE, consumer);
        makeSlabStonecutting(GlanceBlocks.GLANCE_BRICK_SLAB, GlanceBlocks.GLANCE_BRICKS, consumer);
        makeSlabStonecutting(GlanceBlocks.POLISHED_GLANCE_SLAB, GlanceBlocks.POLISHED_GLANCE, consumer);

        makeStairsStonecutting(GlanceBlocks.GLANCE_STAIRS, GlanceBlocks.GLANCE, consumer);
        makeStairsStonecutting(GlanceBlocks.GLANCE_BRICK_STAIRS, GlanceBlocks.GLANCE_BRICKS, consumer);
        makeStairsStonecutting(GlanceBlocks.POLISHED_GLANCE_STAIRS, GlanceBlocks.POLISHED_GLANCE, consumer);

        makeWallStonecutting(GlanceBlocks.GLANCE_WALL, GlanceBlocks.GLANCE, consumer);
        makeWallStonecutting(GlanceBlocks.GLANCE_BRICK_WALL, GlanceBlocks.GLANCE_BRICKS, consumer);

        makeChiseledStonecutting(GlanceBlocks.CHISELED_GLANCE, GlanceBlocks.GLANCE, GlanceBlocks.GLANCE_SLAB, consumer);

        stonecutting(GlanceBlocks.GLANCE, GlanceBlocks.POLISHED_GLANCE.get()).save(consumer, OConstants.modLoc("stonecutting/polished_glance"));
        stonecutting(GlanceBlocks.GLANCE, GlanceBlocks.GLANCE_BRICKS.get()).save(consumer, OConstants.modLoc("stonecutting/glance_bricks_from_glance"));
        stonecutting(GlanceBlocks.GLANCE, GlanceBlocks.GLANCE_BRICK_STAIRS.get()).save(consumer, OConstants.modLoc("stonecutting/glance_brick_stairs_from_glance"));
        stonecutting(GlanceBlocks.GLANCE, GlanceBlocks.GLANCE_BRICK_SLAB.get(), 2).save(consumer, OConstants.modLoc("stonecutting/glance_brick_slab_from_glance"));
        stonecutting(GlanceBlocks.GLANCE, GlanceBlocks.GLANCE_BRICK_WALL.get()).save(consumer, OConstants.modLoc("stonecutting/glance_brick_wall_from_glance"));

        stonecutting(GlanceBlocks.POLISHED_GLANCE, GlanceBlocks.GLANCE_BRICKS.get()).save(consumer, OConstants.modLoc("stonecutting/glance_bricks_from_polished"));
        stonecutting(GlanceBlocks.POLISHED_GLANCE, GlanceBlocks.GLANCE_BRICK_STAIRS.get()).save(consumer, OConstants.modLoc("stonecutting/glance_brick_stairs_from_polished"));
        stonecutting(GlanceBlocks.POLISHED_GLANCE, GlanceBlocks.GLANCE_BRICK_SLAB.get(), 2).save(consumer, OConstants.modLoc("stonecutting/glance_brick_slab_from_polished"));
        stonecutting(GlanceBlocks.POLISHED_GLANCE, GlanceBlocks.GLANCE_BRICK_WALL.get()).save(consumer, OConstants.modLoc("stonecutting/glance_brick_wall_from_polished"));

        makeWaxed(GlanceBlocks.WAXED_SPOTTED_GLANCE, GlanceBlocks.SPOTTED_GLANCE).save(consumer);
        // TODO modular should be covered by above?
        application(DeployerApplicationRecipe::new, "glance")
                .output(GlanceBlocks.WAXED_SPOTTED_GLANCE)
                .require(GlanceBlocks.SPOTTED_GLANCE)
                .require(Blocks.HONEYCOMB_BLOCK)
                .toolNotConsumed()
                .build(consumer);

        shaped(RecipeCategory.BUILDING_BLOCKS, GlanceBlocks.SPOTTED_GLANCE.get())
                .pattern(" X ")
                .pattern("XOX")
                .pattern(" X ")
                .define('X', OTags.Items.NUGGETS_LEAD)
                .define('O', GlanceBlocks.GLANCE.get())
                .unlockedBy("has_glance", has(GlanceBlocks.GLANCE.get()))
                .save(consumer);

        shaped(RecipeCategory.BUILDING_BLOCKS, GlanceBlocks.GLANCE.get(), 2)
                .pattern("AB")
                .pattern("BA")
                .define('A', OTags.Items.NUGGETS_LEAD)
                .define('B', Items.DIORITE)
                .unlockedBy("has_lead_ingot", has(OTags.Items.INGOTS_LEAD))
                .save(consumer);

        // TODO will need to be conditional in the future
        processing(CrushingRecipe::new, "glance")
                .output(0.8F, AllItems.CRUSHED_LEAD, 1)
                .output(0.8F, PlumbumItems.LEAD_NUGGET.get())
                .require(GlanceBlocks.GLANCE.get())
                .duration(250)
                .build(consumer);

        // TODO will need to be conditional in the future
        processing(CrushingRecipe::new, "glance_recycling")
                .output(0.8F, AllItems.CRUSHED_LEAD, 1)
                .output(0.8F, PlumbumItems.LEAD_NUGGET.get())
                .require(OTags.Items.STONE_TYPES_GLANCE)
                .duration(250)
                .build(consumer);

        processing(FillingRecipe::new, "spotted_glance")
                .output(GlanceBlocks.SPOTTED_GLANCE.get())
                .require(GlanceBlocks.GLANCE.get())
                .require(OTags.Fluids.MOLTEN_LEAD, 250)
                .build(consumer);

        processing(MixingRecipe::new, "glance")
                .output(GlanceBlocks.GLANCE.get())
                .require(Items.DIORITE)
                .require(OTags.Items.NUGGETS_LEAD)
                .build(consumer);
    }
}
