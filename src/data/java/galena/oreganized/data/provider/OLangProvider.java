package galena.oreganized.data.provider;

import galena.oreganized.index.TarnishedBlocks;

import java.util.*;
import java.util.function.Supplier;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.data.LanguageProvider;

public abstract class OLangProvider extends LanguageProvider {
    private final Set<String> added = new HashSet<>();

    public OLangProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    public void add(String key, String value) {
        super.add(key, value);
        this.added.add(key);
    }

    public void addPotion(Supplier<? extends Potion> potion, String name) {
        add("item.minecraft.potion.effect." + BuiltInRegistries.POTION.getKey(potion.get()).getPath(), "Potion of " + name);
        add("item.minecraft.splash_potion.effect." + BuiltInRegistries.POTION.getKey(potion.get()).getPath(), "Splash Potion of " + name);
        add("item.minecraft.lingering_potion.effect." + BuiltInRegistries.POTION.getKey(potion.get()).getPath(), "Lingering Potion of " + name);
        add("item.minecraft.tipped_arrow.effect." + BuiltInRegistries.POTION.getKey(potion.get()).getPath(), "Arrow of " + name);
    }

    public void addDisc(Supplier<? extends Item> disc, String desc) {
        addItem(disc, "Music Disc");
        add(disc.get().getDescriptionId() + ".desc", desc);
    }

    public void addDisc(Supplier<? extends Item> disc, String artist, String song) {
        addDisc(disc, artist + " - " + song);
    }

    public void addAdvTitle(String advancementTitle, String name) {
        add("advancements." + advancementTitle + ".title", name);
    }

    public void addAdvDesc(String advancementTitle, String name) {
        add("advancements." + advancementTitle + ".description", name);
    }

    public void addSubtitle(String category, String subtitleName, String name) {
        add("subtitles." + category + "." + subtitleName, name);
    }

    public void addDeath(String deathName, String name) {
        add("death.attack." + deathName, name);
    }

    private void tryAdd(String key, String value) {
        if (this.added.contains(key)) return;
        add(key, value);
    }

    public void tryBlock(Holder<? extends Block> block) {
        var key = block.value().getDescriptionId();
        var value = toTranslation(block.getKey());
        tryAdd(key, value);
    }


    public void tryItem(Holder<? extends Item> item) {
        var key = item.value().getDescriptionId();
        var value = toTranslation(item.getKey());
        tryAdd(key, value);
    }

    public void tryFluid(Holder<? extends Fluid> fluid) {
        var key = Util.makeDescriptionId("fluid", fluid.getKey().location());
        var value = toTranslation(fluid.getKey());
        tryAdd(key, value);
    }

    public void tryEntity(Holder<? extends EntityType<?>> entity) {
        var key = entity.value().getDescriptionId();
        var value = toTranslation(entity.getKey());
        tryAdd(key, value);
    }

    private String toTranslation(ResourceKey<?> key) {
        String[] strArr = key.location().getPath().split("_");
        var builder = new StringBuilder();
        for (String str : strArr) {
            char[] stringArray = str.trim().toCharArray();
            stringArray[0] = Character.toUpperCase(stringArray[0]);
            str = new String(stringArray);

            builder.append(str).append(" ");
        }
        return builder.toString().trim();
    }

    public void addPainting(ResourceKey<PaintingVariant> key, String title, String author) {
        var id = key.location();
        add("painting.%s.%s.title".formatted(id.getNamespace(), id.getPath()), title);
        add("painting.%s.%s.author".formatted(id.getNamespace(), id.getPath()), author);
    }

    public void addTarnished(TarnishedBlocks<?> blocks, String pristine) {
        addBlock(blocks.base(), pristine);
        addBlock(blocks.blemished(), "Blemished " + pristine);
        addBlock(blocks.tarnished(), "Tarnished " + pristine);
    }

    public void addAttribute(Holder<Attribute> attribute, String translation) {
        var id = attribute.getKey().location();
        add("attribute.%s.%s".formatted(id.getNamespace(), id.getPath()), translation);
    }

}
