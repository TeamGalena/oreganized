@file:JvmName("OItemModelExtensions")

package galena.oreganized.data.extensions

import galena.oreganized.ModCompat
import net.minecraft.client.renderer.block.model.BlockModel
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.PackType
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.Item
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder
import net.neoforged.neoforge.client.model.generators.ItemModelProvider
import net.neoforged.neoforge.client.model.generators.ModelFile
import net.neoforged.neoforge.client.model.generators.ModelProvider
import net.neoforged.neoforge.registries.DeferredItem

fun ItemModelProvider.generatedItem(
    name: ResourceLocation,
    texture: ResourceLocation,
    type: String?,
): ItemModelBuilder =
    withExistingParent(name.getPath(), "item/generated")
        .texture("layer0", texture.withPrefix(type + "/"))

fun ItemModelProvider.normalItem(item: DeferredItem<out Item?>): ItemModelBuilder =
    generatedItem(item.getId(), item.getId(), ModelProvider.ITEM_FOLDER)

fun ItemModelProvider.toolItem(item: DeferredItem<out Item?>): ItemModelBuilder =
    withExistingParent(item.getId().getPath(), ResourceLocation.withDefaultNamespace("item/handheld"))
        .texture("layer0", itemTexture(item.getId()))

fun ItemModelProvider.shieldItem(item: DeferredItem<out Item?>): ItemModelBuilder {
    val texture = itemTexture(item.getId())
    val name = item.getId().getPath()

    val blockingModel =
        withExistingParent(
            name + "_blocking",
            ResourceLocation.fromNamespaceAndPath(
                ModCompat.SHIELD_EXPANSION,
                "item/netherite_shield_blocking",
            ),
        ).guiLight(BlockModel.GuiLight.FRONT)
            .texture("1", texture)
            .texture("particle", texture)

    return withExistingParent(
        name,
        ResourceLocation.fromNamespaceAndPath(ModCompat.SHIELD_EXPANSION, "item/netherite_shield"),
    ).guiLight(BlockModel.GuiLight.FRONT)
        .texture("1", texture)
        .texture("particle", texture)
        .override()
        .predicate(ResourceLocation.withDefaultNamespace("blocking"), 1.0f)
        .model(blockingModel)
        .end()
}

fun ItemModelProvider.leveledDevice(
    item: DeferredItem<out Item?>,
    levels: Int,
    property: ResourceLocation,
): ItemModelBuilder {
    val model = withExistingParent(item.getId().getPath(), "item/generated")

    for (i in 0..<levels) {
        val subName = item.getId().withSuffix("_" + i)
        val subModel = generatedItem(subName, subName, ModelProvider.ITEM_FOLDER)
        model
            .override()
            .model(subModel)
            .predicate(property, i.toFloat())
            .end()
    }

    return model
}

/**
 * copied from [com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider]
 */
fun ItemModelProvider.trimmableArmorItem(item: DeferredItem<out ArmorItem?>) {
    val id = item.getId()
    val model = generatedItem(id, id, ModelProvider.ITEM_FOLDER)
    var trimType = 1
    for (trim in arrayOf<String>(
        "quartz",
        "iron",
        "netherite",
        "redstone",
        "copper",
        "gold",
        "emerald",
        "diamond",
        "lapis",
        "amethyst",
    )) {
        val name =
            ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "item/" + id.getPath() + "_" + trim + "_trim")
        model
            .override()
            .model(ModelFile.UncheckedModelFile(name))
            .predicate(ResourceLocation.withDefaultNamespace("trim_type"), (trimType / 10.0).toFloat())
        val texture =
            ResourceLocation.withDefaultNamespace(
                "trims/items/" + item.value().getType().getName() + "_trim_" + trim,
            )
        existingFileHelper.trackGenerated(texture, PackType.CLIENT_RESOURCES, ".png", "textures")
        withExistingParent(name.getPath(), "item/generated")
            .texture("layer0", itemTexture(id))
            .texture("layer1", texture)
        trimType++
    }
}

fun itemTexture(id: ResourceLocation): ResourceLocation = id.withPrefix(ModelProvider.ITEM_FOLDER + "/")
