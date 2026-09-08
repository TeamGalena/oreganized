package galena.oreganized.gothic.index;

import galena.oreganized.OConstants;
import galena.oreganized.gothic.world.block.CrystalGlassBlock;
import galena.oreganized.gothic.world.block.CrystalGlassPaneBlock;
import galena.oreganized.gothic.world.block.GargoyleBlock;
import galena.oreganized.register.BlockRegistryHelper;
import java.util.Map;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;

@Mod(OConstants.MOD_ID)
public class GothicBlocks {

    private static final BlockRegistryHelper BLOCKS = OConstants.REGISTRY_HELPER.getBlockSubHelper();

    public static final DeferredBlock<Block> GARGOYLE = BLOCKS.createBlock("gargoyle",
            () -> new GargoyleBlock(Properties.ofFullCopy(Blocks.STONE).noOcclusion()));


    public static final Map<DyeColor, DeferredBlock<Block>> CRYSTAL_GLASS = BLOCKS.createColored("crystal_glass", dye ->
            new CrystalGlassBlock(dye, Properties.ofFullCopy(Blocks.RED_STAINED_GLASS).mapColor(dye)));
    public static final Map<DyeColor, DeferredBlock<Block>> CRYSTAL_GLASS_PANES = BLOCKS.createColored("crystal_glass_pane", dye ->
            new CrystalGlassPaneBlock(dye, Properties.ofFullCopy(Blocks.RED_STAINED_GLASS_PANE).mapColor(dye)));


}
