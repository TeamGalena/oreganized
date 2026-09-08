package galena.oreganized.waxed;

import galena.oreganized.CreativeModeTabBuilder;
import galena.oreganized.index.DyeColors;
import galena.oreganized.waxed.index.WaxedBlocks;
import java.util.Map;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@EventBusSubscriber
public class WaxedTabs {

    @SubscribeEvent
    private static void buildTabs(BuildCreativeModeTabContentsEvent event) {
        var builder = new CreativeModeTabBuilder(event);

        builder.add(CreativeModeTabs.COLORED_BLOCKS, tab -> {
            tab.putBefore(
                    Blocks.WHITE_GLAZED_TERRACOTTA,
                    WaxedBlocks.WAXED_CONCRETE_POWDER.entrySet()
                            .stream()
                            .sorted(Map.Entry.comparingByKey(DyeColors.comparator()))
                            .map(Map.Entry::getValue)
            );
        });
    }

}
