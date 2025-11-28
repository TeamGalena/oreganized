package galena.oreganized.content.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class SilverBlock extends Block implements IBrushableSilver {
    public static final MapCodec<SilverBlock> CODEC = simpleCodec(SilverBlock::new);

    public SilverBlock(Properties prop) {
        super(prop);
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    protected boolean triggerEvent(BlockState p_60490_, Level level, BlockPos p_60492_, int index, int p_60494_) {
        if (index == 1) {
            // On tarnish event
            // You can add custom behavior here if needed
            if(level.isClientSide){
                ParticleUtils.spawnParticlesAroundBlock(level, p_60492_, OParticleTypes.SILVER_TARNISHING.get(), 10, 0.5D, 0.5D, 0.5D, 0.1D);
            }
            return true;
        } else if (index == 2) {
            // On untarnish event
            // You can add custom behavior here if needed
            return true;
        }
        return super.triggerEvent(p_60490_, p_60491_, p_60492_, p_60493_, p_60494_);
    }
}
