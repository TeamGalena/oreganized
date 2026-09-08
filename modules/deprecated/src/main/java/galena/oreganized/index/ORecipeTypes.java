package galena.oreganized.index;

import galena.oreganized.argentum.index.ArgentumRecipeTypes;
import galena.oreganized.argentum.world.recipe.ScribeRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ORecipeTypes {

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<RecipeType<?>, RecipeType<ScribeRecipe>> SCRIBE_RECIPE = ArgentumRecipeTypes.SCRIBE_RECIPE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ScribeRecipe>> SCRIBE_SERIALIZER = ArgentumRecipeTypes.SCRIBE_SERIALIZER;


}
