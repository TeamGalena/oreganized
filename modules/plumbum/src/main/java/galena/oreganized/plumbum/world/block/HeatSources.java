package galena.oreganized.plumbum.world.block;

import galena.oreganized.plumbum.index.PlumbumTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;

public class HeatSources {

    public static boolean meltsLead(BlockState state) {
        if (!state.is(PlumbumTags.Blocks.MELTS_LEAD)) return false;

        if (state.is(BlockTags.CAMPFIRES)) {
            return state.hasProperty(CampfireBlock.LIT) && state.getValue(CampfireBlock.LIT);
        }

        return true;
    }

}
