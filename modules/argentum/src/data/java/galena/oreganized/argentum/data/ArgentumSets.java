package galena.oreganized.argentum.data;

import galena.oreganized.argentum.index.ArgentumBlocks;

import java.util.stream.Stream;

public class ArgentumSets {

    public static Stream<galena.oreganized.argentum.index.TarnishedBlocks<?>> tarnishedBlocks() {
        return Stream.of(
                ArgentumBlocks.SILVER_BLOCKS,
                ArgentumBlocks.SILVER_BULBS,
                ArgentumBlocks.SILVER_BARS,
                ArgentumBlocks.SILVER_DOORS,
                ArgentumBlocks.SILVER_TRAPDOORS,
                ArgentumBlocks.SILVER_LATTICES,
                ArgentumBlocks.SILVER_PILLARS,
                ArgentumBlocks.CHISELED_SILVER,
                ArgentumBlocks.CUT_SILVERS,
                ArgentumBlocks.CUT_SILVER_SLABS,
                ArgentumBlocks.CUT_SILVER_STAIRS
        );
    }

}
