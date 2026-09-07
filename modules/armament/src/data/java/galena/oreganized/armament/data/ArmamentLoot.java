package galena.oreganized.armament.data;


import static galena.oreganized.data.provider.OBlockLootProvider.dropSelf;

import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import galena.oreganized.OConstants;
import galena.oreganized.armament.index.ArmamentBlocks;
import galena.oreganized.data.ODatagen;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class ArmamentLoot {

    public ArmamentLoot() {
        ODatagen.addBlockLootProvider(this::generate);
    }

    private void generate(RegistrateBlockLootTables provider) {
        dropSelf(provider, ArmamentBlocks.SHRAPNEL_BOMB);
        dropSelf(provider, ArmamentBlocks.LEAD_BOLT_CRATE);
    }

}
