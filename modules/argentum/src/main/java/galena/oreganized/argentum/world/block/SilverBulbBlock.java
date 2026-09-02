package galena.oreganized.argentum.world.block;

import java.util.function.ToIntFunction;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.state.BlockState;

public class SilverBulbBlock extends RedstoneLampBlock {

    public static ToIntFunction<BlockState> lightLevel(int tarnishedLevel) {
        return state -> {
            if(!state.getValue(LIT)) return 0;
            return switch (tarnishedLevel) {
                case 0 -> 4;
                case 1 -> 10;
                default -> 15;
            };
        };
    }

    public SilverBulbBlock(Properties prop) {
        super(prop);
    }


}
