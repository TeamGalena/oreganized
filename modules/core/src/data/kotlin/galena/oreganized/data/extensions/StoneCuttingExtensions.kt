@file:JvmMultifileClass
@file:JvmName("ORecipeExtensions")

package galena.oreganized.data.extensions

import com.tterrag.registrate.providers.RegistrateRecipeProvider
import galena.oreganized.OConstants
import galena.oreganized.index.sets.StoneSet
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.SingleItemRecipeBuilder
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SlabBlock
import java.util.function.Supplier
import kotlin.math.max

@JvmOverloads
fun stonecutting(
    from: Supplier<out Block>,
    to: ItemLike,
    resultAmount: Int = 1,
): SingleItemRecipeBuilder =
    SingleItemRecipeBuilder
        .stonecutting(
            Ingredient.of(from.get()),
            RecipeCategory.BUILDING_BLOCKS,
            to,
            resultAmount,
        ).unlockedBy(RegistrateRecipeProvider.getHasName(from.get()), RegistrateRecipeProvider.has(from.get()))

@JvmOverloads
fun RecipeOutput.makeStoneCutting(
    from: Supplier<out Block>,
    to: ItemLike,
    resultAmount: Int = 1,
) {
    stonecutting(from, to, resultAmount).save(
        this,
        OConstants.modLoc(
            "stonecutting/" + RegistrateRecipeProvider.getItemName(to) + "_from_" +
                RegistrateRecipeProvider.getItemName(
                    from.get(),
                ),
        ),
    )
}

fun RecipeOutput.makeSlabStonecutting(
    to: Supplier<out Block>,
    from: Supplier<out Block>,
) {
    slab(to, from).save(this)
    makeStoneCutting(from, to.get(), 2)
}

fun RecipeOutput.makeStairsStonecutting(
    to: Supplier<out Block>,
    from: Supplier<out Block>,
) {
    stairs(to, from).save(this)
    makeStoneCutting(from, to.get())
}

fun RecipeOutput.makeWallStonecutting(
    to: Supplier<out Block>,
    from: Supplier<out Block>,
) {
    wall(to, from).save(this)
    makeStoneCutting(from, to.get())
}

fun RecipeOutput.makeChiseledStonecutting(
    to: Supplier<out Block>,
    fromBase: Supplier<out Block>,
    fromSlab: Supplier<out SlabBlock>,
) {
    chiseled(to, fromSlab).save(this)
    makeStoneCutting(fromBase, to.get())
}

fun RecipeOutput.makePillarStonecutting(
    to: Supplier<out Block>,
    fromBase: Supplier<out Block>,
    fromBlock: Supplier<out Block>,
) {
    pillar(to, fromBlock).save(this)
    makeStoneCutting(fromBase, to.get())
}

fun RecipeOutput.makePillarStonecutting(
    to: Supplier<out Block>,
    fromBlock: Supplier<out Block>,
) {
    makePillarStonecutting(to, fromBlock, fromBlock)
}

fun RecipeOutput.makePolishedStonecutting(
    to: Supplier<out Block>,
    from: Supplier<out Block>,
) {
    RegistrateRecipeProvider.polished(this, RecipeCategory.BUILDING_BLOCKS, to.get(), from.get())
    makeStoneCutting(from, to.get())
}

fun RecipeOutput.makeStoneSetRecipes(set: StoneSet<*, *, *, *>) {
    makeSlabStonecutting(set.slab, set.block)
    makeStairsStonecutting(set.stairs, set.block)
    makeWallStonecutting(set.wall, set.block)
}

fun RecipeOutput.makeStoneSetRecipes(
    set: StoneSet<*, *, *, *>,
    from: Supplier<out Block>,
) {
    makeStoneCutting(from, set.slab, 2)
    makeStoneCutting(from, set.stairs)
    makeStoneCutting(from, set.wall)
}

@JvmOverloads
fun RecipeOutput.makeQuadTransformStonecutting(
    to: Supplier<out Block>,
    from: Supplier<out Block>,
    amount: Int = 4,
) {
    quadTransform(to, from, amount).save(this)
    makeStoneCutting(from, to.get(), max(1, amount / 4))
}
