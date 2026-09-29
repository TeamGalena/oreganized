package galena.oreganized.argentum.compat.jei;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

import com.mojang.datafixers.util.Pair;
import com.simibubi.create.compat.jei.ConversionRecipe;
import com.simibubi.create.compat.jei.DoubleItemIcon;
import com.simibubi.create.compat.jei.EmptyBackground;
import com.simibubi.create.compat.jei.category.CreateRecipeCategory;
import com.simibubi.create.content.kinetics.fan.processing.HauntingRecipe;
import com.simibubi.create.content.processing.recipe.StandardProcessingRecipe;
import galena.oreganized.ModCompat;
import galena.oreganized.OConstants;
import galena.oreganized.argentum.compat.ItemTarnishing;
import galena.oreganized.argentum.world.Tarnishable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;

public class CreateCompat {

    private static final ResourceLocation FAN_PROCESSING_ID = OConstants.modLoc("fan_tarnishing");
    public static final String FAN_PROCESSING_LANG_KEY = FAN_PROCESSING_ID.toLanguageKey("recipe");

    private static final CreateRecipeCategory.Info<StandardProcessingRecipe<?>> FAN_TARNISHING = new CreateRecipeCategory.Info<>(
            RecipeType.createRecipeHolderType(FAN_PROCESSING_ID),
            Component.translatable(FAN_PROCESSING_LANG_KEY),
            new EmptyBackground(178, 72),
            new DoubleItemIcon(
                    () -> BuiltInRegistries.ITEM.get(fromNamespaceAndPath(ModCompat.CREATE, "propeller")).getDefaultInstance(),
                    Items.SPAWNER::getDefaultInstance
            ),
            List::of,
            List.of()
    );

    static void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new FanTarnishingCategory(FAN_TARNISHING));
    }

    static void registerRecipes(IRecipeRegistration registration) {
        var tarnishables = registration.getJeiHelpers().getIngredientManager()
                .getAllItemStacks()
                .stream()
                .map(ItemTarnishing::asTarnishable)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        createMysteryConversions(registration, tarnishables);
        createHaunting(registration, tarnishables);
    }

    static void registerCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(BuiltInRegistries.BLOCK.get(fromNamespaceAndPath(ModCompat.CREATE, "encased_fan")), FAN_TARNISHING.recipeType());
    }

    public static <T> void registerRecipes(IRecipeRegistration registration, ResourceLocation id, Stream<T> recipes) {
        var optionalRecipeType = registration.getJeiHelpers().getRecipeType(id);
        if (optionalRecipeType.isEmpty()) {
            OConstants.LOGGER.warn("unable to locate {} category, but {} is loaded", id.getPath(), id.getNamespace());
            return;
        }

        @SuppressWarnings("unchecked")
        var recipeType = (RecipeType<T>) optionalRecipeType.get();
        registration.addRecipes(recipeType, recipes.toList());
    }

    private static void createMysteryConversions(IRecipeRegistration registration, Collection<Pair<Holder<Block>, Tarnishable>> tarnishables) {
        registerRecipes(registration, fromNamespaceAndPath(ModCompat.CREATE, "mystery_conversion"), tarnishables.stream().map(it ->
                ConversionRecipe.create(
                        new ItemStack(it.getFirst().value()),
                        new ItemStack(it.getSecond().nextStage())
                )
        ));
    }

    private static void createHaunting(IRecipeRegistration registration, Collection<Pair<Holder<Block>, Tarnishable>> tarnishables) {
        registration.addRecipes(FAN_TARNISHING.recipeType(), tarnishables.stream().map(it -> {
                    var id = it.getFirst().getKey().location().withPrefix("fan_tarnishing/");
                    return new RecipeHolder<StandardProcessingRecipe<?>>(
                            id,
                            new StandardProcessingRecipe.Builder<>(HauntingRecipe::new, id)
                                    .require(it.getFirst().value())
                                    .output(it.getSecond().nextStage())
                                    .build()
                    );
                }
        ).toList());
    }
}
