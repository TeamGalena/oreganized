package galena.oreganized.content.block;

import galena.oreganized.network.packet.TarnishParticlePacket;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;

public interface IBrushableSilver extends IBrushable {

    default boolean brush(BlockState state, BlockPos pos, Level level, ItemStack stack, Player livingEntity, HumanoidArm arm, BlockHitResult hit, Vec3 vec3) {
        BlockState previous = TarnishManager.previous(state);
        if (previous != null) {
            level.setBlockAndUpdate(pos, previous);
            if (level instanceof ServerLevel sl)
                PacketDistributor.sendToPlayersInDimension(sl, new TarnishParticlePacket(pos, false));
            return true;
        }
        return false;
    }
}
