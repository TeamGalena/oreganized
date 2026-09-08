package galena.oreganized.gothic;

import galena.oreganized.CreativeModeTabBuilder;
import galena.oreganized.gothic.index.GothicBlocks;
import galena.oreganized.index.DyeColors;
import java.util.List;
import java.util.Map;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@EventBusSubscriber
public class GothicTabs {

    @SubscribeEvent
    private static void buildTabs(BuildCreativeModeTabContentsEvent event) {
        var builder = new CreativeModeTabBuilder(event);

        builder.add(CreativeModeTabs.COLORED_BLOCKS, tab -> {
            tab.putAfter(
                    Blocks.GLASS_PANE,
                    GothicBlocks.CRYSTAL_GLASS.entrySet()
                            .stream()
                            .sorted(Map.Entry.comparingByKey(DyeColors.comparator()))
                            .map(Map.Entry::getValue)
            );

            tab.putAfter(
                    Blocks.SHULKER_BOX,
                    GothicBlocks.CRYSTAL_GLASS_PANES.entrySet()
                            .stream()
                            .sorted(Map.Entry.comparingByKey(DyeColors.comparator()))
                            .map(Map.Entry::getValue)
            );
        });

        builder.add(List.of(CreativeModeTabs.REDSTONE_BLOCKS, CreativeModeTabs.FUNCTIONAL_BLOCKS), tab -> {
            tab.putBefore(Blocks.NOTE_BLOCK, GothicBlocks.GARGOYLE);
        });
    }

}
