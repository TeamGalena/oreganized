@file:JvmName("OConditionExtensions")

package galena.oreganized.data.extensions

import com.possible_triangle.multikulti.datagen.conditions.*
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.DyeColor

fun <T : Any> unlessLoaded(
    value: T,
    vararg modIds: String,
): T = value.`when`(ModLoaded(*modIds).inverted())

fun <T : Any> whenLoaded(
    value: T,
    vararg modIds: String,
): T = value.`when`(ModLoaded(*modIds))

fun whenLoaded(
    value: Any,
    modId: String,
    runnable: Runnable,
) {
    value.withConditions(ModLoaded(modId)) {
        runnable.run()
    }
}

private fun dyeCondition(color: DyeColor): Condition? {
    val namespace = color.getNamespace()
    if (namespace == ResourceLocation.DEFAULT_NAMESPACE) return null
    return ModLoaded(namespace)
}

fun <T : Any> dyed(
    color: DyeColor,
    value: T,
): T =
    dyeCondition(color)
        ?.let { value.`when`(it) }
        ?: value

fun <T : Any> dyed(
    color: DyeColor,
    value: T,
    block: Runnable,
) {
    dyeCondition(color)?.let {
        value.withConditions(it) { block.run() }
    } ?: run {
        block.run()
    }
}
