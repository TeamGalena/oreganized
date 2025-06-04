package galena.oreganized.carcinogenius.data;

import galena.oreganized.carcinogenius.OreganizedCarcinogenius;
import galena.oreganized.carcinogenius.data.provider.OBlockLootProvider;
import galena.oreganized.carcinogenius.index.OBlocks;
import galena.oreganized.carcinogenius.index.OItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class OLootTables extends LootTableProvider {

    public OLootTables(PackOutput output) {
        super(output, Set.of(), List.of(
                new SubProviderEntry(BlockLoot::new, LootContextParamSets.BLOCK)
        ));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext tracker) {
    }

    public static class BlockLoot extends OBlockLootProvider {

        protected void generate() {
            ore(OBlocks.ASBESTOS_ORE, OItems.RAW_ASBESTOS);
            ore(OBlocks.DEEPSLATE_ASBESTOS_ORE, OItems.RAW_ASBESTOS);
            dropSelf(OBlocks.ASBESTOS_BLOCK);
            dropSelf(OBlocks.RAW_ASBESTOS_BLOCK);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return OreganizedCarcinogenius.REGISTRY_HELPER.getBlockSubHelper().getDeferredRegister().getEntries().stream().map(Supplier::get).collect(Collectors.toList());
        }
    }
}
