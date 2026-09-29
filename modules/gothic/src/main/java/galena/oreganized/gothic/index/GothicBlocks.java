package galena.oreganized.gothic.index;

import galena.oreganized.OConstants;
import galena.oreganized.gothic.world.block.*;
import galena.oreganized.index.sets.DyedBlockSet;
import galena.oreganized.index.sets.StoneSet;
import galena.oreganized.register.BlockRegistryHelper;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;

@Mod(OConstants.MOD_ID)
public class GothicBlocks {

    private static final BlockRegistryHelper BLOCKS = OConstants.REGISTRY_HELPER.getBlockSubHelper();

    public static final DeferredBlock<Block> GARGOYLE = BLOCKS.createBlock("gargoyle",
            () -> new GargoyleBlock(Properties.ofFullCopy(Blocks.STONE).noOcclusion()));

    public static final DyedBlockSet<Block> CRYSTAL_GLASS = BLOCKS.createColored("crystal_glass", dye ->
            new CrystalGlassBlock(dye, Properties.ofFullCopy(Blocks.RED_STAINED_GLASS).mapColor(dye)));
    public static final DyedBlockSet<Block> CRYSTAL_GLASS_PANES = BLOCKS.createColored("crystal_glass_pane", dye ->
            new CrystalGlassPaneBlock(dye, Properties.ofFullCopy(Blocks.RED_STAINED_GLASS_PANE).mapColor(dye)));

    private static final MapColor DARK_GRIMSTONE_COLOR = MapColor.COLOR_BLACK;
    private static final MapColor PALE_GRIMSTONE_COLOR = MapColor.TERRACOTTA_WHITE;

    public static final StoneSet<Block, StairBlock, SlabBlock, WallBlock> DARK_GRIMSTONE_BRICKS =
            BLOCKS.createStoneSet("dark_grimstone_bricks", () -> Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(DARK_GRIMSTONE_COLOR));

    public static final StoneSet<Block, StairBlock, SlabBlock, WallBlock> PALE_GRIMSTONE_BRICKS =
            BLOCKS.createStoneSet("pale_grimstone_bricks", () -> Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(PALE_GRIMSTONE_COLOR));

    public static final StoneSet<Block, StairBlock, SlabBlock, WallBlock> POLISHED_DARK_GRIMSTONE =
            BLOCKS.createStoneSet("polished_dark_grimstone", () -> Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(PALE_GRIMSTONE_COLOR));

    public static final StoneSet<Block, StairBlock, SlabBlock, WallBlock> POLISHED_PALE_GRIMSTONE =
            BLOCKS.createStoneSet("polished_pale_grimstone", () -> Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(PALE_GRIMSTONE_COLOR));

    public static final DeferredBlock<RotatedPillarBlock> DARK_GRIMSTONE_PILLAR = BLOCKS.createBlock("dark_grimstone_pillar", () ->
            new RotatedPillarBlock(Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(DARK_GRIMSTONE_COLOR)));

    public static final DeferredBlock<RotatedPillarBlock> PALE_GRIMSTONE_PILLAR = BLOCKS.createBlock("pale_grimstone_pillar", () ->
            new RotatedPillarBlock(Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(PALE_GRIMSTONE_COLOR)));

    public static final DeferredBlock<Block> CHISELED_DARK_GRIMSTONE = BLOCKS.createBlock("chiseled_dark_grimstone", () ->
            new Block(Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(DARK_GRIMSTONE_COLOR)));

    public static final DeferredBlock<Block> CHISELED_PALE_GRIMSTONE = BLOCKS.createBlock("chiseled_pale_grimstone", () ->
            new Block(Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(PALE_GRIMSTONE_COLOR)));

    public static final DeferredBlock<Block> DARK_GRIMSTONE_SPYRE_BLOCK = BLOCKS.createBlock("dark_grimstone_spyre_block", () ->
            new Block(Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(DARK_GRIMSTONE_COLOR)));

    public static final DeferredBlock<Block> PALE_GRIMSTONE_SPYRE_BLOCK = BLOCKS.createBlock("pale_grimstone_spyre_block", () ->
            new Block(Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(PALE_GRIMSTONE_COLOR)));

    public static final DeferredBlock<SpyreBlock> DARK_GRIMSTONE_SPYRE = BLOCKS.createBlock("dark_grimstone_spyre", () ->
            new SpyreBlock(Properties.ofFullCopy(Blocks.DRIPSTONE_BLOCK).mapColor(DARK_GRIMSTONE_COLOR)));

    public static final DeferredBlock<SpyreBlock> PALE_GRIMSTONE_SPYRE = BLOCKS.createBlock("pale_grimstone_spyre", () ->
            new SpyreBlock(Properties.ofFullCopy(Blocks.DRIPSTONE_BLOCK).mapColor(PALE_GRIMSTONE_COLOR)));

    public static final DeferredBlock<SpyreFenceBlock> DARK_GRIMSTONE_SPYRE_FENCE = BLOCKS.createBlock("dark_grimstone_spyre_fence", () ->
            new ConnectedSpyreFenceBlock(Properties.ofFullCopy(Blocks.IRON_BARS).mapColor(DARK_GRIMSTONE_COLOR)));

    public static final DeferredBlock<SpyreFenceBlock> PALE_GRIMSTONE_SPYRE_FENCE = BLOCKS.createBlock("pale_grimstone_spyre_fence", () ->
            new SpyreFenceBlock(Properties.ofFullCopy(Blocks.IRON_BARS).mapColor(PALE_GRIMSTONE_COLOR)));

}
