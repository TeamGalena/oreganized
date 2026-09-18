package galena.oreganized.argentum.data;


import static galena.oreganized.data.extensions.OBlockLootExtensions.dropOre;
import static galena.oreganized.data.extensions.OBlockLootExtensions.dropSelf;

import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import galena.oreganized.OConstants;
import galena.oreganized.argentum.index.ArgentumBlocks;
import galena.oreganized.argentum.index.ArgentumItems;
import galena.oreganized.data.ODatagen;
import galena.oreganized.data.provider.RegistrateLootModifierProvider;
import galena.oreganized.world.AddItemLootModifier;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

@Mod(OConstants.MOD_ID)
public class ArgentumLoot {

    public ArgentumLoot() {
        ODatagen.addBlockLootProvider(this::generate);
        ODatagen.addLootModifierProvider(this::modifiers);
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

    private void modifiers(RegistrateLootModifierProvider provider) {

        provider.add(
                "scribe_in_ancient_cities",
                new AddItemLootModifier(
                        new LootItemCondition[]{
                                LootTableIdCondition.builder(BuiltInLootTables.ANCIENT_CITY.location()).build(),
                                new LootItemRandomChanceCondition(ConstantValue.exactly(0.15F))
                        },
                        ArgentumItems.SCRIBE.toStack()
                )
        );

        Stream.concat(ArgentumItems.silverArmor(), ArgentumSets.silverTools()).forEach(item ->
                provider.add(
                        item.getId().getPath() + "_in_chests",
                        new AddItemLootModifier(
                                new LootItemCondition[]{
                                        AnyOfCondition.anyOf(
                                                LootTableIdCondition.builder(BuiltInLootTables.ANCIENT_CITY.location()),
                                                LootTableIdCondition.builder(BuiltInLootTables.ABANDONED_MINESHAFT.location()),
                                                LootTableIdCondition.builder(BuiltInLootTables.SIMPLE_DUNGEON.location())
                                        ).build(),
                                        new LootItemRandomChanceCondition(ConstantValue.exactly(0.1F))
                                },
                                item.toStack(),
                                List.of(
                                        SetItemDamageFunction.setDamage(UniformGenerator.between(0.3F, 0.8F)).build()
                                )
                        )
                )
        );
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
