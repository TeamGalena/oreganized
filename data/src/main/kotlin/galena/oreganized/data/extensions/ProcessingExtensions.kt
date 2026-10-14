@file:JvmMultifileClass
@file:JvmName("ORecipeExtensions")

package galena.oreganized.data.extensions

import com.simibubi.create.content.kinetics.deployer.ItemApplicationRecipe
import com.simibubi.create.content.processing.recipe.StandardProcessingRecipe

fun <R : StandardProcessingRecipe<*>> processing(
    factory: StandardProcessingRecipe.Factory<R>,
    id: String,
): StandardProcessingRecipe.Builder<R> =
    whenLoaded(
        StandardProcessingRecipe.Builder<R>(
            factory,
            galena.oreganized.OConstants.modLoc(id),
        ),
        galena.oreganized.ModCompat.CREATE,
    )

fun <R : ItemApplicationRecipe> application(
    factory: ItemApplicationRecipe.Factory<R>,
    id: String?,
): ItemApplicationRecipe.Builder<R> =
    whenLoaded(
        ItemApplicationRecipe.Builder(
            factory,
            galena.oreganized.OConstants.modLoc(id),
        ),
        galena.oreganized.ModCompat.CREATE,
    )
