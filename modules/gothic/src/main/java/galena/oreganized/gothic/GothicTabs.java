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

        builder.add(CreativeModeTabs.BUILDING_BLOCKS, tab -> {
            tab.putAfter(Blocks.IRON_BARS, GothicBlocks.DARK_GRIMSTONE_SPYRE_FENCE, GothicBlocks.PALE_GRIMSTONE_SPYRE_FENCE);
            tab.putAfter(Blocks.STONE_BRICK_SLAB, GothicBlocks.GRIMSTONE_SLAB);
            tab.putAfter(
                    Blocks.STONE_BRICKS,
                    GothicBlocks.GRIMSTONE_BRICKS,
                    GothicBlocks.POLISHED_GRIMSTONE,
                    GothicBlocks.GRIMSTONE_PILLAR,
                    GothicBlocks.CHISELED_GRIMSTONE,
                    GothicBlocks.DARK_GRIMSTONE_SPYRE_BLOCK,
                    GothicBlocks.PALE_GRIMSTONE_SPYRE_BLOCK,
                    GothicBlocks.DARK_GRIMSTONE_SPYRE,
                    GothicBlocks.PALE_GRIMSTONE_SPYRE
            );
        });
    }

}
