package galena.oreganized.data.extensions;

import static com.tterrag.registrate.providers.RegistrateRecipeProvider.*;

import com.possible_triangle.multikulti.datagen.conditions.Conditional;
import com.possible_triangle.multikulti.datagen.conditions.Inverted;
import com.possible_triangle.multikulti.datagen.conditions.ModLoaded;
import com.simibubi.create.content.kinetics.deployer.DeployerApplicationRecipe;
import com.simibubi.create.content.kinetics.deployer.ItemApplicationRecipe;
import com.simibubi.create.content.kinetics.millstone.MillingRecipe;
import com.simibubi.create.content.processing.recipe.StandardProcessingRecipe;
import galena.oreganized.ModCompat;
import galena.oreganized.OConstants;
import galena.oreganized.index.sets.StoneSet;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import net.minecraft.core.Holder;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.registries.DeferredHolder;
import vectorwing.farmersdelight.common.crafting.ingredient.ItemAbilityIngredient;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

/**
 * method prefixed with `make` should accept the `RecipeOutput` as their first argument and actually save the recipes
 * all other methods should not do any of the two and instead return the recipe builder.
 */
public final class ORecipeExtensions {

    public static ShapedRecipeBuilder slab(Supplier<? extends Block> to, Supplier<? extends Block> from) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, to.get(), 6)
                .pattern("AAA")
                .define('A', from.get())
                .unlockedBy(getHasName(from.get()), has(from.get()));
    }

    public static ShapedRecipeBuilder stairs(Supplier<? extends Block> to, Supplier<? extends Block> from) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, to.get(), 4)
                .pattern("A  ")
                .pattern("AA ")
                .pattern("AAA")
                .define('A', from.get())
                .unlockedBy(getHasName(from.get()), has(from.get()));
    }

    public static ShapedRecipeBuilder wall(Supplier<? extends Block> to, Supplier<? extends Block> from) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, to.get(), 6)
                .pattern("AAA")
                .pattern("AAA")
                .define('A', from.get())
                .unlockedBy(getHasName(from.get()), has(from.get()));
    }

    public static ShapedRecipeBuilder pane(Supplier<? extends Block> to, Supplier<? extends Block> from) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, to.get(), 16)
                .pattern("AAA")
                .pattern("AAA")
                .define('A', from.get())
                .unlockedBy(getHasName(from.get()), has(from.get()));
    }

    public static void makePane(RecipeOutput output, Supplier<? extends Block> to, Supplier<? extends Block> from) {
        pane(to, from).save(output);
    }

    public static ShapedRecipeBuilder bars(Supplier<? extends Block> to, TagKey<Item> from) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, to.get(), 16)
                .define('#', from)
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_lead", has(from));
    }

    public static void makeBars(RecipeOutput output, Supplier<? extends Block> to, TagKey<Item> from) {
        bars(to, from).save(output);
    }

    public static ShapedRecipeBuilder quadTransform(Supplier<? extends Block> to, Supplier<? extends Block> from) {
        return quadTransform(to, from, 4);
    }

    public static ShapedRecipeBuilder quadTransform(Supplier<? extends Block> to, Supplier<? extends Block> from, int amount) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, to.get(), amount)
                .pattern("AA")
                .pattern("AA")
                .define('A', from.get())
                .unlockedBy(getHasName(from.get()), has(from.get()));
    }

    public static void makeQuadTransformStonecutting(RecipeOutput output, Supplier<? extends Block> to, Supplier<? extends Block> from) {
        makeQuadTransformStonecutting(output, to, from, 4);
    }

    public static void makeQuadTransformStonecutting(RecipeOutput output, Supplier<? extends Block> to, Supplier<? extends Block> from, int amount) {
        quadTransform(to, from, amount).save(output);
        makeStoneCutting(output, from, to.get(), Math.max(1, amount / 4));
    }

    public static ShapedRecipeBuilder chiseled(Supplier<? extends Block> to, Supplier<? extends SlabBlock> slabIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, to.get())
                .pattern("A")
                .pattern("A")
                .define('A', slabIn.get())
                .unlockedBy(getHasName(slabIn.get()), has(slabIn.get()));
    }

    public static ShapedRecipeBuilder pillar(Supplier<? extends Block> to, Supplier<? extends Block> from) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, to.get(), 2)
                .pattern("A")
                .pattern("A")
                .define('A', from.get())
                .unlockedBy(getHasName(from.get()), has(from.get()));
    }

    public static ShapedRecipeBuilder compact(Item from, Item to) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, from)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', to)
                .unlockedBy("has_" + getItemName(to), has(to));
    }

    public static ShapelessRecipeBuilder unCompact(Item from, Item to) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, from, 9)
                .requires(to)
                .unlockedBy("has_" + getItemName(to), has(to));
    }

    public static void makeOreSmelting(RecipeOutput output, Holder<? extends ItemLike> result, List<ItemLike> ingredients, float xp) {
        makeOreSmeltingRecipe(output, result.value(), ingredients, xp, result.getRegisteredName());
        makeOreBlastingRecipe(output, result.value(), ingredients, xp, result.getRegisteredName());
    }

    public static SimpleCookingRecipeBuilder smeltingRecipe(ItemLike result, ItemLike ingredient, float exp) {
        return smeltingRecipe(result, ingredient, exp, 1);
    }

    private static void makeOreSmeltingRecipe(RecipeOutput output, ItemLike result, List<ItemLike> ingredients, float xp, String group) {
        for (ItemLike ingredient : ingredients) {
            smeltingRecipe(result, ingredient, xp, 1).group(group).save(output, OConstants.modLoc("smelt_" + getItemName(ingredient.asItem())));
        }
    }

    public static SimpleCookingRecipeBuilder smeltingRecipe(ItemLike result, ItemLike ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(new ItemStack(ingredient, count)), RecipeCategory.MISC, result, exp, 200)
                .unlockedBy(getHasName(ingredient), has(ingredient));
    }

    public static SimpleCookingRecipeBuilder smeltingRecipeTag(ItemLike result, TagKey<Item> ingredient, float exp) {
        return smeltingRecipeTag(result, ingredient, exp, 1);
    }

    public static SimpleCookingRecipeBuilder smeltingRecipeTag(ItemLike result, TagKey<Item> ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.MISC, result, exp, 200)
                .unlockedBy("has_" + ingredient.location().getPath(), has(ingredient));
    }

    public static SimpleCookingRecipeBuilder blastingRecipe(ItemLike result, ItemLike ingredient, float exp) {
        return blastingRecipe(result, ingredient, exp, 1);
    }

    private static void makeOreBlastingRecipe(RecipeOutput output, ItemLike result, List<ItemLike> ingredients, float xp, String group) {
        for (ItemLike ingredient : ingredients) {
            blastingRecipe(result, ingredient, xp, 1).group(group).save(output, OConstants.modLoc("blast_" + getItemName(ingredient)));
        }
    }

    public static SimpleCookingRecipeBuilder blastingRecipe(ItemLike result, ItemLike ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.blasting(Ingredient.of(new ItemStack(ingredient, count)), RecipeCategory.MISC, result, exp, 100)
                .unlockedBy(getHasName(ingredient), has(ingredient));
    }

    public static SimpleCookingRecipeBuilder blastingRecipeTag(ItemLike result, TagKey<Item> ingredient, float exp) {
        return blastingRecipeTag(result, ingredient, exp, 1);
    }

    public static SimpleCookingRecipeBuilder blastingRecycling(ItemLike nugget, Collection<? extends Holder<? extends ItemLike>> items) {
        var builder = SimpleCookingRecipeBuilder.blasting(Ingredient.of(items.stream().map(Holder::value).map(ItemStack::new)), RecipeCategory.MISC, nugget, 0.1F, 100);
        for (var holder : items) {
            var item = holder.value();
            builder.unlockedBy(getHasName(item), has(item));
        }
        return builder;
    }

    public static SimpleCookingRecipeBuilder smeltingRecycling(ItemLike nugget, Collection<? extends Holder<? extends ItemLike>> itemms) {
        var builder = SimpleCookingRecipeBuilder.smelting(Ingredient.of(itemms.stream().map(Holder::value).map(ItemStack::new)), RecipeCategory.MISC, nugget, 0.1F, 100);
        for (var holder : itemms) {
            var item = holder.value();
            builder.unlockedBy(getHasName(item), has(item));
        }
        return builder;
    }

    public static void makeMetalRecycling(RecipeOutput output, ItemLike nugget, Collection<? extends Holder<? extends ItemLike>> items, String suffix) {
        var name = getItemName(nugget);
        blastingRecycling(nugget, items).save(output, OConstants.modLoc(name + "_from_blasting").withSuffix(suffix));
        smeltingRecycling(nugget, items).save(output, OConstants.modLoc(name + "_from_smelting").withSuffix(suffix));
    }

    public static void makeMetalRecycling(RecipeOutput output, ItemLike nugget, Collection<? extends Holder<? extends ItemLike>> items) {
        makeMetalRecycling(output, nugget, items, "");
    }

    public static SimpleCookingRecipeBuilder blastingRecipeTag(ItemLike result, TagKey<Item> ingredient, float exp, int count) {
        return SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient), RecipeCategory.MISC, result, exp, 100)
                .unlockedBy("has_" + ingredient.location().getPath(), has(ingredient));
    }

    public static SmithingTransformRecipeBuilder smithingRecipe(Supplier<? extends Item> input, Supplier<? extends Item> upgradeItem, Supplier<? extends Item> templateItem, Supplier<? extends Item> result) {
        return SmithingTransformRecipeBuilder.smithing(Ingredient.of(templateItem.get()), Ingredient.of(input.get()), Ingredient.of(upgradeItem.get()), RecipeCategory.MISC, result.get())
                .unlocks(getHasName(upgradeItem.get()), has(upgradeItem.get()));
    }

    public static SmithingTransformRecipeBuilder smithingRecipe(Supplier<? extends Item> input, TagKey<Item> upgradeItem, Supplier<? extends Item> templateItem, Supplier<? extends Item> result) {
        return SmithingTransformRecipeBuilder.smithing(Ingredient.of(templateItem.get()), Ingredient.of(input.get()), Ingredient.of(upgradeItem), RecipeCategory.MISC, result.get())
                .unlocks("has_" + upgradeItem.location().getPath(), has(upgradeItem));
    }

    public static SingleItemRecipeBuilder stonecutting(Supplier<? extends Block> from, ItemLike to) {
        return stonecutting(from, to, 1);
    }

    public static SingleItemRecipeBuilder stonecutting(Supplier<? extends Block> from, ItemLike to, int resultAmount) {
        return SingleItemRecipeBuilder.stonecutting(Ingredient.of(from.get()), RecipeCategory.BUILDING_BLOCKS, to, resultAmount)
                .unlockedBy(getHasName(from.get()), has(from.get()));
    }

    public static void makeStoneCutting(RecipeOutput output, Supplier<? extends Block> from, ItemLike to, int resultAmount) {
        stonecutting(from, to, resultAmount).save(output, OConstants.modLoc("stonecutting/" + getItemName(to) + "_from_" + getItemName(from.get())));
    }

    public static void makeStoneCutting(RecipeOutput output, Supplier<? extends Block> from, ItemLike to) {
        makeStoneCutting(output, from, to, 1);
    }

    public static void makeWaxed(RecipeOutput output, DeferredHolder<Block, ? extends Block> waxed, Block unwaxed) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, waxed.value())
                .requires(unwaxed)
                .requires(Items.HONEYCOMB)
                .unlockedBy(getHasName(unwaxed), has(unwaxed))
                .unlockedBy("has_honeycomb", has(Items.HONEYCOMB))
                .save(output);

        application(DeployerApplicationRecipe::new, waxed.getId().getPath())
                .output(waxed.value())
                .require(unwaxed)
                .require(Blocks.HONEYCOMB_BLOCK)
                .toolNotConsumed()
                .build(output);
    }

    public static void makeWaxed(RecipeOutput output, DeferredHolder<Block, ? extends Block> to, Supplier<? extends Block> from) {
        makeWaxed(output, to, from.get());
    }

    public static void makeSlabStonecutting(RecipeOutput output, Supplier<? extends Block> to, Supplier<? extends Block> from) {
        slab(to, from).save(output);
        makeStoneCutting(output, from, to.get(), 2);
    }

    public static void makeStairsStonecutting(RecipeOutput output, Supplier<? extends Block> to, Supplier<? extends Block> from) {
        stairs(to, from).save(output);
        makeStoneCutting(output, from, to.get());
    }

    public static void makeWallStonecutting(RecipeOutput output, Supplier<? extends Block> to, Supplier<? extends Block> from) {
        wall(to, from).save(output);
        makeStoneCutting(output, from, to.get());
    }

    public static void makeChiseledStonecutting(RecipeOutput output, Supplier<? extends Block> to, Supplier<? extends Block> fromBase, Supplier<? extends SlabBlock> fromSlab) {
        chiseled(to, fromSlab).save(output);
        makeStoneCutting(output, fromBase, to.get());
    }

    public static void makePillarStonecutting(RecipeOutput output, Supplier<? extends Block> to, Supplier<? extends Block> fromBase, Supplier<? extends Block> fromBlock) {
        pillar(to, fromBlock).save(output);
        makeStoneCutting(output, fromBase, to.get());
    }

    public static void makePillarStonecutting(RecipeOutput output, Supplier<? extends Block> to, Supplier<? extends Block> fromBlock) {
        makePillarStonecutting(output, to, fromBlock, fromBlock);
    }

    public static void makePolishedStonecutting(RecipeOutput output, Supplier<? extends Block> to, Supplier<? extends Block> from) {
        polished(output, RecipeCategory.BUILDING_BLOCKS, to.get(), from.get());
        makeStoneCutting(output, from, to.get());
    }

    public static <R extends StandardProcessingRecipe<?>> StandardProcessingRecipe.Builder<R> processing(StandardProcessingRecipe.Factory<R> factory, String id) {
        return whenLoaded(
                new StandardProcessingRecipe.Builder<>(factory, OConstants.modLoc(id)),
                ModCompat.CREATE
        );
    }

    public static <R extends ItemApplicationRecipe> ItemApplicationRecipe.Builder<R> application(ItemApplicationRecipe.Factory<R> factory, String id) {
        return whenLoaded(
                new ItemApplicationRecipe.Builder<>(factory, OConstants.modLoc(id)),
                ModCompat.CREATE
        );
    }

    public static void makeFlowerDye(RecipeOutput output, Supplier<? extends ItemLike> flower, ItemLike primary) {
        var name = getItemName(flower.get());

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, primary)
                .requires(flower.get())
                .unlockedBy(getHasName(flower.get()), has(flower.get()))
                .save(output, OConstants.modLoc("dye_from_" + name));

        processing(MillingRecipe::new, name)
                .require(flower.get())
                .output(primary, 2)
                .output(0.05F, Items.GREEN_DYE)
                .build(output);

        whenLoaded(output, ModCompat.FARMERS_DELIGHT, () -> {
            var knifeIngredient = new ItemAbilityIngredient(ItemAbility.get("knife_dig")).toVanilla();
            CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(flower.get()), knifeIngredient, primary, 2)
                    .save(output);
        });
    }

    public static void makeStoneSetRecipes(RecipeOutput output, StoneSet<?, ?, ?, ?> set) {
        makeSlabStonecutting(output, set.slab(), set.block());
        makeStairsStonecutting(output, set.stairs(), set.block());
        makeWallStonecutting(output, set.wall(), set.block());
    }

    public static <T> T unlessLoaded(T value, String... modIds) {
        return Conditional.with(value, new Inverted(new ModLoaded(modIds, true)));
    }

    public static <T> T whenLoaded(T value, String... modIds) {
        return Conditional.with(value, new ModLoaded(modIds));
    }

    public static void whenLoaded(RecipeOutput value, String modId, Runnable runnable) {
        Conditional.with(value, List.of(new ModLoaded(modId)), runnable);
    }

}
