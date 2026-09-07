package galena.oreganized.argentum.data;


import static galena.oreganized.data.provider.OBlockLootProvider.dropOre;
import static galena.oreganized.data.provider.OBlockLootProvider.dropSelf;

import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import galena.oreganized.OConstants;
import galena.oreganized.argentum.index.ArgentumBlocks;
import galena.oreganized.argentum.index.ArgentumItems;
import galena.oreganized.data.ODatagen;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class ArgentumLoot {

    public ArgentumLoot() {
        ODatagen.addBlockLootProvider(this::generate);
    }

    private void generate(RegistrateBlockLootTables provider) {
        dropOre(provider, ArgentumBlocks.SILVER_ORE, ArgentumItems.RAW_SILVER);
        dropOre(provider, ArgentumBlocks.DEEPSLATE_SILVER_ORE, ArgentumItems.RAW_SILVER);
        dropSelf(provider, ArgentumBlocks.RAW_SILVER_BLOCK);

        dropGrooved(provider, ArgentumBlocks.GROOVED_ICE, Blocks.ICE);
        dropGrooved(provider, ArgentumBlocks.GROOVED_BLUE_ICE, Blocks.BLUE_ICE);
        dropGrooved(provider, ArgentumBlocks.GROOVED_PACKED_ICE, Blocks.PACKED_ICE);

        ArgentumBlocks.CUT_SILVERS.all().forEach(it -> dropSelf(provider, it));
        ArgentumBlocks.SILVER_LATTICES.all().forEach(it -> dropSelf(provider, it));
        ArgentumBlocks.SILVER_BLOCKS.all().forEach(it -> dropSelf(provider, it));
        ArgentumBlocks.SILVER_BULBS.all().forEach(it -> dropSelf(provider, it));
        ArgentumBlocks.SILVER_BARS.all().forEach(it -> dropSelf(provider, it));
        ArgentumBlocks.SILVER_PILLARS.all().forEach(it -> dropSelf(provider, it));
        ArgentumBlocks.CHISELED_SILVER.all().forEach(it -> dropSelf(provider, it));
        ArgentumBlocks.CUT_SILVER_STAIRS.all().forEach(it -> dropSelf(provider, it));
        ArgentumBlocks.CUT_SILVER_SLABS.all().forEach(it -> dropSelf(provider, it));

        ArgentumBlocks.SILVER_DOORS.all().forEach(holder -> {
            var isLowerHalf = LootItemBlockStatePropertyCondition.hasBlockStateProperties(holder.get())
                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoorBlock.HALF, DoubleBlockHalf.LOWER));
            var itemEntry = LootItem.lootTableItem(holder.get()).when(isLowerHalf);
            var pool = LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(itemEntry);

            provider.add(holder.get(), LootTable.lootTable().withPool(provider.applyExplosionCondition(holder.get(), pool)));
        });

        ArgentumBlocks.SILVER_TRAPDOORS.all().forEach(it -> dropSelf(provider, it));
    }

    private static void dropGrooved(RegistrateBlockLootTables provider, Holder<Block> block, Block other) {
        var hasScribe = MatchTool.toolMatches(ItemPredicate.Builder.item().of(ArgentumItems.SCRIBE.get()));
        provider.add(block.value(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(AlternativesEntry.alternatives(
                                LootItem.lootTableItem(block.value().asItem())
                                        .when(provider.hasSilkTouch()),
                                LootItem.lootTableItem(other.asItem())
                                        .when(hasScribe)
                        ))
                ));
    }

}
