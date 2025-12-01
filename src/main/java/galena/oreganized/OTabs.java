package galena.oreganized;

import static galena.oreganized.ModCompat.FARMERS_DELIGHT_ID;
import static galena.oreganized.ModCompat.NETHERS_DELIGHT_ID;
import static galena.oreganized.ModCompat.SHIELD_EXPANSION_ID;

import galena.oreganized.index.DyeColors;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.OItems;

import java.util.Map;
import java.util.function.Supplier;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.infernalstudios.shieldexp.init.ItemsInit;
import vectorwing.farmersdelight.common.registry.ModItems;

@EventBusSubscriber(modid = Oreganized.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class OTabs {

    private static final ResourceLocation FD_TAB = ResourceLocation.fromNamespaceAndPath(FARMERS_DELIGHT_ID, FARMERS_DELIGHT_ID);
    private static final ResourceLocation ND_TAB = ResourceLocation.fromNamespaceAndPath(NETHERS_DELIGHT_ID, "main");

    @SubscribeEvent
    public static void buildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tab = event.getTabKey();

        if (tab == CreativeModeTabs.NATURAL_BLOCKS || tab == CreativeModeTabs.BUILDING_BLOCKS) {
            putBefore(event, Items.DEEPSLATE, OBlocks.GLANCE);
            putAfter(event, OBlocks.GLANCE.get(), OBlocks.SPOTTED_GLANCE);
        }

        if (tab == CreativeModeTabs.BUILDING_BLOCKS) {
            putAfter(event, Blocks.REINFORCED_DEEPSLATE, OBlocks.GLANCE_STAIRS);
            putAfter(event, OBlocks.GLANCE_STAIRS.get(), OBlocks.GLANCE_SLAB);
            putAfter(event, OBlocks.GLANCE_SLAB.get(), OBlocks.GLANCE_WALL);
            putAfter(event, OBlocks.GLANCE_WALL.get(), OBlocks.CHISELED_GLANCE);
            putAfter(event, OBlocks.CHISELED_GLANCE.get(), OBlocks.POLISHED_GLANCE);
            putAfter(event, OBlocks.POLISHED_GLANCE.get(), OBlocks.POLISHED_GLANCE_STAIRS);
            putAfter(event, OBlocks.POLISHED_GLANCE_STAIRS.get(), OBlocks.POLISHED_GLANCE_SLAB);
            putAfter(event, OBlocks.POLISHED_GLANCE_SLAB.get(), OBlocks.GLANCE_BRICKS);
            putAfter(event, OBlocks.GLANCE_BRICKS.get(), OBlocks.GLANCE_BRICK_STAIRS);
            putAfter(event, OBlocks.GLANCE_BRICK_STAIRS.get(), OBlocks.GLANCE_BRICK_SLAB);
            putAfter(event, OBlocks.GLANCE_BRICK_SLAB.get(), OBlocks.GLANCE_BRICK_WALL);
            putAfter(event, OBlocks.GLANCE_BRICK_WALL.get(), OBlocks.WAXED_SPOTTED_GLANCE);

            putBefore(event, Items.GOLD_BLOCK, OBlocks.SILVER_BLOCKS.array());
            putBefore(event, Items.GOLD_BLOCK, OBlocks.CUT_SILVERS.array());
            putBefore(event, Items.GOLD_BLOCK, OBlocks.SILVER_PILLARS.array());
            putBefore(event, Items.GOLD_BLOCK, OBlocks.SILVER_BARS.array());
            putBefore(event, Items.NETHERITE_BLOCK, OBlocks.ELECTRUM_BLOCK);
            putAfter(event, Items.WAXED_OXIDIZED_CUT_COPPER_SLAB, OBlocks.LEAD_BLOCK);
            putAfter(event, OBlocks.LEAD_BLOCK.get(), OBlocks.CUT_LEAD);
            putAfter(event, OBlocks.CUT_LEAD.get(), OBlocks.LEAD_BRICKS);
            putAfter(event, OBlocks.LEAD_BRICKS.get(), OBlocks.LEAD_PILLAR);
            putAfter(event, OBlocks.LEAD_PILLAR.get(), OBlocks.LEAD_BARS, OBlocks.LEAD_DOOR, OBlocks.LEAD_TRAPDOOR);

            putAfter(event, Blocks.CUT_RED_SANDSTONE_SLAB, OBlocks.GROOVED_ICE);
            putAfter(event, OBlocks.GROOVED_ICE.get(), OBlocks.GROOVED_PACKED_ICE);
            putAfter(event, OBlocks.GROOVED_PACKED_ICE.get(), OBlocks.GROOVED_BLUE_ICE);
        }

        if (tab == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            putBefore(event, Blocks.BARREL, OBlocks.LEAD_BOLT_CRATE);
            putBefore(event, Items.REDSTONE_LAMP, OBlocks.SILVER_BULBS.array());
        }

        if (tab == CreativeModeTabs.COLORED_BLOCKS) {
            OBlocks.CRYSTAL_GLASS.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey(DyeColors.comparator()))
                    .forEach(entry -> putBefore(event, Items.GLASS_PANE, entry.getValue()));

            OBlocks.CRYSTAL_GLASS_PANES.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey(DyeColors.comparator()))
                    .forEach(entry -> putAfter(event, Items.SHULKER_BOX, entry.getValue()));

            OBlocks.WAXED_CONCRETE_POWDER.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey(DyeColors.comparator()))
                    .forEach(entry -> putBefore(event, Items.WHITE_GLAZED_TERRACOTTA, entry.getValue()));
        }

        if (tab == CreativeModeTabs.NATURAL_BLOCKS) {
            putAfter(event, Items.DEEPSLATE_COPPER_ORE, OBlocks.LEAD_ORE);
            putAfter(event, OBlocks.LEAD_ORE.get(), OBlocks.DEEPSLATE_LEAD_ORE);
            putAfter(event, Items.DEEPSLATE_GOLD_ORE, OBlocks.SILVER_ORE);
            putAfter(event, OBlocks.SILVER_ORE.get(), OBlocks.DEEPSLATE_SILVER_ORE);
            putAfter(event, Items.RAW_COPPER_BLOCK, OBlocks.RAW_LEAD_BLOCK);
            putAfter(event, Items.RAW_GOLD_BLOCK, OBlocks.RAW_SILVER_BLOCK);
        }

        if (tab == CreativeModeTabs.REDSTONE_BLOCKS || tab == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            putBefore(event, Items.NOTE_BLOCK, OBlocks.GARGOYLE);
            putAfter(event, Blocks.REDSTONE_LAMP, OBlocks.LEAD_BULB);
        }

        if (tab == CreativeModeTabs.REDSTONE_BLOCKS || tab == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            putAfter(event, Items.TNT_MINECART, OItems.SHRAPNEL_BOMB_MINECART);
        }

        if (tab == CreativeModeTabs.REDSTONE_BLOCKS || tab == CreativeModeTabs.COMBAT) {
            putAfter(event, Items.TNT, OBlocks.SHRAPNEL_BOMB);
        }

        if (tab == CreativeModeTabs.REDSTONE_BLOCKS) {
            putAfter(event, Blocks.IRON_DOOR, OBlocks.LEAD_DOOR);
            putAfter(event, Blocks.IRON_TRAPDOOR, OBlocks.LEAD_TRAPDOOR);
        }

        if (tab == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            putBefore(event, Items.NETHERITE_SHOVEL, OItems.ELECTRUM_SHOVEL);
            putAfter(event, OItems.ELECTRUM_SHOVEL.get(), OItems.ELECTRUM_PICKAXE);
            putAfter(event, OItems.ELECTRUM_PICKAXE.get(), OItems.ELECTRUM_AXE);
            putAfter(event, OItems.ELECTRUM_AXE.get(), OItems.ELECTRUM_HOE);
            putBefore(event, Items.MILK_BUCKET, OItems.MOLTEN_LEAD_BUCKET);
            putBefore(event, Items.SPYGLASS, OItems.SILVER_MIRROR);
            putBefore(event, OItems.SILVER_MIRROR.get(), OItems.THERMOMETER);
            putBefore(event, OItems.THERMOMETER.get(), OItems.SPEEDOMETER);
            putBefore(event, OItems.SPEEDOMETER.get(), OItems.UNKNOWN_DEVICE);
            putAfter(event, Items.FLINT_AND_STEEL, OItems.FLINT_AND_PEWTER);
            putAfter(event, Items.SHEARS, OItems.SCRIBE);
            putBefore(event, Items.MUSIC_DISC_5, OItems.MUSIC_DISC_STRUCTURE);
        }

        if (tab == CreativeModeTabs.COMBAT) {
            putBefore(event, Items.DIAMOND_SWORD, OItems.ELECTRUM_SWORD);
            putAfter(event, Items.DIAMOND_AXE, OItems.ELECTRUM_AXE);
            putBefore(event, Items.NETHERITE_HELMET, OItems.ELECTRUM_HELMET);
            putAfter(event, OItems.ELECTRUM_HELMET.get(), OItems.ELECTRUM_CHESTPLATE);
            putAfter(event, OItems.ELECTRUM_CHESTPLATE.get(), OItems.ELECTRUM_LEGGINGS);
            putAfter(event, OItems.ELECTRUM_LEGGINGS.get(), OItems.ELECTRUM_BOOTS);
            putBefore(event, Items.ARROW, OItems.LEAD_BOLT);
        }

        if (tab == CreativeModeTabs.INGREDIENTS) {
            putAfter(event, Items.RAW_COPPER, OItems.RAW_LEAD);
            putAfter(event, Items.RAW_GOLD, OItems.RAW_SILVER);

            putAfter(event, Items.IRON_NUGGET, OItems.LEAD_NUGGET);
            putAfter(event, Items.GOLD_NUGGET, OItems.SILVER_NUGGET);
            putAfter(event, OItems.SILVER_NUGGET.get(), OItems.ELECTRUM_NUGGET);

            putBefore(event, Items.IRON_INGOT, OItems.NETHERITE_NUGGET);
            putAfter(event, Items.COPPER_INGOT, OItems.LEAD_INGOT);
            putAfter(event, Items.GOLD_INGOT, OItems.SILVER_INGOT);
            putBefore(event, Items.NETHERITE_SCRAP, OItems.ELECTRUM_INGOT);

            putBefore(event, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, OItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE);
        }


        if (tab == CreativeModeTabs.NATURAL_BLOCKS) {
            putAfter(event, Items.LILY_OF_THE_VALLEY, OBlocks.PURPLE_DATURA);
            putAfter(event, OBlocks.PURPLE_DATURA.get(), OBlocks.WHITE_DATURA);
        }

        if (ModList.get().isLoaded(FARMERS_DELIGHT_ID) && tab.location().equals(FD_TAB)) {
            putAfter(event, ModItems.NETHERITE_KNIFE.get(), OItems.ELECTRUM_KNIFE);
        }
         if (ModList.get().isLoaded(SHIELD_EXPANSION_ID) && tab == CreativeModeTabs.COMBAT) {
             putAfter(event, ItemsInit.NETHERITE_SHIELD.get(), OItems.ELECTRUM_SHIELD);
         }

        // TODO enable again after mod is ported to 1.21.1
        // if (ModList.get().isLoaded(NETHERS_DELIGHT_ID) && (tab.location().equals(ND_TAB) || tab == CreativeModeTabs.TOOLS_AND_UTILITIES)) {
        //     putAfter(event, NDItems.NETHERITE_MACHETE.get(), OItems.ELECTRUM_MACHETE);
        // }
    }

    @SafeVarargs
    private static void putAfter(BuildCreativeModeTabContentsEvent event, ItemLike after, Supplier<? extends ItemLike>... supplier) {
        for (int i = supplier.length - 1; i >= 0; i--) {
            ItemLike key = supplier[i].get();
            event.insertAfter(new ItemStack(after), new ItemStack(key), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    @SafeVarargs
    private static void putBefore(BuildCreativeModeTabContentsEvent event, ItemLike before, Supplier<? extends ItemLike>... supplier) {
        for (Supplier<? extends ItemLike> supplier1 : supplier) {
            ItemLike key = supplier1.get();
            event.insertBefore(new ItemStack(before), new ItemStack(key), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

}
