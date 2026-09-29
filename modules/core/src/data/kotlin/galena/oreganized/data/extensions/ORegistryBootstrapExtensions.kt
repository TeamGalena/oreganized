@file:JvmName("ORegistryBootstrapExtensions")

package galena.oreganized.data.extensions

import net.minecraft.Util
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Style
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.Item
import net.minecraft.world.item.armortrim.TrimMaterial
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature

fun BootstrapContext<TrimMaterial?>.registerTrim(
    key: ResourceKey<TrimMaterial?>,
    item: Holder<Item?>,
    style: Style,
    overrides: MutableMap<Holder<ArmorMaterial?>?, String?>,
) {
    val location = key.location()
    register(
        key,
        TrimMaterial(
            location.getNamespace() + "_" + location.getPath(),
            item,
            -1.0f,
            overrides,
            Component.translatable(Util.makeDescriptionId("trim_material", location)).withStyle(style),
        ),
    )
}

fun BootstrapContext<*>.getConfigured(id: ResourceLocation): Holder<ConfiguredFeature<*, *>?> {
    val configured = this.lookup<ConfiguredFeature<*, *>?>(Registries.CONFIGURED_FEATURE)
    return configured.getOrThrow(ResourceKey.create<ConfiguredFeature<*, *>?>(Registries.CONFIGURED_FEATURE, id))
}
