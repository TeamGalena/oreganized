package galena.oreganized.glance.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import galena.oreganized.glance.index.GlanceBlocks;
import galena.oreganized.glance.index.GlanceTags;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PointedDripstoneBlock.class)
public class PointedDripstoneBlockMixin {

    @Shadow
    private static boolean canDripThrough(BlockGetter level, BlockPos pos, BlockState state) {
        throw new UnsupportedOperationException("Implemented via mixin");
    }

    @Shadow
    private static Optional<BlockPos> findBlockVertical(LevelAccessor level, BlockPos pos, Direction.AxisDirection axis, BiPredicate<BlockPos, BlockState> positionalStatePredicate, Predicate<BlockState> statePredicate, int maxIterations) {
        throw new UnsupportedOperationException("Implemented via mixin");
    }

    @Inject(
            method = "maybeTransferFluid(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;F)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/PointedDripstoneBlock;findFillableCauldronBelowStalactiteTip(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/material/Fluid;)Lnet/minecraft/core/BlockPos;")
    )
    private static void renewSpottedGlance(BlockState state, ServerLevel level, BlockPos pos, float randChance, CallbackInfo ci, @Local Fluid fluid) {
        if (!fluid.is(GlanceTags.Fluids.RENEWS_SPOTTED_GLANCE)) return;

        findBlockVertical(
                level, pos, Direction.DOWN.getAxisDirection(),
                (p, s) -> canDripThrough(level, p, s),
                s -> s.is(GlanceBlocks.GLANCE.block()),
                11
        ).ifPresent(at -> {
            var from = level.getBlockState(at);
            var to = GlanceBlocks.SPOTTED_GLANCE.value();
            level.setBlockAndUpdate(at, to.withPropertiesOf(from));
        });
    }

}
