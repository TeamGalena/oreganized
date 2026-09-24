package galena.oreganized.gothic.world;

import galena.oreganized.OConstants;
import galena.oreganized.argentum.network.TarnishParticlePacket;
import galena.oreganized.gothic.index.GothicBlocks;
import galena.oreganized.gothic.index.GothicTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.network.PacketDistributor;

// TODO might convert into a recipe type or data map in the future
public class DripstoneConversion {

    /**
     * in order for the spyres to not break immediately, they need to be placed in the correct order.
     * It starts top to bottom, until it reaches a connection point where the stalactite meets a stalagmite.
     * If it encounters one, it will stop and continues at the bottom until that connection point.
     * ____________________________
     *   1 ▊   1  ▊   1 ▼
     *   2 ▊   2  ▊
     *   3 ▼    3 ▼
     *   6 ▲
     *   5 ▊
     *   4 ▊
     */
    private static void convert(ServerLevel level, BlockPos pos, Block spyre) {
        var mutable = pos.mutable();
        Integer connection = null;
        while (true) {
            var from = level.getBlockState(mutable);
            if (!from.is(Blocks.POINTED_DRIPSTONE)) break;

            var facing = from.getValue(BlockStateProperties.VERTICAL_DIRECTION);
            if (facing == Direction.UP && connection == null) {
                connection = mutable.getY() + 1;
            }

            mutable.setY(mutable.getY() - 1);
        }

        var bottom = mutable.getY() + 1;
        var lastPointingDown = connection == null ? bottom : connection;

        for (int y = pos.getY(); y >= lastPointingDown; y--) {
            mutable.setY(y);
            var from = level.getBlockState(mutable);
            level.setBlock(mutable, spyre.withPropertiesOf(from), 2);
            PacketDistributor.sendToPlayersInDimension(level, new TarnishParticlePacket(mutable.immutable(), true));
        }

        if (connection != null) {
            for (int y = bottom; y < connection; y++) {
                mutable.setY(y);
                var from = level.getBlockState(mutable);
                level.setBlock(mutable, spyre.withPropertiesOf(from), 2);
                PacketDistributor.sendToPlayersInDimension(level, new TarnishParticlePacket(mutable.immutable(), true));
            }
        }
    }

    public static boolean tryConvertDripstone(ServerLevel level, BlockPos pos, Fluid fluid, BlockState catalyst) {
        if (!fluid.is(GothicTags.Fluids.SPYRE_CONVERSION_FLUID)) return false;

        if (catalyst.is(GothicTags.Blocks.PALE_SPYRE_CATALYST)) {
            convert(level, pos, GothicBlocks.PALE_GRIMSTONE_SPYRE.value());
            return true;
        } else if (catalyst.is(GothicTags.Blocks.DARK_SPYRE_CATALYST)) {
            convert(level, pos, GothicBlocks.DARK_GRIMSTONE_SPYRE.value());
            return true;
        }

        return false;
    }

}
