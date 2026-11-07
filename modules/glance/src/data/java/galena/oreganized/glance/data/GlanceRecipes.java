package galena.oreganized.glance.data;


import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;
import static galena.oreganized.data.extensions.ORecipeExtensions.*;
import static galena.oreganized.data.extensions.ORecipeExtensions.makeChiseledStonecutting;
import static galena.oreganized.data.extensions.ORecipeExtensions.makeWaxed;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;

import com.possible_triangle.multikulti.datagen.conditions.Conditional;
import com.possible_triangle.multikulti.datagen.conditions.ModLoaded;
import com.possible_triangle.multikulti.datagen.conditions.TagPopulated;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.fluids.transfer.FillingRecipe;
import com.simibubi.create.content.kinetics.crusher.CrushingRecipe;
import com.simibubi.create.content.kinetics.fan.processing.SplashingRecipe;
import com.simibubi.create.content.kinetics.mixer.MixingRecipe;
import galena.oreganized.data.ODatagen;
import galena.oreganized.glance.GlanceModule;
import galena.oreganized.glance.index.GlanceBlocks;
import galena.oreganized.glance.index.GlanceTags;
import galena.oreganized.plumbum.PlumbumModule;
import galena.oreganized.plumbum.index.PlumbumItems;
import galena.oreganized.plumbum.index.PlumbumTags;
import java.util.List;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.fml.common.Mod;

@Mod(GlanceModule.MOD_ID)
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
                .define('X', PlumbumTags.Items.NUGGETS_LEAD)
                .define('O', GlanceBlocks.GLANCE.block())
                .unlockedBy("has_glance", has(GlanceBlocks.GLANCE.block()))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, GlanceBlocks.GLANCE.block(), 2)
                .pattern("AB")
                .pattern("BA")
                .define('A', PlumbumTags.Items.NUGGETS_LEAD)
                .define('B', Items.DIORITE)
                .unlockedBy("has_lead_ingot", has(PlumbumTags.Items.INGOTS_LEAD))
                .save(output);

        processing(FillingRecipe::new, "spotted_glance")
                .output(GlanceBlocks.SPOTTED_GLANCE)
                .require(GlanceBlocks.GLANCE.block())
                .require(GlanceTags.Fluids.RENEWS_SPOTTED_GLANCE, 250)
                .build(output);

        Conditional.with(output, List.of(new TagPopulated(PlumbumTags.Items.NUGGETS_LEAD)), () -> {
            processing(MixingRecipe::new, "glance")
                    .output(GlanceBlocks.GLANCE.block())
                    .require(Items.DIORITE)
                    .require(PlumbumTags.Items.NUGGETS_LEAD)
                    .build(output);
        });

        // TODO could also include other mod's lead in the future
        leadRecipes(output, PlumbumModule.MOD_ID, PlumbumItems.LEAD_NUGGET);
    }

    private static void leadRecipes(RecipeOutput output, String modId, ItemLike nugget) {
        Conditional.with(output, List.of(new ModLoaded(modId)), () -> {
            processing(CrushingRecipe::new, "glance")
                    .output(0.8F, AllItems.CRUSHED_LEAD, 1)
                    .output(0.8F, nugget)
                    .require(GlanceBlocks.GLANCE.block())
                    .duration(250)
                    .build(output);

            processing(CrushingRecipe::new, "glance_recycling")
                    .output(0.8F, AllItems.CRUSHED_LEAD, 1)
                    .output(0.8F, nugget)
                    .require(GlanceTags.Items.STONE_TYPES_GLANCE)
                    .duration(250)
                    .build(output);

            processing(SplashingRecipe::new, "spotted_glance")
                    .output(1.5F, nugget)
                    .require(GlanceBlocks.SPOTTED_GLANCE)
                    .build(output);
        });
    }
}
