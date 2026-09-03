package galena.oreganized.index;

import galena.oreganized.argentum.index.ArgentumBlocks;
import galena.oreganized.argentum.index.TarnishedBlocks;
import galena.oreganized.armament.index.ArmamentBlocks;
import galena.oreganized.electrum.index.ElectrumBlocks;
import galena.oreganized.glance.index.GlanceBlocks;
import galena.oreganized.gothic.index.GothicBlocks;
import galena.oreganized.plumbum.index.PlumbumBlocks;
import galena.oreganized.plumbum.world.block.*;
import galena.oreganized.waxed.index.WaxedBlocks;
import java.util.Map;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.registries.DeferredBlock;

public class OBlocks {

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<Block> GLANCE = GlanceBlocks.GLANCE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<Block> POLISHED_GLANCE = GlanceBlocks.POLISHED_GLANCE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<Block> GLANCE_BRICKS = GlanceBlocks.GLANCE_BRICKS;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<Block> CHISELED_GLANCE = GlanceBlocks.CHISELED_GLANCE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<SlabBlock> GLANCE_SLAB = GlanceBlocks.GLANCE_SLAB;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<SlabBlock> POLISHED_GLANCE_SLAB = GlanceBlocks.POLISHED_GLANCE_SLAB;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<SlabBlock> GLANCE_BRICK_SLAB = GlanceBlocks.GLANCE_SLAB;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<StairBlock> GLANCE_STAIRS = GlanceBlocks.GLANCE_STAIRS;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<StairBlock> POLISHED_GLANCE_STAIRS = GlanceBlocks.POLISHED_GLANCE_STAIRS;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<StairBlock> GLANCE_BRICK_STAIRS = GlanceBlocks.GLANCE_BRICK_STAIRS;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<WallBlock> GLANCE_WALL = GlanceBlocks.GLANCE_WALL;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<WallBlock> GLANCE_BRICK_WALL = GlanceBlocks.GLANCE_BRICK_WALL;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<Block> SPOTTED_GLANCE = GlanceBlocks.SPOTTED_GLANCE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<Block> WAXED_SPOTTED_GLANCE = GlanceBlocks.WAXED_SPOTTED_GLANCE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<Block> RAW_LEAD_BLOCK = PlumbumBlocks.RAW_LEAD_BLOCK;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<Block> LEAD_ORE = PlumbumBlocks.LEAD_ORE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<Block> DEEPSLATE_LEAD_ORE = PlumbumBlocks.DEEPSLATE_LEAD_ORE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<MeltableBlock> LEAD_BLOCK = PlumbumBlocks.LEAD_BLOCK;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<MeltableBlock> LEAD_BRICKS = PlumbumBlocks.LEAD_BRICKS;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<MeltablePillarBlock> CUT_LEAD = PlumbumBlocks.CUT_LEAD;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<MeltablePillarBlock> LEAD_PILLAR = PlumbumBlocks.LEAD_PILLAR;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<LeadBulbBlock> LEAD_BULB = PlumbumBlocks.LEAD_BULB;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final BlockSetType LEAD_BLOCK_SET = PlumbumBlocks.LEAD_BLOCK_SET;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<LeadDoorBlock> LEAD_DOOR = PlumbumBlocks.LEAD_DOOR;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<LeadTrapdoorBlock> LEAD_TRAPDOOR = PlumbumBlocks.LEAD_TRAPDOOR;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<LeadBarsBlock> LEAD_BARS = PlumbumBlocks.LEAD_BARS;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<SturdyLeverBlock> STURDY_LEVER = PlumbumBlocks.STURDY_LEVER;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<SturdyButtonBlock> STURDY_BUTTON = PlumbumBlocks.STURDY_BUTTON;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<LiquidBlock> MOLTEN_LEAD = PlumbumBlocks.MOLTEN_LEAD;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<Block> MOLTEN_LEAD_CAULDRON = PlumbumBlocks.MOLTEN_LEAD_CAULDRON;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<Block> SILVER_ORE = ArgentumBlocks.SILVER_ORE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<Block> DEEPSLATE_SILVER_ORE = ArgentumBlocks.DEEPSLATE_SILVER_ORE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<Block> RAW_SILVER_BLOCK = ArgentumBlocks.RAW_SILVER_BLOCK;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final BlockSetType SILVER_BLOCK_SET = ArgentumBlocks.SILVER_BLOCK_SET;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final TarnishedBlocks<Block> SILVER_BLOCKS = ArgentumBlocks.SILVER_BLOCKS;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final TarnishedBlocks<Block> SILVER_BULBS = ArgentumBlocks.SILVER_BULBS;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final TarnishedBlocks<Block> CUT_SILVERS = ArgentumBlocks.CUT_SILVERS;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final TarnishedBlocks<Block> SILVER_LATTICES = ArgentumBlocks.SILVER_LATTICES;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final TarnishedBlocks<IronBarsBlock> SILVER_BARS = ArgentumBlocks.SILVER_BARS;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final TarnishedBlocks<StairBlock> CUT_SILVER_STAIRS = ArgentumBlocks.CUT_SILVER_STAIRS;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final TarnishedBlocks<SlabBlock> CUT_SILVER_SLABS = ArgentumBlocks.CUT_SILVER_SLABS;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final TarnishedBlocks<RotatedPillarBlock> SILVER_PILLARS = ArgentumBlocks.SILVER_PILLARS;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final TarnishedBlocks<Block> CHISELED_SILVER = ArgentumBlocks.CHISELED_SILVER;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final TarnishedBlocks<DoorBlock> SILVER_DOORS = ArgentumBlocks.SILVER_DOORS;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final TarnishedBlocks<TrapDoorBlock> SILVER_TRAPDOORS = ArgentumBlocks.SILVER_TRAPDOORS;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<Block> ELECTRUM_BLOCK = ElectrumBlocks.ELECTRUM_BLOCK;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<Block> GARGOYLE = GothicBlocks.GARGOYLE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final Map<DyeColor, DeferredBlock<Block>> CRYSTAL_GLASS = GothicBlocks.CRYSTAL_GLASS;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final Map<DyeColor, DeferredBlock<Block>> CRYSTAL_GLASS_PANES = GothicBlocks.CRYSTAL_GLASS_PANES;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<Block> LEAD_BOLT_CRATE = ArmamentBlocks.LEAD_BOLT_CRATE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<Block> SHRAPNEL_BOMB = ArmamentBlocks.SHRAPNEL_BOMB;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<Block> WHITE_DATURA = PlumbumBlocks.WHITE_DATURA;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<Block> PURPLE_DATURA = PlumbumBlocks.PURPLE_DATURA;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<FlowerPotBlock> POTTED_WHITE_DATURA = PlumbumBlocks.POTTED_WHITE_DATURA;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<FlowerPotBlock> POTTED_PURPLE_DATURA = PlumbumBlocks.POTTED_PURPLE_DATURA;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final Map<DyeColor, DeferredBlock<Block>> WAXED_CONCRETE_POWDER = WaxedBlocks.WAXED_CONCRETE_POWDER;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<Block> GROOVED_ICE = ArgentumBlocks.GROOVED_ICE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<Block> GROOVED_PACKED_ICE = ArgentumBlocks.GROOVED_PACKED_ICE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<Block> GROOVED_BLUE_ICE = ArgentumBlocks.GROOVED_BLUE_ICE;


}
