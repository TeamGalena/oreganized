package galena.oreganized.plumbum.data;


import static galena.oreganized.data.extensions.OBlockLootExtensions.dropSelf;
import static galena.oreganized.data.extensions.OConditionExtensions.dyed;

import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.waxed.index.WaxedBlocks;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class WaxedLoot {

    public WaxedLoot() {
        ODatagen.addBlockLootProvider(this::generate);
    }

    private void generate(RegistrateBlockLootTables provider) {
        WaxedBlocks.WAXED_CONCRETE_POWDER.map().forEach((c, b) -> dyed(c, provider, () -> dropSelf(provider, b)));
    }

}
