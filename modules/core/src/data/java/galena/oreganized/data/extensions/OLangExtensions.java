package galena.oreganized.data.extensions;


import static com.tterrag.registrate.providers.RegistrateLangProvider.toEnglishName;
import static net.minecraft.Util.makeDescriptionId;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import galena.oreganized.index.sets.IBlockSet;
import java.util.function.Supplier;
import java.util.stream.Stream;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class OLangExtensions {

    public static void addPotion(LanguageProvider provider, Supplier<? extends Potion> potion, String name) {
        provider.add("item.minecraft.potion.effect." + BuiltInRegistries.POTION.getKey(potion.get()).getPath(), "Potion of " + name);
        provider.add("item.minecraft.splash_potion.effect." + BuiltInRegistries.POTION.getKey(potion.get()).getPath(), "Splash Potion of " + name);
        provider.add("item.minecraft.lingering_potion.effect." + BuiltInRegistries.POTION.getKey(potion.get()).getPath(), "Lingering Potion of " + name);
        provider.add("item.minecraft.tipped_arrow.effect." + BuiltInRegistries.POTION.getKey(potion.get()).getPath(), "Arrow of " + name);
    }

    public static void addDisc(LanguageProvider provider, Supplier<? extends Item> disc, String desc) {
        provider.addItem(disc, "Music Disc");
        provider.add(disc.get().getDescriptionId() + ".desc", desc);
    }

    public static void addDisc(LanguageProvider provider, Supplier<? extends Item> disc, String artist, String song) {
        addDisc(provider, disc, artist + " - " + song);
    }

    public static void addAdvTitle(LanguageProvider provider, String advancementTitle, String name) {
        provider.add("advancements." + advancementTitle + ".title", name);
    }

    public static void addAdvDesc(LanguageProvider provider, String advancementTitle, String name) {
        provider.add("advancements." + advancementTitle + ".description", name);
    }

    public static void addSubtitle(LanguageProvider provider, String category, String subtitleName, String name) {
        provider.add("subtitles." + category + "." + subtitleName, name);
    }

    public static void addDeath(LanguageProvider provider, String deathName, String name) {
        provider.add("death.attack." + deathName, name);
    }

    public static void addPainting(LanguageProvider provider, ResourceKey<PaintingVariant> key, String title, String author) {
        var id = key.location();
        provider.add("painting.%s.%s.title".formatted(id.getNamespace(), id.getPath()), title);
        provider.add("painting.%s.%s.author".formatted(id.getNamespace(), id.getPath()), author);
    }

    public static void addAttribute(LanguageProvider provider, Holder<Attribute> attribute, String translation) {
        var id = attribute.getKey().location();
        provider.add("attribute.%s.%s".formatted(id.getNamespace(), id.getPath()), translation);
    }

    public static void addFluid(LanguageProvider provider, Holder<Fluid> fluid, String translation) {
        var key = makeDescriptionId("fluid", fluid.getKey().location());
        provider.add(key, translation);
    }

    public static void addFluid(LanguageProvider provider, Holder<Fluid> fluid) {
        addFluid(provider, fluid, toEnglishName(fluid.getKey().location().getPath()));
    }

    public static void autoTranslate(RegistrateLangProvider provider, Holder<?> holder) {
        var key = holder.getKey();
        var id = key.location();
        var registry = key.registryKey();
        provider.add(Util.makeDescriptionId(registry.location().getPath(), id), provider.toEnglishName(id.getPath()));
    }

    public static void autoTranslate(RegistrateLangProvider provider, Stream<? extends Holder<?>> stream) {
        stream.forEach(it -> autoTranslate(provider, it));
    }

    public static void autoTranslate(RegistrateLangProvider provider, IBlockSet set) {
        autoTranslate(provider, set.stream());
    }

}
