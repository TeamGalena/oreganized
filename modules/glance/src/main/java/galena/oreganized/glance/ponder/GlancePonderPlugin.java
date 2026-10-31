package galena.oreganized.glance.ponder;

import galena.oreganized.OConstants;
import galena.oreganized.ponder.OPonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.core.Holder;

public class GlancePonderPlugin implements OPonderPlugin {

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<Holder<?>> helper) {
        if (OConstants.moduleLoaded("plumbum")) {
            RenewableLeadScenes.registerScenes(helper);
        }
    }

}
