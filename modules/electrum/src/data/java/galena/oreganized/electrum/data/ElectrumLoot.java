package galena.oreganized.electrum.data;


import static galena.oreganized.data.provider.OBlockLootProvider.dropSelf;

import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.electrum.index.ElectrumBlocks;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class ElectrumLoot {

    public ElectrumLoot() {
        ODatagen.addBlockLootProvider(this::generate);
    }

    private void generate(RegistrateBlockLootTables provider) {
        dropSelf(provider, ElectrumBlocks.ELECTRUM_BLOCK);
    }

}
