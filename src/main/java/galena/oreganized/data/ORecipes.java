package galena.oreganized.data;

import com.google.common.collect.ImmutableList;
import galena.oreganized.Oreganized;
import galena.oreganized.data.provider.ORecipeProvider;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.OItems;
import galena.oreganized.index.OTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import org.infernalstudios.shieldexp.init.ItemsInit;
import umpaz.nethersdelight.common.registry.NDItems;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Consumer;

public class ORecipes extends ORecipeProvider {

    protected static final ImmutableList<ItemLike> LEAD_SMELTABLES = ImmutableList.of(OBlocks.LEAD_ORE.get(), OBlocks.DEEPSLATE_LEAD_ORE.get(), OItems.RAW_LEAD.get());
    protected static final ImmutableList<ItemLike> SILVER_SMELTABLES = ImmutableList.of(OBlocks.SILVER_ORE.get(), OBlocks.DEEPSLATE_SILVER_ORE.get(), OItems.RAW_SILVER.get());

    public ORecipes(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ore(OItems.LEAD_INGOT.get(), LEAD_SMELTABLES, 0.7F, "oreganized:lead_ingot", consumer);
        ore(OItems.SILVER_INGOT.get(), SILVER_SMELTABLES, 1.0F, "oreganized:silver_ingot", consumer);

        smeltingRecipe(OItems.LEAD_NUGGET.get(), OItems.BUSH_HAMMER.get(), 0.1F).save(consumer, Oreganized.modLoc("lead_nugget_from_smelting"));
        blastingRecipe(OItems.LEAD_NUGGET.get(), OItems.BUSH_HAMMER.get(), 0.1F).save(consumer, Oreganized.modLoc("lead_nugget_from_blasting"));

        quadTransform(OBlocks.POLISHED_GLANCE, OBlocks.GLANCE).save(consumer);
        quadTransform(OBlocks.GLANCE_BRICKS, OBlocks.POLISHED_GLANCE).save(consumer);

        compact(OBlocks.SILVER_BLOCK.get().asItem(), OItems.SILVER_INGOT.get()).save(consumer);
        compact(OBlocks.LEAD_BLOCK.get().asItem(), OItems.LEAD_INGOT.get()).save(consumer);
        compact(OBlocks.ELECTRUM_BLOCK.get().asItem(), OItems.ELECTRUM_INGOT.get()).save(consumer);

        compact(OBlocks.RAW_SILVER_BLOCK.get().asItem(), OItems.RAW_SILVER.get()).save(consumer);
        compact(OBlocks.RAW_LEAD_BLOCK.get().asItem(), OItems.RAW_LEAD.get()).save(consumer);

        compact(OItems.SILVER_INGOT.get(), OItems.SILVER_NUGGET.get()).save(consumer);
        compact(OItems.LEAD_INGOT.get(), OItems.LEAD_NUGGET.get()).save(consumer);
        compact(OItems.ELECTRUM_INGOT.get(), OItems.ELECTRUM_NUGGET.get()).save(consumer, Oreganized.modLoc("electrum_ingot_from_nuggets"));
        compact(Items.NETHERITE_INGOT, OItems.NETHERITE_NUGGET.get()).save(consumer, Oreganized.modLoc("netherite_ingot_from_nuggets"));

        unCompact(OItems.SILVER_INGOT.get(), OBlocks.SILVER_BLOCK.get().asItem()).save(consumer, Oreganized.modLoc("silver_ingot_from_block"));
        unCompact(OItems.LEAD_INGOT.get(), OBlocks.LEAD_BLOCK.get().asItem()).save(consumer, Oreganized.modLoc("lead_ingot_from_block"));
        unCompact(OItems.ELECTRUM_INGOT.get(), OBlocks.ELECTRUM_BLOCK.get().asItem()).save(consumer, Oreganized.modLoc("electrum_ingot_from_block"));

        unCompact(OItems.RAW_SILVER.get(), OBlocks.RAW_SILVER_BLOCK.get().asItem()).save(consumer, Oreganized.modLoc("raw_silver_from_block"));
        unCompact(OItems.RAW_LEAD.get(), OBlocks.RAW_LEAD_BLOCK.get().asItem()).save(consumer, Oreganized.modLoc("raw_lead_from_block"));

        unCompact(OItems.SILVER_NUGGET.get(), OItems.SILVER_INGOT.get()).save(consumer);
        unCompact(OItems.LEAD_NUGGET.get(), OItems.LEAD_INGOT.get()).save(consumer);
        unCompact(OItems.ELECTRUM_NUGGET.get(), OItems.ELECTRUM_INGOT.get()).save(consumer);
        unCompact(OItems.NETHERITE_NUGGET.get(), Items.NETHERITE_INGOT).save(consumer);

        makeSlabStonecutting(OBlocks.GLANCE_SLAB, OBlocks.GLANCE, consumer);
        makeSlabStonecutting(OBlocks.GLANCE_BRICK_SLAB, OBlocks.GLANCE_BRICKS, consumer);

        makeStairsStonecutting(OBlocks.GLANCE_STAIRS, OBlocks.GLANCE, consumer);
        makeStairsStonecutting(OBlocks.GLANCE_BRICK_STAIRS, OBlocks.GLANCE_BRICKS, consumer);

        makeWallStonecutting(OBlocks.GLANCE_WALL, OBlocks.GLANCE, consumer);
        makeWallStonecutting(OBlocks.GLANCE_BRICK_WALL, OBlocks.GLANCE_BRICKS, consumer);

        makeChiseledStonecutting(OBlocks.CHISELED_GLANCE, OBlocks.GLANCE, OBlocks.GLANCE_SLAB, consumer);

        stonecutting(OBlocks.GLANCE, OBlocks.POLISHED_GLANCE.get()).save(consumer, Oreganized.modLoc("stonecutting/polished_glance"));
        stonecutting(OBlocks.GLANCE, OBlocks.GLANCE_BRICKS.get()).save(consumer, Oreganized.modLoc("stonecutting/glance_bricks_from_glance"));
        stonecutting(OBlocks.GLANCE, OBlocks.GLANCE_BRICK_STAIRS.get()).save(consumer, Oreganized.modLoc("stonecutting/glance_brick_stairs_from_glance"));
        stonecutting(OBlocks.GLANCE, OBlocks.GLANCE_BRICK_SLAB.get(), 2).save(consumer, Oreganized.modLoc("stonecutting/glance_brick_slab_from_glance"));
        stonecutting(OBlocks.GLANCE, OBlocks.GLANCE_BRICK_WALL.get()).save(consumer, Oreganized.modLoc("stonecutting/glance_brick_wall_from_glance"));

        stonecutting(OBlocks.POLISHED_GLANCE, OBlocks.GLANCE_BRICKS.get()).save(consumer, Oreganized.modLoc("stonecutting/glance_bricks_from_polished"));
        stonecutting(OBlocks.POLISHED_GLANCE, OBlocks.GLANCE_BRICK_STAIRS.get()).save(consumer, Oreganized.modLoc("stonecutting/glance_brick_stairs_from_polished"));
        stonecutting(OBlocks.POLISHED_GLANCE, OBlocks.GLANCE_BRICK_SLAB.get(), 2).save(consumer, Oreganized.modLoc("stonecutting/glance_brick_slab_from_polished"));
        stonecutting(OBlocks.POLISHED_GLANCE, OBlocks.GLANCE_BRICK_WALL.get()).save(consumer, Oreganized.modLoc("stonecutting/glance_brick_wall_from_polished"));

        makeWaxed(OBlocks.WAXED_SPOTTED_GLANCE, OBlocks.SPOTTED_GLANCE).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, OBlocks.SPOTTED_GLANCE.get())
                .pattern(" X ")
                .pattern("XOX")
                .pattern(" X ")
                .define('X', OTags.Items.NUGGETS_LEAD)
                .define('O', OBlocks.GLANCE.get())
                .unlockedBy("has_glance", has(OBlocks.GLANCE.get()))
                .save(consumer);

