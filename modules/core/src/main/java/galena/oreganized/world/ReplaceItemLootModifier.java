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

public class ReplaceItemLootModifier extends StackBasedLootModifier {

    public static final MapCodec<ReplaceItemLootModifier> CODEC = RecordCodecBuilder.mapCodec(builder ->
            stackCodecStart(builder).apply(builder, ReplaceItemLootModifier::new)
    );

    public ReplaceItemLootModifier(LootItemCondition[] conditions, ItemStack stack, List<LootItemFunction> functions) {
        super(conditions, stack, functions);
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        return new ObjectArrayList<>(List.of(createStack(context)));
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

    public static Builder builder(ItemStack stack) {
        return new Builder(stack);
    }

    public static class Builder extends StackBasedLootModifier.Builder<ReplaceItemLootModifier, Builder> {
        protected Builder(ItemStack stack) {
            super(stack);
        }

        @Override
        protected ReplaceItemLootModifier build(LootItemCondition[] conditions, ItemStack stack, List<LootItemFunction> functions) {
            return new ReplaceItemLootModifier(conditions, stack, functions);
        }
    }

}
