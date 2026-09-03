package galena.oreganized.plumbum;

import galena.oreganized.OConstants;
import galena.oreganized.plumbum.index.*;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class PlumbumModule {

    public PlumbumModule() {
        PlumbumItems.register();
        PlumbumBlocks.register();
        PlumbumBlockEntities.register();
        PlumbumFluids.register();
        PlumbumEffects.register();
        PlumbumParticles.register();
        PlumbumDamageTypes.register();
        PlumbumAttachmentTypes.register();
        PlumbumDataComponents.register();
        PlumbumCriterionTriggers.register();
    }

}