        smithingElectrum(() -> Items.DIAMOND_SWORD, OItems.ELECTRUM_SWORD).save(consumer, Oreganized.modLoc("electrum_sword"));
        smithingElectrum(() -> Items.DIAMOND_SHOVEL, OItems.ELECTRUM_SHOVEL).save(consumer, Oreganized.modLoc("electrum_shovel"));
        smithingElectrum(() -> Items.DIAMOND_PICKAXE, OItems.ELECTRUM_PICKAXE).save(consumer, Oreganized.modLoc("electrum_pickaxe"));
        smithingElectrum(() -> Items.DIAMOND_AXE, OItems.ELECTRUM_AXE).save(consumer, Oreganized.modLoc("electrum_axe"));
        smithingElectrum(() -> Items.DIAMOND_HOE, OItems.ELECTRUM_HOE).save(consumer, Oreganized.modLoc("electrum_hoe"));
        smithingElectrum(ModItems.DIAMOND_KNIFE, OItems.ELECTRUM_KNIFE).save(consumer, Oreganized.modLoc("electrum_knife"));
        smithingElectrum(ItemsInit.DIAMOND_SHIELD, OItems.ELECTRUM_SHIELD).save(consumer, Oreganized.modLoc("electrum_shield"));
        smithingElectrum(NDItems.DIAMOND_MACHETE, OItems.ELECTRUM_MACHETE).save(consumer, Oreganized.modLoc("electrum_machete"));
        smithingElectrum(() -> Items.DIAMOND_HELMET, OItems.ELECTRUM_HELMET).save(consumer, Oreganized.modLoc("electrum_helmet"));
        smithingElectrum(() -> Items.DIAMOND_CHESTPLATE, OItems.ELECTRUM_CHESTPLATE).save(consumer, Oreganized.modLoc("electrum_chestplate"));
        smithingElectrum(() -> Items.DIAMOND_LEGGINGS, OItems.ELECTRUM_LEGGINGS).save(consumer, Oreganized.modLoc("electrum_leggings"));
        smithingElectrum(() -> Items.DIAMOND_BOOTS, OItems.ELECTRUM_BOOTS).save(consumer, Oreganized.modLoc("electrum_boots"));

