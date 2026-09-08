package galena.oreganized.plumbum.ponder;

import galena.oreganized.ModCompat;
import galena.oreganized.ponder.OPonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.core.Holder;

public class PlumbumPonderPlugin implements OPonderPlugin {

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<Holder<?>> helper) {
        CauldronScenes.registerScenes(helper);

        if (ModCompat.CREATE_LOADED) {
            CreateCompatScenes.registerScenes(helper);
        }
    }

}
