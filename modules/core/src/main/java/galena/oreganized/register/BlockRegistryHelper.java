package galena.oreganized.register;

import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import galena.oreganized.index.DyeColors;
import java.util.Map;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public class BlockRegistryHelper extends BlockSubRegistryHelper {

    public BlockRegistryHelper(RegistryHelper parent) {
        super(parent);
    }

    public <T extends Block> Map<DyeColor, DeferredBlock<T>> createColored(UnaryOperator<String> nameCreator, Function<DyeColor, ? extends T> factory) {
        return DyeColors.supported().collect(Collectors.toMap(
                it -> it,
                color -> createBlock(nameCreator.apply(color.getSerializedName()),
                        () -> factory.apply(color))
        ));
    }

    public <T extends Block> Map<DyeColor, DeferredBlock<T>> createColored(String baseName, Function<DyeColor, ? extends T> factory) {
        return createColored(color -> color + "_" + baseName, factory);
    }

}
