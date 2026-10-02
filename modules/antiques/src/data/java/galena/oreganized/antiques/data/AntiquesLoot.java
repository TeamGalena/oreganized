package galena.oreganized.antiques.data;

import static net.minecraft.world.level.storage.loot.LootPool.lootPool;
import static net.minecraft.world.level.storage.loot.LootTable.lootTable;
import static net.minecraft.world.level.storage.loot.entries.LootItem.lootTableItem;

import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import galena.oreganized.OConstants;
import galena.oreganized.antiques.index.AntiquesBlocks;
import galena.oreganized.argentum.data.ArgentumSets;
import galena.oreganized.argentum.index.ArgentumBlocks;
import galena.oreganized.argentum.index.ArgentumItems;
import galena.oreganized.armament.index.ArmamentItems;
import galena.oreganized.data.ODatagen;
import galena.oreganized.electrum.index.ElectrumItems;
import galena.oreganized.plumbum.index.PlumbumItems;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;

@Mod(OConstants.MOD_ID)
public class AntiquesLoot {

    public AntiquesLoot() {
        ODatagen.addBlockLootProvider(this::generate);
    }

    private void generate(RegistrateBlockLootTables provider) {
        dropAntiques(provider, AntiquesBlocks.ANTIQUES_PILE);
    }

    private void dropAntiques(RegistrateBlockLootTables provider, DeferredBlock<Block> block) {
        provider.add(block.value(), lootTable()
                .withPool(
                        lootPool()
                                .when(provider.hasSilkTouch())
                                .add(lootTableItem(block))
                )
                .withPool(
                        antiquesPool()
                                .when(provider.hasSilkTouch().invert())
                )
        );
    }

    // TODO modular uses other modules stuff
    private LootPool.Builder antiquesPool() {
        var pool = lootPool();

        pool.add(lootTableItem(Blocks.BREWING_STAND));
        pool.add(lootTableItem(ArgentumBlocks.SILVER_BARS.base()));
        pool.add(lootTableItem(ArgentumItems.SILVER_NUGGET));
        pool.add(lootTableItem(ArgentumItems.SCRIBE));
        ArgentumSets.silverTools().forEach(it -> pool.add(lootTableItem(it)));
        ArgentumItems.silverArmor().forEach(it -> pool.add(lootTableItem(it)));
        pool.add(lootTableItem(Items.SHEARS));
        pool.add(lootTableItem(ArmamentItems.FLINT_AND_PEWTER));
        pool.add(lootTableItem(Items.BUCKET));
        pool.add(lootTableItem(Items.SPYGLASS));
        pool.add(lootTableItem(Items.FLINT_AND_STEEL));
        pool.add(lootTableItem(Blocks.LANTERN));
        pool.add(lootTableItem(ElectrumItems.SPEEDOMETER));
        pool.add(lootTableItem(Items.GOLD_NUGGET));
        pool.add(lootTableItem(ArgentumItems.SILVER_MIRROR));
        pool.add(lootTableItem(Items.CLOCK));
        pool.add(lootTableItem(ArgentumBlocks.SILVER_DOORS.base()));
        pool.add(lootTableItem(Items.COMPASS));
        pool.add(lootTableItem(Items.TRIPWIRE_HOOK));
        pool.add(lootTableItem(ElectrumItems.ELECTRUM_NUGGET));
        pool.add(lootTableItem(ArmamentItems.LEAD_BOLT));
        pool.add(lootTableItem(PlumbumItems.THERMOMETER));

        return pool;
    }

}
