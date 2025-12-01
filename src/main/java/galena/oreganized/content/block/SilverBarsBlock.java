package galena.oreganized.content.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;

public class SilverBarsBlock extends IronBarsBlock implements IBrushableSilver {
    public static final MapCodec<IronBarsBlock> CODEC = simpleCodec(SilverBarsBlock::new);

    public SilverBarsBlock(Properties prop) {
        super(prop);
    }

    @Override
    public MapCodec<? extends IronBarsBlock> codec() {
        return CODEC;
    }

}
