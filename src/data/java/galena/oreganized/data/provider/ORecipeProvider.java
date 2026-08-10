package galena.oreganized.data.provider;

import com.possible_triangle.multikulti.datagen.conditions.Conditional;
import com.possible_triangle.multikulti.datagen.conditions.Inverted;
import com.possible_triangle.multikulti.datagen.conditions.ModLoaded;
import com.simibubi.create.content.kinetics.deployer.ItemApplicationRecipe;
import com.simibubi.create.content.kinetics.millstone.MillingRecipe;
import com.simibubi.create.content.processing.recipe.StandardProcessingRecipe;
import galena.oreganized.ModCompat;
import galena.oreganized.Oreganized;
import galena.oreganized.index.OItems;
import galena.oreganized.index.OTags;
import galena.oreganized.index.TarnishedBlocks;
import galena.oreganized.world.recipe.ScribeRecipe;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.neoforged.neoforge.common.Tags;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

public abstract class ORecipeProvider extends RecipeProvider {

    public ORecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup);
    }

    public ShapedRecipeBuilder makeSlab(Supplier<? extends Block> slabOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, slabOut.get(), 6)
                .pattern("AAA")
                .define('A', blockIn.get())
                .unlockedBy(getHasName(blockIn.get()), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeStairs(Supplier<? extends Block> stairsOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stairsOut.get(), 4)
                .pattern("A  ")
                .pattern("AA ")
                .pattern("AAA")
                .define('A', blockIn.get())
                .unlockedBy(getHasName(blockIn.get()), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeWall(Supplier<? extends Block> wallOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, wallOut.get(), 6)
                .pattern("AAA")
                .pattern("AAA")
                .define('A', blockIn.get())
                .unlockedBy(getHasName(blockIn.get()), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makePane(Supplier<? extends Block> barsOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, barsOut.get(), 16)
                .pattern("AAA")
                .pattern("AAA")
                .define('A', blockIn.get())
                .unlockedBy(getHasName(blockIn.get()), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeBars(Supplier<? extends Block> barsOut, TagKey<Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, barsOut.get(), 16)
                .define('#', itemIn)
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_lead", has(itemIn));
    }

    public ShapedRecipeBuilder quadTransform(Supplier<? extends Block> blockOut, Supplier<? extends Block> blockIn) {
        return quadTransform(blockOut, blockIn, 4);
    }

    public ShapedRecipeBuilder quadTransform(Supplier<? extends Block> blockOut, Supplier<? extends Block> blockIn, int amount) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockOut.get(), amount)
                .pattern("AA")
                .pattern("AA")
                .define('A', blockIn.get())
                .unlockedBy(getHasName(blockIn.get()), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeChiseled(Supplier<? extends Block> blockOut, Supplier<? extends SlabBlock> slabIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockOut.get())
                .pattern("A")
                .pattern("A")
                .define('A', slabIn.get())
                .unlockedBy(getHasName(slabIn.get()), has(slabIn.get()));
    }

    public ShapedRecipeBuilder makePillar(Supplier<? extends Block> blockOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockOut.get(), 2)
                .pattern("A")
                .pattern("A")
                .define('A', blockIn.get())
                .unlockedBy(getHasName(blockIn.get()), has(blockIn.get()));
    }

    public ShapedRecipeBuilder compact(Item itemOut, Item itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, itemOut)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', itemIn)
                .unlockedBy("has_" + getItemName(itemIn), has(itemIn));
    }

    public ShapelessRecipeBuilder unCompact(Item itemOut, Item itemIn) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, itemOut, 9)
                .requires(itemIn)
                .unlockedBy("has_" + getItemName(itemIn), has(itemIn));
    }

    public ShapedRecipeBuilder crystalGlass(Supplier<? extends Block> blockOut, Block blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, blockOut.get(), 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', blockIn)
                .define('B', OTags.Items.INGOTS_LEAD)
                .unlockedBy("has_lead_ingot", has(OTags.Items.INGOTS_LEAD))
                .unlockedBy("has_any_glass", has(Tags.Items.GLASS_BLOCKS));
    }

    public void ore(ItemLike result, List<ItemLike> ingredients, float xp, String group, RecipeOutput consumer) {
        oreSmeltingRecipe(result, ingredients, xp, group, consumer);
        oreBlastingRecipe(result, ingredients, xp, group, consumer);
    }

    public SimpleCookingRecipeBuilder smeltingRecipe(ItemLike result, ItemLike ingredient, float exp) {
        return smeltingRecipe(result, ingredient, exp, 1);
    }

    private void oreSmeltingRecipe(ItemLike result, List<ItemLike> ingredients, float xp, String group, RecipeOutput consumer) {
        for (ItemLike ingredient : ingredients) {
            smeltingRecipe(result, ingredient, xp, 1).group(group).save(consumer, Oreganized.modLoc("smelt_" + getItemName(ingredient.asItem())));
        }
    }

    public SimpleCookingRecipeBuilder smeltingRecipe(ItemLike result, ItemLike ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(new ItemStack(ingredient, count)), RecipeCategory.MISC, result, exp, 200)
                .unlockedBy(getHasName(ingredient), has(ingredient));
    }

    public SimpleCookingRecipeBuilder smeltingRecipeTag(ItemLike result, TagKey<Item> ingredient, float exp) {
        return smeltingRecipeTag(result, ingredient, exp, 1);
    }

    public SimpleCookingRecipeBuilder smeltingRecipeTag(ItemLike result, TagKey<Item> ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.MISC, result, exp, 200)
                .unlockedBy("has_" + ingredient.location().getPath(), has(ingredient));
    }

    public SimpleCookingRecipeBuilder blastingRecipe(ItemLike result, ItemLike ingredient, float exp) {
        return blastingRecipe(result, ingredient, exp, 1);
    }

    private void oreBlastingRecipe(ItemLike result, List<ItemLike> ingredients, float xp, String group, RecipeOutput consumer) {
        for (ItemLike ingredient : ingredients) {
            blastingRecipe(result, ingredient, xp, 1).group(group).save(consumer, Oreganized.modLoc("blast_" + getItemName(ingredient)));
        }
    }

    public SimpleCookingRecipeBuilder blastingRecipe(ItemLike result, ItemLike ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.blasting(Ingredient.of(new ItemStack(ingredient, count)), RecipeCategory.MISC, result, exp, 100)
                .unlockedBy(getHasName(ingredient), has(ingredient));
    }

    public SimpleCookingRecipeBuilder blastingRecipeTag(ItemLike result, TagKey<Item> ingredient, float exp) {
        return blastingRecipeTag(result, ingredient, exp, 1);
    }

    public SimpleCookingRecipeBuilder armorRecycling(ItemLike nugget, Collection<? extends Holder<? extends ItemLike>> armor) {
        var builder = SimpleCookingRecipeBuilder.blasting(Ingredient.of(armor.stream().map(Holder::value).map(ItemStack::new)), RecipeCategory.MISC, nugget, 0.1F, 100);
        for (var holder : armor) {
            var item = holder.value();
            builder.unlockedBy(getHasName(item), has(item));
        }
        return builder;
    }

    public SimpleCookingRecipeBuilder blastingRecipeTag(ItemLike result, TagKey<Item> ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient), RecipeCategory.MISC, result, exp, 100)
                .unlockedBy("has_" + ingredient.location().getPath(), has(ingredient));
    }

    public SmithingTransformRecipeBuilder smithingRecipe(Supplier<? extends Item> input, Supplier<? extends Item> upgradeItem, Supplier<? extends Item> templateItem, Supplier<? extends Item> result) {
        return SmithingTransformRecipeBuilder.smithing(Ingredient.of(templateItem.get()), Ingredient.of(input.get()), Ingredient.of(upgradeItem.get()), RecipeCategory.MISC, result.get())
                .unlocks(getHasName(upgradeItem.get()), has(upgradeItem.get()));
    }

    public SmithingTransformRecipeBuilder smithingRecipe(Supplier<? extends Item> input, TagKey<Item> upgradeItem, Supplier<? extends Item> templateItem, Supplier<? extends Item> result) {
        return SmithingTransformRecipeBuilder.smithing(Ingredient.of(templateItem.get()), Ingredient.of(input.get()), Ingredient.of(upgradeItem), RecipeCategory.MISC, result.get())
                .unlocks("has_" + upgradeItem.location().getPath(), has(upgradeItem));
    }

    public SmithingTransformRecipeBuilder smithingElectrum(Supplier<? extends Item> input, Supplier<? extends Item> result) {
        return smithingRecipe(input, OItems.ELECTRUM_INGOT, OItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE, result);
    }

    public SingleItemRecipeBuilder stonecutting(Supplier<? extends Block> input, ItemLike result) {
        return SingleItemRecipeBuilder.stonecutting(Ingredient.of(input.get()), RecipeCategory.BUILDING_BLOCKS, result)
                .unlockedBy(getHasName(input.get()), has(input.get()));
    }

    public SingleItemRecipeBuilder stonecutting(Supplier<? extends Block> input, ItemLike result, int resultAmount) {
        return SingleItemRecipeBuilder.stonecutting(Ingredient.of(input.get()), RecipeCategory.BUILDING_BLOCKS, result, resultAmount)
                .unlockedBy(getHasName(input.get()), has(input.get()));
    }

    public ShapelessRecipeBuilder makeWaxed(Supplier<? extends Block> blockOut, Block blockIn) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, blockOut.get())
                .requires(blockIn)
                .requires(Items.HONEYCOMB)
                .unlockedBy(getHasName(blockIn), has(blockIn))
                .unlockedBy("has_honeycomb", has(Items.HONEYCOMB));
    }

    public ShapelessRecipeBuilder makeWaxed(Supplier<? extends Block> blockOut, Supplier<? extends Block> blockIn) {
        return makeWaxed(blockOut, blockIn.get());
    }

    public void makeSlabStonecutting(Supplier<? extends Block> blockOut, Supplier<? extends Block> blockIn, RecipeOutput consumer) {
        makeSlab(blockOut, blockIn).save(consumer);
        stonecutting(blockIn, blockOut.get(), 2).save(consumer, Oreganized.modLoc("stonecutting/" + getItemName(blockOut.get())));
    }

    public void makeStairsStonecutting(Supplier<? extends Block> blockOut, Supplier<? extends Block> blockIn, RecipeOutput consumer) {
        makeStairs(blockOut, blockIn).save(consumer);
        stonecutting(blockIn, blockOut.get()).save(consumer, Oreganized.modLoc("stonecutting/" + getItemName(blockOut.get())));
    }

    public void makeWallStonecutting(Supplier<? extends Block> blockOut, Supplier<? extends Block> blockIn, RecipeOutput consumer) {
        makeWall(blockOut, blockIn).save(consumer);
        stonecutting(blockIn, blockOut.get()).save(consumer, Oreganized.modLoc("stonecutting/" + getItemName(blockOut.get())));
    }

    public void makeChiseledStonecutting(Supplier<? extends Block> blockOut, Supplier<? extends Block> blockIn, Supplier<? extends SlabBlock> slabIn, RecipeOutput consumer) {
        makeChiseled(blockOut, slabIn).save(consumer);
        stonecutting(blockIn, blockOut.get()).save(consumer, Oreganized.modLoc("stonecutting/" + getItemName(blockOut.get())));
    }

    public void makePolishedStonecutting(Supplier<? extends Block> blockOut, Supplier<? extends Block> blockIn, RecipeOutput consumer) {
        polished(consumer, RecipeCategory.BUILDING_BLOCKS, blockOut.get(), blockIn.get());
        stonecutting(blockIn, blockOut.get()).save(consumer, Oreganized.modLoc("stonecutting/" + getItemName(blockOut.get())));
    }

    public <R extends StandardProcessingRecipe<?>> StandardProcessingRecipe.Builder<R> processing(StandardProcessingRecipe.Factory<R> factory, String id) {
        return whenLoaded(
                new StandardProcessingRecipe.Builder<>(factory, Oreganized.modLoc(id)),
                ModCompat.CREATE
        );
    }

    public <R extends ItemApplicationRecipe> ItemApplicationRecipe.Builder<R> application(ItemApplicationRecipe.Factory<R> factory, String id) {
        return whenLoaded(
                new ItemApplicationRecipe.Builder<>(factory, Oreganized.modLoc(id)),
                ModCompat.CREATE
        );
    }

    public ScribeRecipe.Builder scribeConversion(Block from, Block to) {
        return new ScribeRecipe.Builder()
                .from(from)
                .result(to);
    }

    public ScribeRecipe.Builder scribeHarvesting(TagKey<Block> from, Block to) {
        return new ScribeRecipe.Builder()
                .from(from)
                .result(to)
                .dropResources();
    }

    public CuttingBoardRecipeBuilder scribeCuttingBoard(ItemLike from, ItemLike to) {
        return CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(from), Ingredient.of(OItems.SCRIBE.asItem()), to);
    }

    public void brushing(RecipeOutput output, TarnishedBlocks<?> blocks) {
        brushing(output, blocks.tarnished(), blocks.blemished());
        brushing(output, blocks.blemished(), blocks.base());
    }

    public void brushing(RecipeOutput output, ItemLike from, ItemLike to) {
        Conditional.with(this, List.of(new ModLoaded(ModCompat.FARMERS_DELIGHT_ID)), () -> {
            CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(from), Ingredient.of(Items.BRUSH), to)
                    .save(output, RecipeBuilder.getDefaultRecipeId(from).withPrefix("brushing/"));
        });
    }

    public void scribeConversionAndCutting(RecipeOutput output, Block from, Block to) {
        var id = RecipeBuilder.getDefaultRecipeId(to);
        scribeConversion(from, to).save(output, id);
        Conditional.with(this, List.of(new ModLoaded(ModCompat.FARMERS_DELIGHT_ID)), () -> {
            scribeCuttingBoard(from, to).save(output, id.withPrefix("cutting/"));
        });
    }

    public void flowerDye(Supplier<? extends ItemLike> flower, ItemLike primary, RecipeOutput consumer) {
        var name = getItemName(flower.get());

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, primary)
                .requires(flower.get())
                .unlockedBy(getHasName(flower.get()), has(flower.get()))
                .save(consumer, Oreganized.modLoc("dye_from_" + name));

        processing(MillingRecipe::new, name)
                .require(flower.get())
                .output(primary, 2)
                .output(0.05F, Items.GREEN_DYE)
                .build(consumer);

        Conditional.with(this, List.of(new ModLoaded(ModCompat.FARMERS_DELIGHT_ID)), () -> {
            CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(flower.get()), Ingredient.of(OTags.Items.TOOLS_KNIVES), primary, 2)
                    .save(consumer);
        });
    }

    public <T> T unlessLoaded(T value, String... modIds) {
        return Conditional.with(value, new Inverted(new ModLoaded(modIds, true)));
    }

    public <T> T whenLoaded(T value, String... modIds) {
        return Conditional.with(value, new ModLoaded(modIds));
    }
}
