package galena.oreganized.data;

import galena.oreganized.index.OBlocks;
import galena.oreganized.index.TarnishedBlocks;

import java.util.stream.Stream;

public class OCollections {

    public static Stream<TarnishedBlocks<?>> tarnishedBlocks() {
        return Stream.of(
                OBlocks.SILVER_BLOCKS,
                OBlocks.SILVER_BULBS,
                OBlocks.SILVER_BARS,
                OBlocks.SILVER_DOORS,
                OBlocks.SILVER_TRAPDOORS,
                OBlocks.SILVER_LATTICES,
                OBlocks.SILVER_PILLARS,
                OBlocks.CHISELED_SILVER,
                OBlocks.CUT_SILVERS,
                OBlocks.CUT_SILVER_SLABS,
                OBlocks.CUT_SILVER_STAIRS
        );
    }

}
