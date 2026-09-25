@file:JvmMultifileClass
@file:JvmName("ORecipeExtensions")

package galena.oreganized.data.extensions

import com.tterrag.registrate.providers.RegistrateRecipeProvider
import com.tterrag.registrate.providers.RegistrateRecipeProvider.getHasName
import com.tterrag.registrate.providers.RegistrateRecipeProvider.getItemName
import com.tterrag.registrate.providers.RegistrateRecipeProvider.has
import galena.oreganized.OConstants
import galena.oreganized.index.sets.StoneSet
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.SingleItemRecipeBuilder
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SlabBlock
import java.util.function.Supplier
import kotlin.math.max

@JvmOverloads
fun stonecutting(
    to: Supplier<out Block>,
    from: Supplier<out Block>,
    resultAmount: Int = 1,
): SingleItemRecipeBuilder =
    SingleItemRecipeBuilder
        .stonecutting(
            Ingredient.of(from.get()),
            RecipeCategory.BUILDING_BLOCKS,
            to.get(),
            resultAmount,
        ).unlockedBy(getHasName(from.get()), has(from.get()))

@JvmOverloads
fun RecipeOutput.makeStoneCutting(
    to: Supplier<out Block>,
    from: Supplier<out Block>,
    resultAmount: Int = 1,
) {
    stonecutting(to, from, resultAmount).save(
        this,
        OConstants.modLoc(
            "stonecutting/${getItemName(to.get())}_from_${getItemName(from.get())}",
        ),
    )
}

fun RecipeOutput.makeSlabStonecutting(
    to: Supplier<out Block>,
    from: Supplier<out Block>,
) {
    slab(to, from).save(this)
    makeStoneCutting(to, from, 2)
}

fun RecipeOutput.makeStairsStonecutting(
    to: Supplier<out Block>,
    from: Supplier<out Block>,
) {
    stairs(to, from).save(this)
    makeStoneCutting(to, from)
}

fun RecipeOutput.makeWallStonecutting(
    to: Supplier<out Block>,
    from: Supplier<out Block>,
) {
    wall(to, from).save(this)
    makeStoneCutting(to, from)
}

fun RecipeOutput.makeChiseledStonecutting(
    to: Supplier<out Block>,
    fromBase: Supplier<out Block>,
    fromSlab: Supplier<out SlabBlock>,
) {
    chiseled(to, fromSlab).save(this)
    makeStoneCutting(to, fromBase)
}

fun RecipeOutput.makePillarStonecutting(
    to: Supplier<out Block>,
    fromBase: Supplier<out Block>,
    fromBlock: Supplier<out Block>,
) {
    pillar(to, fromBlock).save(this)
    makeStoneCutting(to, fromBase)
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
    makeStoneCutting(to, from)
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
    makeStoneCutting(set.slab, from, 2)
    makeStoneCutting(set.stairs, from)
    makeStoneCutting(set.wall, from)
}

@JvmOverloads
fun RecipeOutput.makeQuadTransformStonecutting(
    to: Supplier<out Block>,
    from: Supplier<out Block>,
    amount: Int = 4,
) {
    quadTransform(to, from, amount).save(this)
    makeStoneCutting(to, from, max(1, amount / 4))
}
