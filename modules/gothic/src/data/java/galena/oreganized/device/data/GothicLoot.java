package galena.oreganized.device.data;


import static galena.oreganized.data.ConditionalData.dyed;
import static galena.oreganized.data.provider.OBlockLootProvider.dropAsSilk;
import static galena.oreganized.data.provider.OBlockLootProvider.dropSelf;

import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.gothic.index.GothicBlocks;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class GothicLoot {

    public GothicLoot() {
        ODatagen.addBlockLootProvider(this::generate);
    }

    private void generate(RegistrateBlockLootTables provider) {
        dropSelf(provider, GothicBlocks.GARGOYLE);

        GothicBlocks.CRYSTAL_GLASS.forEach((c, b) -> dyed(c, provider, () -> dropAsSilk(provider, b)));
        GothicBlocks.CRYSTAL_GLASS_PANES.forEach((c, b) -> dyed(c, provider, () -> dropAsSilk(provider, b)));
    }

}
