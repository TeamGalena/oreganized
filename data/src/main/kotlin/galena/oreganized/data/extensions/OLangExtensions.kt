@file:JvmName("OLangExtensions")

package galena.oreganized.data.extensions

import com.tterrag.registrate.providers.RegistrateLangProvider
import galena.oreganized.index.sets.IHolderSet
import net.minecraft.Util
import net.minecraft.core.Holder
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.decoration.PaintingVariant
import net.minecraft.world.item.Item
import net.minecraft.world.item.alchemy.Potion
import net.neoforged.neoforge.common.data.LanguageProvider
import net.neoforged.neoforge.registries.DeferredHolder
import java.util.function.Supplier
import java.util.stream.Stream

fun LanguageProvider.addPotion(
    potion: DeferredHolder<Potion, *>,
    name: String,
) {
    val path = potion.key!!.location().path
    add(
        "item.minecraft.potion.effect.$path",
        "Potion of $name",
    )
    add(
        "item.minecraft.splash_potion.effect.$path",
        "Splash Potion of $name",
    )
    add(
        "item.minecraft.lingering_potion.effect.$path",
        "Lingering Potion of $name",
    )
    add(
        "item.minecraft.tipped_arrow.effect.$path",
        "Arrow of $name",
    )
}

fun LanguageProvider.addDisc(
    disc: Supplier<out Item>,
    desc: String,
) {
    addItem(disc, "Music Disc")
    add(disc.get().getDescriptionId() + ".desc", desc)
}

fun LanguageProvider.addDisc(
    disc: Supplier<out Item>,
    artist: String,
    song: String,
) {
    addDisc(disc, artist + " - " + song)
}

fun LanguageProvider.addAdvTitle(
    advancementTitle: String,
    name: String,
) {
    add("advancements.$advancementTitle.title", name)
}

fun LanguageProvider.addAdvDesc(
    advancementTitle: String,
    name: String,
) {
    add("advancements.$advancementTitle.description", name)
}

fun LanguageProvider.addSubtitle(
    category: String,
    subtitleName: String,
    name: String,
) {
    add("subtitles.$category.$subtitleName", name)
}

fun LanguageProvider.addDeath(
    deathName: String,
    name: String,
) {
    add("death.attack.$deathName", name)
}

fun LanguageProvider.addPainting(
    key: ResourceKey<PaintingVariant>,
    title: String,
    author: String,
) {
    val id = key.location()
    add("painting.${id.namespace}.${id.path}.title", title)
    add("painting.${id.namespace}.${id.path}.author", author)
}

fun LanguageProvider.addAttribute(
    attribute: Holder<Attribute>,
    translation: String,
) {
    val id = attribute.key!!.location()
    add("attribute.${id.namespace}.${id.path}", translation)
}

fun RegistrateLangProvider.autoTranslate(holder: Holder<*>) {
    val key = holder.key!!
    val id = key.location()
    val registry = key.registryKey()
    add(
        Util.makeDescriptionId(registry.location().getPath(), id),
        RegistrateLangProvider.toEnglishName(id.getPath()),
    )
}

fun RegistrateLangProvider.autoTranslate(stream: Stream<out Holder<*>>) {
    stream.forEach { autoTranslate(it) }
}

fun RegistrateLangProvider.autoTranslate(set: IHolderSet<*, *, *>) {
    autoTranslate(set.stream())
}
