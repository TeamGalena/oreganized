@file:JvmName("OBlockStateExtensions")

package galena.oreganized.data.extensions

import galena.oreganized.index.sets.StoneSet
import net.minecraft.core.Direction
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.Property
import net.neoforged.neoforge.client.model.generators.*
import net.neoforged.neoforge.registries.DeferredHolder
import java.util.function.BiConsumer

@JvmField
val CUTOUT: ResourceLocation = ResourceLocation.withDefaultNamespace("cutout")

@JvmField
val TRANSLUCENT: ResourceLocation = ResourceLocation.withDefaultNamespace("translucent")

fun BlockStateProvider.cubeAll(block: DeferredHolder<Block, out Block>) {
    val model = cubeAll(block.value())
    simpleBlockWithItem(block.value(), model)
}

fun BlockStateProvider.slab(
    block: DeferredHolder<Block, out Block>,
    slab: DeferredHolder<Block, out SlabBlock>,
) {
    val texture = blockTexture(block.value())
    slabBlock(slab.value(), texture, texture)
    blockItem(slab)
}

fun BlockStateProvider.stairs(
    block: DeferredHolder<Block, out Block>,
    stairs: DeferredHolder<Block, out StairBlock>,
) {
    val texture = blockTexture(block.value())
    stairsBlock(stairs.value(), texture)
    blockItem(stairs)
}

fun BlockStateProvider.wall(
    block: DeferredHolder<Block, out Block>,
    wall: DeferredHolder<Block, out WallBlock>,
) {
    val texture = blockTexture(block.value())
    wallBlock(wall.value(), texture)
    itemModels()
        .getBuilder(wall.getId().getPath())
        .parent(models().wallInventory(wall.getId().getPath() + "_inventory", texture))
}

fun BlockStateProvider.waxed(
    block: DeferredHolder<Block, out Block>,
    origin: Block,
) {
    val parent = blockTexture(origin)
    val model = models().getExistingFile(parent)
    simpleBlockWithItem(block.value(), model)
}

fun BlockStateProvider.trapDoor(block: DeferredHolder<Block, out TrapDoorBlock>) {
    val texture = blockTexture(block.value())
    trapdoorBlock(block.value(), texture, true)
    simpleBlockItem(block.value(), models().getExistingFile(texture.withSuffix("_bottom")))
}

fun BlockStateProvider.door(block: DeferredHolder<Block, out DoorBlock>) {
    val texture = blockTexture(block.value())
    doorBlock(block.value(), texture.withSuffix("_bottom"), texture.withSuffix("_top"))
    generatedItem(block, ModelProvider.ITEM_FOLDER)
}

/**
 * copied from [com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider]
 */
fun BlockStateProvider.pane(
    block: Block,
    post: ModelFile,
    postEnds: ModelFile,
    side: ModelFile,
    sideAlt: ModelFile,
    cap: ModelFile,
    capAlt: ModelFile,
) {
    val builder =
        getMultipartBuilder(block)
            .part()
            .modelFile(postEnds)
            .addModel()
            .end()
    builder
        .part()
        .modelFile(post)
        .addModel()
        .condition(BlockStateProperties.NORTH, false)
        .condition(BlockStateProperties.WEST, false)
        .condition(BlockStateProperties.SOUTH, false)
        .condition(BlockStateProperties.EAST, false)
        .end()

    for (direction in Direction.Plane.HORIZONTAL
        .stream()
        .toList()) {
        builder
            .part()
            .modelFile(if (direction == Direction.SOUTH || direction == Direction.WEST) capAlt else cap)
            .rotationY(if (direction.getAxis() === Direction.Axis.X) 90 else 0)
            .addModel()
            .condition(
                BlockStateProperties.NORTH,
                PipeBlock.PROPERTY_BY_DIRECTION.get(direction) === BlockStateProperties.NORTH,
            ).condition(
                BlockStateProperties.WEST,
                PipeBlock.PROPERTY_BY_DIRECTION.get(direction) === BlockStateProperties.WEST,
            ).condition(
                BlockStateProperties.SOUTH,
                PipeBlock.PROPERTY_BY_DIRECTION.get(direction) === BlockStateProperties.SOUTH,
            ).condition(
                BlockStateProperties.EAST,
                PipeBlock.PROPERTY_BY_DIRECTION.get(direction) === BlockStateProperties.EAST,
            ).end()
    }

    directions { dir, prop ->
        if (dir.getAxis().isHorizontal()) {
            builder
                .part()
                .modelFile(if (dir == Direction.SOUTH || dir == Direction.WEST) sideAlt else side)
                .rotationY(if (dir.getAxis() === Direction.Axis.X) 90 else 0)
                .addModel()
                .condition(prop, true)
                .end()
        }
    }
}

/**
 * copied from [com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider]
 */
