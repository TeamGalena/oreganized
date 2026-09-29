@file:JvmName("OBlockLootExtensions")

package galena.oreganized.data.extensions

import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables
import galena.oreganized.index.sets.StoneSet
import net.minecraft.core.Holder
import net.minecraft.world.item.Item
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks

fun RegistrateBlockLootTables.dropSelf(block: Holder<out Block?>) {
    dropSelf(block.value())
}

fun RegistrateBlockLootTables.dropSlab(slab: Holder<out Block?>) {
    add(slab.value(), createSlabItemTable(slab.value()))
}

fun RegistrateBlockLootTables.dropOther(
    brokenBlock: Holder<out Block?>,
    droppedBlock: ItemLike,
) {
    dropOther(brokenBlock.value(), droppedBlock)
}

fun RegistrateBlockLootTables.dropAsSilk(block: Holder<out Block?>) {
    dropWhenSilkTouch(block.value())
}

fun RegistrateBlockLootTables.dropWithSilk(
    block: Holder<out Block?>,
    drop: Holder<out ItemLike?>,
) {
    add(block.value(), createSingleItemTableWithSilkTouch(block.value(), drop.value()))
}

fun RegistrateBlockLootTables.dropOre(
    block: Holder<out Block?>,
    drop: Holder<out Item?>,
) {
    add(block.value(), createOreDrop(block.value(), drop.value()))
}

fun RegistrateBlockLootTables.dropOre(
    block: Holder<out Block?>,
    drop: Item,
) {
    add(block.value(), createOreDrop(block.value(), drop))
}

fun RegistrateBlockLootTables.dropCauldron(block: Holder<out Block?>) {
    dropOther(block, Blocks.CAULDRON)
}

fun RegistrateBlockLootTables.dropNothing(block: Holder<out Block?>) {
    dropOther(block, Blocks.AIR)
}

fun RegistrateBlockLootTables.dropPottedPlant(block: Holder<out Block?>) {
    dropPottedContents(block.value())
}

fun RegistrateBlockLootTables.dropStoneSet(set: StoneSet<*, *, *, *>) {
    dropSelf(set.block)
    dropSlab(set.slab)
    dropSelf(set.stairs)
    dropSelf(set.wall)
}
