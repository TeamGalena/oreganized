package galena.oreganized.world.recipe;

import com.google.gson.JsonObject;
import galena.oreganized.index.ORecipeTypes;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

public record ScribeRecipe(
        ResourceLocation id,
        BlockPredicate from,
        Block to,
        boolean dropResources
) implements Recipe<BlockRecipeInput> {

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public boolean matches(BlockRecipeInput input, Level level) {
        if (!(level instanceof ServerLevel serverLevel)) return false;
        return from.matches(serverLevel, input.block().getPos()) && !input.block().getState().is(to);
    }

    @Override
    public ItemStack assemble(BlockRecipeInput input, RegistryAccess provider) {
        return getResultItem(provider);
    }

    @Override
    public boolean canCraftInDimensions(int height, int width) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess provider) {
        return to.asItem().getDefaultInstance();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ORecipeTypes.SCRIBE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ORecipeTypes.SCRIBE_RECIPE.get();
    }

    public static class Serializer implements RecipeSerializer<ScribeRecipe> {

        @Override
        public ScribeRecipe fromJson(ResourceLocation id, JsonObject json) {
            return new ScribeRecipe(
                    id,
                    BlockPredicate.fromJson(json.get("from")),
                    ForgeRegistries.BLOCKS.getValue(new ResourceLocation(GsonHelper.getAsString(json, "to"))),
                    GsonHelper.getAsBoolean(json, "drop_resources", false)
            );
        }

        @Override
        public @Nullable ScribeRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            return new ScribeRecipe(
                    id,
                    BlockPredicateNetworking.decode(buffer),
                    buffer.readRegistryIdUnsafe(ForgeRegistries.BLOCKS),
                    buffer.readBoolean()
            );
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, ScribeRecipe recipe) {
            BlockPredicateNetworking.encode(buffer, recipe.from());
            buffer.writeRegistryIdUnsafe(ForgeRegistries.BLOCKS, recipe.to());
            buffer.writeBoolean(recipe.dropResources());
        }

        public void toJson(ResourceLocation id, ScribeRecipe recipe, JsonObject json) {
            json.addProperty("id", id.toString());
            json.add("from", recipe.from().serializeToJson());
            json.addProperty("to", ForgeRegistries.BLOCKS.getKey(recipe.to()).toString());
            if (recipe.dropResources()) json.addProperty("drop_resources", true);
        }
    }

    public static class Builder implements RecipeBuilder {

        private BlockPredicate.Builder from;
        private Block to;
        private boolean dropResources = false;
        private Set<ICondition> conditions = new HashSet<>();

        @Override
        public Builder unlockedBy(String key, CriterionTriggerInstance criterion) {
            return this;
        }

        @Override
        public Builder group(@Nullable String group) {
            return this;
        }

        @Override
        public Item getResult() {
            return to.asItem();
        }

        public ScribeRecipe build(ResourceLocation id) {
            Objects.requireNonNull(from);
            Objects.requireNonNull(to);
            return new ScribeRecipe(id, from.build(), to, dropResources);
        }

        public Builder result(Block block) {
            this.to = block;
            return this;
        }

        public Builder from(BlockPredicate.Builder predicate) {
            this.from = predicate;
            return this;
        }

        public Builder from(Block block) {
            return from(BlockPredicate.Builder.block().of(block));
        }

        public Builder from(TagKey<Block> tag) {
            return from(BlockPredicate.Builder.block().of(tag));
        }

        public Builder when(String... modIds) {
            return when(Arrays.stream(modIds).map(ModLoadedCondition::new).toArray(ICondition[]::new));
        }

        public Builder when(ICondition... conditions) {
            this.conditions.addAll(Arrays.asList(conditions));
            return this;
        }

        public Builder dropResources(boolean value) {
            this.dropResources = value;
            return this;
        }

        public Builder dropResources() {
            return dropResources(true);
        }

        @Override
        public void save(Consumer<FinishedRecipe> output, ResourceLocation id) {
            output.accept(new Finished(id.withPrefix("scribe/"), build(id)));
        }
    }

    public record Finished(ResourceLocation id, ScribeRecipe recipe) implements FinishedRecipe {

        @Override
        public void serializeRecipeData(JsonObject json) {
            ORecipeTypes.SCRIBE_SERIALIZER.get().toJson(id(), recipe, json);
        }

        @Override
        public ResourceLocation getId() {
            return id();
        }

        @Override
        public RecipeSerializer<?> getType() {
            return recipe.getSerializer();
        }

        @Override
        public @Nullable JsonObject serializeAdvancement() {
            return null;
        }

        @Override
        public @Nullable ResourceLocation getAdvancementId() {
            return null;
        }
    }

}
