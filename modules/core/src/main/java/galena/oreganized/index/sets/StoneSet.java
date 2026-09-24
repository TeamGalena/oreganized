package galena.oreganized.index.sets;

import java.util.stream.Stream;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public record StoneSet<TBlock extends Block, TStairs extends StairBlock, TSlab extends SlabBlock, TWall extends WallBlock>(
        DeferredBlock<? extends TBlock> block, DeferredBlock<? extends TStairs> stairs,
        DeferredBlock<? extends TSlab> slab, DeferredBlock<? extends TWall> wall) implements IBlockSet {

    @Override
    public Stream<DeferredBlock<?>> stream() {
        return Stream.of(block(), stairs(), slab(), wall());
    }

}
