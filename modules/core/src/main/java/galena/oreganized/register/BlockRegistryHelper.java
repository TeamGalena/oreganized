package galena.oreganized.register;

import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import galena.oreganized.index.DyeColors;
import galena.oreganized.index.sets.DyedBlockSet;
import galena.oreganized.index.sets.StoneSet;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class BlockRegistryHelper extends BlockSubRegistryHelper {

    public BlockRegistryHelper(RegistryHelper parent) {
        super(parent);
    }

    public <T extends Block> DyedBlockSet<T> createColored(UnaryOperator<String> nameCreator, Function<DyeColor, ? extends T> factory) {
        return DyedBlockSet.create(
                DyeColors.supported(),
                color -> createBlock(nameCreator.apply(color.getSerializedName()), () -> factory.apply(color))
        );
    }

    public <T extends Block> DyedBlockSet<T> createColored(String baseName, Function<DyeColor, ? extends T> factory) {
        return createColored(color -> color + "_" + baseName, factory);
    }

    public <TBlock extends Block, TStairs extends StairBlock, TSlab extends SlabBlock, TWall extends WallBlock> StoneSet<TBlock, TStairs, TSlab, TWall> createStoneSet(
            String name,
            Supplier<TBlock> blockSupplier,
            Function<TBlock, TStairs> stairsSupplier,
            Function<TBlock, TSlab> slabSupplier,
            Function<TBlock, TWall> wallSupplier
    ) {
        var withoutPlural = removePlural(name);
        var block = createBlock(name, blockSupplier);
        return new StoneSet<>(
                block,
                createBlock(withoutPlural + "_stairs", () -> stairsSupplier.apply(block.value())),
                createBlock(withoutPlural + "_slab", () -> slabSupplier.apply(block.value())),
                createBlock(withoutPlural + "_wall", () -> wallSupplier.apply(block.value()))
        );
    }

    public StoneSet<Block, StairBlock, SlabBlock, WallBlock> createStoneSet(
            String name,
            Supplier<Properties> properties
    ) {
        return createStoneSet(
                name,
                () -> new Block(properties.get()),
                block -> new StairBlock(block.defaultBlockState(), properties.get()),
                $ -> new SlabBlock(properties.get()),
                $ -> new WallBlock(properties.get())
        );
    }

    private static String removePlural(String name) {
        if (name.endsWith("bricks")) return name.substring(0, name.length() - 1);
        return name;
    }

}
