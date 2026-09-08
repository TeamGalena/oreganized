package galena.oreganized.glance;

import galena.oreganized.CreativeModeTabBuilder;
import galena.oreganized.glance.index.GlanceBlocks;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@EventBusSubscriber
public class GlanceTabs {

    @SubscribeEvent
    private static void buildTabs(BuildCreativeModeTabContentsEvent event) {
        var builder = new CreativeModeTabBuilder(event);

        builder.add(CreativeModeTabs.BUILDING_BLOCKS, tab -> {
            tab.putAfter(
                    Blocks.REINFORCED_DEEPSLATE,
                    GlanceBlocks.GLANCE_STAIRS,
                    GlanceBlocks.GLANCE_SLAB,
                    GlanceBlocks.GLANCE_WALL,
                    GlanceBlocks.CHISELED_GLANCE,
                    GlanceBlocks.POLISHED_GLANCE,
                    GlanceBlocks.POLISHED_GLANCE_STAIRS,
                    GlanceBlocks.POLISHED_GLANCE_SLAB,
                    GlanceBlocks.GLANCE_BRICKS,
                    GlanceBlocks.GLANCE_BRICK_STAIRS,
                    GlanceBlocks.GLANCE_BRICK_SLAB,
                    GlanceBlocks.GLANCE_BRICK_WALL,
                    GlanceBlocks.WAXED_SPOTTED_GLANCE
            );
        });

        // TODO modular apparently these were also in the building blocks tab?
        builder.add(CreativeModeTabs.NATURAL_BLOCKS, tab -> {
            tab.putBefore(Blocks.DEEPSLATE, GlanceBlocks.GLANCE, GlanceBlocks.SPOTTED_GLANCE);
        });
    }

}
