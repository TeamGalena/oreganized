package galena.oreganized.antiques;

import galena.oreganized.CreativeModeTabBuilder;
import galena.oreganized.antiques.index.AntiquesBlocks;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@EventBusSubscriber
public class AntiquesTabs {


    @SubscribeEvent
    private static void buildTabs(BuildCreativeModeTabContentsEvent event) {
        var builder = new CreativeModeTabBuilder(event);

        builder.add(CreativeModeTabs.FUNCTIONAL_BLOCKS, tab -> {
            tab.putAfter(Blocks.CHEST, AntiquesBlocks.ANTIQUES_PILE);
        });
    }

}
