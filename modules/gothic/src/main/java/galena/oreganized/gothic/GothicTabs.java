package galena.oreganized.gothic;

import galena.oreganized.CreativeModeTabBuilder;
import galena.oreganized.gothic.index.GothicBlocks;

import java.util.List;

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
            tab.putAfter(Blocks.GLASS_PANE, GothicBlocks.CRYSTAL_GLASS.stream());
            tab.putAfter(Blocks.SHULKER_BOX, GothicBlocks.CRYSTAL_GLASS_PANES.stream());
        });

        builder.add(List.of(CreativeModeTabs.REDSTONE_BLOCKS, CreativeModeTabs.FUNCTIONAL_BLOCKS), tab -> {
            tab.putBefore(Blocks.NOTE_BLOCK, GothicBlocks.GARGOYLE);
        });

        builder.add(CreativeModeTabs.BUILDING_BLOCKS, tab -> {
            tab.putAfter(Blocks.IRON_BARS, GothicBlocks.DARK_GRIMSTONE_SPYRE_FENCE, GothicBlocks.PALE_GRIMSTONE_SPYRE_FENCE);
            tab.putAfter(
                    Blocks.STONE_BRICK_SLAB,
                    GothicBlocks.DARK_GRIMSTONE_BRICKS.slab(),
                    GothicBlocks.POLISHED_DARK_GRIMSTONE.slab(),

                    GothicBlocks.PALE_GRIMSTONE_BRICKS.slab(),
                    GothicBlocks.POLISHED_PALE_GRIMSTONE.slab()
            );
            tab.putAfter(
                    Blocks.STONE_BRICK_STAIRS,
                    GothicBlocks.DARK_GRIMSTONE_BRICKS.stairs(),
                    GothicBlocks.POLISHED_DARK_GRIMSTONE.stairs(),

                    GothicBlocks.PALE_GRIMSTONE_BRICKS.stairs(),
                    GothicBlocks.POLISHED_PALE_GRIMSTONE.stairs()
            );
            tab.putAfter(
                    Blocks.STONE_BRICK_WALL,
                    GothicBlocks.DARK_GRIMSTONE_BRICKS.wall(),
                    GothicBlocks.POLISHED_DARK_GRIMSTONE.wall(),

                    GothicBlocks.PALE_GRIMSTONE_BRICKS.wall(),
                    GothicBlocks.POLISHED_PALE_GRIMSTONE.wall()
            );
            tab.putAfter(
                    Blocks.STONE_BRICKS,
                    GothicBlocks.DARK_GRIMSTONE_BRICKS.block(),
                    GothicBlocks.POLISHED_DARK_GRIMSTONE.block(),
                    GothicBlocks.DARK_GRIMSTONE_PILLAR,
                    GothicBlocks.CHISELED_DARK_GRIMSTONE,
                    GothicBlocks.DARK_GRIMSTONE_SPYRE_BLOCK,
                    GothicBlocks.DARK_GRIMSTONE_SPYRE,

                    GothicBlocks.PALE_GRIMSTONE_BRICKS.block(),
                    GothicBlocks.POLISHED_PALE_GRIMSTONE.block(),
                    GothicBlocks.PALE_GRIMSTONE_PILLAR,
                    GothicBlocks.CHISELED_PALE_GRIMSTONE,
                    GothicBlocks.PALE_GRIMSTONE_SPYRE_BLOCK,
                    GothicBlocks.PALE_GRIMSTONE_SPYRE
            );
        });
    }

}
