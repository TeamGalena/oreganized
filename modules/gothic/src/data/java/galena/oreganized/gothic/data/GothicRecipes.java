package galena.oreganized.gothic.data;


import static com.tterrag.registrate.providers.RegistrateRecipeProvider.getHasName;
import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;
import static galena.oreganized.data.extensions.OColorExtensions.*;
import static galena.oreganized.data.extensions.OConditionExtensions.dyed;
import static galena.oreganized.data.extensions.ORecipeExtensions.*;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;

import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.gothic.index.GothicBlocks;
import galena.oreganized.index.CoreTags;
import java.util.function.Supplier;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(OConstants.MOD_ID)
public class GothicRecipes {

    public GothicRecipes() {
        ODatagen.addRecipeProvider(this::generate);
    }

    private void generate(RecipeOutput output) {
        GothicBlocks.CRYSTAL_GLASS.map().forEach((color, crystalGlass) -> {
            var glass = getColoredBlock(color, "stained_glass");
            dyed(color, output, () -> makeCrystalGlass(output, crystalGlass, glass));
        });

        GothicBlocks.CRYSTAL_GLASS_PANES.map().forEach((color, pane) ->
                dyed(color, output, () -> makePane(output, pane, GothicBlocks.CRYSTAL_GLASS.get(color)))
        );

        shaped(RecipeCategory.REDSTONE, GothicBlocks.GARGOYLE.get())
                .pattern(" P ")
                .pattern("#S#")
                .pattern("###")
                .define('#', ItemTags.STONE_CRAFTING_MATERIALS)
                .define('P', Items.CARVED_PUMPKIN)
                .define('S', CoreTags.Items.INGOTS_SILVER)
                .unlockedBy("has_pumpkin", has(Items.CARVED_PUMPKIN))
                .unlockedBy("has_silver_ingot", has(CoreTags.Items.INGOTS_SILVER))
                .save(output);

        makeStoneSetRecipes(output, GothicBlocks.DARK_GRIMSTONE_BRICKS);
        makeStoneSetRecipes(output, GothicBlocks.PALE_GRIMSTONE_BRICKS);
        makeStoneSetRecipes(output, GothicBlocks.POLISHED_DARK_GRIMSTONE);
        makeStoneSetRecipes(output, GothicBlocks.POLISHED_PALE_GRIMSTONE);
        makeStoneSetRecipes(output, GothicBlocks.DARK_GRIMSTONE_BRICKS, GothicBlocks.DARK_GRIMSTONE_SPYRE_BLOCK);
        makeStoneSetRecipes(output, GothicBlocks.PALE_GRIMSTONE_BRICKS, GothicBlocks.PALE_GRIMSTONE_SPYRE_BLOCK);
        makeStoneSetRecipes(output, GothicBlocks.DARK_GRIMSTONE_BRICKS, GothicBlocks.POLISHED_DARK_GRIMSTONE.block());
        makeStoneSetRecipes(output, GothicBlocks.PALE_GRIMSTONE_BRICKS, GothicBlocks.POLISHED_PALE_GRIMSTONE.block());
        makeStoneSetRecipes(output, GothicBlocks.POLISHED_DARK_GRIMSTONE, GothicBlocks.DARK_GRIMSTONE_SPYRE_BLOCK);
        makeStoneSetRecipes(output, GothicBlocks.POLISHED_PALE_GRIMSTONE, GothicBlocks.PALE_GRIMSTONE_SPYRE_BLOCK);

        makeSpyreCompacting(output, GothicBlocks.DARK_GRIMSTONE_SPYRE, GothicBlocks.DARK_GRIMSTONE_SPYRE_BLOCK);
        makeSpyreCompacting(output, GothicBlocks.PALE_GRIMSTONE_SPYRE, GothicBlocks.PALE_GRIMSTONE_SPYRE_BLOCK);

        makeQuadTransformStonecutting(output, GothicBlocks.POLISHED_DARK_GRIMSTONE.block(), GothicBlocks.DARK_GRIMSTONE_SPYRE_BLOCK);
        makeQuadTransformStonecutting(output, GothicBlocks.POLISHED_PALE_GRIMSTONE.block(), GothicBlocks.PALE_GRIMSTONE_SPYRE_BLOCK);

        makePillarStonecutting(output, GothicBlocks.DARK_GRIMSTONE_PILLAR, GothicBlocks.DARK_GRIMSTONE_SPYRE_BLOCK, GothicBlocks.POLISHED_DARK_GRIMSTONE.block());
        makePillarStonecutting(output, GothicBlocks.PALE_GRIMSTONE_PILLAR, GothicBlocks.PALE_GRIMSTONE_SPYRE_BLOCK, GothicBlocks.POLISHED_PALE_GRIMSTONE.block());

        makeQuadTransformStonecutting(output, GothicBlocks.DARK_GRIMSTONE_BRICKS.block(), GothicBlocks.POLISHED_DARK_GRIMSTONE.block());
        makeQuadTransformStonecutting(output, GothicBlocks.PALE_GRIMSTONE_BRICKS.block(), GothicBlocks.POLISHED_PALE_GRIMSTONE.block());
        makeStoneCutting(output, GothicBlocks.DARK_GRIMSTONE_SPYRE_BLOCK, GothicBlocks.DARK_GRIMSTONE_BRICKS.block());
        makeStoneCutting(output, GothicBlocks.PALE_GRIMSTONE_SPYRE_BLOCK, GothicBlocks.PALE_GRIMSTONE_BRICKS.block());

        makeChiseledStonecutting(output, GothicBlocks.CHISELED_DARK_GRIMSTONE, GothicBlocks.POLISHED_DARK_GRIMSTONE.block(), GothicBlocks.POLISHED_DARK_GRIMSTONE.slab());
        makeChiseledStonecutting(output, GothicBlocks.CHISELED_PALE_GRIMSTONE, GothicBlocks.POLISHED_PALE_GRIMSTONE.block(), GothicBlocks.POLISHED_PALE_GRIMSTONE.slab());
    }

    private static void makeSpyreCompacting(RecipeOutput output, DeferredHolder<Block, ?> from, DeferredHolder<Block, ?> to) {
        shaped(RecipeCategory.BUILDING_BLOCKS, to.value())
                .pattern("AA")
                .pattern("AA")
                .define('A', from.value())
                .unlockedBy(getHasName(from.value()), has(from.value()))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, from.value(), 4)
                .pattern("A")
                .pattern("A")
                .define('A', to.value())
                .unlockedBy(getHasName(from.value()), has(from.value()))
                .save(output);
    }

    public static void makeCrystalGlass(RecipeOutput output, Supplier<? extends Block> to, Block from) {
        crystalGlass(to, from).save(output);
    }

    public static ShapedRecipeBuilder crystalGlass(Supplier<? extends Block> to, Block from) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, to.get(), 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', from)
                .define('B', CoreTags.Items.INGOTS_LEAD)
                .unlockedBy("has_lead_ingot", has(CoreTags.Items.INGOTS_LEAD))
                .unlockedBy("has_any_glass", has(Tags.Items.GLASS_BLOCKS));
    }
}