        crystalGlass(OBlocks.BLACK_CRYSTAL_GLASS, Blocks.BLACK_STAINED_GLASS).save(consumer);
        crystalGlass(OBlocks.BLUE_CRYSTAL_GLASS, Blocks.BLUE_STAINED_GLASS).save(consumer);
        crystalGlass(OBlocks.BROWN_CRYSTAL_GLASS, Blocks.BROWN_STAINED_GLASS).save(consumer);
        crystalGlass(OBlocks.CYAN_CRYSTAL_GLASS, Blocks.CYAN_STAINED_GLASS).save(consumer);
        crystalGlass(OBlocks.GRAY_CRYSTAL_GLASS, Blocks.GRAY_STAINED_GLASS).save(consumer);
        crystalGlass(OBlocks.GREEN_CRYSTAL_GLASS, Blocks.GREEN_STAINED_GLASS).save(consumer);
        crystalGlass(OBlocks.LIGHT_BLUE_CRYSTAL_GLASS, Blocks.LIGHT_BLUE_STAINED_GLASS).save(consumer);
        crystalGlass(OBlocks.LIGHT_GRAY_CRYSTAL_GLASS, Blocks.LIGHT_GRAY_STAINED_GLASS).save(consumer);
        crystalGlass(OBlocks.LIME_CRYSTAL_GLASS, Blocks.LIME_STAINED_GLASS).save(consumer);
        crystalGlass(OBlocks.MAGENTA_CRYSTAL_GLASS, Blocks.MAGENTA_STAINED_GLASS).save(consumer);
        crystalGlass(OBlocks.ORANGE_CRYSTAL_GLASS, Blocks.ORANGE_STAINED_GLASS).save(consumer);
        crystalGlass(OBlocks.PINK_CRYSTAL_GLASS, Blocks.PINK_STAINED_GLASS).save(consumer);
        crystalGlass(OBlocks.PURPLE_CRYSTAL_GLASS, Blocks.PURPLE_STAINED_GLASS).save(consumer);
        crystalGlass(OBlocks.RED_CRYSTAL_GLASS, Blocks.RED_STAINED_GLASS).save(consumer);
        crystalGlass(OBlocks.WHITE_CRYSTAL_GLASS, Blocks.WHITE_STAINED_GLASS).save(consumer);
        crystalGlass(OBlocks.YELLOW_CRYSTAL_GLASS, Blocks.YELLOW_STAINED_GLASS).save(consumer);

