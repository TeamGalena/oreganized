package galena.oreganized.gothic.ponder;

import galena.oreganized.ModCompat;
import galena.oreganized.ponder.OPonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.createmod.ponder.api.registration.SharedTextRegistrationHelper;
import net.minecraft.core.Holder;

public class GothicPonderPlugin implements OPonderPlugin {

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<Holder<?>> helper) {
        GargoyleScenes.registerScenes(helper);
    }

    @Override
    public void registerSharedText(SharedTextRegistrationHelper helper) {
        // necessary because depending on whether create is loaded or not, the dispenser tooltip has the index 0 or 1
        helper.registerSharedText("gargoyle_automate.dispenser", "You can also feed it using a dispenser");
        helper.registerSharedText("gargoyle_automate.mechanical_arm", "Mechanical arms can be used to feed them");
    }

    @Override
    public void registerTags(PonderTagRegistrationHelper<Holder<?>> helper) {
        if (ModCompat.CREATE_LOADED) {
            CreateCompatScenes.addTags(helper);
        }
    }
}
