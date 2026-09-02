package galena.oreganized.electrum;

import galena.oreganized.OConstants;
import galena.oreganized.electrum.index.ElectrumAttributes;
import galena.oreganized.electrum.index.ElectrumBlocks;
import galena.oreganized.electrum.index.ElectrumItems;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class ElectrumModule {

    public ElectrumModule() {
        ElectrumBlocks.register();
        ElectrumItems.register();
        ElectrumAttributes.register();
    }

}
