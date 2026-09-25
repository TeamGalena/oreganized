package galena.oreganized.index.sets;

import java.util.stream.Stream;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public record StoneSet<TBlock extends Block, TStairs extends StairBlock, TSlab extends SlabBlock, TWall extends WallBlock>(
        DeferredBlock<TBlock> block,
        DeferredBlock<TStairs> stairs,
        DeferredBlock<TSlab> slab,
        DeferredBlock<TWall> wall
) implements IHolderSet<Block, Block, DeferredBlock<? extends Block>> {

    @Override
    public Stream<DeferredBlock<? extends Block>> stream() {
        return Stream.of(block(), stairs(), slab(), wall());
    }

}
