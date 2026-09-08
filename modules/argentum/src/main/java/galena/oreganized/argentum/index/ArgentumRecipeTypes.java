package galena.oreganized.argentum.index;

import galena.oreganized.OConstants;
import galena.oreganized.argentum.world.recipe.ScribeRecipe;
import galena.oreganized.register.RecipeTypeRegistryHelper;
import galena.oreganized.register.SimpleRegistryHelper;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(OConstants.MOD_ID)
public class ArgentumRecipeTypes {

    private static final RecipeTypeRegistryHelper TYPES = OConstants.REGISTRY_HELPER.getRecipeTypeSubHelper();
    private static final SimpleRegistryHelper<RecipeSerializer<?>> SERIALIZERS = OConstants.REGISTRY_HELPER.getRecipeSerializerSubHelper();

    public static final DeferredHolder<RecipeType<?>, RecipeType<ScribeRecipe>> SCRIBE_RECIPE = TYPES.createRecipeType("scribe");

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ScribeRecipe>> SCRIBE_SERIALIZER = SERIALIZERS.create("scribe", $ -> new ScribeRecipe.Serializer());


}
