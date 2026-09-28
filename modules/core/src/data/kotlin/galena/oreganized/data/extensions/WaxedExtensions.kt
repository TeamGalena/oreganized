@file:JvmMultifileClass
@file:JvmName("ORecipeExtensions")

package galena.oreganized.data.extensions

import com.simibubi.create.content.kinetics.deployer.DeployerApplicationRecipe
import com.simibubi.create.content.kinetics.deployer.ItemApplicationRecipe
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.ShapelessRecipeBuilder
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.neoforged.neoforge.registries.DeferredHolder
import java.util.function.Supplier

fun RecipeOutput.makeWaxed(
    waxed: DeferredHolder<Block, out Block>,
    unwaxed: Block,
) {
    ShapelessRecipeBuilder
        .shapeless(RecipeCategory.DECORATIONS, waxed.value())
        .requiresUnlocking(unwaxed)
        .requiresUnlocking(Items.HONEYCOMB)
        .save(this)

    application(
        ItemApplicationRecipe.Factory(::DeployerApplicationRecipe),
        waxed.getId().getPath(),
    ).output(waxed.value())
        .require(unwaxed)
        .require(Blocks.HONEYCOMB_BLOCK)
        .toolNotConsumed()
        .build(this)
}

fun RecipeOutput.makeWaxed(
    to: DeferredHolder<Block, out Block>,
    from: Supplier<out Block>,
) {
    makeWaxed(to, from.get())
}
