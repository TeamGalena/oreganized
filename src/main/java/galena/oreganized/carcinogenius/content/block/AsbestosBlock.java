package galena.oreganized.carcinogenius.content.block;

import galena.oreganized.carcinogenius.content.AsbestosCloud;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class AsbestosBlock extends Block {
    public AsbestosBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        AsbestosCloud.create(pPos, pLevel, 2F, 0.01F, 80);
        super.randomTick(pState, pLevel, pPos, pRandom);
    }

    @Override
    public void fallOn(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, float pFallDistance) {
        AsbestosCloud.create(pPos, pLevel, 4F, 0.02F, 120);
        super.fallOn(pLevel, pState, pPos, pEntity, pFallDistance);
    }
}
