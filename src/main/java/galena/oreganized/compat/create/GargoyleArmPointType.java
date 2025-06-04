package galena.oreganized.compat.create;

import com.simibubi.create.content.kinetics.mechanicalArm.AllArmInteractionPointTypes;
import com.simibubi.create.content.kinetics.mechanicalArm.ArmInteractionPoint;
import com.simibubi.create.content.kinetics.mechanicalArm.ArmInteractionPointType;
import galena.oreganized.content.entity.GargoyleBlockEntity;
import galena.oreganized.index.OBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class GargoyleArmPointType extends ArmInteractionPointType {

    public GargoyleArmPointType() {
        super();
    }

    @Override
    public boolean canCreatePoint(Level level, BlockPos pos, BlockState state) {
        return state.is(OBlocks.GARGOYLE.get());
    }

    @Override
    public @Nullable ArmInteractionPoint createPoint(Level level, BlockPos pos, BlockState state) {
        return new Point(level, pos, state);
    }

    public class Point extends AllArmInteractionPointTypes.DepositOnlyArmInteractionPoint {

        public Point(Level level, BlockPos pos, BlockState state) {
            super(GargoyleArmPointType.this, level, pos, state);
        }

        @Override
        public ItemStack insert(ItemStack stack, boolean simulate) {
            var blockEntity = level.getBlockEntity(pos);
            if (!(blockEntity instanceof GargoyleBlockEntity gargoyle)) return stack;

            var cloned = stack.copy();
            gargoyle.interact(level, pos, null, cloned, simulate);
            return cloned;
        }
    }
}
