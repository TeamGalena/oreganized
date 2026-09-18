package galena.oreganized.glance.data;


import static galena.oreganized.data.extensions.OBlockLootExtensions.dropSelf;
import static galena.oreganized.data.extensions.OBlockLootExtensions.dropSlab;

import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.glance.index.GlanceBlocks;
import galena.oreganized.glance.world.block.SpottedGlanceBlock;
import galena.oreganized.plumbum.index.PlumbumItems;
import java.util.function.BiConsumer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class GlanceLoot {

    public GlanceLoot() {
        ODatagen.addBlockLootProvider(this::blocks);
        ODatagen.addLootProvider(LootContextParamSets.GIFT, this::gameplay);
    }

    private void blocks(RegistrateBlockLootTables provider) {
        dropSelf(provider, GlanceBlocks.GLANCE);
        dropSelf(provider, GlanceBlocks.POLISHED_GLANCE);
        dropSelf(provider, GlanceBlocks.GLANCE_BRICKS);
        dropSelf(provider, GlanceBlocks.CHISELED_GLANCE);
        dropSlab(provider, GlanceBlocks.GLANCE_SLAB);
        dropSlab(provider, GlanceBlocks.POLISHED_GLANCE_SLAB);
        dropSlab(provider, GlanceBlocks.GLANCE_BRICK_SLAB);
        dropSelf(provider, GlanceBlocks.GLANCE_STAIRS);
        dropSelf(provider, GlanceBlocks.POLISHED_GLANCE_STAIRS);
        dropSelf(provider, GlanceBlocks.GLANCE_BRICK_STAIRS);
        dropSelf(provider, GlanceBlocks.GLANCE_WALL);
        dropSelf(provider, GlanceBlocks.GLANCE_BRICK_WALL);
        dropSelf(provider, GlanceBlocks.SPOTTED_GLANCE);
        dropSelf(provider, GlanceBlocks.WAXED_SPOTTED_GLANCE);
    }

    private void gameplay(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
        consumer.accept(
                SpottedGlanceBlock.WASH_LOOT_TABLE,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .add(LootItem.lootTableItem(PlumbumItems.LEAD_NUGGET.value()))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1F, 2F)))
                        )
        );
    }

}
