package galena.oreganized.ponder;

import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.createmod.ponder.api.registration.SharedTextRegistrationHelper;
import net.minecraft.core.Holder;

public interface OPonderPlugin {

    default void registerScenes(PonderSceneRegistrationHelper<Holder<?>> helper) {
    }

    default void registerTags(PonderTagRegistrationHelper<Holder<?>> helper) {
    }

    default void registerSharedText(SharedTextRegistrationHelper helper) {
    }

}
