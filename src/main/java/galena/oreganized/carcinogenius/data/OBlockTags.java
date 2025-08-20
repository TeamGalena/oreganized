package galena.oreganized.carcinogenius.data;

import galena.oreganized.carcinogenius.OreganizedCarcinogenius;
import galena.oreganized.carcinogenius.index.OCBlocks;
import galena.oreganized.carcinogenius.index.OCTags;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

public class OBlockTags extends IntrinsicHolderTagsProvider<Block> {

    public OBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future, @Nullable ExistingFileHelper helper) {
        super(output, Registries.BLOCK, future, block -> block.builtInRegistryHolder().key(), OreganizedCarcinogenius.NAMESPACE, helper);
    }

    @Override
    public @NotNull String getName() {
        return "Oreganized Block Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(Tags.Blocks.ORES).add(OCBlocks.ASBESTOS_ORE.get(), OCBlocks.DEEPSLATE_ASBESTOS_ORE.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                OCBlocks.ASBESTOS_ORE.get(),
                OCBlocks.DEEPSLATE_ASBESTOS_ORE.get(),
                OCBlocks.ASBESTOS_BLOCK.get(),
                OCBlocks.RAW_ASBESTOS_BLOCK.get()
        );

        tag(OCTags.CREATES_ASBESTOS_CLOUD)
                .add(OCBlocks.RAW_ASBESTOS_BLOCK.get())
                .add(OCBlocks.ASBESTOS_BLOCK.get())
                .add(OCBlocks.DEEPSLATE_ASBESTOS_ORE.get())
                .add(OCBlocks.ASBESTOS_ORE.get());
    }
}
