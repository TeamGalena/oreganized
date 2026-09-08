package galena.oreganized.register;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class RecipeTypeRegistryHelper extends SimpleRegistryHelper<RecipeType<?>> {

    public RecipeTypeRegistryHelper(RegistryHelper parent) {
        super(parent, Registries.RECIPE_TYPE);
    }

    public <T extends Recipe<?>> DeferredHolder<RecipeType<?>, RecipeType<T>> createRecipeType(String name) {
        return create(name, id -> new RecipeType<>() {
            public String toString() {
                return id.toString();
            }
        });
    }

}
