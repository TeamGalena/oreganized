package galena.oreganized.carcinogenius.data;

import galena.oreganized.carcinogenius.OreganizedCarcinogenius;
import galena.oreganized.carcinogenius.data.provider.OBlockLootProvider;
import galena.oreganized.carcinogenius.index.OCBlocks;
import galena.oreganized.carcinogenius.index.OCItems;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

public class OLootTables extends LootTableProvider {

    public OLootTables(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, Set.of(), List.of(
                new SubProviderEntry(BlockLoot::new, LootContextParamSets.BLOCK)
        ), lookup);
    }

    public static class BlockLoot extends OBlockLootProvider {

        protected BlockLoot(HolderLookup.Provider lookup) {
            super(lookup);
        }

        protected void generate() {
            ore(OCBlocks.ASBESTOS_ORE, OCItems.RAW_ASBESTOS);
            ore(OCBlocks.DEEPSLATE_ASBESTOS_ORE, OCItems.RAW_ASBESTOS);
            dropSelf(OCBlocks.ASBESTOS_BLOCK);
            dropSelf(OCBlocks.RAW_ASBESTOS_BLOCK);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return OreganizedCarcinogenius.REGISTRY_HELPER.getBlockSubHelper().getDeferredRegister().getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }
    }
}
