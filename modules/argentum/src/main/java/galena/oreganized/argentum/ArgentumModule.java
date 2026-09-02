package galena.oreganized.argentum;

import galena.oreganized.OConstants;
import galena.oreganized.argentum.index.ArgentumAttributes;
import galena.oreganized.argentum.index.ArgentumBlocks;
import galena.oreganized.argentum.index.ArgentumItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class ArgentumModule {

    public ArgentumModule() {
        ArgentumBlocks.register();
        ArgentumItems.register();
        ArgentumAttributes.register();
    }

}
