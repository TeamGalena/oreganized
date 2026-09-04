package galena.oreganized.electrum.data;


import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;
import static galena.oreganized.data.provider.ORecipeProvider.*;
import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.shapeless;

import com.possible_triangle.multikulti.datagen.conditions.Conditional;
import com.possible_triangle.multikulti.datagen.conditions.ModLoaded;
import com.simibubi.create.content.kinetics.mixer.MixingRecipe;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import galena.oreganized.ModCompat;
import galena.oreganized.OConstants;
import galena.oreganized.electrum.index.ElectrumBlocks;
import galena.oreganized.electrum.index.ElectrumItems;
import galena.oreganized.index.OTags;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
import org.infernalstudios.shieldexp.init.ItemsInit;
import vectorwing.farmersdelight.common.registry.ModItems;

public class ElectrumRecipes {

    static void generate(RegistrateRecipeProvider consumer) {
        compact(ElectrumBlocks.ELECTRUM_BLOCK.get().asItem(), ElectrumItems.ELECTRUM_INGOT.get()).save(consumer);
        unCompact(ElectrumItems.ELECTRUM_INGOT.get(), ElectrumBlocks.ELECTRUM_BLOCK.get().asItem()).save(consumer, OConstants.modLoc("electrum_ingot_from_block"));

        compact(ElectrumItems.ELECTRUM_INGOT.get(), ElectrumItems.ELECTRUM_NUGGET.get()).save(consumer, OConstants.modLoc("electrum_ingot_from_nuggets"));
        unCompact(ElectrumItems.ELECTRUM_NUGGET.get(), ElectrumItems.ELECTRUM_INGOT.get()).save(consumer);

        shaped(RecipeCategory.TOOLS, ElectrumItems.SPEEDOMETER.get())
                .pattern(" O ")
                .pattern(" X ")
                .define('X', OTags.Items.INGOTS_ELECTRUM)
                .define('O', Items.COMPASS)
                .unlockedBy("has_electrum_ingot", has(ElectrumItems.ELECTRUM_INGOT.get()))
                .save(consumer);

        shaped(RecipeCategory.MISC, ElectrumItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE.get(), 2)
                .pattern("ABA")
                .pattern("ACA")
                .pattern("AAA")
                .define('A', Tags.Items.GEMS_DIAMOND)
                .define('B', ElectrumItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE.get())
                .define('C', Items.STONE)
                .unlockedBy("has_template", has(ElectrumItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE.get()))
                .save(consumer);

        smithingElectrum(() -> Items.DIAMOND_SWORD, ElectrumItems.ELECTRUM_SWORD).save(consumer, OConstants.modLoc("electrum_sword"));
        smithingElectrum(() -> Items.DIAMOND_SHOVEL, ElectrumItems.ELECTRUM_SHOVEL).save(consumer, OConstants.modLoc("electrum_shovel"));
        smithingElectrum(() -> Items.DIAMOND_PICKAXE, ElectrumItems.ELECTRUM_PICKAXE).save(consumer, OConstants.modLoc("electrum_pickaxe"));
        smithingElectrum(() -> Items.DIAMOND_AXE, ElectrumItems.ELECTRUM_AXE).save(consumer, OConstants.modLoc("electrum_axe"));
        smithingElectrum(() -> Items.DIAMOND_HOE, ElectrumItems.ELECTRUM_HOE).save(consumer, OConstants.modLoc("electrum_hoe"));

        whenLoaded(smithingElectrum(ModItems.DIAMOND_KNIFE, ElectrumItems.ELECTRUM_KNIFE), "farmersdelight").save(consumer, OConstants.modLoc("electrum_knife"));
        whenLoaded(smithingElectrum(ItemsInit.DIAMOND_SHIELD, ElectrumItems.ELECTRUM_SHIELD), "shieldexp").save(consumer, OConstants.modLoc("electrum_shield"));
        // TODO re-add when ported to 1.21.1
        // whenLoaded(smithingElectrum(NDItems.DIAMOND_MACHETE, ElectrumItems.ELECTRUM_MACHETE), "nethersdelight").save(consumer, OConstants.modLoc("electrum_machete"));
        smithingElectrum(() -> Items.DIAMOND_HELMET, ElectrumItems.ELECTRUM_HELMET).save(consumer, OConstants.modLoc("electrum_helmet"));
        smithingElectrum(() -> Items.DIAMOND_CHESTPLATE, ElectrumItems.ELECTRUM_CHESTPLATE).save(consumer, OConstants.modLoc("electrum_chestplate"));
        smithingElectrum(() -> Items.DIAMOND_LEGGINGS, ElectrumItems.ELECTRUM_LEGGINGS).save(consumer, OConstants.modLoc("electrum_leggings"));
        smithingElectrum(() -> Items.DIAMOND_BOOTS, ElectrumItems.ELECTRUM_BOOTS).save(consumer, OConstants.modLoc("electrum_boots"));

        // TODO modular add whenLoaded
        Conditional.with(consumer, List.of(new ModLoaded(ModCompat.FARMERS_DELIGHT_ID)), () ->
                metalRecycling(consumer, ElectrumItems.ELECTRUM_NUGGET, List.of(ElectrumItems.ELECTRUM_KNIFE), "_from_knife")
        );
        Conditional.with(consumer, List.of(new ModLoaded(ModCompat.NETHERS_DELIGHT_ID)), () ->
                metalRecycling(consumer, ElectrumItems.ELECTRUM_NUGGET, List.of(ElectrumItems.ELECTRUM_MACHETE), "_from_machete")
        );

        unlessLoaded(
                shapeless(RecipeCategory.MISC, ElectrumItems.ELECTRUM_INGOT.get())
                        .requires(OTags.Items.INGOTS_SILVER)
                        .requires(OTags.Items.INGOTS_SILVER)
                        .requires(OTags.Items.INGOTS_SILVER)
                        .requires(OTags.Items.INGOTS_SILVER)
                        .requires(OTags.Items.INGOTS_SILVER)
                        .requires(OTags.Items.INGOTS_GOLD)
                        .requires(OTags.Items.INGOTS_GOLD)
                        .requires(OTags.Items.INGOTS_GOLD)
                        .unlockedBy("has_gold", has(OTags.Items.INGOTS_GOLD))
                        .unlockedBy("has_silver", has(OTags.Items.INGOTS_SILVER)),
                ModCompat.CREATE
        ).save(consumer);

        processing(MixingRecipe::new, "electrum_ingot")
                .output(ElectrumItems.ELECTRUM_INGOT.get())
                .require(OTags.Items.INGOTS_SILVER)
                .require(OTags.Items.INGOTS_SILVER)
                .require(OTags.Items.INGOTS_SILVER)
                .require(OTags.Items.INGOTS_SILVER)
                .require(OTags.Items.INGOTS_SILVER)
                .require(OTags.Items.INGOTS_GOLD)
                .require(OTags.Items.INGOTS_GOLD)
                .require(OTags.Items.INGOTS_GOLD)
                .requiresHeat(HeatCondition.HEATED)
                .build(consumer);
    }

    public static SmithingTransformRecipeBuilder smithingElectrum(Supplier<? extends Item> input, Supplier<? extends Item> result) {
        return smithingRecipe(input, ElectrumItems.ELECTRUM_INGOT, ElectrumItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE, result);
    }
}
