@file:JvmName("OSoundExtensions")

package galena.oreganized.data.extensions

import com.possible_triangle.multikulti.registrate.provider.RegistrateSoundsProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.level.block.SoundType
import net.neoforged.neoforge.common.data.SoundDefinition
import net.neoforged.neoforge.common.data.SoundDefinition.Sound.sound
import net.neoforged.neoforge.common.data.SoundDefinition.definition

@JvmOverloads
fun SoundDefinition.withVariants(
    name: ResourceLocation,
    count: Int,
    type: SoundDefinition.SoundType = SoundDefinition.SoundType.SOUND,
) = apply {
    val names = (1..count).map { name.withSuffix("_$it") }
    val sounds = names.map { sound(it, type) }
    with(*sounds.toTypedArray())
}

@JvmOverloads
fun RegistrateSoundsProvider.blockSound(
    sound: SoundEvent,
    name: ResourceLocation,
    variants: Int,
    subtitle: Boolean = true,
) {
    add(
        sound,
        definition()
            .also {
                val type = sound.location.path.substringAfterLast('.')
                if (subtitle) it.subtitle("subtitles.block.generic.$type")
            }.withVariants(
                name.withPrefix("block/"),
                variants,
            ),
    )
}
