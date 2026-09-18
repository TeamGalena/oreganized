package galena.oreganized.armament.data;


import static galena.oreganized.data.extensions.OBlockLootExtensions.dropSelf;

import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import galena.oreganized.OConstants;
import galena.oreganized.armament.index.ArmamentBlocks;
import galena.oreganized.armament.index.ArmamentItems;
import galena.oreganized.data.ODatagen;
import galena.oreganized.data.provider.RegistrateLootModifierProvider;
import galena.oreganized.world.AddItemLootModifier;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

@Mod(OConstants.MOD_ID)
public class ArmamentLoot {

    public ArmamentLoot() {
        ODatagen.addBlockLootProvider(this::generate);
        ODatagen.addLootModifierProvider(this::modifiers);
    }

    private void generate(RegistrateBlockLootTables provider) {
        dropSelf(provider, ArmamentBlocks.SHRAPNEL_BOMB);
        dropSelf(provider, ArmamentBlocks.LEAD_BOLT_CRATE);
    }


    private void modifiers(RegistrateLootModifierProvider provider) {
        provider.add(
                "lead_bolts_in_outpost",
                new AddItemLootModifier(
                        new LootItemCondition[]{
                                LootTableIdCondition.builder(BuiltInLootTables.PILLAGER_OUTPOST.location()).build(),
                                new LootItemRandomChanceCondition(ConstantValue.exactly(0.7F))
                        },
                        ArmamentItems.LEAD_BOLT.toStack(2)
                )
        );
    }

}
