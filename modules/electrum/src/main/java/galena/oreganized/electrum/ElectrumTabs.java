package galena.oreganized.electrum;

import static galena.oreganized.ModCompat.FARMERS_DELIGHT;
import static galena.oreganized.ModCompat.NETHERS_DELIGHT;
import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

import galena.oreganized.CreativeModeTabBuilder;
import galena.oreganized.ModCompat;
import galena.oreganized.electrum.index.ElectrumBlocks;
import galena.oreganized.electrum.index.ElectrumItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.infernalstudios.shieldexp.init.ItemsInit;
import vectorwing.farmersdelight.common.registry.ModItems;

@EventBusSubscriber
public class ElectrumTabs {

    private static final ResourceKey<CreativeModeTab> FD_TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, fromNamespaceAndPath(FARMERS_DELIGHT, FARMERS_DELIGHT));
    private static final ResourceKey<CreativeModeTab> ND_TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, fromNamespaceAndPath(NETHERS_DELIGHT, "main"));

    @SubscribeEvent
    private static void buildTabs(BuildCreativeModeTabContentsEvent event) {
        var builder = new CreativeModeTabBuilder(event);

        builder.add(CreativeModeTabs.BUILDING_BLOCKS, tab -> {
            tab.putBefore(Blocks.NETHERITE_BLOCK, ElectrumBlocks.ELECTRUM_BLOCK);
        });

        builder.add(CreativeModeTabs.TOOLS_AND_UTILITIES, tab -> {
            tab.putBefore(Items.NETHERITE_SHOVEL, ElectrumItems.ELECTRUM_SHOVEL, ElectrumItems.ELECTRUM_PICKAXE, ElectrumItems.ELECTRUM_AXE, ElectrumItems.ELECTRUM_HOE);

            tab.putBefore(Items.SPYGLASS, ElectrumItems.SPEEDOMETER);
        });

        builder.add(CreativeModeTabs.COMBAT, tab -> {
            tab.putAfter(Items.DIAMOND_SWORD, ElectrumItems.ELECTRUM_SWORD);
            tab.putAfter(Items.DIAMOND_AXE, ElectrumItems.ELECTRUM_AXE);
            tab.putBefore(Items.NETHERITE_HELMET, ElectrumItems.electrumArmor());

            if (ModCompat.SHIELD_EXPANSION_LOADED) {
                tab.putAfter(ItemsInit.NETHERITE_SHIELD.get(), ElectrumItems.ELECTRUM_SHIELD);
            }
        });

        builder.add(CreativeModeTabs.INGREDIENTS, tab -> {
            tab.putBefore(Items.IRON_INGOT, ElectrumItems.ELECTRUM_NUGGET);
            tab.putBefore(Items.NETHERITE_SCRAP, ElectrumItems.ELECTRUM_INGOT);
            tab.putBefore(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ElectrumItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE);
        });

        if (ModCompat.FARMERS_DELIGHT_LOADED) {
            builder.add(FD_TAB, tab -> {
                tab.putAfter(ModItems.NETHERITE_KNIFE.get(), ElectrumItems.ELECTRUM_KNIFE);
            });
        }

        // TODO enable again after mod is ported to 1.21.1
        // if (ModCompat.NETHERS_DELIGHT_LOADED && (tab.location().equals(ND_TAB) || tab == CreativeModeTabs.TOOLS_AND_UTILITIES)) {
        //     tab.putAfter(NDItems.NETHERITE_MACHETE, OItems.ELECTRUM_MACHETE);
        // }
    }

}
