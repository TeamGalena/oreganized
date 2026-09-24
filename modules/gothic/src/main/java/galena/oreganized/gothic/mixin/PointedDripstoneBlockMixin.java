package galena.oreganized.gothic.mixin;

import galena.oreganized.gothic.world.DripstoneConversion;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PointedDripstoneBlock.class)
public class PointedDripstoneBlockMixin {

    @Inject(
            method = "randomTick(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    public void convertToSpyre(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (!PointedDripstoneBlock.isStalactiteStartPos(state, level, pos)) return;

        var fluid = PointedDripstoneBlock.getFluidAboveStalactite(level, pos, state);
        if (fluid.isEmpty()) return;

        var catalyst = level.getBlockState(pos.above());
        if (DripstoneConversion.tryConvertDripstone(level, pos, fluid.get().fluid(), catalyst)) {
            ci.cancel();
        }
    }

}
