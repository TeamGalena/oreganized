package galena.oreganized.mixin.compat;

import galena.oreganized.content.block.LeadOreBlock;
import net.mehvahdjukaar.supplementaries.common.block.blocks.BellowsBlock;
import net.mehvahdjukaar.supplementaries.common.block.tiles.BellowsBlockTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BellowsBlockTile.class, remap = false)
public class BellowBlockTileMixin {

    @Inject(
            method = "pushAir(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;IJFF)V",
            require = 0,
            at = @At("HEAD")
    )
    private void spawnLeadDustClouds(Level level, BlockPos pos, BlockState state, int power, long time, float period, float airIntensity, CallbackInfo ci) {
        if (level.random.nextFloat() > airIntensity) return;
        var facing = state.getValue(BellowsBlock.FACING);
        LeadOreBlock.blowParticles(level, pos, facing, 5);
    }

}
