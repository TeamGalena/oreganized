package galena.oreganized.argentum;

import galena.oreganized.CreativeModeTabBuilder;
import galena.oreganized.argentum.index.ArgentumBlocks;
import galena.oreganized.argentum.index.ArgentumItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@EventBusSubscriber
public class ArgentumTabs {

    @SubscribeEvent
    private static void buildTabs(BuildCreativeModeTabContentsEvent event) {
        var builder = new CreativeModeTabBuilder(event);

        builder.add(CreativeModeTabs.NATURAL_BLOCKS, tab -> {
            tab.putAfter(Blocks.DEEPSLATE_GOLD_ORE, ArgentumBlocks.SILVER_ORE, ArgentumBlocks.DEEPSLATE_SILVER_ORE);
            tab.putAfter(Blocks.RAW_GOLD_BLOCK, ArgentumBlocks.RAW_SILVER_BLOCK);
        });

        builder.add(CreativeModeTabs.BUILDING_BLOCKS, tab -> {
            tab.putBefore(Blocks.GOLD_BLOCK, ArgentumBlocks.SILVER_BLOCKS.stream());
            tab.putBefore(Blocks.GOLD_BLOCK, ArgentumBlocks.CHISELED_SILVER.stream());
            tab.putBefore(Blocks.GOLD_BLOCK, ArgentumBlocks.SILVER_LATTICES.stream());
            tab.putBefore(Blocks.GOLD_BLOCK, ArgentumBlocks.SILVER_PILLARS.stream());
            tab.putBefore(Blocks.GOLD_BLOCK, ArgentumBlocks.CUT_SILVERS.stream());
            tab.putBefore(Blocks.GOLD_BLOCK, ArgentumBlocks.CUT_SILVER_STAIRS.stream());
            tab.putBefore(Blocks.GOLD_BLOCK, ArgentumBlocks.CUT_SILVER_SLABS.stream());
            tab.putBefore(Blocks.GOLD_BLOCK, ArgentumBlocks.SILVER_BARS.stream());
            tab.putBefore(Blocks.GOLD_BLOCK, ArgentumBlocks.SILVER_DOORS.stream());
            tab.putBefore(Blocks.GOLD_BLOCK, ArgentumBlocks.SILVER_TRAPDOORS.stream());

            tab.putAfter(Blocks.CUT_RED_SANDSTONE_SLAB, ArgentumBlocks.GROOVED_ICE, ArgentumBlocks.GROOVED_PACKED_ICE, ArgentumBlocks.GROOVED_BLUE_ICE);
        });

        builder.add(CreativeModeTabs.FUNCTIONAL_BLOCKS, tab -> {
            tab.putBefore(Blocks.REDSTONE_LAMP, ArgentumBlocks.SILVER_BULBS.stream());
        });

        builder.add(CreativeModeTabs.TOOLS_AND_UTILITIES, tab -> {
            tab.putBefore(Items.DIAMOND_SHOVEL, ArgentumItems.SILVER_SHOVEL, ArgentumItems.SILVER_PICKAXE, ArgentumItems.SILVER_AXE, ArgentumItems.SILVER_HOE);
            tab.putBefore(Items.SPYGLASS, ArgentumItems.SILVER_MIRROR);
            tab.putAfter(Items.SHEARS, ArgentumItems.SCRIBE);
        });

        builder.add(CreativeModeTabs.COMBAT, tab -> {
            tab.putAfter(Items.GOLDEN_SWORD, ArgentumItems.SILVER_SWORD);
            tab.putAfter(Items.GOLDEN_AXE, ArgentumItems.SILVER_AXE);
            tab.putBefore(Items.GOLDEN_HELMET, ArgentumItems.silverArmor());
        });

        builder.add(CreativeModeTabs.INGREDIENTS, tab -> {
            tab.putAfter(Items.RAW_GOLD, ArgentumItems.RAW_SILVER);
            tab.putAfter(Items.GOLD_NUGGET, ArgentumItems.SILVER_NUGGET);
            tab.putAfter(Items.GOLD_INGOT, ArgentumItems.SILVER_INGOT);
        });
    }

}
