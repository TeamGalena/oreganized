package galena.oreganized.compat.ponder;

import galena.oreganized.ModCompat;
import galena.oreganized.OConstants;
import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.createmod.ponder.api.registration.SharedTextRegistrationHelper;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;

public class OreganizedPonderPlugin implements PonderPlugin {

    static boolean CREATED_LOADED = ModList.get().isLoaded(ModCompat.CREATE);

    @Override
    public String getModId() {
        return OConstants.MOD_ID;
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        var registrar = helper.<Holder<?>>withKeyFunction(it -> it.getKey().location());

        GargoyleScenes.registerScenes(registrar);
        CauldronScenes.registerScenes(registrar);

        if (CREATED_LOADED) {
            CreateCompatScenes.registerScenes(registrar);
        }
    }

    @Override
    public void registerSharedText(SharedTextRegistrationHelper helper) {
        // necessary because depending on whether create is loaded or not, the dispenser tooltip has the index 0 or 1
        helper.registerSharedText("gargoyle_automate.dispenser", "You can also feed it using a dispenser");
        helper.registerSharedText("gargoyle_automate.mechanical_arm", "Mechanical arms can be used to feed them");
    }

    @Override
    public void registerTags(PonderTagRegistrationHelper<ResourceLocation> helper) {
        var registrar = helper.<Holder<?>>withKeyFunction(it -> it.getKey().location());

        if (CREATED_LOADED) {
            CreateCompatScenes.addTags(registrar);
        }
    }
}
