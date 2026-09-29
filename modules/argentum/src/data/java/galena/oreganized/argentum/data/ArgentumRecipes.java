package galena.oreganized.argentum.data;

import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;
import static galena.oreganized.data.extensions.OConditionExtensions.whenLoaded;
import static galena.oreganized.data.extensions.ORecipeExtensions.*;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;

import galena.oreganized.ModCompat;
import galena.oreganized.OConstants;
import galena.oreganized.argentum.index.ArgentumBlocks;
import galena.oreganized.argentum.index.ArgentumItemAbilities;
import galena.oreganized.argentum.index.ArgentumItems;
import galena.oreganized.argentum.index.TarnishedBlocks;
import galena.oreganized.argentum.world.recipe.ScribeRecipe;
import galena.oreganized.data.ODatagen;
import galena.oreganized.index.CoreTags;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;
import vectorwing.farmersdelight.common.crafting.ingredient.ItemAbilityIngredient;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

@Mod(OConstants.MOD_ID)
public class ArgentumRecipes {

    public ArgentumRecipes() {
        ODatagen.addRecipeProvider(this::generate);
    }

    private void generate(RecipeOutput output) {
        makeOreSmelting(
                output,
                ArgentumItems.SILVER_INGOT,
                List.of(ArgentumBlocks.SILVER_ORE, ArgentumBlocks.DEEPSLATE_SILVER_ORE, ArgentumItems.RAW_SILVER),
                1.0F
        );

        shaped(RecipeCategory.TOOLS, ArgentumItems.SCRIBE)
                .define('A', Items.AMETHYST_SHARD)
                .define('S', CoreTags.Items.INGOTS_SILVER)
                .pattern("A")
                .pattern("S")
                .pattern("S")
                .unlockedBy("has_silver", has(CoreTags.Items.INGOTS_SILVER))
                .unlockedBy("has_amethyst", has(Items.AMETHYST_SHARD))
                .save(output);

        compact(ArgentumBlocks.RAW_SILVER_BLOCK, ArgentumItems.RAW_SILVER).save(output);
        unCompact(ArgentumItems.RAW_SILVER, ArgentumBlocks.RAW_SILVER_BLOCK).save(output, OConstants.modLoc("raw_silver_from_block"));

        compact(ArgentumBlocks.SILVER_BLOCKS.base(), ArgentumItems.SILVER_INGOT).save(output);
        unCompact(ArgentumItems.SILVER_INGOT, ArgentumBlocks.SILVER_BLOCKS.base()).save(output, OConstants.modLoc("silver_ingot_from_block"));

        compact(ArgentumItems.SILVER_INGOT, ArgentumItems.SILVER_NUGGET).save(output, OConstants.modLoc("silver_ingot_from_nuggets"));
        unCompact(ArgentumItems.SILVER_NUGGET, ArgentumItems.SILVER_INGOT).save(output);

        makeMetalRecycling(output, ArgentumItems.SILVER_NUGGET, Stream.concat(ArgentumItems.silverArmor(), ArgentumSets.silverTools()).toList());

        shaped(RecipeCategory.TOOLS, ArgentumItems.SILVER_MIRROR)
                .pattern("ABA")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Tags.Items.INGOTS_GOLD)
                .define('B', CoreTags.Items.INGOTS_SILVER)
                .unlockedBy("has_gold_ingot", has(Tags.Items.INGOTS_GOLD))
                .unlockedBy("has_silver_ingot", has(CoreTags.Items.INGOTS_SILVER))
                .save(output);

        scribeConversionAndCutting(output, Blocks.ICE, ArgentumBlocks.GROOVED_ICE.value());
        scribeConversionAndCutting(output, Blocks.PACKED_ICE, ArgentumBlocks.GROOVED_PACKED_ICE.value());
        scribeConversionAndCutting(output, Blocks.BLUE_ICE, ArgentumBlocks.GROOVED_BLUE_ICE.value());

