package galena.oreganized.plumbum.data;

import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;
import static galena.oreganized.data.provider.ORecipeProvider.*;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.shapeless;

import com.simibubi.create.content.kinetics.mixer.CompactingRecipe;
import com.simibubi.create.content.kinetics.mixer.MixingRecipe;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import galena.oreganized.OConstants;
import galena.oreganized.index.OTags;
import galena.oreganized.plumbum.index.PlumbumBlocks;
import galena.oreganized.plumbum.index.PlumbumFluids;
import galena.oreganized.plumbum.index.PlumbumItems;
import java.util.List;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.crafting.CompoundIngredient;

public class PlumbumRecipes {

    static void generate(RegistrateRecipeProvider consumer) {
        ore(
                PlumbumItems.LEAD_INGOT,
                List.of(PlumbumBlocks.LEAD_ORE.get(), PlumbumBlocks.DEEPSLATE_LEAD_ORE.get(), PlumbumItems.RAW_LEAD.get()),
                0.7F,
                consumer
        );

        compact(PlumbumBlocks.RAW_LEAD_BLOCK.get().asItem(), PlumbumItems.RAW_LEAD.get()).save(consumer);
        unCompact(PlumbumItems.RAW_LEAD.get(), PlumbumBlocks.RAW_LEAD_BLOCK.get().asItem()).save(consumer, OConstants.modLoc("raw_lead_from_block"));

        compact(PlumbumBlocks.LEAD_BLOCK.get().asItem(), PlumbumItems.LEAD_INGOT.get()).save(consumer);
        unCompact(PlumbumItems.LEAD_INGOT.get(), PlumbumBlocks.LEAD_BLOCK.get().asItem()).save(consumer, OConstants.modLoc("lead_ingot_from_block"));

        compact(PlumbumItems.LEAD_INGOT.get(), PlumbumItems.LEAD_NUGGET.get()).save(consumer, OConstants.modLoc("lead_ingot_from_nuggets"));
        unCompact(PlumbumItems.LEAD_NUGGET.get(), PlumbumItems.LEAD_INGOT.get()).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, PlumbumItems.THERMOMETER.get())
                .pattern(" X ")
                .pattern("XOX")
                .pattern(" X ")
                .define('X', OTags.Items.INGOTS_LEAD)
                .define('O', Items.REDSTONE)
                .unlockedBy("has_lead_ingot", has(PlumbumItems.LEAD_INGOT.get()))
                .save(consumer);

        quadTransform(PlumbumBlocks.CUT_LEAD, PlumbumBlocks.LEAD_BLOCK, 8).save(consumer);
        quadTransform(PlumbumBlocks.LEAD_BRICKS, PlumbumBlocks.CUT_LEAD).save(consumer);
        makePillar(PlumbumBlocks.LEAD_PILLAR, PlumbumBlocks.CUT_LEAD).save(consumer);

        stonecutting(PlumbumBlocks.LEAD_BLOCK, PlumbumBlocks.CUT_LEAD.get(), 2).save(consumer, OConstants.modLoc("stonecutting/cut_lead"));
        stonecutting(PlumbumBlocks.LEAD_BLOCK, PlumbumBlocks.LEAD_BRICKS.get(), 4).save(consumer, OConstants.modLoc("stonecutting/lead_bricks"));
        stonecutting(PlumbumBlocks.CUT_LEAD, PlumbumBlocks.LEAD_BRICKS.get()).save(consumer, OConstants.modLoc("stonecutting/lead_bricks_from_cut_lead"));
        stonecutting(PlumbumBlocks.LEAD_BLOCK, PlumbumBlocks.LEAD_PILLAR.get(), 4).save(consumer, OConstants.modLoc("stonecutting/lead_pillar"));
        stonecutting(PlumbumBlocks.CUT_LEAD, PlumbumBlocks.LEAD_PILLAR.get()).save(consumer, OConstants.modLoc("stonecutting/lead_pillar_from_cut_lad"));

        shapeless(RecipeCategory.BREWING, Items.POISONOUS_POTATO, 1)
                .requires(Items.POTATO)
                .requires(OTags.Items.NUGGETS_LEAD)
                .requires(OTags.Items.NUGGETS_LEAD)
                .requires(OTags.Items.NUGGETS_LEAD)
                .unlockedBy("has_lead", has(OTags.Items.NUGGETS_LEAD))
                .unlockedBy("has_potato", has(Items.POTATO))
                .save(consumer, OConstants.modLoc("poisonous_potato_from_lead"));

        shaped(RecipeCategory.BUILDING_BLOCKS, PlumbumBlocks.LEAD_BULB.get(), 1)
                .pattern(" I ")
                .pattern("IGI")
                .pattern(" B ")
                .define('I', OTags.Items.INGOTS_LEAD)
                .define('G', Items.GLOW_INK_SAC)
                .define('B', PlumbumItems.MOLTEN_LEAD_BUCKET.get())
                .unlockedBy("has_lead", has(OTags.Items.INGOTS_LEAD))
                .save(consumer);

        shaped(RecipeCategory.REDSTONE, PlumbumBlocks.LEAD_DOOR.get())
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', Ingredient.of(OTags.Items.INGOTS_LEAD))
                .unlockedBy("has_lead", has(OTags.Items.INGOTS_LEAD))
                .save(consumer);

        shaped(RecipeCategory.REDSTONE, PlumbumBlocks.LEAD_TRAPDOOR.get())
                .define('#', OTags.Items.INGOTS_LEAD)
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_lead", has(OTags.Items.INGOTS_LEAD))
                .save(consumer);

        makeBars(PlumbumBlocks.LEAD_BARS, OTags.Items.INGOTS_LEAD).save(consumer);

        shaped(RecipeCategory.REDSTONE, PlumbumBlocks.STURDY_LEVER.get())
                .define('#', Items.LEVER)
                .define('X', OTags.Items.INGOTS_LEAD)
                .pattern("X")
                .pattern("#")
                .unlockedBy("has_lead", has(OTags.Items.INGOTS_LEAD))
                .save(consumer);

        shaped(RecipeCategory.REDSTONE, PlumbumBlocks.STURDY_BUTTON.get())
                .define('#', ItemTags.STONE_BUTTONS)
                .define('X', OTags.Items.INGOTS_LEAD)
                .pattern("X")
                .pattern("#")
                .unlockedBy("has_lead", has(OTags.Items.INGOTS_LEAD))
                .save(consumer);

        processing(CompactingRecipe::new, "molten_lead")
                .output(PlumbumBlocks.LEAD_BLOCK.get())
                .require(OTags.Fluids.MOLTEN_LEAD, 1000)
                .build(consumer);

        processing(MixingRecipe::new, "molten_lead")
                .output(PlumbumFluids.MOLTEN_LEAD.get(), 1000)
                .require(new CompoundIngredient(List.of(
                        Ingredient.of(OTags.Items.STORAGE_BLOCKS_LEAD),
                        Ingredient.of(OTags.Items.STORAGE_BLOCKS_RAW_LEAD)
                )))
                .requiresHeat(HeatCondition.HEATED)
                .build(consumer);

        flowerDye(PlumbumBlocks.WHITE_DATURA, Items.WHITE_DYE, consumer);
        flowerDye(PlumbumBlocks.PURPLE_DATURA, Items.PURPLE_DYE, consumer);
    }

}
