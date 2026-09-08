package galena.oreganized.plumbum;

import galena.oreganized.CreativeModeTabBuilder;
import galena.oreganized.plumbum.index.PlumbumBlocks;
import galena.oreganized.plumbum.index.PlumbumItems;
import java.util.List;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@EventBusSubscriber
public class PlumbumTabs {

    @SubscribeEvent
    private static void buildTabs(BuildCreativeModeTabContentsEvent event) {
        var builder = new CreativeModeTabBuilder(event);

        builder.add(CreativeModeTabs.NATURAL_BLOCKS, tab -> {
            tab.putAfter(Blocks.DEEPSLATE_COPPER_ORE, PlumbumBlocks.LEAD_ORE, PlumbumBlocks.DEEPSLATE_LEAD_ORE);
            tab.putAfter(Blocks.RAW_COPPER_BLOCK, PlumbumBlocks.RAW_LEAD_BLOCK);

            tab.putAfter(Blocks.LILY_OF_THE_VALLEY, PlumbumBlocks.PURPLE_DATURA, PlumbumBlocks.WHITE_DATURA);
        });

        builder.add(CreativeModeTabs.BUILDING_BLOCKS, tab -> {
            tab.putAfter(
                    Blocks.WAXED_OXIDIZED_COPPER_BULB,
                    PlumbumBlocks.LEAD_BLOCK,
                    PlumbumBlocks.CUT_LEAD,
                    PlumbumBlocks.LEAD_BRICKS,
                    PlumbumBlocks.LEAD_PILLAR,
                    PlumbumBlocks.LEAD_BARS,
                    PlumbumBlocks.LEAD_DOOR,
                    PlumbumBlocks.LEAD_TRAPDOOR
            );
        });

        builder.add(CreativeModeTabs.REDSTONE_BLOCKS, tab -> {
            tab.putAfter(Blocks.IRON_DOOR, PlumbumBlocks.LEAD_DOOR);
            tab.putAfter(Blocks.IRON_TRAPDOOR, PlumbumBlocks.LEAD_TRAPDOOR);
            tab.putAfter(Blocks.LEVER, PlumbumBlocks.STURDY_LEVER);
            tab.putAfter(Blocks.STONE_BUTTON, PlumbumBlocks.STURDY_BUTTON);
        });

        builder.add(List.of(CreativeModeTabs.REDSTONE_BLOCKS, CreativeModeTabs.FUNCTIONAL_BLOCKS), tab -> {
            tab.putAfter(Blocks.REDSTONE_LAMP, PlumbumBlocks.LEAD_BULB);
        });

        builder.add(CreativeModeTabs.INGREDIENTS, tab -> {
            tab.putAfter(Items.RAW_COPPER, PlumbumItems.RAW_LEAD);
            tab.putAfter(Items.IRON_NUGGET, PlumbumItems.LEAD_NUGGET);
            tab.putAfter(Items.COPPER_INGOT, PlumbumItems.LEAD_INGOT);
        });

        builder.add(CreativeModeTabs.TOOLS_AND_UTILITIES, tab -> {
            tab.putBefore(Items.MILK_BUCKET, PlumbumItems.MOLTEN_LEAD_BUCKET);
            tab.putBefore(Items.SPYGLASS, PlumbumItems.THERMOMETER);
            tab.putBefore(Items.MUSIC_DISC_5, PlumbumItems.MUSIC_DISC_STRUCTURE);
        });
    }

}
