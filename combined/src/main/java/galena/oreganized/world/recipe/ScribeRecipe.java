package galena.oreganized.world.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import galena.oreganized.index.ORecipeTypes;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import org.jetbrains.annotations.Nullable;

public record ScribeRecipe(
        BlockPredicate from,
        Block to,
        boolean dropResources
) implements Recipe<BlockRecipeInput> {

    @Override
    public boolean matches(BlockRecipeInput input, Level level) {
        return from.matches(input.block()) && !input.block().getState().is(to);
    }

    @Override
    public ItemStack assemble(BlockRecipeInput input, HolderLookup.Provider provider) {
        return getResultItem(provider);
    }

    @Override
    public boolean canCraftInDimensions(int height, int width) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
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

        public static final MapCodec<ScribeRecipe> CODEC = RecordCodecBuilder.mapCodec(builder ->
                builder.group(
                        BlockPredicate.CODEC.fieldOf("from").forGetter(ScribeRecipe::from),
                        BuiltInRegistries.BLOCK.byNameCodec().fieldOf("to").forGetter(ScribeRecipe::to),
                        Codec.BOOL.optionalFieldOf("drop_resources", false).forGetter(ScribeRecipe::dropResources)
                ).apply(builder, ScribeRecipe::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, ScribeRecipe> STREAM_CODEC = StreamCodec.composite(
                BlockPredicate.STREAM_CODEC, ScribeRecipe::from,
                ByteBufCodecs.registry(Registries.BLOCK), ScribeRecipe::to,
                ByteBufCodecs.BOOL, ScribeRecipe::dropResources,
                ScribeRecipe::new
        );

        @Override
        public MapCodec<ScribeRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ScribeRecipe> streamCodec() {
            return STREAM_CODEC;
        }

    }

    public static class Builder implements RecipeBuilder {

        private BlockPredicate.Builder from;
        private Block to;
        private boolean dropResources = false;
        private Set<ICondition> conditions = new HashSet<>();

        @Override
        public Builder unlockedBy(String key, Criterion<?> criterion) {
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

        public ScribeRecipe build() {
            Objects.requireNonNull(from);
            Objects.requireNonNull(to);
            return new ScribeRecipe(from.build(), to, dropResources);
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
        public void save(RecipeOutput output, ResourceLocation id) {
            output.accept(id.withPrefix("scribe/"), build(), null, conditions.toArray(ICondition[]::new));
        }
    }

}
