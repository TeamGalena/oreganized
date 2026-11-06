package galena.oreganized.antiques.data;

import static galena.oreganized.data.extensions.OBlockLootExtensions.dropSelf;
import static net.minecraft.world.level.storage.loot.LootPool.lootPool;
import static net.minecraft.world.level.storage.loot.LootTable.lootTable;
import static net.minecraft.world.level.storage.loot.entries.LootItem.lootTableItem;

import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import galena.oreganized.antiques.AntiquesModule;
import galena.oreganized.antiques.index.AntiquesBlocks;
import galena.oreganized.antiques.index.AntiquesItems;
import galena.oreganized.antiques.world.block.AntiquesPileBlock;
import galena.oreganized.argentum.ArgentumModule;
import galena.oreganized.argentum.index.ArgentumBlocks;
import galena.oreganized.argentum.index.ArgentumItems;
import galena.oreganized.argentum.index.ArgentumSets;
import galena.oreganized.armament.ArmamentModule;
import galena.oreganized.armament.index.ArmamentItems;
import galena.oreganized.data.ODatagen;
import galena.oreganized.electrum.ElectrumModule;
import galena.oreganized.electrum.index.ElectrumItems;
import galena.oreganized.plumbum.PlumbumModule;
import galena.oreganized.plumbum.index.PlumbumItems;
import galena.oreganized.world.AddItemLootModifier;
import galena.oreganized.world.ReplaceItemLootModifier;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Stream;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(AntiquesModule.MOD_ID)
public class AntiquesLoot {

    public AntiquesLoot() {
        ODatagen.addBlockLootProvider(this::blocks);
        ODatagen.addLootProvider(LootContextParamSets.BLOCK, this::gameplay);
        ODatagen.addLootModifierProvider(this::modifiers);
    }

    private void blocks(RegistrateBlockLootTables provider) {
        dropSelf(provider, AntiquesBlocks.ANTIQUES_PILE);
    }

    private void gameplay(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
        consumer.accept(AntiquesPileBlock.POLISH_LOOT_TABLE, createAntiquesDrops());
    }

    private void modifiers(GlobalLootModifierProvider provider) {
        provider.add("antiques_in_chests", AddItemLootModifier.builder(AntiquesBlocks.ANTIQUES_PILE.toStack())
                .when(LootItemRandomChanceCondition.randomChance(ConstantValue.exactly(0.4F)))
                .when(
                        AnyOfCondition.anyOf(
                                LootTableIdCondition.builder(BuiltInLootTables.ANCIENT_CITY.location()),
                                LootTableIdCondition.builder(BuiltInLootTables.DESERT_PYRAMID.location()),
                                LootTableIdCondition.builder(BuiltInLootTables.SHIPWRECK_TREASURE.location()),
                                LootTableIdCondition.builder(BuiltInLootTables.SIMPLE_DUNGEON.location())
                        )
                )
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                .build()
        );

        provider.add("buried_antiques", ReplaceItemLootModifier.builder(AntiquesBlocks.ANTIQUES_PILE.toStack())
                .when(LootItemRandomChanceCondition.randomChance(ConstantValue.exactly(0.1F)))
                .when(AnyOfCondition.anyOf(
                        LootTableIdCondition.builder(BuiltInLootTables.DESERT_PYRAMID_ARCHAEOLOGY.location()),
                        LootTableIdCondition.builder(BuiltInLootTables.DESERT_WELL_ARCHAEOLOGY.location()),
                        LootTableIdCondition.builder(BuiltInLootTables.TRAIL_RUINS_ARCHAEOLOGY_COMMON.location())
                ))
                .build()
        );

        antiquesInject(provider, ElectrumModule.MOD_ID, Stream.of(
                ElectrumItems.SPEEDOMETER,
                ElectrumItems.ELECTRUM_NUGGET
        ));

        antiquesInject(provider, ArmamentModule.MOD_ID, Stream.of(
                ArmamentItems.FLINT_AND_PEWTER,
                ArmamentItems.LEAD_BOLT
        ));

        antiquesInject(provider, ArgentumModule.MOD_ID, Stream.of(
                Stream.of(
                        ArgentumBlocks.SILVER_BARS.base(),
                        ArgentumItems.SILVER_NUGGET,
                        ArgentumItems.SCRIBE,
                        ArgentumItems.SILVER_MIRROR,
                        ArgentumBlocks.SILVER_DOORS.base()
                ),
                ArgentumSets.silverTools(),
                ArgentumSets.silverArmor()
        ).flatMap(Function.identity()));

        antiquesInject(provider, PlumbumModule.MOD_ID, Stream.of(
                PlumbumItems.THERMOMETER
        ));

        antiquesInject(provider, PlumbumModule.MOD_ID, Stream.of(
                PlumbumItems.THERMOMETER
        ));
    }

    private LootTable.Builder createAntiquesDrops() {
        return lootTable().withPool(createAntiquesPool());
    }

    private void antiquesInject(GlobalLootModifierProvider provider, String modId, Stream<DeferredHolder<? extends ItemLike, ?>> items) {
        var list = items.toList();
        list.forEach(item -> {
            provider.add(
                    "antiques_pile/%s/%s".formatted(modId, item.getId().getPath()), ReplaceItemLootModifier.builder(new ItemStack(item.value()))
                            .when(LootItemRandomChanceCondition.randomChance(1F / 28))
                            .when(LootTableIdCondition.builder(AntiquesPileBlock.POLISH_LOOT_TABLE.location()))
                            .build(),
                    new ModLoadedCondition(modId)
            );
        });
    }

    // TODO modular uses other modules stuff
    private LootPool.Builder createAntiquesPool() {
        var pool = lootPool();

        pool.add(lootTableItem(Blocks.BREWING_STAND));
        pool.add(lootTableItem(Items.SHEARS));
        pool.add(lootTableItem(Items.BUCKET));
        pool.add(lootTableItem(Items.SPYGLASS));
        pool.add(lootTableItem(Items.FLINT_AND_STEEL));
        pool.add(lootTableItem(Blocks.LANTERN));
        pool.add(lootTableItem(Items.GOLD_NUGGET));
        pool.add(lootTableItem(Items.CLOCK));
        pool.add(lootTableItem(Items.COMPASS));
        pool.add(lootTableItem(Items.TRIPWIRE_HOOK));
        pool.add(lootTableItem(AntiquesItems.MUSIC_DISC_RADIO));

        return pool;
    }

}
