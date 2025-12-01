package galena.oreganized.content.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.Block;

public class SilverBlock extends Block implements IBrushableSilver {
    public static final MapCodec<SilverBlock> CODEC = simpleCodec(SilverBlock::new);

    public SilverBlock(Properties prop) {
        super(prop);
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

}
