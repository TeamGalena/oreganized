package galena.oreganized.index;

import galena.oreganized.Oreganized;
import galena.oreganized.argentum.index.ArgentumBlocks;
import galena.oreganized.argentum.index.TarnishedBlocks;
import galena.oreganized.content.block.BulbBlock;
import galena.oreganized.content.block.CrystalGlassBlock;
import galena.oreganized.content.block.CrystalGlassPaneBlock;
import galena.oreganized.content.block.GargoyleBlock;
import galena.oreganized.content.block.IMeltableBlock;
import galena.oreganized.content.block.LeadBarsBlock;
import galena.oreganized.content.block.LeadDoorBlock;
import galena.oreganized.content.block.LeadTrapdoorBlock;
import galena.oreganized.content.block.MeltableBlock;
import galena.oreganized.content.block.MeltablePillarBlock;
import galena.oreganized.content.block.MoltenLeadBlock;
import galena.oreganized.content.block.MoltenLeadCauldronBlock;
import galena.oreganized.content.block.ShrapnelBombBlock;
import galena.oreganized.content.block.SpottedGlanceBlock;
import galena.oreganized.content.block.SturdyButtonBlock;
import galena.oreganized.content.block.SturdyLeverBlock;
import galena.oreganized.electrum.index.ElectrumBlocks;
import galena.oreganized.register.BlockRegistryHelper;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;

public class OBlocks {
    private static final BlockRegistryHelper HELPER = Oreganized.REGISTRY_HELPER.getBlockSubHelper();

    private static Properties glanceProperties() {
        return Properties.of().explosionResistance(6).strength(1.5F).mapColor(MapColor.CLAY);
    }

    private static final MapColor[] LEAD_MAP_COLORS = {
            MapColor.TERRACOTTA_LIGHT_BLUE,
            MapColor.TERRACOTTA_MAGENTA,
            MapColor.TERRACOTTA_PINK
    };

