package galena.oreganized.plumbum.data;


import static galena.oreganized.data.extensions.OBlockLootExtensions.*;

import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.plumbum.index.PlumbumBlocks;
import galena.oreganized.plumbum.index.PlumbumItems;
import galena.oreganized.plumbum.world.block.IMeltableBlock;
import galena.oreganized.plumbum.world.block.MeltingLeadCauldronBlock;
import galena.oreganized.plumbum.world.item.ThermometerItem;
import java.util.function.BiConsumer;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class PlumbumLoot {

    public PlumbumLoot() {
        ODatagen.addBlockLootProvider(this::blocks);
        ODatagen.addLootProvider(LootContextParamSets.GIFT, this::gameplay);
    }

    private void blocks(RegistrateBlockLootTables provider) {
        dropCauldron(provider, PlumbumBlocks.MOLTEN_LEAD_CAULDRON);
        meltingCauldron(provider, PlumbumBlocks.MELTING_LEAD_CAULDRON, PlumbumBlocks.LEAD_BLOCK);

        dropOre(provider, PlumbumBlocks.LEAD_ORE, PlumbumItems.RAW_LEAD);
        dropOre(provider, PlumbumBlocks.DEEPSLATE_LEAD_ORE, PlumbumItems.RAW_LEAD);
        dropSelf(provider, PlumbumBlocks.RAW_LEAD_BLOCK);

        dropSelf(provider, PlumbumBlocks.LEAD_BLOCK);
        dropSelf(provider, PlumbumBlocks.LEAD_BRICKS);
        dropSelf(provider, PlumbumBlocks.LEAD_PILLAR);
        dropSelf(provider, PlumbumBlocks.LEAD_BULB);
        dropSelf(provider, PlumbumBlocks.CUT_LEAD);

        provider.add(PlumbumBlocks.LEAD_DOOR.value(), LootTable.lootTable()
                .withPool(provider.applyExplosionCondition(PlumbumBlocks.LEAD_DOOR.value(), LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(PlumbumBlocks.LEAD_DOOR.get())
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoorBlock.HALF, DoubleBlockHalf.LOWER)))
                        .add(LootItem.lootTableItem(PlumbumBlocks.LEAD_DOOR.get())))));
        dropSelf(provider, PlumbumBlocks.LEAD_TRAPDOOR);

        provider.add(PlumbumBlocks.LEAD_BARS.value(), LootTable.lootTable()
                .withPool(provider.applyExplosionCondition(PlumbumBlocks.LEAD_BARS.value(), LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(AlternativesEntry.alternatives(
                                LootItem.lootTableItem(PlumbumBlocks.LEAD_BARS.value()).when(provider.hasSilkTouch()),
                                LootItem.lootTableItem(PlumbumItems.LEAD_NUGGET.value())
                                        .apply(SetItemCountFunction.setCount((UniformGenerator.between(2F, 3F))))
                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(PlumbumBlocks.LEAD_BARS.value())
                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(IMeltableBlock.GOOPYNESS_3, 2))
                                        ),
                                LootItem.lootTableItem(PlumbumBlocks.LEAD_BARS.value())
                        )))));

        dropSelf(provider, PlumbumBlocks.STURDY_LEVER);
        dropSelf(provider, PlumbumBlocks.STURDY_BUTTON);

        dropSelf(provider, PlumbumBlocks.WHITE_DATURA);
        dropSelf(provider, PlumbumBlocks.PURPLE_DATURA);
        dropPottedPlant(provider, PlumbumBlocks.POTTED_PURPLE_DATURA);
        dropPottedPlant(provider, PlumbumBlocks.POTTED_WHITE_DATURA);
    }

    private static void meltingCauldron(RegistrateBlockLootTables provider, Holder<? extends Block> cauldron, Holder<? extends ItemLike> content) {
        provider.add(cauldron.value(), LootTable.lootTable().withPool(
                provider.applyExplosionCondition(
                        cauldron.value(),
                        LootPool.lootPool()
                                .add(LootItem.lootTableItem(Blocks.CAULDRON))
                )
        ).withPool(
                LootPool.lootPool()
                        .add(LootItem.lootTableItem(content.value()))
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(cauldron.value())
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(MeltingLeadCauldronBlock.AGE, 0)
                                )
                        )
        ));
    }

    private void gameplay(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
        consumer.accept(
                ThermometerItem.BREAK_LOOT_TABLE,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .add(LootItem.lootTableItem(PlumbumItems.LEAD_NUGGET.value()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1F, 4F)))
                        )
        );
    }


}
