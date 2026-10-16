@file:JvmName("OTagExtensions")

package galena.oreganized.data.extensions

import com.mojang.datafixers.util.Pair
import com.teamabnormals.blueprint.core.util.TagUtil
import com.tterrag.registrate.providers.RegistrateItemTagsProvider
import com.tterrag.registrate.providers.RegistrateTagsProvider.IntrinsicImpl
import galena.oreganized.ModCompat
import galena.oreganized.index.sets.DyedBlockSet
import galena.oreganized.index.sets.StoneSet
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

private val BLOCK_ITEM_TAGS =
    mapOf(
        BlockTags.SMALL_FLOWERS to ItemTags.SMALL_FLOWERS,
        Tags.Blocks.ORES to Tags.Items.ORES,
        Tags.Blocks.ORE_RATES_SINGULAR to Tags.Items.ORE_RATES_SINGULAR,
        Tags.Blocks.ORES_IN_GROUND_STONE to Tags.Items.ORES_IN_GROUND_STONE,
        Tags.Blocks.ORES_IN_GROUND_DEEPSLATE to Tags.Items.ORES_IN_GROUND_DEEPSLATE,
        BlockTags.WALLS to ItemTags.WALLS,
        BlockTags.FENCES to ItemTags.FENCES,
        BlockTags.STAIRS to ItemTags.STAIRS,
        BlockTags.SLABS to ItemTags.SLABS,
        BlockTags.DOORS to ItemTags.DOORS,
        BlockTags.TRAPDOORS to ItemTags.TRAPDOORS,
        BlockTags.BUTTONS to ItemTags.BUTTONS,
        Tags.Blocks.STORAGE_BLOCKS to Tags.Items.STORAGE_BLOCKS,
    )

fun RegistrateItemTagsProvider.copyBlockTags() {
    BLOCK_ITEM_TAGS.forEach(this::copy)
}

fun IntrinsicImpl<Block>.createCopiedBlockTags() {
    BLOCK_ITEM_TAGS.forEach { (block) -> addTag(block) }
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

fun IntrinsicImpl<Block>.tagStoneSet(set: StoneSet<*, *, *, *>) {
    addTag(BlockTags.SLABS).add(set.slab.getKey()!!)
    addTag(BlockTags.STAIRS).add(set.stairs.getKey()!!)
    addTag(BlockTags.WALLS).add(set.wall.getKey()!!)
}
