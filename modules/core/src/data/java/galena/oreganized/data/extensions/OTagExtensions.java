package galena.oreganized.data.extensions;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import galena.oreganized.index.DyeColors;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
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
        provider.copy(BlockTags.STAIRS, ItemTags.STAIRS);
        provider.copy(BlockTags.SLABS, ItemTags.SLABS);

        provider.copy(BlockTags.DOORS, ItemTags.DOORS);
        provider.copy(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS);
        provider.copy(BlockTags.BUTTONS, ItemTags.BUTTONS);

        provider.copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);
    }

    public static void tagDyed(RegistrateItemTagsProvider provider, Map<DyeColor, ? extends ItemLike> values, TagKey<Item>... keys) {
        var intrinsic = (RegistrateTagsProvider.IntrinsicImpl<Item>) provider;
        Map<DyeColor, Supplier<Item>> mapped = values.entrySet().stream().collect(Collectors.toMap(
                it -> it.getKey(),
                it -> () -> it.getValue().asItem())
        );
        tagDyed(intrinsic, BuiltInRegistries.ITEM, mapped, keys);
    }

    public static void tagDyed(RegistrateTagsProvider.IntrinsicImpl<Block> provider, Map<DyeColor, ? extends Supplier<? extends Block>> values, TagKey<Block>... keys) {
        tagDyed(provider, BuiltInRegistries.BLOCK, values, keys);
    }

    private static <T> TagKey<T> dyedTag(ResourceKey<? extends Registry<T>> registry, DyeColor color) {
        return TagKey.create(registry, ResourceLocation.fromNamespaceAndPath("c", "dyed/" + color.getSerializedName()));
    }

    private static <T> TagKey<T> dyedTag(ResourceKey<? extends Registry<T>> registry) {
        return TagKey.create(registry, ResourceLocation.fromNamespaceAndPath("c", "dyed"));
    }

    private static <T> void tagDyed(RegistrateTagsProvider.IntrinsicImpl<T> provider, Registry<T> registry, Map<DyeColor, ? extends Supplier<? extends T>> values, TagKey<T>... keys) {
        values.entrySet().stream().sorted(Map.Entry.comparingByKey(DyeColors.comparator())).forEach(entry -> {
            var item = entry.getValue().get();
            var id = registry.getKey(item);
            for (var key : keys) {
                provider.addTag(key).addOptional(id);
            }
            provider.addTag(dyedTag(registry.key())).addOptional(id);
            provider.addTag(dyedTag(registry.key(), entry.getKey())).addOptional(id);
        });
    }

}
