@file:JvmMultifileClass
@file:JvmName("ORecipeExtensions")

package galena.oreganized.data.extensions

import com.tterrag.registrate.providers.RegistrateRecipeProvider
import galena.oreganized.OConstants
import net.minecraft.core.Holder
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike

fun RecipeOutput.makeOreSmelting(
    result: Holder<out ItemLike>,
    ingredients: MutableList<ItemLike>,
    xp: Float,
) {
    makeOreSmeltingOnly(result.value(), ingredients, xp, result.getRegisteredName())
    makeOreBlasting(result.value(), ingredients, xp, result.getRegisteredName())
}

private fun RecipeOutput.makeOreSmeltingOnly(
    result: ItemLike,
    ingredients: MutableList<ItemLike>,
    xp: Float,
    group: String?,
) {
    for (ingredient in ingredients) {
        smeltingRecipe(result, ingredient, xp, 1)
            .group(group)
            .save(this, OConstants.modLoc("smelt_" + RegistrateRecipeProvider.getItemName(ingredient.asItem())))
    }
}

@JvmOverloads
fun smeltingRecipe(
    result: ItemLike,
    ingredient: ItemLike,
    exp: Float,
    count: Int = 1,
): SimpleCookingRecipeBuilder =
    SimpleCookingRecipeBuilder
        .smelting(
            Ingredient.of(ItemStack(ingredient, count)),
            RecipeCategory.MISC,
            result,
            exp,
            200,
        ).unlockedBy(RegistrateRecipeProvider.getHasName(ingredient), RegistrateRecipeProvider.has(ingredient))

private fun RecipeOutput.makeOreBlasting(
    result: ItemLike,
    ingredients: MutableList<ItemLike>,
    xp: Float,
    group: String?,
) {
    for (ingredient in ingredients) {
        blastingRecipe(result, ingredient, xp, 1)
            .group(group)
            .save(this, OConstants.modLoc("blast_" + RegistrateRecipeProvider.getItemName(ingredient)))
    }
}

@JvmOverloads
fun blastingRecipe(
    result: ItemLike,
    ingredient: ItemLike,
    exp: Float,
    count: Int = 1,
): SimpleCookingRecipeBuilder =
    SimpleCookingRecipeBuilder
        .blasting(
            Ingredient.of(ItemStack(ingredient, count)),
            RecipeCategory.MISC,
            result,
            exp,
            100,
        ).unlockedBy(RegistrateRecipeProvider.getHasName(ingredient), RegistrateRecipeProvider.has(ingredient))

fun blastingRecycling(
    nugget: ItemLike,
    items: Collection<Holder<out ItemLike>>,
): SimpleCookingRecipeBuilder {
    val builder =
        SimpleCookingRecipeBuilder.blasting(
            Ingredient.of(items.stream().map { it.value() }.map(::ItemStack)),
            RecipeCategory.MISC,
            nugget,
            0.1f,
            100,
        )
    for (holder in items) {
        val item: ItemLike = holder.value()
        builder.unlockedBy(RegistrateRecipeProvider.getHasName(item), RegistrateRecipeProvider.has(item))
    }
    return builder
}

fun smeltingRecycling(
    nugget: ItemLike,
    items: Collection<Holder<out ItemLike>>,
): SimpleCookingRecipeBuilder {
    val builder =
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(items.stream().map { it.value() }.map(::ItemStack)),
            RecipeCategory.MISC,
            nugget,
            0.1f,
            100,
        )
    for (holder in items) {
        val item: ItemLike = holder.value()
        builder.unlockedBy(RegistrateRecipeProvider.getHasName(item), RegistrateRecipeProvider.has(item))
    }
    return builder
}

@JvmOverloads
fun RecipeOutput.makeMetalRecycling(
    nugget: ItemLike,
    items: Collection<Holder<out ItemLike>>,
    suffix: String = "",
) {
    val name = RegistrateRecipeProvider.getItemName(nugget)
    blastingRecycling(nugget, items).save(this, OConstants.modLoc(name + "_from_blasting").withSuffix(suffix))
    smeltingRecycling(nugget, items).save(this, OConstants.modLoc(name + "_from_smelting").withSuffix(suffix))
}
