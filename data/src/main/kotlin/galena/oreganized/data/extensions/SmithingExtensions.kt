@file:JvmMultifileClass
@file:JvmName("ORecipeExtensions")

package galena.oreganized.data.extensions

import com.tterrag.registrate.providers.RegistrateRecipeProvider
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.crafting.Ingredient
import java.util.function.Supplier

fun smithingRecipe(
    input: Supplier<out Item>,
    upgradeItem: Supplier<out Item>,
    templateItem: Supplier<out Item>,
    result: Supplier<out Item>,
): SmithingTransformRecipeBuilder =
    SmithingTransformRecipeBuilder
        .smithing(
            Ingredient.of(templateItem.get()),
            Ingredient.of(input.get()),
            Ingredient.of(upgradeItem.get()),
            RecipeCategory.MISC,
            result.get(),
        ).unlocks(
            RegistrateRecipeProvider.getHasName(upgradeItem.get()),
            RegistrateRecipeProvider.has(upgradeItem.get()),
        )

fun smithingRecipe(
    input: Supplier<out Item>,
    upgradeItem: TagKey<Item>,
    templateItem: Supplier<out Item>,
    result: Supplier<out Item>,
): SmithingTransformRecipeBuilder =
    SmithingTransformRecipeBuilder
        .smithing(
            Ingredient.of(templateItem.get()),
            Ingredient.of(input.get()),
            Ingredient.of(upgradeItem),
            RecipeCategory.MISC,
            result.get(),
        ).unlocks("has_" + upgradeItem.location().getPath(), RegistrateRecipeProvider.has(upgradeItem))
