package galena.oreganized.electrum.data;


import static galena.oreganized.data.extensions.OBlockLootExtensions.dropSelf;

import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.data.provider.RegistrateLootModifierProvider;
import galena.oreganized.electrum.index.ElectrumBlocks;
import galena.oreganized.electrum.index.ElectrumItems;
import galena.oreganized.world.AddItemLootModifier;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

@Mod(OConstants.MOD_ID)
public class ElectrumLoot {

    public ElectrumLoot() {
        ODatagen.addBlockLootProvider(this::generate);
        ODatagen.addLootModifierProvider(this::modifiers);
    }

    private void generate(RegistrateBlockLootTables provider) {
        dropSelf(provider, ElectrumBlocks.ELECTRUM_BLOCK);
    }

    private void modifiers(RegistrateLootModifierProvider provider) {
        provider.add(
                "electrum_upgrade_smithing_template",
                new AddItemLootModifier(
                        new LootItemCondition[]{
                                LootTableIdCondition.builder(BuiltInLootTables.WOODLAND_MANSION.location()).build(),
                                new LootItemRandomChanceCondition(ConstantValue.exactly(0.7F))
                        },
                        ElectrumItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE.toStack()
                )
        );
    }

}
