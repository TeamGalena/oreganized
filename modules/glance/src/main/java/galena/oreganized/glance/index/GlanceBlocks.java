package galena.oreganized.glance.index;

import galena.oreganized.OConstants;
import galena.oreganized.glance.world.block.SpottedGlanceBlock;
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

    public static final DeferredBlock<Block> GLANCE = BLOCKS.createBlock("glance",
            () -> new Block(glanceProperties()));
    public static final DeferredBlock<Block> POLISHED_GLANCE = BLOCKS.createBlock("polished_glance",
            () -> new Block(glanceProperties()));
    public static final DeferredBlock<Block> GLANCE_BRICKS = BLOCKS.createBlock("glance_bricks",
            () -> new Block(glanceProperties()));
    public static final DeferredBlock<Block> CHISELED_GLANCE = BLOCKS.createBlock("chiseled_glance",
            () -> new Block(glanceProperties()));
    public static final DeferredBlock<SlabBlock> GLANCE_SLAB = BLOCKS.createBlock("glance_slab",
            () -> new SlabBlock(glanceProperties()));
    public static final DeferredBlock<SlabBlock> POLISHED_GLANCE_SLAB = BLOCKS.createBlock("polished_glance_slab",
            () -> new SlabBlock(glanceProperties()));
    public static final DeferredBlock<SlabBlock> GLANCE_BRICK_SLAB = BLOCKS.createBlock("glance_brick_slab",
            () -> new SlabBlock(glanceProperties()));
    public static final DeferredBlock<StairBlock> GLANCE_STAIRS = BLOCKS.createBlock("glance_stairs",
            () -> new StairBlock(GLANCE.get().defaultBlockState(), glanceProperties()));
    public static final DeferredBlock<StairBlock> POLISHED_GLANCE_STAIRS = BLOCKS.createBlock("polished_glance_stairs",
            () -> new StairBlock(POLISHED_GLANCE.get().defaultBlockState(), glanceProperties()));
    public static final DeferredBlock<StairBlock> GLANCE_BRICK_STAIRS = BLOCKS.createBlock("glance_brick_stairs",
            () -> new StairBlock(GLANCE_BRICKS.get().defaultBlockState(), glanceProperties()));
    public static final DeferredBlock<WallBlock> GLANCE_WALL = BLOCKS.createBlock("glance_wall",
            () -> new WallBlock(glanceProperties()));
    public static final DeferredBlock<WallBlock> GLANCE_BRICK_WALL = BLOCKS.createBlock("glance_brick_wall",
            () -> new WallBlock(glanceProperties()));

    public static final DeferredBlock<Block> SPOTTED_GLANCE = BLOCKS.createBlock("spotted_glance",
            () -> new SpottedGlanceBlock(glanceProperties()));
    public static final DeferredBlock<Block> WAXED_SPOTTED_GLANCE = BLOCKS.createBlock("waxed_spotted_glance",
            () -> new Block(glanceProperties()));

}
