package galena.oreganized.world;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import org.jetbrains.annotations.NotNull;

public class AddItemLootModifier extends StackBasedLootModifier {

    public static final MapCodec<AddItemLootModifier> CODEC = RecordCodecBuilder.mapCodec(builder ->
            stackCodecStart(builder).apply(builder, AddItemLootModifier::new)
    );

    public AddItemLootModifier(LootItemCondition[] conditions, ItemStack stack, List<LootItemFunction> functions) {
        super(conditions, stack, functions);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.add(createStack(context));
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

    public static ReplaceItemLootModifier.Builder builder(ItemStack stack) {
        return new ReplaceItemLootModifier.Builder(stack);
    }

    public static class Builder extends StackBasedLootModifier.Builder<AddItemLootModifier, Builder> {
        protected Builder(ItemStack stack) {
            super(stack);
        }

        @Override
        protected AddItemLootModifier build(LootItemCondition[] conditions, ItemStack stack, List<LootItemFunction> functions) {
            return new AddItemLootModifier(conditions, stack, functions);
        }
    }
}
