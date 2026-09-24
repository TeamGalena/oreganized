package galena.oreganized.data.extensions;

import static com.teamabnormals.blueprint.core.util.TagUtil.itemTag;

import com.mojang.datafixers.util.Pair;
import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import galena.oreganized.ModCompat;
import galena.oreganized.index.sets.DyedBlockSet;
import java.util.function.Supplier;
import java.util.stream.Stream;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;

public class OTagExtensions {

    public static final void copyBlockTags(RegistrateItemTagsProvider provider) {
        provider.copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);

        provider.copy(Tags.Blocks.ORES, Tags.Items.ORES);
        provider.copy(Tags.Blocks.ORE_RATES_SINGULAR, Tags.Items.ORE_RATES_SINGULAR);
        provider.copy(Tags.Blocks.ORES_IN_GROUND_STONE, Tags.Items.ORES_IN_GROUND_STONE);
        provider.copy(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE, Tags.Items.ORES_IN_GROUND_DEEPSLATE);

        provider.copy(BlockTags.WALLS, ItemTags.WALLS);
        provider.copy(BlockTags.FENCES, ItemTags.FENCES);

        provider.copy(BlockTags.STAIRS, ItemTags.STAIRS);
        provider.copy(BlockTags.SLABS, ItemTags.SLABS);

        provider.copy(BlockTags.DOORS, ItemTags.DOORS);
        provider.copy(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS);
        provider.copy(BlockTags.BUTTONS, ItemTags.BUTTONS);

        provider.copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);
    }

    public static void tagDyed(RegistrateItemTagsProvider provider, DyedBlockSet<?> values, TagKey<Item>... keys) {
        var intrinsic = (RegistrateTagsProvider.IntrinsicImpl<Item>) provider;
        Stream<Pair<DyeColor, Supplier<Item>>> mapped = values.entries()
                .map(it -> Pair.of(it.getKey(), () -> it.getValue().asItem()));
        tagDyed(intrinsic, BuiltInRegistries.ITEM, mapped, keys);
    }

    public static void tagDyed(RegistrateTagsProvider.IntrinsicImpl<Block> provider, DyedBlockSet<?> values, TagKey<Block>... keys) {
        tagDyed(provider, BuiltInRegistries.BLOCK, values.entries().map(it -> Pair.of(it.getKey(), it.getValue())), keys);
    }

    private static <T> TagKey<T> dyedTag(ResourceKey<? extends Registry<T>> registry, DyeColor color) {
        return TagKey.create(registry, ResourceLocation.fromNamespaceAndPath("c", "dyed/" + color.getSerializedName()));
    }

    private static <T> TagKey<T> dyedTag(ResourceKey<? extends Registry<T>> registry) {
        return TagKey.create(registry, ResourceLocation.fromNamespaceAndPath("c", "dyed"));
    }

    private static <T> void tagDyed(RegistrateTagsProvider.IntrinsicImpl<T> provider, Registry<T> registry, Stream<? extends Pair<DyeColor, ? extends Supplier<? extends T>>> values, TagKey<T>... keys) {
        values.forEach(entry -> {
            var item = entry.getSecond().get();
            var id = registry.getKey(item);
            for (var key : keys) {
                provider.addTag(key).addOptional(id);
            }
            provider.addTag(dyedTag(registry.key())).addOptional(id);
            provider.addTag(dyedTag(registry.key(), entry.getFirst())).addOptional(id);
        });
    }

    public static void tagKnife(RegistrateItemTagsProvider provider, Holder<Item> item) {
        provider.addTag(itemTag("c", "tools/knife")).add(item.getKey());
        provider.addTag(itemTag(ModCompat.FARMERS_DELIGHT, "tools/knives")).add(item.getKey());
    }

    public static void tagShield(RegistrateItemTagsProvider provider, Holder<Item> item) {
        provider.addTag(Tags.Items.TOOLS_SHIELD).add(item.getKey());
        provider.addTag(itemTag(ModCompat.SHIELD_EXPANSION, "shields")).add(item.getKey());
    }

    public static void tagMachete(RegistrateItemTagsProvider provider, Holder<Item> item) {
        provider.addTag(itemTag(ModCompat.NETHERS_DELIGHT, "tools/machete")).add(item.getKey());
    }

}