    private static Properties leadProperties() {
        return Properties.of()
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.METAL)
                .lightLevel(IMeltableBlock::getLightLevel)
                .mapColor(OBlocks::leadMapColor)
                .randomTicks();
    }

    private static MapColor leadMapColor(BlockState state) {
        if (!(state.getBlock() instanceof MeltableBlock block)) {
            return LEAD_MAP_COLORS[0];
        }

        int goopyness = Math.min(2, block.getGoopyness(state));
        return LEAD_MAP_COLORS[goopyness];
    }

    private static Properties leadDecoProperties() {
        return leadProperties().noOcclusion().isValidSpawn(Blocks::never);
    }

    // Glance
    public static final DeferredBlock<Block> GLANCE = register("glance",
            () -> new Block(glanceProperties()));
    public static final DeferredBlock<Block> POLISHED_GLANCE = register("polished_glance",
            () -> new Block(glanceProperties()));
    public static final DeferredBlock<Block> GLANCE_BRICKS = register("glance_bricks",
            () -> new Block(glanceProperties()));
    public static final DeferredBlock<Block> CHISELED_GLANCE = register("chiseled_glance",
            () -> new Block(glanceProperties()));
    public static final DeferredBlock<SlabBlock> GLANCE_SLAB = register("glance_slab",
            () -> new SlabBlock(glanceProperties()));
    public static final DeferredBlock<SlabBlock> POLISHED_GLANCE_SLAB = register("polished_glance_slab",
            () -> new SlabBlock(glanceProperties()));
    public static final DeferredBlock<SlabBlock> GLANCE_BRICK_SLAB = register("glance_brick_slab",
            () -> new SlabBlock(glanceProperties()));
    public static final DeferredBlock<StairBlock> GLANCE_STAIRS = register("glance_stairs",
            () -> new StairBlock(GLANCE.get().defaultBlockState(), glanceProperties()));
    public static final DeferredBlock<StairBlock> POLISHED_GLANCE_STAIRS = register("polished_glance_stairs",
            () -> new StairBlock(POLISHED_GLANCE.get().defaultBlockState(), glanceProperties()));
    public static final DeferredBlock<StairBlock> GLANCE_BRICK_STAIRS = register("glance_brick_stairs",
            () -> new StairBlock(GLANCE_BRICKS.get().defaultBlockState(), glanceProperties()));
    public static final DeferredBlock<WallBlock> GLANCE_WALL = register("glance_wall",
            () -> new WallBlock(glanceProperties()));
    public static final DeferredBlock<WallBlock> GLANCE_BRICK_WALL = register("glance_brick_wall",
            () -> new WallBlock(glanceProperties()));

    public static final DeferredBlock<Block> SPOTTED_GLANCE = register("spotted_glance",
            () -> new SpottedGlanceBlock(glanceProperties()));
    public static final DeferredBlock<Block> WAXED_SPOTTED_GLANCE = register("waxed_spotted_glance",
            () -> new Block(glanceProperties()));

    // Ores
    public static final DeferredBlock<Block> LEAD_ORE = register("lead_ore",
            () -> new DropExperienceBlock(ConstantInt.of(0), Properties.ofFullCopy(Blocks.GOLD_ORE).strength(3.0F, 3.0F)));
    public static final DeferredBlock<Block> DEEPSLATE_LEAD_ORE = register("deepslate_lead_ore",
            () -> new DropExperienceBlock(ConstantInt.of(0), Properties.ofFullCopy(Blocks.DEEPSLATE_GOLD_ORE)));

    // Storage Blocks
    public static final DeferredBlock<Block> RAW_LEAD_BLOCK = register("raw_lead_block",
            () -> new Block(Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).mapColor(LEAD_MAP_COLORS[0])));

    // Silver
    public static final DeferredBlock<Block> GARGOYLE = register("gargoyle",
            () -> new GargoyleBlock(Properties.ofFullCopy(Blocks.STONE).noOcclusion()));

    public static final DeferredBlock<Block> WHITE_DATURA = register("datura",
            () -> new FlowerBlock(OEffects.STUNNING, 21, Properties.ofFullCopy(Blocks.OXEYE_DAISY)));
    public static final DeferredBlock<Block> PURPLE_DATURA = register("purple_datura",
            () -> new FlowerBlock(OEffects.STUNNING, 21, Properties.ofFullCopy(Blocks.ALLIUM)));
    public static final DeferredBlock<FlowerPotBlock> POTTED_WHITE_DATURA = HELPER.createBlockNoItem("potted_datura",
            () -> new FlowerPotBlock(WHITE_DATURA.get(), Properties.ofFullCopy(Blocks.POTTED_OXEYE_DAISY)));
    public static final DeferredBlock<FlowerPotBlock> POTTED_PURPLE_DATURA = HELPER.createBlockNoItem("potted_purple_datura",
            () -> new FlowerPotBlock(PURPLE_DATURA.get(), Properties.ofFullCopy(Blocks.POTTED_ALLIUM)));

    public static final DeferredBlock<Block> LEAD_BOLT_CRATE = register("lead_bolt_crate",
            () -> new Block(Properties.of().strength(1.5F).sound(SoundType.WOOD)));

    public static final DeferredBlock<MeltableBlock> LEAD_BLOCK = register("lead_block",
            () -> new MeltableBlock(leadProperties()));
    public static final DeferredBlock<MeltableBlock> LEAD_BRICKS = register("lead_bricks",
            () -> new MeltableBlock(leadProperties()));
    public static final DeferredBlock<MeltablePillarBlock> CUT_LEAD = register("cut_lead",
            () -> new MeltablePillarBlock(leadProperties()));
    public static final DeferredBlock<MeltablePillarBlock> LEAD_PILLAR = register("lead_pillar",
            () -> new MeltablePillarBlock(leadProperties()));

    public static final DeferredBlock<BulbBlock> LEAD_BULB = register("lead_bulb",
            () -> new BulbBlock(leadProperties().lightLevel(BulbBlock::getLightLevel)));

    public static final BlockSetType LEAD_BLOCK_SET = BlockSetType.register(new BlockSetType("lead",
            false, false, false,
            BlockSetType.PressurePlateSensitivity.MOBS, SoundType.METAL,
            SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN,
            SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
    );

    public static final DeferredBlock<LeadDoorBlock> LEAD_DOOR = baseRegister("lead_door",
            () -> new LeadDoorBlock(leadDecoProperties()), block -> () -> new DoubleHighBlockItem(block.get(), new Item.Properties()));
    public static final DeferredBlock<LeadTrapdoorBlock> LEAD_TRAPDOOR = register("lead_trapdoor",
            () -> new LeadTrapdoorBlock(leadDecoProperties()));
    public static final DeferredBlock<LeadBarsBlock> LEAD_BARS = register("lead_bars",
            () -> new LeadBarsBlock(leadDecoProperties()));

    public static final DeferredBlock<SturdyLeverBlock> STURDY_LEVER = register("sturdy_lever",
            () -> new SturdyLeverBlock(leadProperties().noCollission().pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<SturdyButtonBlock> STURDY_BUTTON = register("sturdy_button",
            () -> new SturdyButtonBlock(leadProperties().noCollission().pushReaction(PushReaction.DESTROY)));

    // Redstone components
    public static final DeferredBlock<Block> SHRAPNEL_BOMB = register("shrapnel_bomb",
            () -> new ShrapnelBombBlock(Properties.ofFullCopy(Blocks.TNT)));

    public static final Map<DyeColor, DeferredBlock<Block>> CRYSTAL_GLASS = registerColored("crystal_glass", dye -> new CrystalGlassBlock(dye, Properties.ofFullCopy(Blocks.RED_STAINED_GLASS).mapColor(dye)));
    public static final Map<DyeColor, DeferredBlock<Block>> CRYSTAL_GLASS_PANES = registerColored("crystal_glass_pane", dye -> new CrystalGlassPaneBlock(dye, Properties.ofFullCopy(Blocks.RED_STAINED_GLASS_PANE).mapColor(dye)));

    public static final DeferredBlock<Block> GROOVED_ICE = register("grooved_ice",
            () -> new IceBlock(Properties.ofFullCopy(Blocks.ICE).friction(0.6F)));
    public static final DeferredBlock<Block> GROOVED_PACKED_ICE = register("grooved_packed_ice",
            () -> new Block(Properties.ofFullCopy(Blocks.PACKED_ICE).friction(0.6F)));
    public static final DeferredBlock<Block> GROOVED_BLUE_ICE = register("grooved_blue_ice",
            () -> new Block(Properties.ofFullCopy(Blocks.BLUE_ICE).friction(0.6F)));

    public static final Map<DyeColor, DeferredBlock<Block>> WAXED_CONCRETE_POWDER = registerColored(color -> "waxed_" + color + "_concrete_powder", dye -> new Block(Properties.ofFullCopy(Blocks.GREEN_CONCRETE_POWDER).mapColor(dye)));

    // Fluids and Cauldrons
    public static final DeferredBlock<LiquidBlock> MOLTEN_LEAD = HELPER.createBlockNoItem("molten_lead",
            () ->
                    new MoltenLeadBlock(OFluids.MOLTEN_LEAD, Properties.ofFullCopy(Blocks.LAVA).mapColor(MapColor.COLOR_PURPLE)));
    public static final DeferredBlock<Block> MOLTEN_LEAD_CAULDRON = HELPER.createBlockNoItem("molten_lead_cauldron",
            () -> new MoltenLeadCauldronBlock(Properties.ofFullCopy(Blocks.LAVA_CAULDRON).randomTicks()));

    public static <T extends Block> Map<DyeColor, DeferredBlock<T>> registerColored(UnaryOperator<String> nameCreator, Function<DyeColor, ? extends T> factory) {
        return DyeColors.supported().collect(Collectors.toMap(
                it -> it,
                color -> register(nameCreator.apply(color.getSerializedName()),
                        () -> factory.apply(color))
        ));
    }

    public static <T extends Block> Map<DyeColor, DeferredBlock<T>> registerColored(String baseName, Function<DyeColor, ? extends T> factory) {
        return registerColored(color -> color + "_" + baseName, factory);
    }

    public static <T extends Block> DeferredBlock<T> baseRegister(String name, Supplier<? extends T> block, Function<DeferredBlock<T>, Supplier<? extends Item>> item) {
        DeferredBlock<T> register = HELPER.createBlockNoItem(name, block);
        OItems.HELPER.createItem(name, item.apply(register));
        return register;
    }

    public static <T extends Block> DeferredBlock<T> register(String name, Supplier<T> block) {
        return baseRegister(name, block, OBlocks::registerBlockItem);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final DeferredBlock<T> block) {
        return () -> new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties());
    }

    public static void register() {
        // Load this class
    }

    // deprecated

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<Block> SILVER_ORE = ArgentumBlocks.SILVER_ORE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<Block> DEEPSLATE_SILVER_ORE = ArgentumBlocks.DEEPSLATE_SILVER_ORE;

    @Deprecated(forRemoval = true, since = "5.3.0")
    public static final DeferredBlock<Block> RAW_SILVER_BLOCK = ArgentumBlocks.RAW_SILVER_BLOCK;

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

}
