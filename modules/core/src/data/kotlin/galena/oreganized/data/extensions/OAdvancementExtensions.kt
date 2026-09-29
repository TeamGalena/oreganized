@file:JvmName("OAdvancementExtensions")

package galena.oreganized.data.extensions

import com.tterrag.registrate.providers.RegistrateAdvancementProvider
import galena.oreganized.OConstants
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.advancements.AdvancementType
import net.minecraft.advancements.DisplayInfo
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ItemLike
import java.util.*

fun RegistrateAdvancementProvider.info(
    icon: ItemLike,
    id: String,
    type: AdvancementType,
    title: String,
    description: String,
): DisplayInfo = info(ItemStack(icon), id, type, title, description)

fun RegistrateAdvancementProvider.info(
    icon: ItemStack,
    id: String,
    type: AdvancementType,
    title: String,
    description: String,
): DisplayInfo =
    DisplayInfo(
        icon,
        title(OConstants.MOD_ID, id, title),
        desc(OConstants.MOD_ID, id, description),
        Optional.empty<ResourceLocation?>(),
        type,
        true,
        true,
        false,
    )

fun getAdv(id: String): AdvancementHolder = Advancement.Builder.advancement().build(ResourceLocation.parse(id))
