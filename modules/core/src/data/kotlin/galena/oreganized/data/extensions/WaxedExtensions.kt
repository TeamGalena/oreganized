@file:JvmMultifileClass
@file:JvmName("ORecipeExtensions")

package galena.oreganized.data.extensions

import com.simibubi.create.content.kinetics.deployer.DeployerApplicationRecipe
import com.simibubi.create.content.kinetics.deployer.ItemApplicationRecipe
import com.tterrag.registrate.providers.RegistrateRecipeProvider
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
        .requires(unwaxed)
        .requires(Items.HONEYCOMB)
        .unlockedBy(RegistrateRecipeProvider.getHasName(unwaxed), RegistrateRecipeProvider.has(unwaxed))
        .unlockedBy("has_honeycomb", RegistrateRecipeProvider.has(Items.HONEYCOMB))
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