        makeWaxed(OBlocks.WAXED_WHITE_CONCRETE_POWDER, Blocks.WHITE_CONCRETE_POWDER).save(consumer);
        makeWaxed(OBlocks.WAXED_ORANGE_CONCRETE_POWDER, Blocks.ORANGE_CONCRETE_POWDER).save(consumer);
        makeWaxed(OBlocks.WAXED_MAGENTA_CONCRETE_POWDER, Blocks.MAGENTA_CONCRETE_POWDER).save(consumer);
        makeWaxed(OBlocks.WAXED_LIGHT_BLUE_CONCRETE_POWDER, Blocks.LIGHT_BLUE_CONCRETE_POWDER).save(consumer);
        makeWaxed(OBlocks.WAXED_YELLOW_CONCRETE_POWDER, Blocks.YELLOW_CONCRETE_POWDER).save(consumer);
        makeWaxed(OBlocks.WAXED_LIME_CONCRETE_POWDER, Blocks.LIME_CONCRETE_POWDER).save(consumer);
        makeWaxed(OBlocks.WAXED_PINK_CONCRETE_POWDER, Blocks.PINK_CONCRETE_POWDER).save(consumer);
        makeWaxed(OBlocks.WAXED_GRAY_CONCRETE_POWDER, Blocks.GRAY_CONCRETE_POWDER).save(consumer);
        makeWaxed(OBlocks.WAXED_LIGHT_GRAY_CONCRETE_POWDER, Blocks.LIGHT_GRAY_CONCRETE_POWDER).save(consumer);
        makeWaxed(OBlocks.WAXED_CYAN_CONCRETE_POWDER, Blocks.CYAN_CONCRETE_POWDER).save(consumer);
        makeWaxed(OBlocks.WAXED_PURPLE_CONCRETE_POWDER, Blocks.PURPLE_CONCRETE_POWDER).save(consumer);
        makeWaxed(OBlocks.WAXED_BLUE_CONCRETE_POWDER, Blocks.BLUE_CONCRETE_POWDER).save(consumer);
        makeWaxed(OBlocks.WAXED_BROWN_CONCRETE_POWDER, Blocks.BROWN_CONCRETE_POWDER).save(consumer);
        makeWaxed(OBlocks.WAXED_GREEN_CONCRETE_POWDER, Blocks.GREEN_CONCRETE_POWDER).save(consumer);
        makeWaxed(OBlocks.WAXED_RED_CONCRETE_POWDER, Blocks.RED_CONCRETE_POWDER).save(consumer);
        makeWaxed(OBlocks.WAXED_BLACK_CONCRETE_POWDER, Blocks.BLACK_CONCRETE_POWDER).save(consumer);

