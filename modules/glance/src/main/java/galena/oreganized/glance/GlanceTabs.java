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
                    GlanceBlocks.GLANCE.block(),
                    GlanceBlocks.GLANCE.stairs(),
                    GlanceBlocks.GLANCE.slab(),
                    GlanceBlocks.GLANCE.wall(),
                    GlanceBlocks.CHISELED_GLANCE,
                    GlanceBlocks.POLISHED_GLANCE.block(),
                    GlanceBlocks.POLISHED_GLANCE.stairs(),
                    GlanceBlocks.POLISHED_GLANCE.slab(),
                    GlanceBlocks.POLISHED_GLANCE.wall(),
                    GlanceBlocks.GLANCE_BRICKS.block(),
                    GlanceBlocks.GLANCE_BRICKS.stairs(),
                    GlanceBlocks.GLANCE_BRICKS.slab(),
                    GlanceBlocks.GLANCE_BRICKS.wall(),
                    GlanceBlocks.WAXED_SPOTTED_GLANCE
            );
        });

        builder.add(CreativeModeTabs.NATURAL_BLOCKS, tab -> {
            tab.putBefore(Blocks.DEEPSLATE, GlanceBlocks.GLANCE.block(), GlanceBlocks.SPOTTED_GLANCE);
        });
    }

}
