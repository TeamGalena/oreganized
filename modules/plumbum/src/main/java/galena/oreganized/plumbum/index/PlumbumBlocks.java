package galena.oreganized.plumbum.index;

import galena.oreganized.OConstants;
import galena.oreganized.plumbum.world.block.*;
import galena.oreganized.register.BlockRegistryHelper;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;

@Mod(OConstants.MOD_ID)
public class PlumbumBlocks {

    private static final BlockRegistryHelper BLOCKS = OConstants.REGISTRY_HELPER.getBlockSubHelper();

    private static final MapColor[] LEAD_MAP_COLORS = {
            MapColor.TERRACOTTA_LIGHT_BLUE,
            MapColor.TERRACOTTA_MAGENTA,
            MapColor.TERRACOTTA_PINK
    };

    private static BlockBehaviour.Properties leadProperties() {
        return BlockBehaviour.Properties.of()
                .strength(5.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.METAL)
                .lightLevel(IMeltableBlock::getLightLevel)
                .mapColor(PlumbumBlocks::leadMapColor)
                .randomTicks();
    }

    private static MapColor leadMapColor(BlockState state) {
        if (!(state.getBlock() instanceof MeltableBlock block)) {
            return LEAD_MAP_COLORS[0];
        }

        int goopyness = Math.min(2, block.getGoopyness(state));
        return LEAD_MAP_COLORS[goopyness];
    }

    private static BlockBehaviour.Properties leadDecoProperties() {
        return leadProperties().noOcclusion().isValidSpawn(Blocks::never);
    }

    public static final DeferredBlock<Block> LEAD_ORE = BLOCKS.createBlock("lead_ore",
            () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_ORE).strength(3.0F, 3.0F)));
    public static final DeferredBlock<Block> DEEPSLATE_LEAD_ORE = BLOCKS.createBlock("deepslate_lead_ore",
            () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_GOLD_ORE)));

    public static final DeferredBlock<Block> RAW_LEAD_BLOCK = BLOCKS.createBlock("raw_lead_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).mapColor(LEAD_MAP_COLORS[0])));
    public static final DeferredBlock<MeltableBlock> LEAD_BLOCK = BLOCKS.createBlock("lead_block",
            () -> new MeltableBlock(leadProperties()));
    public static final DeferredBlock<MeltableBlock> LEAD_BRICKS = BLOCKS.createBlock("lead_bricks",
            () -> new MeltableBlock(leadProperties()));
    public static final DeferredBlock<MeltablePillarBlock> CUT_LEAD = BLOCKS.createBlock("cut_lead",
            () -> new MeltablePillarBlock(leadProperties()));
    public static final DeferredBlock<MeltablePillarBlock> LEAD_PILLAR = BLOCKS.createBlock("lead_pillar",
            () -> new MeltablePillarBlock(leadProperties()));

    public static final DeferredBlock<LeadBulbBlock> LEAD_BULB = BLOCKS.createBlock("lead_bulb",
            () -> new LeadBulbBlock(leadProperties().lightLevel(LeadBulbBlock::getLightLevel)));

    public static final BlockSetType LEAD_BLOCK_SET = BlockSetType.register(new BlockSetType("lead",
            false, false, false,
            BlockSetType.PressurePlateSensitivity.MOBS, SoundType.METAL,
            SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN,
            SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON)
    );

    public static final DeferredBlock<LeadDoorBlock> LEAD_DOOR = BLOCKS.createBlock("lead_door",
            () -> new LeadDoorBlock(LEAD_BLOCK_SET, leadDecoProperties()));
    public static final DeferredBlock<LeadTrapdoorBlock> LEAD_TRAPDOOR = BLOCKS.createBlock("lead_trapdoor",
            () -> new LeadTrapdoorBlock(LEAD_BLOCK_SET, leadDecoProperties()));
    public static final DeferredBlock<LeadBarsBlock> LEAD_BARS = BLOCKS.createBlock("lead_bars",
            () -> new LeadBarsBlock(leadDecoProperties()));

    public static final DeferredBlock<SturdyLeverBlock> STURDY_LEVER = BLOCKS.createBlock("sturdy_lever",
            () -> new SturdyLeverBlock(leadProperties().noCollission().pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<SturdyButtonBlock> STURDY_BUTTON = BLOCKS.createBlock("sturdy_button",
            () -> new SturdyButtonBlock(LEAD_BLOCK_SET, leadProperties().noCollission().pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<LiquidBlock> MOLTEN_LEAD = BLOCKS.createBlockNoItem("molten_lead",
            () -> new MoltenLeadBlock(PlumbumFluids.MOLTEN_LEAD, BlockBehaviour.Properties.ofFullCopy(Blocks.LAVA).mapColor(MapColor.COLOR_PURPLE)));

    public static final DeferredBlock<Block> MOLTEN_LEAD_CAULDRON = BLOCKS.createBlockNoItem("molten_lead_cauldron",
            () -> new MoltenLeadCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LAVA_CAULDRON).randomTicks()));



}
