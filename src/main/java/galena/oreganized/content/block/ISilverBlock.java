package galena.oreganized.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public interface ISilverBlock extends IBrushable {

    default boolean brush(BlockState state, BlockPos pos, Level level, ItemStack stack, Player livingEntity, HumanoidArm arm, BlockHitResult hit, Vec3 vec3) {
        var previous = TarnishManager.previous(state);
        if (previous != null) {
            //TODO: Sound
            level.setBlockAndUpdate(pos, previous);
            return true;
        }
        return false;
    }
}
