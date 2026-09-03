package galena.oreganized.gothic.index;

import galena.oreganized.OConstants;
import galena.oreganized.gothic.world.block.GargoyleBlock;
import galena.oreganized.register.BlockRegistryHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

public class GothicBlocks {

    private static final BlockRegistryHelper BLOCKS = OConstants.REGISTRY_HELPER.getBlockSubHelper();

    public static final DeferredBlock<Block> GARGOYLE = BLOCKS.createBlock("gargoyle",
            () -> new GargoyleBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion()));

    public static void register() {
        // Load this class
    }

}
