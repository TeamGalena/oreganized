package galena.oreganized.argentum.mixin.compat;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.simibubi.create.content.kinetics.deployer.ItemApplicationRecipe;
import com.simibubi.create.content.kinetics.deployer.ManualApplicationRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.foundation.data.recipe.LogStrippingFakeRecipes;
import galena.oreganized.OConstants;
import galena.oreganized.argentum.index.ArgentumItems;
import galena.oreganized.argentum.index.ArgentumRecipeTypes;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = LogStrippingFakeRecipes.class, remap = false)
public class LogStrippingFakeRecipesMixin {

    @Unique
    private static Optional<Ingredient> createIngredient(BlockPredicate predicate, Block exclude) {
        return predicate.blocks().map(holderSet -> {
            var items = holderSet.stream()
                    .map(Holder::value)
                    .filter(it -> it != exclude)
                    .map(Block::asItem)
                    .map(Item::getDefaultInstance);
            return Ingredient.of(items);
        }).filter(it -> !it.isEmpty());
    }

    @ModifyReturnValue(method = "createRecipes", at = @At("RETURN"), require = 0)
    private static List<RecipeHolder<ManualApplicationRecipe>> addGroovedRecipes(List<RecipeHolder<ManualApplicationRecipe>> value) {
        var newList = new ArrayList<>(value);

        var connection = Minecraft.getInstance().getConnection();
        if (connection == null) return value;

        var manager = connection.getRecipeManager();
        var recipes = manager.getAllRecipesFor(ArgentumRecipeTypes.SCRIBE_RECIPE.get());

        recipes.stream()
                .filter(it -> !it.value().dropResources())
                .forEach(holder -> {
                    createIngredient(holder.value().from(), holder.value().to()).ifPresentOrElse(from -> {
                        var recipe = new ItemApplicationRecipe.Builder<>(ManualApplicationRecipe::new, holder.id())
                                .withItemIngredients(from, Ingredient.of(ArgentumItems.SCRIBE.get()))
                                .withItemOutputs(new ProcessingOutput(new ItemStack(holder.value().to()), 1F))
                                .toolNotConsumed()
                                .build();
                        newList.add(new RecipeHolder<>(holder.id(), recipe));
                    }, () -> {
                        OConstants.LOGGER.warn("unable to convert scribe recipe {}", holder.id());
                    });
                });

        return newList;
    }

}
