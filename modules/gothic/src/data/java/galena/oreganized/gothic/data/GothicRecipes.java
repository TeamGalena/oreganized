package galena.oreganized.gothic.data;


import static com.tterrag.registrate.providers.RegistrateRecipeProvider.getHasName;
import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;
import static galena.oreganized.data.ConditionalData.dyed;
import static galena.oreganized.data.extensions.ORecipeExtensions.*;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;

import galena.oreganized.OConstants;
import galena.oreganized.data.ColorCompat;
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

    private void generate(RecipeOutput provider) {
        GothicBlocks.CRYSTAL_GLASS.map().forEach((color, crystalGlass) -> {
            var glass = ColorCompat.getColoredBlock("stained_glass", color);
            dyed(color, crystalGlass(crystalGlass, glass)).save(provider);
        });

        GothicBlocks.CRYSTAL_GLASS_PANES.map().forEach((color, pane) ->
                dyed(color, makePane(pane, GothicBlocks.CRYSTAL_GLASS.get(color))).save(provider)
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
                .save(provider);

        stoneSet(provider, GothicBlocks.DARK_GRIMSTONE_BRICKS);
        stoneSet(provider, GothicBlocks.PALE_GRIMSTONE_BRICKS);
        stoneSet(provider, GothicBlocks.POLISHED_DARK_GRIMSTONE);
        stoneSet(provider, GothicBlocks.POLISHED_PALE_GRIMSTONE);

        spyreCompacting(provider, GothicBlocks.DARK_GRIMSTONE_SPYRE_BLOCK, GothicBlocks.DARK_GRIMSTONE_SPYRE);
        spyreCompacting(provider, GothicBlocks.PALE_GRIMSTONE_SPYRE_BLOCK, GothicBlocks.PALE_GRIMSTONE_SPYRE);

        quadTransform(GothicBlocks.POLISHED_DARK_GRIMSTONE.block(), GothicBlocks.DARK_GRIMSTONE_SPYRE_BLOCK).save(provider);
        quadTransform(GothicBlocks.POLISHED_PALE_GRIMSTONE.block(), GothicBlocks.PALE_GRIMSTONE_SPYRE_BLOCK).save(provider);

        makePillar(GothicBlocks.DARK_GRIMSTONE_PILLAR, GothicBlocks.POLISHED_DARK_GRIMSTONE.block()).save(provider);
        makePillar(GothicBlocks.PALE_GRIMSTONE_PILLAR, GothicBlocks.POLISHED_PALE_GRIMSTONE.block()).save(provider);

        quadTransform(GothicBlocks.DARK_GRIMSTONE_BRICKS.block(), GothicBlocks.POLISHED_DARK_GRIMSTONE.block()).save(provider);
        quadTransform(GothicBlocks.PALE_GRIMSTONE_BRICKS.block(), GothicBlocks.POLISHED_PALE_GRIMSTONE.block()).save(provider);

        makeChiseledStonecutting(GothicBlocks.CHISELED_DARK_GRIMSTONE, GothicBlocks.POLISHED_DARK_GRIMSTONE.block(), GothicBlocks.POLISHED_DARK_GRIMSTONE.slab(), provider);
        makeChiseledStonecutting(GothicBlocks.CHISELED_PALE_GRIMSTONE, GothicBlocks.POLISHED_PALE_GRIMSTONE.block(), GothicBlocks.POLISHED_PALE_GRIMSTONE.slab(), provider);
    }

    private static void spyreCompacting(RecipeOutput output, DeferredHolder<Block, ?> from, DeferredHolder<Block, ?> to) {
        shaped(RecipeCategory.BUILDING_BLOCKS, to.value())
                .pattern("AA")
                .pattern("AA")
                .define('A', GothicBlocks.DARK_GRIMSTONE_SPYRE)
                .unlockedBy(getHasName(from.value()), has(from.value()))
                .save(output);
    }

    public static ShapedRecipeBuilder crystalGlass(Supplier<? extends Block> blockOut, Block blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, blockOut.get(), 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', blockIn)
                .define('B', CoreTags.Items.INGOTS_LEAD)
                .unlockedBy("has_lead_ingot", has(CoreTags.Items.INGOTS_LEAD))
                .unlockedBy("has_any_glass", has(Tags.Items.GLASS_BLOCKS));
    }
}
