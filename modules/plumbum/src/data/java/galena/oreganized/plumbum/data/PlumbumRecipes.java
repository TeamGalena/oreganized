package galena.oreganized.plumbum.data;

import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;
import static galena.oreganized.data.extensions.ORecipeExtensions.*;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.shapeless;

import com.simibubi.create.content.kinetics.mixer.CompactingRecipe;
import com.simibubi.create.content.kinetics.mixer.MixingRecipe;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.index.CoreTags;
import galena.oreganized.plumbum.index.PlumbumBlocks;
import galena.oreganized.plumbum.index.PlumbumFluids;
import galena.oreganized.plumbum.index.PlumbumItems;
import galena.oreganized.plumbum.index.PlumbumTags;
import java.util.List;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.crafting.CompoundIngredient;

@Mod(OConstants.MOD_ID)
public class PlumbumRecipes {

    public PlumbumRecipes() {
        ODatagen.addRecipeProvider(this::generate);
    }

    private void generate(RecipeOutput output) {
        makeOreSmelting(
                output,
                PlumbumItems.LEAD_INGOT,
                List.of(PlumbumBlocks.LEAD_ORE.get(), PlumbumBlocks.DEEPSLATE_LEAD_ORE.get(), PlumbumItems.RAW_LEAD.get()),
                0.7F
        );

        compact(PlumbumBlocks.RAW_LEAD_BLOCK.get().asItem(), PlumbumItems.RAW_LEAD.get()).save(output);
        unCompact(PlumbumItems.RAW_LEAD.get(), PlumbumBlocks.RAW_LEAD_BLOCK.get().asItem()).save(output, OConstants.modLoc("raw_lead_from_block"));

        compact(PlumbumBlocks.LEAD_BLOCK.get().asItem(), PlumbumItems.LEAD_INGOT.get()).save(output);
        unCompact(PlumbumItems.LEAD_INGOT.get(), PlumbumBlocks.LEAD_BLOCK.get().asItem()).save(output, OConstants.modLoc("lead_ingot_from_block"));

        compact(PlumbumItems.LEAD_INGOT.get(), PlumbumItems.LEAD_NUGGET.get()).save(output, OConstants.modLoc("lead_ingot_from_nuggets"));
        unCompact(PlumbumItems.LEAD_NUGGET.get(), PlumbumItems.LEAD_INGOT.get()).save(output);

        shaped(RecipeCategory.TOOLS, PlumbumItems.THERMOMETER.get())
                .pattern(" X ")
                .pattern("XOX")
                .pattern(" X ")
                .define('X', CoreTags.Items.INGOTS_LEAD)
                .define('O', Items.REDSTONE)
                .unlockedBy("has_lead_ingot", has(PlumbumItems.LEAD_INGOT.get()))
                .save(output);

        makeQuadTransformStonecutting(output, PlumbumBlocks.CUT_LEAD, PlumbumBlocks.LEAD_BLOCK, 8);
        makeQuadTransformStonecutting(output, PlumbumBlocks.LEAD_BRICKS, PlumbumBlocks.CUT_LEAD);
        makePillarStonecutting(output, PlumbumBlocks.LEAD_PILLAR, PlumbumBlocks.CUT_LEAD);

        makeStoneCutting(output, PlumbumBlocks.LEAD_BLOCK, PlumbumBlocks.LEAD_BRICKS.get(), 4);
        makeStoneCutting(output, PlumbumBlocks.LEAD_BLOCK, PlumbumBlocks.LEAD_PILLAR.get(), 4);

        shapeless(RecipeCategory.BREWING, Items.POISONOUS_POTATO, 1)
                .requires(Items.POTATO)
                .requires(CoreTags.Items.NUGGETS_LEAD)
                .requires(CoreTags.Items.NUGGETS_LEAD)
                .requires(CoreTags.Items.NUGGETS_LEAD)
                .unlockedBy("has_lead", has(CoreTags.Items.NUGGETS_LEAD))
                .unlockedBy("has_potato", has(Items.POTATO))
                .save(output, OConstants.modLoc("poisonous_potato_from_lead"));

        shaped(RecipeCategory.BUILDING_BLOCKS, PlumbumBlocks.LEAD_BULB.get(), 1)
                .pattern(" I ")
                .pattern("IGI")
                .pattern(" B ")
                .define('I', CoreTags.Items.INGOTS_LEAD)
                .define('G', Items.GLOW_INK_SAC)
                .define('B', PlumbumItems.MOLTEN_LEAD_BUCKET.get())
                .unlockedBy("has_lead", has(CoreTags.Items.INGOTS_LEAD))
                .save(output);

        shaped(RecipeCategory.REDSTONE, PlumbumBlocks.LEAD_DOOR.get())
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', Ingredient.of(CoreTags.Items.INGOTS_LEAD))
                .unlockedBy("has_lead", has(CoreTags.Items.INGOTS_LEAD))
                .save(output);

        shaped(RecipeCategory.REDSTONE, PlumbumBlocks.LEAD_TRAPDOOR.get())
                .define('#', CoreTags.Items.INGOTS_LEAD)
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_lead", has(CoreTags.Items.INGOTS_LEAD))
                .save(output);

        makeBars(output, PlumbumBlocks.LEAD_BARS, CoreTags.Items.INGOTS_LEAD);

        shaped(RecipeCategory.REDSTONE, PlumbumBlocks.STURDY_LEVER.get())
                .define('#', Items.LEVER)
                .define('X', CoreTags.Items.INGOTS_LEAD)
                .pattern("X")
                .pattern("#")
                .unlockedBy("has_lead", has(CoreTags.Items.INGOTS_LEAD))
                .save(output);

        shaped(RecipeCategory.REDSTONE, PlumbumBlocks.STURDY_BUTTON.get())
                .define('#', ItemTags.STONE_BUTTONS)
                .define('X', CoreTags.Items.INGOTS_LEAD)
                .pattern("X")
                .pattern("#")
                .unlockedBy("has_lead", has(CoreTags.Items.INGOTS_LEAD))
                .save(output);

        processing(CompactingRecipe::new, "molten_lead")
                .output(PlumbumBlocks.LEAD_BLOCK.get())
                .require(PlumbumTags.Fluids.MOLTEN_LEAD, 1000)
                .build(output);

        processing(MixingRecipe::new, "molten_lead")
                .output(PlumbumFluids.MOLTEN_LEAD.get(), 1000)
                .require(new CompoundIngredient(List.of(
                        Ingredient.of(CoreTags.Items.STORAGE_BLOCKS_LEAD),
                        Ingredient.of(CoreTags.Items.STORAGE_BLOCKS_RAW_LEAD)
                )))
                .requiresHeat(HeatCondition.HEATED)
                .build(output);

        makeFlowerDye(output, PlumbumBlocks.WHITE_DATURA, Items.WHITE_DYE);
        makeFlowerDye(output, PlumbumBlocks.PURPLE_DATURA, Items.PURPLE_DYE);
    }

}
