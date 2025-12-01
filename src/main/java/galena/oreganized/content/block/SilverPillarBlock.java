package galena.oreganized.content.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.RotatedPillarBlock;

public class SilverPillarBlock extends RotatedPillarBlock implements IBrushableSilver {
    public static final MapCodec<SilverPillarBlock> CODEC = simpleCodec(SilverPillarBlock::new);

    public SilverPillarBlock(Properties prop) {
        super(prop);
    }

    @Override
    public MapCodec<? extends RotatedPillarBlock> codec() {
        return CODEC;
    }

}
