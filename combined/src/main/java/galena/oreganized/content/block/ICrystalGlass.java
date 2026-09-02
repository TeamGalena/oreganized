package galena.oreganized.content.block;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.BeaconBeamBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public interface ICrystalGlass extends BeaconBeamBlock {

    int NORMAL = 0, ROTATED = 1, INNER = 2, OUTER = 3, MAX_TYPE = 3;
    IntegerProperty TYPE = IntegerProperty.create("type", 0, MAX_TYPE);

    default int getType(BlockPlaceContext context) {
        var below = context.getLevel().getBlockState(context.getClickedPos().below());
        var above = context.getLevel().getBlockState(context.getClickedPos().above());

        if (below.getBlock() == this && above.getBlock() == this) {
            if (below.getValue(TYPE) == NORMAL && above.getValue(TYPE) == ROTATED) return OUTER;
            if (above.getValue(TYPE) == NORMAL && below.getValue(TYPE) == ROTATED) return INNER;
        }

        return context.getPlayer() != null && context.getPlayer().isShiftKeyDown() ? ROTATED : NORMAL;
    }

}