fun BlockStateProvider.bars(block: DeferredHolder<Block, out IronBarsBlock>) {
    val texture = blockTexture(block.value())
    val name = block.getId().getPath()
    val edgeTexture = texture.withSuffix("_edge")

    val post = ironBarsModel(name, "post", texture).texture("bars", edgeTexture)
    val postEnds = ironBarsModel(name, "post_ends", texture).texture("edge", edgeTexture)
    val side = ironBarsModel(name, "side", texture).texture("bars", texture).texture("edge", edgeTexture)
    val sideAlt =
        ironBarsModel(name, "side_alt", texture).texture("bars", texture).texture("edge", edgeTexture)
    val cap = ironBarsModel(name, "cap", texture).texture("bars", texture).texture("edge", edgeTexture)
    val capAlt =
        ironBarsModel(name, "cap_alt", texture).texture("bars", texture).texture("edge", edgeTexture)

    pane(block.value(), post, postEnds, side, sideAlt, cap, capAlt)
    generatedItem(block, ModelProvider.BLOCK_FOLDER)
}

fun BlockStateProvider.ironBarsModel(
    name: String,
    suffix: String,
    texture: ResourceLocation,
): BlockModelBuilder =
    models()
        .withExistingParent(name + "_" + suffix, ResourceLocation.withDefaultNamespace("block/iron_bars_" + suffix))
        .renderType(CUTOUT)
        .texture("particle", texture)

fun BlockStateProvider.pillar(block: DeferredHolder<Block, out RotatedPillarBlock>) {
    logBlock(block.value())
    blockItem(block)
}

fun BlockStateProvider.cubeColumn(block: DeferredHolder<Block, out Block>) {
    val texture = blockTexture(block.value())
    cubeBottomTopBlock(
        block,
        texture.withSuffix("_side"),
        texture.withSuffix("_top"),
        texture.withSuffix("_top"),
    )
}

fun BlockStateProvider.crossWithPot(
    cross: DeferredHolder<Block, out Block>,
    potted: DeferredHolder<Block, out FlowerPotBlock>,
) {
    val texture = blockTexture(cross.value())
    crossBlock(cross)
    val pottedModel =
        models()
            .singleTexture(
                potted.getId().getPath(),
                ResourceLocation.withDefaultNamespace("block/flower_pot_cross"),
                "plant",
                texture,
            ).renderType(
                CUTOUT,
            )
    simpleBlock(potted.value(), pottedModel)
}

fun BlockStateProvider.crossBlock(cross: DeferredHolder<Block, out Block>) {
    val texture = blockTexture(cross.value())
    simpleBlock(
        cross.value(),
        models().cross(cross.getId().getPath(), texture).renderType(CUTOUT),
    )
    generatedItem(cross, ModelProvider.BLOCK_FOLDER)
}

fun BlockStateProvider.generatedItem(
    block: DeferredHolder<Block, out Block>,
    type: String,
): ItemModelBuilder = itemModels().generatedItem(block.getId(), block.getId(), type)

fun BlockStateProvider.cauldronModel(
    block: DeferredHolder<Block, out Block>,
    texture: ResourceLocation,
    age: Int,
): ModelFile {
    val name = block.getId().getPath() + "_" + age
    return cauldronModel(name, texture)
}

fun BlockStateProvider.cauldronModel(
    name: String,
    texture: ResourceLocation,
): ModelFile =
    models()
        .withExistingParent(name, ModelProvider.BLOCK_FOLDER + "/template_cauldron_full")
        .texture("content", texture)

fun BlockStateProvider.cubeBottomTopBlock(block: DeferredHolder<Block, out Block>) {
    val texture = blockTexture(block.value())
    cubeBottomTopBlock(
        block,
        texture.withSuffix("_side"),
        texture.withSuffix("_bottom"),
        texture.withSuffix("_top"),
    )
}

fun BlockStateProvider.cubeBottomTopBlock(
    block: DeferredHolder<Block, out Block>,
    sideTexture: ResourceLocation,
    bottomTexture: ResourceLocation,
    topTexture: ResourceLocation,
) {
    simpleBlock(
        block.value(),
        models().cubeBottomTop(block.getId().getPath(), sideTexture, bottomTexture, topTexture),
    )
    blockItem(block)
}

@JvmOverloads
fun BlockStateProvider.blockItem(
    block: DeferredHolder<Block, out Block>,
    model: ResourceLocation = blockTexture(block.value()),
) {
    val existing = models().getExistingFile(model)
    simpleBlockItem(block.value(), existing)
}

fun ResourceLocation.blockTexture(): ResourceLocation = withPrefix(ModelProvider.BLOCK_FOLDER + "/")

fun BlockStateProvider.stoneSet(block: StoneSet<*, *, *, *>) {
    cubeAll(block.block)
    stairs(block.block, block.stairs)
    slab(block.block, block.slab)
    wall(block.block, block.wall)
}

fun horizontalDirections(consumer: BiConsumer<Direction, Property<Boolean>>) {
    CrossCollisionBlock.PROPERTY_BY_DIRECTION
        .asSequence()
        .sortedBy { it.key.ordinal }
        .forEach { entry ->
            consumer.accept(
                entry.key,
                entry.value,
            )
        }
}

fun directions(consumer: BiConsumer<Direction, Property<Boolean>>) {
    PipeBlock.PROPERTY_BY_DIRECTION
        .asSequence()
        .sortedBy { it.key.ordinal }
        .forEach { entry ->
            consumer.accept(
                entry.key,
                entry.value,
            )
        }
}
