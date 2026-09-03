package galena.oreganized.argentum.index;

import galena.oreganized.OConstants;
import galena.oreganized.argentum.world.block.SilverBulbBlock;
import galena.oreganized.argentum.world.block.SilverDoorBlock;
import galena.oreganized.argentum.world.block.SilverTrapdoorBlock;
import galena.oreganized.register.BlockRegistryHelper;
import java.util.function.IntFunction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ArgentumBlocks {

    private static final BlockRegistryHelper BLOCKS = OConstants.REGISTRY_HELPER.getBlockSubHelper();

    public static final DeferredBlock<Block> SILVER_ORE = BLOCKS.createBlock("silver_ore",
            () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_ORE)));
    public static final DeferredBlock<Block> DEEPSLATE_SILVER_ORE = BLOCKS.createBlock("deepslate_silver_ore",
            () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_GOLD_ORE)));

    public static final DeferredBlock<Block> RAW_SILVER_BLOCK = BLOCKS.createBlock("raw_silver_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).mapColor(MapColor.CLAY)));

    private static BlockBehaviour.Properties silverProperties() {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.METAL);
    }

    public static final TarnishedBlocks<Block> SILVER_BLOCKS = registerTarnished("silver_block", $ -> new Block(silverProperties()));
    public static final TarnishedBlocks<Block> SILVER_BULBS = registerTarnished("silver_bulb", i ->
            new SilverBulbBlock(silverProperties().lightLevel(SilverBulbBlock.lightLevel(i))));
    public static final TarnishedBlocks<Block> CUT_SILVERS = registerTarnished("cut_silver", $ -> new Block(silverProperties()));
    public static final TarnishedBlocks<Block> SILVER_LATTICES = registerTarnished("silver_lattice", $ -> new Block(silverProperties()));

    public static final TarnishedBlocks<IronBarsBlock> SILVER_BARS = registerTarnished("silver_bars",
            $ -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS).noOcclusion()));

    public static final TarnishedBlocks<StairBlock> CUT_SILVER_STAIRS = registerTarnished("cut_silver_stairs",
            i -> new StairBlock(CUT_SILVERS.get(i).get().defaultBlockState(), silverProperties()));

    public static final TarnishedBlocks<SlabBlock> CUT_SILVER_SLABS = registerTarnished("cut_silver_slab",
            $ -> new SlabBlock(silverProperties()));

    public static final TarnishedBlocks<RotatedPillarBlock> SILVER_PILLARS = registerTarnished("silver_pillar", $ -> new RotatedPillarBlock(silverProperties()));
    public static final TarnishedBlocks<Block> CHISELED_SILVER = registerTarnished("chiseled_silver", $ -> new Block(silverProperties()));

    public static final BlockSetType SILVER_BLOCK_SET = BlockSetType.register(new BlockSetType("silver",
            false, true, false,
            BlockSetType.PressurePlateSensitivity.MOBS, SoundType.METAL,
            SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
    );

    public static final TarnishedBlocks<DoorBlock> SILVER_DOORS = registerTarnished("silver_door", $ -> new SilverDoorBlock(SILVER_BLOCK_SET, silverProperties().noOcclusion()));
    public static final TarnishedBlocks<TrapDoorBlock> SILVER_TRAPDOORS = registerTarnished("silver_trapdoor", $ -> new SilverTrapdoorBlock(SILVER_BLOCK_SET, silverProperties().noOcclusion()));

    private static <T extends Block> TarnishedBlocks<T> registerTarnished(String baseName, IntFunction<? extends T> factory) {
        return new TarnishedBlocks<>(
                BLOCKS.createBlock(baseName, () -> factory.apply(0)),
                BLOCKS.createBlock("blemished_" + baseName, () -> factory.apply(1)),
                BLOCKS.createBlock("tarnished_" + baseName, () -> factory.apply(2))
        );
    }

    public static void register() {
        // Load this class
    }

}
