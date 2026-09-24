package galena.oreganized.gothic.world;

import galena.oreganized.OConstants;
import galena.oreganized.argentum.network.TarnishParticlePacket;
import galena.oreganized.gothic.index.GothicBlocks;
import galena.oreganized.gothic.index.GothicTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.network.PacketDistributor;

// TODO might convert into a recipe type or data map in the future
public class DripstoneConversion {

    private static void convert(ServerLevel level, BlockPos pos, Block spyre) {
        var mutable = pos.mutable();
        while (true) {
            var from = level.getBlockState(mutable);
            if (!from.is(Blocks.POINTED_DRIPSTONE)) break;

            OConstants.LOGGER.info("direction {}", from.getValue(BlockStateProperties.VERTICAL_DIRECTION).getSerializedName());
            level.setBlock(mutable, spyre.withPropertiesOf(from), 27);
            mutable.setY(mutable.getY() - 1);
        }

        var bottom = mutable.getY() + 1;
        for (int y = pos.getY(); y >= bottom; y--) {
            mutable.setY(y);
            PacketDistributor.sendToPlayersInDimension(level, new TarnishParticlePacket(mutable.immutable(), true));
            level.updateNeighborsAt(mutable, spyre);
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
