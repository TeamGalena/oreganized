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

fun RegistrateSoundsProvider.blockSound(
    sound: SoundEvent,
    name: ResourceLocation,
    variants: Int,
) {
    val type = sound.location.path.substringAfterLast('.')
    add(
        sound,
        definition()
            .subtitle("subtitles.block.generic.$type")
            .withVariants(
                name.withPrefix("block/"),
                variants,
            ),
    )
}
