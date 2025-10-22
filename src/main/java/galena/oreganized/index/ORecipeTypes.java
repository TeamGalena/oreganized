package galena.oreganized.index;

import galena.oreganized.Oreganized;
import galena.oreganized.world.recipe.ScribeRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ORecipeTypes {

    private static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, Oreganized.MOD_ID);
    private static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, Oreganized.MOD_ID);

    public static final RegistryObject<RecipeType<ScribeRecipe>> SCRIBE_RECIPE = TYPES.register("scribe", () -> new RecipeType<>() {
    });
    public static final RegistryObject<ScribeRecipe.Serializer> SCRIBE_SERIALIZER = SERIALIZERS.register(SCRIBE_RECIPE.getId().getPath(), ScribeRecipe.Serializer::new);

    public static void register(IEventBus modBus) {
        SERIALIZERS.register(modBus);
        TYPES.register(modBus);
    }

}
