@file:JvmName("OTagExtensions")

package galena.oreganized.data.extensions

import com.mojang.datafixers.util.Pair
import com.teamabnormals.blueprint.core.util.TagUtil
import com.tterrag.registrate.providers.RegistrateItemTagsProvider
import com.tterrag.registrate.providers.RegistrateTagsProvider.IntrinsicImpl
import galena.oreganized.ModCompat
import galena.oreganized.index.sets.DyedBlockSet
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BlockTags
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.DyeColor
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.common.Tags
import java.util.function.Supplier
import java.util.stream.Stream

fun copyBlockTags(provider: RegistrateItemTagsProvider) {
    provider.copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS)

    provider.copy(Tags.Blocks.ORES, Tags.Items.ORES)
    provider.copy(Tags.Blocks.ORE_RATES_SINGULAR, Tags.Items.ORE_RATES_SINGULAR)
    provider.copy(Tags.Blocks.ORES_IN_GROUND_STONE, Tags.Items.ORES_IN_GROUND_STONE)
    provider.copy(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE, Tags.Items.ORES_IN_GROUND_DEEPSLATE)

    provider.copy(BlockTags.WALLS, ItemTags.WALLS)
    provider.copy(BlockTags.FENCES, ItemTags.FENCES)

    provider.copy(BlockTags.STAIRS, ItemTags.STAIRS)
    provider.copy(BlockTags.SLABS, ItemTags.SLABS)

    provider.copy(BlockTags.DOORS, ItemTags.DOORS)
    provider.copy(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS)
    provider.copy(BlockTags.BUTTONS, ItemTags.BUTTONS)

    provider.copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS)
}

fun RegistrateItemTagsProvider.tagDyed(
    values: DyedBlockSet<*>,
    vararg keys: TagKey<Item>,
) {
    val intrinsic = this as IntrinsicImpl<Item>
    val mapped = values.entries().map { Pair.of(it.key, Supplier { it.value.asItem() }) }
    tagDyed(intrinsic, BuiltInRegistries.ITEM, mapped, *keys)
}

fun tagDyed(
    provider: IntrinsicImpl<Block>,
    values: DyedBlockSet<*>,
    vararg keys: TagKey<Block>,
) {
    val mapped = values.entries().map { Pair.of(it.key, it.value) }
    tagDyed(
        provider,
        BuiltInRegistries.BLOCK,
        mapped,
        *keys,
    )
}

private fun <T> dyedTag(
    registry: ResourceKey<out Registry<T>>,
    color: DyeColor,
): TagKey<T> =
    TagKey.create<T>(
        registry,
        ResourceLocation.fromNamespaceAndPath("c", "dyed/" + color.getSerializedName()),
    )

private fun <T> dyedTag(registry: ResourceKey<out Registry<T>>): TagKey<T> =
    TagKey.create<T>(registry, ResourceLocation.fromNamespaceAndPath("c", "dyed"))

private fun <T> tagDyed(
    provider: IntrinsicImpl<T>,
    registry: Registry<T>,
    values: Stream<out Pair<DyeColor, out Supplier<out T>>>,
    vararg keys: TagKey<T>,
) {
    values.forEach { entry: Pair<DyeColor, out Supplier<out T>> ->
        val item = entry.getSecond().get()
        val id = registry.getKey(item)!!
        for (key in keys) {
            provider.addTag(key).addOptional(id)
        }
        provider.addTag(dyedTag(registry.key())).addOptional(id)
        provider.addTag(dyedTag(registry.key(), entry.getFirst())).addOptional(id)
    }
}

fun RegistrateItemTagsProvider.tagKnife(item: Holder<Item>) {
    addTag(TagUtil.itemTag("c", "tools/knife")).add(item.key!!)
    addTag(TagUtil.itemTag(ModCompat.FARMERS_DELIGHT, "tools/knives")).add(item.key!!)
}

fun RegistrateItemTagsProvider.tagShield(item: Holder<Item>) {
    addTag(Tags.Items.TOOLS_SHIELD).add(item.key!!)
    addTag(TagUtil.itemTag(ModCompat.SHIELD_EXPANSION, "shields")).add(item.key!!)
}

fun RegistrateItemTagsProvider.tagMachete(item: Holder<Item>) {
    addTag(TagUtil.itemTag(ModCompat.NETHERS_DELIGHT, "tools/machete")).add(item.key!!)
}