        for (int i = 0; OBlocks.CRYSTAL_GLASS_PANES.size() > i; i++) {
            makeBars(OBlocks.CRYSTAL_GLASS_PANES.get(i), OBlocks.CRYSTAL_GLASS.get(i)).save(consumer);
        }

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, OBlocks.GLANCE.get(), 2)
                .pattern("AB")
                .pattern("BA")
                .define('A', OTags.Items.NUGGETS_LEAD)
                .define('B', Items.DIORITE)
                .unlockedBy("has_lead_ingot", has(OTags.Items.INGOTS_LEAD))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, OItems.BUSH_HAMMER.get())
                .pattern("AA")
                .pattern("B ")
                .define('A', OTags.Items.INGOTS_LEAD)
                .define('B', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_lead_ingot", has(OTags.Items.INGOTS_LEAD))
                .unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, OItems.SILVER_MIRROR.get())
                .pattern("ABA")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Tags.Items.INGOTS_GOLD)
                .define('B', OTags.Items.INGOTS_SILVER)
                .unlockedBy("has_gold_ingot", has(Tags.Items.INGOTS_GOLD))
                .unlockedBy("has_silver_ingot", has(OTags.Items.INGOTS_SILVER))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, OBlocks.GARGOYLE.get())
                .pattern(" P ")
                .pattern("#S#")
                .pattern("###")
                .define('#', ItemTags.STONE_CRAFTING_MATERIALS)
                .define('P', Items.CARVED_PUMPKIN)
                .define('S', OTags.Items.INGOTS_SILVER)
                .unlockedBy("has_pumpkin", has(Items.CARVED_PUMPKIN))
                .unlockedBy("has_silver_ingot", has(OTags.Items.INGOTS_SILVER))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, OBlocks.SHRAPNEL_BOMB.get())
                .pattern("ABA")
                .pattern("BAB")
                .pattern("ABA")
                .define('A', Tags.Items.GUNPOWDER)
                .define('B', OTags.Items.NUGGETS_LEAD)
                .unlockedBy("has_gunpowder", has(Tags.Items.GUNPOWDER))
                .unlockedBy("has_lead_nugget", has(OTags.Items.NUGGETS_LEAD))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION
                        , OItems.SHRAPNEL_BOMB_MINECART.get())
                .requires(OBlocks.SHRAPNEL_BOMB.get())
                .requires(Items.MINECART)
                .unlockedBy("has_shrapnel_bomb", has(OBlocks.SHRAPNEL_BOMB.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, OItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE.get(), 2)
                .pattern("ABA")
                .pattern("ACA")
                .pattern("AAA")
                .define('A', Tags.Items.GEMS_DIAMOND)
                .define('B', OItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE.get())
                .define('C', Items.STONE)
                .unlockedBy("has_template", has(OItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE.get()))
                .save(consumer);

        quadTransform(OBlocks.CUT_LEAD, OBlocks.LEAD_BLOCK, 8).save(consumer);
        quadTransform(OBlocks.LEAD_BRICKS, OBlocks.CUT_LEAD).save(consumer);
        makePillar(OBlocks.LEAD_PILLAR, OBlocks.CUT_LEAD).save(consumer);

        stonecutting(OBlocks.LEAD_BLOCK, OBlocks.CUT_LEAD.get(), 2).save(consumer, Oreganized.modLoc("stonecutting/cut_lead"));
        stonecutting(OBlocks.LEAD_BLOCK, OBlocks.LEAD_BRICKS.get(), 4).save(consumer, Oreganized.modLoc("stonecutting/lead_bricks"));
        stonecutting(OBlocks.CUT_LEAD, OBlocks.LEAD_BRICKS.get()).save(consumer, Oreganized.modLoc("stonecutting/lead_bricks_from_cut_lead"));
        stonecutting(OBlocks.LEAD_BLOCK, OBlocks.LEAD_PILLAR.get(), 4).save(consumer, Oreganized.modLoc("stonecutting/lead_pillar"));
        stonecutting(OBlocks.CUT_LEAD, OBlocks.LEAD_PILLAR.get()).save(consumer, Oreganized.modLoc("stonecutting/lead_pillar_from_cut_lad"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, OItems.LEAD_BOLT.get(), 1)
                .pattern("A")
                .pattern("A")
                .define('A', OTags.Items.INGOTS_LEAD)
                .unlockedBy("has_lead", has(OTags.Items.INGOTS_LEAD))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, OItems.FLINT_AND_PEWTER.get(), 1)
                .requires(OTags.Items.INGOTS_LEAD)
                .requires(Items.FLINT)
                .unlockedBy("has_lead", has(OTags.Items.INGOTS_LEAD))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BREWING, Items.POISONOUS_POTATO, 1)
                .requires(Items.POTATO)
                .requires(OTags.Items.NUGGETS_LEAD)
                .requires(OTags.Items.NUGGETS_LEAD)
                .requires(OTags.Items.NUGGETS_LEAD)
                .unlockedBy("has_lead", has(OTags.Items.NUGGETS_LEAD))
                .unlockedBy("has_potato", has(Items.POTATO))
                .save(consumer, Oreganized.modLoc("poisonous_potato_from_lead"));

        compact(OBlocks.LEAD_BOLT_CRATE.get().asItem(), OItems.LEAD_BOLT.get()).save(consumer);
        unCompact(OItems.LEAD_BOLT.get(), OBlocks.LEAD_BOLT_CRATE.get().asItem()).save(consumer, Oreganized.modLoc( "lead_bolt_from_crate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, OBlocks.LEAD_BULB.get(), 1)
                .pattern(" I ")
                .pattern("IGI")
                .pattern(" B ")
                .define('I', OTags.Items.INGOTS_LEAD)
                .define('G', Items.GLOW_INK_SAC)
                .define('B', OItems.MOLTEN_LEAD_BUCKET.get())
                .unlockedBy("has_lead", has(OTags.Items.INGOTS_LEAD))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, OBlocks.LEAD_DOOR.get())
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', Ingredient.of(OTags.Items.INGOTS_LEAD))
                .unlockedBy("has_lead", has(OTags.Items.INGOTS_LEAD))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, OBlocks.LEAD_TRAPDOOR.get())
                .define('#', OTags.Items.INGOTS_LEAD)
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_lead", has(OTags.Items.INGOTS_LEAD))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, OBlocks.LEAD_BARS.get(), 16)
                .define('#', OTags.Items.INGOTS_LEAD)
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_lead", has(OTags.Items.INGOTS_LEAD))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, OItems.SCRIBE.get())
                .define('A', Items.AMETHYST_SHARD)
                .define('S', OTags.Items.INGOTS_SILVER)
                .pattern("A")
                .pattern("S")
                .pattern("S")
                .unlockedBy("has_silver", has(OTags.Items.INGOTS_SILVER))
                .unlockedBy("has_amethyst", has(Items.AMETHYST_SHARD))
                .save(consumer);
    }
}
