@file:JvmName("OSoundExtensions")

package galena.oreganized.data.extensions

import com.possible_triangle.multikulti.registrate.provider.RegistrateSoundsProvider
import net.minecraft.resources.ResourceLocation
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

fun RegistrateSoundsProvider.blockSoundType(
    type: SoundType,
    name: ResourceLocation,
    digVariants: Int,
    stepVariants: Int,
) {
    fun dig(type: String) =
        definition()
            .subtitle("subtitles.block.generic.$type")
            .withVariants(
                name.withPrefix("block/").withSuffix("_dig"),
                digVariants,
            )

    fun step(type: String) =
        definition()
            .subtitle("subtitles.block.generic.$type")
            .withVariants(
                name.withPrefix("block/").withSuffix("_step"),
                stepVariants,
            )

    add(type.breakSound, dig("break"))
    add(type.fallSound, step("fall"))
    add(type.hitSound, step("hit"))
    add(type.placeSound, dig("place"))
    add(type.stepSound, step("step"))
}
