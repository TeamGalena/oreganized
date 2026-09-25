package galena.oreganized.argentum.compat.jei;

import galena.oreganized.ModCompat;
import galena.oreganized.OConstants;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class ArgentumJeiPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        // TODO modular will be mod id in the future?
        return OConstants.modLoc("argentum");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        if (ModCompat.CREATE_LOADED) {
            CreateCompat.registerRecipes(registration);
        }
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        if (ModCompat.CREATE_LOADED) {
            CreateCompat.registerCategories(registration);
        }
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        if (ModCompat.CREATE_LOADED) {
            CreateCompat.registerCatalysts(registration);
        }
    }
}
