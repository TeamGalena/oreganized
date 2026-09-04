package galena.oreganized.data;

import galena.oreganized.OConstants;
import galena.oreganized.index.OItems;
import galena.oreganized.world.AddItemLootModifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

public class OLootModifiers extends GlobalLootModifierProvider {

    public OLootModifiers(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, OConstants.MOD_ID);
    }

    @Override
    protected void start() {
        add(
                "electrum_upgrade_smithing_template",
                new AddItemLootModifier(
                        new LootItemCondition[]{
                                LootTableIdCondition.builder(BuiltInLootTables.WOODLAND_MANSION.location()).build(),
                                new LootItemRandomChanceCondition(ConstantValue.exactly(0.7F))
                        },
                        OItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE.toStack()
                )
        );

        add(
                "lead_bolts_in_outpost",
                new AddItemLootModifier(
                        new LootItemCondition[]{
                                LootTableIdCondition.builder(BuiltInLootTables.PILLAGER_OUTPOST.location()).build(),
                                new LootItemRandomChanceCondition(ConstantValue.exactly(0.7F))
                        },
                        OItems.LEAD_BOLT.toStack(2)
                )
        );

        add(
                "scribe_in_ancient_cities",
                new AddItemLootModifier(
                        new LootItemCondition[]{
                                LootTableIdCondition.builder(BuiltInLootTables.ANCIENT_CITY.location()).build(),
                                new LootItemRandomChanceCondition(ConstantValue.exactly(0.15F))
                        },
                        OItems.SCRIBE.toStack()
                )
        );

        Stream.concat(OItems.silverArmor(), OItems.silverTools()).forEach(item ->
                add(
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

}
