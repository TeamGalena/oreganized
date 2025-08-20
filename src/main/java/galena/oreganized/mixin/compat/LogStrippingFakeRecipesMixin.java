package galena.oreganized.mixin.compat;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.simibubi.create.content.kinetics.deployer.ItemApplicationRecipe;
import com.simibubi.create.content.kinetics.deployer.ManualApplicationRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.foundation.data.recipe.LogStrippingFakeRecipes;
import galena.oreganized.Oreganized;
import galena.oreganized.content.item.ScribeItem;
import galena.oreganized.index.OItems;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = LogStrippingFakeRecipes.class, remap = false)
public class LogStrippingFakeRecipesMixin {

    @ModifyReturnValue(method = "createRecipes", at = @At("RETURN"), require = 0)
    private static List<RecipeHolder<ManualApplicationRecipe>> addGroovedRecipes(List<RecipeHolder<ManualApplicationRecipe>> value) {
        var newList = new ArrayList<>(value);

        ScribeItem.getGroovedBlocks().forEach(entry -> {
            var blockId = BuiltInRegistries.BLOCK.getKey(entry.getKey()).getPath();
            var id = Oreganized.modLoc("manual_application/" + blockId);
            var recipe = new ItemApplicationRecipe.Builder<>(ManualApplicationRecipe::new, id)
                    .withItemIngredients(Ingredient.of(entry.getKey()), Ingredient.of(OItems.SCRIBE.get()))
                    .withItemOutputs(new ProcessingOutput(new ItemStack(entry.getValue().get()), 1F))
                    .toolNotConsumed()
                    .build();
            newList.add(new RecipeHolder<>(id, recipe));
        });

        return newList;
    }

}
