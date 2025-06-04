package galena.oreganized.carcinogenius.data;

import galena.oreganized.carcinogenius.OreganizedCarcinogenius;
import galena.oreganized.carcinogenius.index.OBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class OBlockTags extends IntrinsicHolderTagsProvider<Block> {

    public OBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future, @Nullable ExistingFileHelper helper) {
        super(output, Registries.BLOCK, future, block -> block.builtInRegistryHolder().key(), OreganizedCarcinogenius.NAMESPACE, helper);
    }

    @Override
    public @NotNull String getName() {
        return "Oreganized Block Tags";
    }

    private void tag(TagKey<Block> key, Map<DyeColor, ? extends Supplier<? extends Block>> values) {
        var tag = tag(key);
        values.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(it -> it.getValue().get())
                .map(BuiltInRegistries.BLOCK::getKey)
                .forEach(tag::addOptional);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Forge
        tag(Tags.Blocks.ORES).add(OBlocks.ASBESTOS_ORE.get(),OBlocks.DEEPSLATE_ASBESTOS_ORE.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(

                OBlocks.ASBESTOS_ORE.get(),
                OBlocks.DEEPSLATE_ASBESTOS_ORE.get(),
                OBlocks.ASBESTOS_BLOCK.get(),
                OBlocks.RAW_ASBESTOS_BLOCK.get()
        );
    }
}
