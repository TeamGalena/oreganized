package galena.oreganized.argentum.ponder;

import galena.oreganized.ponder.OPonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.core.Holder;

public class ArgentumPonderPlugin implements OPonderPlugin {

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<Holder<?>> helper) {
        TarnishingScenes.registerScenes(helper);
    }

}
