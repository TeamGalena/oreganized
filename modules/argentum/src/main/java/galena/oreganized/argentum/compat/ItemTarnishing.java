package galena.oreganized.argentum.compat;

import com.mojang.datafixers.util.Pair;
import galena.oreganized.argentum.index.ArgentumDataMapTypes;
import galena.oreganized.argentum.world.Tarnishable;
import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class ItemTarnishing {

    private static Optional<Holder<Block>> asBlock(ItemStack stack) {
        return Optional.of(stack)
                .map(ItemStack::getItem)
                .filter(it -> it instanceof BlockItem)
                .map(it -> ((BlockItem) it).getBlock())
                .map(BuiltInRegistries.BLOCK::wrapAsHolder);
    }

    public static Optional<Pair<Holder<Block>, Tarnishable>> asTarnishable(ItemStack stack) {
        return asBlock(stack).map(it -> {
            var tarnishable = it.getData(ArgentumDataMapTypes.TARNISHABLES);
            if (tarnishable == null) return null;
            return Pair.of(it, tarnishable);
        });
    }

}
