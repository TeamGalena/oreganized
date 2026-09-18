package galena.oreganized.gothic.world.block;

import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;

public class SpyreFenceBlock extends IronBarsBlock {

    public SpyreFenceBlock(Properties properties) {
        super(properties);
    }

    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return false;
    }

}
