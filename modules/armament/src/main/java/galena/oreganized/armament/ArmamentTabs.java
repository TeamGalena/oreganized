package galena.oreganized.armament;

import galena.oreganized.CreativeModeTabBuilder;
import galena.oreganized.armament.index.ArmamentBlocks;
import galena.oreganized.armament.index.ArmamentItems;
import java.util.List;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@EventBusSubscriber
public class ArmamentTabs {

    @SubscribeEvent
    private static void buildTabs(BuildCreativeModeTabContentsEvent event) {
        var builder = new CreativeModeTabBuilder(event);

        builder.add(CreativeModeTabs.FUNCTIONAL_BLOCKS, tab -> {
            tab.putBefore(Blocks.BARREL, ArmamentBlocks.LEAD_BOLT_CRATE);
        });

        builder.add(CreativeModeTabs.COMBAT, tab -> {
            tab.putBefore(Items.ARROW, ArmamentItems.LEAD_BOLT);
        });

        builder.add(List.of(CreativeModeTabs.REDSTONE_BLOCKS, CreativeModeTabs.TOOLS_AND_UTILITIES), tab -> {
            tab.putAfter(Items.TNT_MINECART, ArmamentItems.SHRAPNEL_BOMB_MINECART);
        });

        builder.add(List.of(CreativeModeTabs.REDSTONE_BLOCKS, CreativeModeTabs.COMBAT), tab -> {
            tab.putAfter(Blocks.TNT, ArmamentBlocks.SHRAPNEL_BOMB);
        });

        builder.add(CreativeModeTabs.TOOLS_AND_UTILITIES, tab -> {
            tab.putBefore(Items.SPYGLASS, ArmamentItems.FLINT_AND_PEWTER);
        });
    }

}
