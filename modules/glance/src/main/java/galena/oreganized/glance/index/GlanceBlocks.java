package galena.oreganized.glance.index;

import galena.oreganized.OConstants;
import galena.oreganized.glance.world.block.SpottedGlanceBlock;
import galena.oreganized.index.sets.StoneSet;
import galena.oreganized.register.BlockRegistryHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;

@Mod(OConstants.MOD_ID)
public class GlanceBlocks {

    private static final BlockRegistryHelper BLOCKS = OConstants.REGISTRY_HELPER.getBlockSubHelper();

    private static BlockBehaviour.Properties glanceProperties() {
        return BlockBehaviour.Properties.of().explosionResistance(6).strength(1.5F).mapColor(MapColor.CLAY);
    }

    public static final StoneSet<Block, StairBlock, SlabBlock, WallBlock> GLANCE_SET =
            BLOCKS.createStoneSet("glance", GlanceBlocks::glanceProperties);

    public static final StoneSet<Block, StairBlock, SlabBlock, WallBlock> POLISHED_GLANCE_SET =
            BLOCKS.createStoneSet("polished_glance", GlanceBlocks::glanceProperties);

    public static final DeferredBlock<Block> CHISELED_GLANCE = BLOCKS.createBlock("chiseled_glance",
            () -> new Block(glanceProperties()));

    public static final StoneSet<Block, StairBlock, SlabBlock, WallBlock> GLANCE_BRICKS_SET =
            BLOCKS.createStoneSet("glance_bricks", GlanceBlocks::glanceProperties);

    public static final DeferredBlock<Block> SPOTTED_GLANCE = BLOCKS.createBlock("spotted_glance",
            () -> new SpottedGlanceBlock(glanceProperties()));
    public static final DeferredBlock<Block> WAXED_SPOTTED_GLANCE = BLOCKS.createBlock("waxed_spotted_glance",
            () -> new Block(glanceProperties()));

    @Deprecated(since = "5.4.0")
    public static final DeferredBlock<Block> GLANCE = GLANCE_SET.block();

    @Deprecated(since = "5.4.0")
    public static final DeferredBlock<SlabBlock> GLANCE_SLAB = GLANCE_SET.slab();

    @Deprecated(since = "5.4.0")
    public static final DeferredBlock<StairBlock> GLANCE_STAIRS = GLANCE_SET.stairs();

    @Deprecated(since = "5.4.0")
    public static final DeferredBlock<WallBlock> GLANCE_WALL = GLANCE_SET.wall();

    @Deprecated(since = "5.4.0")
    public static final DeferredBlock<Block> POLISHED_GLANCE = POLISHED_GLANCE_SET.block();

    @Deprecated(since = "5.4.0")
    public static final DeferredBlock<SlabBlock> POLISHED_GLANCE_SLAB = POLISHED_GLANCE_SET.slab();

    @Deprecated(since = "5.4.0")
    public static final DeferredBlock<StairBlock> POLISHED_GLANCE_STAIRS = POLISHED_GLANCE_SET.stairs();

    @Deprecated(since = "5.4.0")
    public static final DeferredBlock<Block> GLANCE_BRICKS = GLANCE_BRICKS_SET.block();

    @Deprecated(since = "5.4.0")
    public static final DeferredBlock<SlabBlock> GLANCE_BRICK_SLAB = GLANCE_BRICKS_SET.slab();

    @Deprecated(since = "5.4.0")
    public static final DeferredBlock<StairBlock> GLANCE_BRICK_STAIRS = GLANCE_BRICKS_SET.stairs();

    @Deprecated(since = "5.4.0")
    public static final DeferredBlock<WallBlock> GLANCE_BRICK_WALL = GLANCE_BRICKS_SET.wall();

}
