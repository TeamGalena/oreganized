@file:JvmName("OColorExtensions")

package galena.oreganized.data.extensions

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.DyeColor
import net.minecraft.world.level.block.Block

@Suppress("REDUNDANT_ELSE_IN_WHEN")
fun DyeColor.getNamespace(): String =
    if (id < 16) {
        ResourceLocation.DEFAULT_NAMESPACE
    } else {
        "dye_depot"
    }

fun DyeColor.createId(suffix: String): ResourceLocation =
    ResourceLocation.fromNamespaceAndPath(getNamespace(), getSerializedName() + "_" + suffix)

fun DyeColor.createBlockKey(suffix: String): ResourceKey<Block> = ResourceKey.create<Block>(Registries.BLOCK, createId(suffix))

fun DyeColor.getColoredBlock(suffix: String): Block = BuiltInRegistries.BLOCK.getOrThrow(createBlockKey(suffix))

fun DyeColor.isModded(): Boolean = getNamespace() != ResourceLocation.DEFAULT_NAMESPACE
