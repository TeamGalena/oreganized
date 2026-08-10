package galena.oreganized.compat.ponder;

import galena.oreganized.Oreganized;
import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;

public class OreganizedPonderPlugin implements PonderPlugin {

    static boolean CREATED_LOADED = ModList.get().isLoaded("create");

    @Override
    public String getModId() {
        return Oreganized.MOD_ID;
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
    public void registerTags(PonderTagRegistrationHelper<ResourceLocation> helper) {
        var registrar = helper.<Holder<?>>withKeyFunction(it -> it.getKey().location());

        if(CREATED_LOADED) {
            CreateCompatScenes.addTags(registrar);
        }
    }
}
