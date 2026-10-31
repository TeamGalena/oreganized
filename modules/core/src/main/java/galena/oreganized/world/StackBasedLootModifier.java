package galena.oreganized.world;

import com.mojang.datafixers.Products;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctions;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.LootModifier;

public abstract class StackBasedLootModifier extends LootModifier {

    protected static <T extends StackBasedLootModifier> Products.P3<RecordCodecBuilder.Mu<T>, LootItemCondition[], ItemStack, List<LootItemFunction>> stackCodecStart(RecordCodecBuilder.Instance<T> instance) {
        return LootModifier.codecStart(instance).and(
                instance.group(
                        ItemStack.CODEC.fieldOf("item").forGetter(it -> it.stack),
                        LootItemFunctions.ROOT_CODEC.listOf().optionalFieldOf("functions", List.of()).forGetter(it -> it.functions)
                )
        );
    }

    protected final ItemStack stack;
    protected final List<LootItemFunction> functions;

    public StackBasedLootModifier(LootItemCondition[] conditions, ItemStack stack, List<LootItemFunction> functions) {
        super(conditions);
        this.stack = stack;
        this.functions = functions;
    }

    public StackBasedLootModifier(LootItemCondition[] conditions, ItemStack stack) {
        this(conditions, stack, List.of());
    }

    protected ItemStack createStack(LootContext context) {
        var created = stack.copy();
        functions.forEach(function -> {
            function.apply(created, context);
        });
        return created;
    }

    public static abstract class Builder<T extends StackBasedLootModifier, R extends Builder<T, R>> {
        private final List<LootItemCondition> conditions = new ArrayList<>();
        private final List<LootItemFunction> functions = new ArrayList<>();
        private final ItemStack stack;

        protected Builder(ItemStack stack) {
            this.stack = stack;
        }

        protected abstract T build(LootItemCondition[] conditions, ItemStack stack, List<LootItemFunction> functions);

        private R when(LootItemCondition condition) {
            conditions.add(condition);
            return cast();
        }

        public R when(LootItemCondition.Builder condition) {
            return when(condition.build());
        }

        private R apply(LootItemFunction function) {
            functions.add(function);
            return cast();
        }

        public R apply(LootItemFunction.Builder function) {
            return apply(function.build());
        }

        @SuppressWarnings("unchecked")
        private R cast() {
            return (R) this;
        }

        public T build() {
            return build(conditions.toArray(LootItemCondition[]::new), stack, functions);
        }
    }

}
