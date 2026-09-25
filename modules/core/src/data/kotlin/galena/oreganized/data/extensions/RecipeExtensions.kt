@file:JvmMultifileClass
@file:JvmName("ORecipeExtensions")

package galena.oreganized.data.extensions

import com.simibubi.create.content.kinetics.millstone.MillingRecipe
import com.simibubi.create.content.processing.recipe.StandardProcessingRecipe
import com.tterrag.registrate.providers.RegistrateRecipeProvider
import galena.oreganized.ModCompat
import galena.oreganized.OConstants
import net.minecraft.data.recipes.*
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SlabBlock
import net.neoforged.neoforge.common.ItemAbility
import vectorwing.farmersdelight.common.crafting.ingredient.ItemAbilityIngredient
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder.cuttingRecipe
import java.util.function.Supplier

/**
 * method prefixed with `make` should accept the `RecipeOutput` as their receiver and actually save the recipes
 * all other methods should not do any of the two and instead return the recipe builder.
 */

fun slab(
    to: Supplier<out Block>,
    from: Supplier<out Block>,
): ShapedRecipeBuilder =
    ShapedRecipeBuilder
        .shaped(RecipeCategory.BUILDING_BLOCKS, to.get(), 6)
        .pattern("AAA")
        .define('A', from.get())
        .unlockedBy(RegistrateRecipeProvider.getHasName(from.get()), RegistrateRecipeProvider.has(from.get()))

fun stairs(
    to: Supplier<out Block>,
    from: Supplier<out Block>,
): ShapedRecipeBuilder =
    ShapedRecipeBuilder
        .shaped(RecipeCategory.BUILDING_BLOCKS, to.get(), 4)
        .pattern("A  ")
        .pattern("AA ")
        .pattern("AAA")
        .define('A', from.get())
        .unlockedBy(RegistrateRecipeProvider.getHasName(from.get()), RegistrateRecipeProvider.has(from.get()))

fun wall(
    to: Supplier<out Block>,
    from: Supplier<out Block>,
): ShapedRecipeBuilder =
    ShapedRecipeBuilder
        .shaped(RecipeCategory.BUILDING_BLOCKS, to.get(), 6)
        .pattern("AAA")
        .pattern("AAA")
        .define('A', from.get())
        .unlockedBy(RegistrateRecipeProvider.getHasName(from.get()), RegistrateRecipeProvider.has(from.get()))

fun pane(
    to: Supplier<out Block>,
    from: Supplier<out Block>,
): ShapedRecipeBuilder =
    ShapedRecipeBuilder
        .shaped(RecipeCategory.BUILDING_BLOCKS, to.get(), 16)
        .pattern("AAA")
        .pattern("AAA")
        .define('A', from.get())
        .unlockedBy(RegistrateRecipeProvider.getHasName(from.get()), RegistrateRecipeProvider.has(from.get()))

fun RecipeOutput.makePane(
    to: Supplier<out Block>,
    from: Supplier<out Block>,
) {
    pane(to, from).save(this)
}

fun bars(
    to: Supplier<out Block>,
    from: TagKey<Item>,
): ShapedRecipeBuilder =
    ShapedRecipeBuilder
        .shaped(RecipeCategory.REDSTONE, to.get(), 16)
        .define('#', from)
        .pattern("###")
        .pattern("###")
        .unlockedBy("has_lead", RegistrateRecipeProvider.has(from))

fun RecipeOutput.makeBars(
    to: Supplier<out Block>,
    from: TagKey<Item>,
) {
    bars(to, from).save(this)
}

@JvmOverloads
fun quadTransform(
    to: Supplier<out Block>,
    from: Supplier<out Block>,
    amount: Int = 4,
): ShapedRecipeBuilder =
    ShapedRecipeBuilder
        .shaped(RecipeCategory.BUILDING_BLOCKS, to.get(), amount)
        .pattern("AA")
        .pattern("AA")
        .define('A', from.get())
        .unlockedBy(RegistrateRecipeProvider.getHasName(from.get()), RegistrateRecipeProvider.has(from.get()))

fun chiseled(
    to: Supplier<out Block>,
    slabIn: Supplier<out SlabBlock>,
): ShapedRecipeBuilder =
    ShapedRecipeBuilder
        .shaped(RecipeCategory.BUILDING_BLOCKS, to.get())
        .pattern("A")
        .pattern("A")
        .define('A', slabIn.get())
        .unlockedBy(RegistrateRecipeProvider.getHasName(slabIn.get()), RegistrateRecipeProvider.has(slabIn.get()))

fun pillar(
    to: Supplier<out Block>,
    from: Supplier<out Block>,
): ShapedRecipeBuilder =
    ShapedRecipeBuilder
        .shaped(RecipeCategory.BUILDING_BLOCKS, to.get(), 2)
        .pattern("A")
        .pattern("A")
        .define('A', from.get())
        .unlockedBy(RegistrateRecipeProvider.getHasName(from.get()), RegistrateRecipeProvider.has(from.get()))

fun compact(
    from: Item,
    to: Item,
): ShapedRecipeBuilder =
    ShapedRecipeBuilder
        .shaped(RecipeCategory.BUILDING_BLOCKS, from)
        .pattern("AAA")
        .pattern("AAA")
        .pattern("AAA")
        .define('A', to)
        .unlockedBy("has_" + RegistrateRecipeProvider.getItemName(to), RegistrateRecipeProvider.has(to))

fun unCompact(
    from: Item,
    to: Item,
): ShapelessRecipeBuilder =
    ShapelessRecipeBuilder
        .shapeless(RecipeCategory.BUILDING_BLOCKS, from, 9)
        .requires(to)
        .unlockedBy("has_" + RegistrateRecipeProvider.getItemName(to), RegistrateRecipeProvider.has(to))

fun RecipeOutput.makeFlowerDye(
    flower: Supplier<out ItemLike>,
    primary: ItemLike,
) {
    val name = RegistrateRecipeProvider.getItemName(flower.get())

    ShapelessRecipeBuilder
        .shapeless(RecipeCategory.MISC, primary)
        .requires(flower.get())
        .unlockedBy(RegistrateRecipeProvider.getHasName(flower.get()), RegistrateRecipeProvider.has(flower.get()))
        .save(this, OConstants.modLoc("dye_from_" + name))

    processing(StandardProcessingRecipe.Factory(::MillingRecipe), name)
        .require(flower.get())
        .output(primary, 2)
        .output(0.05f, Items.GREEN_DYE)
        .build(this)

    whenLoaded(this, ModCompat.FARMERS_DELIGHT) {
        val knifeIngredient = ItemAbilityIngredient(ItemAbility.get("knife_dig")).toVanilla()
        cuttingRecipe(Ingredient.of(flower.get()), knifeIngredient, primary, 2)
            .save(this)
    }
}
