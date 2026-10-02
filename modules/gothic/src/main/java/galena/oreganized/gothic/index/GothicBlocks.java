package galena.oreganized.gothic.index;

import galena.oreganized.OConstants;
import galena.oreganized.gothic.world.block.*;
import galena.oreganized.index.sets.DyedBlockSet;
import galena.oreganized.index.sets.StoneSet;
import galena.oreganized.register.BlockRegistryHelper;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
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

    private static Properties darkGrimstoneProperties() {
        return Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.COLOR_BLACK).sound(GothicSounds.GRIMSTONE);
    }

    private static Properties paleGrimstoneProperties() {
        return Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.TERRACOTTA_WHITE).sound(GothicSounds.GRIMSTONE);
    }

    private static Properties spyreModifiers(Properties properties) {
        return properties
                .forceSolidOn()
                .noOcclusion()
                .dynamicShape()
                .pushReaction(PushReaction.DESTROY)
                .isRedstoneConductor((s, l, p) -> false)
                .strength(1.5F, 3.0F);
    }

    private static Properties spyreFenceModifiers(Properties properties) {
        return properties
                .noOcclusion()
                .strength(5.0F, 6.0F);
    }

    public static final StoneSet<Block, StairBlock, SlabBlock, WallBlock> DARK_GRIMSTONE_BRICKS =
            BLOCKS.createStoneSet("dark_grimstone_bricks", GothicBlocks::darkGrimstoneProperties);

    public static final StoneSet<Block, StairBlock, SlabBlock, WallBlock> PALE_GRIMSTONE_BRICKS =
            BLOCKS.createStoneSet("pale_grimstone_bricks", GothicBlocks::paleGrimstoneProperties);

    public static final StoneSet<Block, StairBlock, SlabBlock, WallBlock> POLISHED_DARK_GRIMSTONE =
            BLOCKS.createStoneSet("polished_dark_grimstone", GothicBlocks::darkGrimstoneProperties);

    public static final StoneSet<Block, StairBlock, SlabBlock, WallBlock> POLISHED_PALE_GRIMSTONE =
            BLOCKS.createStoneSet("polished_pale_grimstone", GothicBlocks::paleGrimstoneProperties);

    public static final DeferredBlock<RotatedPillarBlock> DARK_GRIMSTONE_PILLAR = BLOCKS.createBlock("dark_grimstone_pillar", () ->
            new RotatedPillarBlock(darkGrimstoneProperties()));

    public static final DeferredBlock<RotatedPillarBlock> PALE_GRIMSTONE_PILLAR = BLOCKS.createBlock("pale_grimstone_pillar", () ->
            new RotatedPillarBlock(paleGrimstoneProperties()));

    public static final DeferredBlock<Block> CHISELED_DARK_GRIMSTONE = BLOCKS.createBlock("chiseled_dark_grimstone", () ->
            new Block(darkGrimstoneProperties()));

    public static final DeferredBlock<Block> CHISELED_PALE_GRIMSTONE = BLOCKS.createBlock("chiseled_pale_grimstone", () ->
            new Block(paleGrimstoneProperties()));

    public static final DeferredBlock<Block> DARK_GRIMSTONE_SPYRE_BLOCK = BLOCKS.createBlock("dark_grimstone_spyre_block", () ->
            new Block(darkGrimstoneProperties()));

    public static final DeferredBlock<Block> PALE_GRIMSTONE_SPYRE_BLOCK = BLOCKS.createBlock("pale_grimstone_spyre_block", () ->
            new Block(paleGrimstoneProperties()));

    public static final DeferredBlock<SpyreBlock> DARK_GRIMSTONE_SPYRE = BLOCKS.createBlock("dark_grimstone_spyre", () ->
            new SpyreBlock(spyreModifiers(darkGrimstoneProperties())));

    public static final DeferredBlock<SpyreBlock> PALE_GRIMSTONE_SPYRE = BLOCKS.createBlock("pale_grimstone_spyre", () ->
            new SpyreBlock(spyreModifiers(paleGrimstoneProperties())));

    public static final DeferredBlock<SpyreFenceBlock> DARK_GRIMSTONE_SPYRE_FENCE = BLOCKS.createBlock("dark_grimstone_spyre_fence", () ->
            new ConnectedSpyreFenceBlock(spyreFenceModifiers(darkGrimstoneProperties())));

    public static final DeferredBlock<SpyreFenceBlock> PALE_GRIMSTONE_SPYRE_FENCE = BLOCKS.createBlock("pale_grimstone_spyre_fence", () ->
            new SpyreFenceBlock(spyreFenceModifiers(paleGrimstoneProperties())));

}
