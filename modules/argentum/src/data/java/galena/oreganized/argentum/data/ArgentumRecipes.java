package galena.oreganized.argentum.data;

import static com.tterrag.registrate.providers.RegistrateRecipeProvider.getItemName;
import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;
import static galena.oreganized.data.provider.ORecipeProvider.*;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;

import galena.oreganized.ModCompat;
import galena.oreganized.OConstants;
import galena.oreganized.argentum.index.ArgentumBlocks;
import galena.oreganized.argentum.index.ArgentumItems;
import galena.oreganized.data.ODatagen;
import galena.oreganized.index.OTags;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;

@Mod(OConstants.MOD_ID)
public class ArgentumRecipes {

    public ArgentumRecipes() {
        ODatagen.addRecipeProvider(this::generate);
    }

    private void generate(RecipeOutput provider) {
        ore(
                ArgentumItems.SILVER_INGOT,
                List.of(ArgentumBlocks.SILVER_ORE.get(), ArgentumBlocks.DEEPSLATE_SILVER_ORE.get(), ArgentumItems.RAW_SILVER.get()),
                1.0F,
                provider
        );

        shaped(RecipeCategory.TOOLS, ArgentumItems.SCRIBE.get())
                .define('A', Items.AMETHYST_SHARD)
                .define('S', OTags.Items.INGOTS_SILVER)
                .pattern("A")
                .pattern("S")
                .pattern("S")
                .unlockedBy("has_silver", has(OTags.Items.INGOTS_SILVER))
                .unlockedBy("has_amethyst", has(Items.AMETHYST_SHARD))
                .save(provider);

        compact(ArgentumBlocks.RAW_SILVER_BLOCK.get().asItem(), ArgentumItems.RAW_SILVER.get()).save(provider);
        unCompact(ArgentumItems.RAW_SILVER.get(), ArgentumBlocks.RAW_SILVER_BLOCK.get().asItem()).save(provider, OConstants.modLoc("raw_silver_from_block"));

        compact(ArgentumBlocks.SILVER_BLOCKS.base().get().asItem(), ArgentumItems.SILVER_INGOT.get()).save(provider);
        unCompact(ArgentumItems.SILVER_INGOT.get(), ArgentumBlocks.SILVER_BLOCKS.base().get().asItem()).save(provider, OConstants.modLoc("silver_ingot_from_block"));

        compact(ArgentumItems.SILVER_INGOT.get(), ArgentumItems.SILVER_NUGGET.get()).save(provider, OConstants.modLoc("silver_ingot_from_nuggets"));
        unCompact(ArgentumItems.SILVER_NUGGET.get(), ArgentumItems.SILVER_INGOT.get()).save(provider);

        metalRecycling(provider, ArgentumItems.SILVER_NUGGET.get(), Stream.concat(ArgentumItems.silverArmor(), ArgentumSets.silverTools()).toList());

        shaped(RecipeCategory.TOOLS, ArgentumItems.SILVER_MIRROR.get())
                .pattern("ABA")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Tags.Items.INGOTS_GOLD)
                .define('B', OTags.Items.INGOTS_SILVER)
                .unlockedBy("has_gold_ingot", has(Tags.Items.INGOTS_GOLD))
                .unlockedBy("has_silver_ingot", has(OTags.Items.INGOTS_SILVER))
                .save(provider);

        scribeConversionAndCutting(provider, Blocks.ICE, ArgentumBlocks.GROOVED_ICE.get());
        scribeConversionAndCutting(provider, Blocks.PACKED_ICE, ArgentumBlocks.GROOVED_PACKED_ICE.get());
        scribeConversionAndCutting(provider, Blocks.BLUE_ICE, ArgentumBlocks.GROOVED_BLUE_ICE.get());

        scribeHarvesting(OTags.Blocks.AMETHYST_CLUSTERS, Blocks.SMALL_AMETHYST_BUD).save(provider);
        scribeHarvesting(OTags.Blocks.QUARTZITE_CLUSTERS, BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(ModCompat.NO_MANS_LAND, "small_quartzite_bud")))
                .when(ModCompat.NO_MANS_LAND)
                .save(provider);


        ArgentumBlocks.CUT_SILVERS.indexed().forEach(pair -> {
            var index = pair.getSecond();
            var cutSilver = pair.getFirst();

            var silverBlock = ArgentumBlocks.SILVER_BLOCKS.get(index);
            makePolishedStonecutting(cutSilver, silverBlock, provider);

            var slab = ArgentumBlocks.CUT_SILVER_SLABS.get(index);
            makeSlabStonecutting(slab, cutSilver, provider);

            var stairs = ArgentumBlocks.CUT_SILVER_STAIRS.get(index);
            makeStairsStonecutting(stairs, cutSilver, provider);

            var pillar = ArgentumBlocks.SILVER_PILLARS.get(index);
            stonecutting(silverBlock, pillar).save(provider, OConstants.modLoc("stonecutting/" + getItemName(pillar)));
            makePillar(pillar, silverBlock).save(provider);

            var chiseled = ArgentumBlocks.CHISELED_SILVER.get(index);
            makeChiseledStonecutting(chiseled, silverBlock, slab, provider);

            var lattice = ArgentumBlocks.SILVER_LATTICES.get(index);
            stonecutting(silverBlock, lattice).save(provider, OConstants.modLoc("stonecutting/" + getItemName(lattice)));
            shaped(RecipeCategory.BUILDING_BLOCKS, lattice, 4)
                    .pattern(" # ")
                    .pattern("# #")
                    .pattern(" # ")
                    .define('#', cutSilver)
                    .unlockedBy("has_cut_silver", has(cutSilver))
                    .save(provider);
        });

        makeBars(ArgentumBlocks.SILVER_BARS.base(), OTags.Items.INGOTS_SILVER).save(provider);

        shaped(RecipeCategory.BUILDING_BLOCKS, ArgentumBlocks.SILVER_BULBS.base())
                .pattern(" C ")
                .pattern("CBC")
                .pattern(" R ")
                .define('C', ArgentumBlocks.CUT_SILVERS.base())
                .define('B', Items.BREEZE_ROD)
                .define('R', Items.REDSTONE)
                .unlockedBy("has_cut_silver", has(ArgentumBlocks.CUT_SILVERS.base()))
                .unlockedBy("has_breeze_rod", has(Items.BREEZE_ROD))
                .save(provider);

        shaped(RecipeCategory.REDSTONE, ArgentumBlocks.SILVER_DOORS.base())
                .define('#', Ingredient.of(OTags.Items.INGOTS_SILVER))
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_silver", has(OTags.Items.INGOTS_SILVER))
                .save(provider);

        shaped(RecipeCategory.REDSTONE, ArgentumBlocks.SILVER_TRAPDOORS.base())
                .define('#', OTags.Items.INGOTS_SILVER)
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_silver", has(OTags.Items.INGOTS_SILVER))
                .save(provider);

        ArgentumSets.tarnishedBlocks().forEach(it -> brushing(provider, it));
    }
}
