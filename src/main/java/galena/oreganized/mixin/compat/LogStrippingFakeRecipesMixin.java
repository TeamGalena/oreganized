package galena.oreganized.mixin.compat;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.simibubi.create.content.kinetics.deployer.ManualApplicationRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.foundation.data.recipe.LogStrippingFakeRecipes;
import galena.oreganized.Oreganized;
import galena.oreganized.index.OItems;
import galena.oreganized.index.ORecipeTypes;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = LogStrippingFakeRecipes.class, remap = false)
public class LogStrippingFakeRecipesMixin {

    @Unique
    private static Optional<Ingredient> createIngredient(BlockPredicate predicate, Block exclude) {
        return Optional.ofNullable(predicate.blocks).map(blocks -> {
            var items = blocks.stream()
                    .filter(it -> it != exclude)
                    .map(Block::asItem)
                    .map(Item::getDefaultInstance);

            return Ingredient.of(items);
        }).filter(it -> !it.isEmpty());
    }

    @ModifyReturnValue(method = "createRecipes", at = @At("RETURN"), require = 0)
    private static List<ManualApplicationRecipe> addGroovedRecipes(List<ManualApplicationRecipe> value) {
        var newList = new ArrayList<>(value);

        var connection = Minecraft.getInstance().getConnection();
        if (connection == null) return value;

        var manager = connection.getRecipeManager();
        var recipes = manager.getAllRecipesFor(ORecipeTypes.SCRIBE_RECIPE.get());

        recipes.stream()
                .filter(it -> !it.dropResources())
                .forEach(holder -> {
                    createIngredient(holder.from(), holder.to()).ifPresentOrElse(from -> {
                        var recipe = new ProcessingRecipeBuilder<>(ManualApplicationRecipe::new, holder.id())
                                .withItemIngredients(from, Ingredient.of(OItems.SCRIBE.get()))
                                .withItemOutputs(new ProcessingOutput(new ItemStack(holder.to()), 1F))
                                .toolNotConsumed()
                                .build();
                        newList.add(recipe);
                    }, () -> {
                        Oreganized.LOGGER.warn("unable to convert scribe recipe {}", holder.id());
                    });
                });

        return newList;
    }

}
