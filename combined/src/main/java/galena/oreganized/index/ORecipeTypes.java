package galena.oreganized.index;

import galena.oreganized.OConstants;
import galena.oreganized.world.recipe.ScribeRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ORecipeTypes {

    private static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, OConstants.MOD_ID);
    private static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, OConstants.MOD_ID);

    public static final DeferredHolder<RecipeType<?>, RecipeType<ScribeRecipe>> SCRIBE_RECIPE = TYPES.register("scribe", () -> new RecipeType<>() {
    });
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ScribeRecipe>> SCRIBE_SERIALIZER = SERIALIZERS.register(SCRIBE_RECIPE.getId().getPath(), ScribeRecipe.Serializer::new);

    public static void register(IEventBus modBus) {
        SERIALIZERS.register(modBus);
        TYPES.register(modBus);
    }

}
