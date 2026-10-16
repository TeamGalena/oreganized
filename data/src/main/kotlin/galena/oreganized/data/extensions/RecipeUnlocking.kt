package galena.oreganized.data.extensions

import com.tterrag.registrate.providers.RegistrateRecipeProvider
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.recipes.RecipeBuilder
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.data.recipes.ShapelessRecipeBuilder
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.level.ItemLike

@Suppress("UNCHECKED_CAST")
fun <T : RecipeBuilder> T.unlockedBy(item: ItemLike): T {
    val id = BuiltInRegistries.ITEM.getKeyOrNull(item.asItem()) ?: error("unknown item")
    return unlockedBy("has_${id.path}", RegistrateRecipeProvider.has(item)) as T
}

@Suppress("UNCHECKED_CAST")
fun <T : RecipeBuilder> T.unlockedBy(tag: TagKey<Item>): T = unlockedBy("has_${tag.location.path}", RegistrateRecipeProvider.has(tag)) as T

fun ShapedRecipeBuilder.defineUnlocking(
    key: Char,
    item: ItemLike,
) = define(key, item).unlockedBy(item)

fun ShapedRecipeBuilder.defineUnlocking(
    key: Char,
    tag: TagKey<Item>,
) = define(key, tag).unlockedBy(tag)

fun ShapelessRecipeBuilder.requiresUnlocking(item: ItemLike) = requires(item).unlockedBy(item)

fun ShapelessRecipeBuilder.requiresUnlocking(tag: TagKey<Item>) = requires(tag).unlockedBy(tag)
