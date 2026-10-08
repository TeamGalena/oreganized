package galena.oreganized.antiques;

import galena.oreganized.CreativeModeTabBuilder;
import galena.oreganized.antiques.index.AntiquesBlocks;
import galena.oreganized.antiques.index.AntiquesItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
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

        builder.add(CreativeModeTabs.TOOLS_AND_UTILITIES, tab -> {
            tab.putBefore(Items.MUSIC_DISC_5, AntiquesItems.MUSIC_DISC_RADIO);
        });
    }

}