        scribeHarvesting(CoreTags.Blocks.AMETHYST_CLUSTERS, Blocks.SMALL_AMETHYST_BUD).save(output);
        scribeHarvesting(CoreTags.Blocks.QUARTZITE_CLUSTERS, BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(ModCompat.NO_MANS_LAND, "small_quartzite_bud")))
                .when(ModCompat.NO_MANS_LAND)
                .save(output);


        ArgentumBlocks.CUT_SILVERS.indexed().forEach(pair -> {
            var index = pair.getSecond();
            var cutSilver = pair.getFirst();

            var silverBlock = ArgentumBlocks.SILVER_BLOCKS.get(index);
            makePolishedStonecutting(output, cutSilver, silverBlock);

            var slab = ArgentumBlocks.CUT_SILVER_SLABS.get(index);
            makeSlabStonecutting(output, slab, cutSilver);

            var stairs = ArgentumBlocks.CUT_SILVER_STAIRS.get(index);
            makeStairsStonecutting(output, stairs, cutSilver);

            var pillar = ArgentumBlocks.SILVER_PILLARS.get(index);
            makePillarStonecutting(output, pillar, silverBlock);

            var chiseled = ArgentumBlocks.CHISELED_SILVER.get(index);
            makeChiseledStonecutting(output, chiseled, silverBlock, slab);

            var lattice = ArgentumBlocks.SILVER_LATTICES.get(index);
            makeStoneCutting(output, lattice, silverBlock);
            shaped(RecipeCategory.BUILDING_BLOCKS, lattice, 4)
                    .pattern(" # ")
                    .pattern("# #")
                    .pattern(" # ")
                    .define('#', cutSilver)
                    .unlockedBy("has_cut_silver", has(cutSilver))
                    .save(output);
        });

        makeBars(output, ArgentumBlocks.SILVER_BARS.base(), CoreTags.Items.INGOTS_SILVER);

        shaped(RecipeCategory.BUILDING_BLOCKS, ArgentumBlocks.SILVER_BULBS.base())
                .pattern(" C ")
                .pattern("CBC")
                .pattern(" R ")
                .define('C', ArgentumBlocks.CUT_SILVERS.base())
                .define('B', Items.BREEZE_ROD)
                .define('R', Items.REDSTONE)
                .unlockedBy("has_cut_silver", has(ArgentumBlocks.CUT_SILVERS.base()))
                .unlockedBy("has_breeze_rod", has(Items.BREEZE_ROD))
                .save(output);

        shaped(RecipeCategory.REDSTONE, ArgentumBlocks.SILVER_DOORS.base())
                .define('#', Ingredient.of(CoreTags.Items.INGOTS_SILVER))
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_silver", has(CoreTags.Items.INGOTS_SILVER))
                .save(output);

        shaped(RecipeCategory.REDSTONE, ArgentumBlocks.SILVER_TRAPDOORS.base())
                .define('#', CoreTags.Items.INGOTS_SILVER)
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_silver", has(CoreTags.Items.INGOTS_SILVER))
                .save(output);

        ArgentumSets.tarnishedBlocks().forEach(it -> brushing(output, it));
    }

    public static CuttingBoardRecipeBuilder scribeCuttingBoard(ItemLike from, ItemLike to) {
        var ingredient = new ItemAbilityIngredient(ArgentumItemAbilities.SCRIBE);
        return CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(from), ingredient.toVanilla(), to);
    }

    public static void scribeConversionAndCutting(RecipeOutput output, Block from, Block to) {
        var id = RecipeBuilder.getDefaultRecipeId(to);
        scribeConversion(from, to).save(output, id);
        whenLoaded(output, ModCompat.FARMERS_DELIGHT, () -> {
            scribeCuttingBoard(from, to).save(output, id.withPrefix("cutting/"));
        });
    }

    public static ScribeRecipe.Builder scribeConversion(Block from, Block to) {
        return new ScribeRecipe.Builder()
                .from(from)
                .result(to);
    }

    public static ScribeRecipe.Builder scribeHarvesting(TagKey<Block> from, Block to) {
        return new ScribeRecipe.Builder()
                .from(from)
                .result(to)
                .dropResources();
    }

    public static void brushing(RecipeOutput output, TarnishedBlocks<?> blocks) {
        brushing(output, blocks.tarnished(), blocks.blemished());
        brushing(output, blocks.blemished(), blocks.base());
    }

    public static void brushing(RecipeOutput output, ItemLike from, ItemLike to) {
        whenLoaded(output, ModCompat.FARMERS_DELIGHT, () -> {
            CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(from), Ingredient.of(Items.BRUSH), to)
                    .save(output, RecipeBuilder.getDefaultRecipeId(from).withPrefix("brushing/"));
        });
    }

}
