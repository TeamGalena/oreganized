package galena.oreganized.world;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctions;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

public class AddItemLootModifier extends LootModifier {

    public static final MapCodec<AddItemLootModifier> CODEC = RecordCodecBuilder.mapCodec(builder ->
            codecStart(builder).and(
                    builder.group(
                            ItemStack.CODEC.fieldOf("item").forGetter(it -> it.stack),
                            LootItemFunctions.ROOT_CODEC.listOf().optionalFieldOf("functions", List.of()).forGetter(it -> it.functions)
                    )
            ).apply(builder, AddItemLootModifier::new)
    );

    private final ItemStack stack;
    private final List<LootItemFunction> functions;

    public AddItemLootModifier(LootItemCondition[] conditions, ItemStack stack, List<LootItemFunction> functions) {
        super(conditions);
        this.stack = stack;
        this.functions = functions;
    }

    public AddItemLootModifier(LootItemCondition[] conditions, ItemStack stack) {
        this(conditions, stack, List.of());
    }

    private ItemStack createStack(LootContext context) {
        var created = stack.copy();
        functions.forEach(function -> {
            function.apply(created, context);
        });
        return created;
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
}
