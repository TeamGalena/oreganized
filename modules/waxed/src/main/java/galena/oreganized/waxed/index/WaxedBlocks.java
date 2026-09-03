package galena.oreganized.waxed.index;

import galena.oreganized.OConstants;
import galena.oreganized.register.BlockRegistryHelper;
import java.util.Map;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;

@Mod(OConstants.MOD_ID)
public class WaxedBlocks {

    private static final BlockRegistryHelper BLOCKS = OConstants.REGISTRY_HELPER.getBlockSubHelper();

    public static final Map<DyeColor, DeferredBlock<Block>> WAXED_CONCRETE_POWDER = BLOCKS.createColored(color -> "waxed_" + color + "_concrete_powder", dye ->
            new Block(Properties.ofFullCopy(Blocks.GREEN_CONCRETE_POWDER).mapColor(dye)));


}
