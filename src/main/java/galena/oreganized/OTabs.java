package galena.oreganized;

import static galena.oreganized.ModCompat.FARMERS_DELIGHT_ID;
import static galena.oreganized.ModCompat.NETHERS_DELIGHT_ID;
import static galena.oreganized.ModCompat.SHIELD_EXPANSION_ID;

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
import net.minecraftforge.common.util.MutableHashedLinkedMap;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.EventBusSubscriber;
import org.infernalstudios.shieldexp.init.ItemsInit;
import umpaz.nethersdelight.common.registry.NDItems;
import vectorwing.farmersdelight.common.registry.ModItems;

@EventBusSubscriber(modid = Oreganized.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class OTabs {

    private static final ResourceLocation FD_TAB = new ResourceLocation(FARMERS_DELIGHT_ID, FARMERS_DELIGHT_ID);
    private static final ResourceLocation ND_TAB = new ResourceLocation(NETHERS_DELIGHT_ID, "main");

    @SubscribeEvent
    public static void buildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tab = event.getTabKey();
        MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries = event.getEntries();

        if (tab == CreativeModeTabs.NATURAL_BLOCKS || tab == CreativeModeTabs.BUILDING_BLOCKS) {
            putBefore(entries, Items.DEEPSLATE, OBlocks.GLANCE);
            putAfter(entries, OBlocks.GLANCE.get(), OBlocks.SPOTTED_GLANCE);
        }

        if (tab == CreativeModeTabs.BUILDING_BLOCKS) {
            putAfter(entries, Blocks.REINFORCED_DEEPSLATE, OBlocks.GLANCE_STAIRS);
            putAfter(entries, OBlocks.GLANCE_STAIRS.get(), OBlocks.GLANCE_SLAB);
            putAfter(entries, OBlocks.GLANCE_SLAB.get(), OBlocks.GLANCE_WALL);
            putAfter(entries, OBlocks.GLANCE_WALL.get(), OBlocks.CHISELED_GLANCE);
            putAfter(entries, OBlocks.CHISELED_GLANCE.get(), OBlocks.POLISHED_GLANCE);
            putAfter(entries, OBlocks.POLISHED_GLANCE.get(), OBlocks.POLISHED_GLANCE_STAIRS);
            putAfter(entries, OBlocks.POLISHED_GLANCE_STAIRS.get(), OBlocks.POLISHED_GLANCE_SLAB);
            putAfter(entries, OBlocks.POLISHED_GLANCE_SLAB.get(), OBlocks.GLANCE_BRICKS);
            putAfter(entries, OBlocks.GLANCE_BRICKS.get(), OBlocks.GLANCE_BRICK_STAIRS);
            putAfter(entries, OBlocks.GLANCE_BRICK_STAIRS.get(), OBlocks.GLANCE_BRICK_SLAB);
            putAfter(entries, OBlocks.GLANCE_BRICK_SLAB.get(), OBlocks.GLANCE_BRICK_WALL);
            putAfter(entries, OBlocks.GLANCE_BRICK_WALL.get(), OBlocks.WAXED_SPOTTED_GLANCE);

            putBefore(entries, Items.GOLD_BLOCK, OBlocks.SILVER_BLOCK);
            putBefore(entries, Items.NETHERITE_BLOCK, OBlocks.ELECTRUM_BLOCK);
            putAfter(entries, Items.WAXED_OXIDIZED_CUT_COPPER_SLAB, OBlocks.LEAD_BLOCK);
            putAfter(entries, OBlocks.LEAD_BLOCK.get(), OBlocks.CUT_LEAD);
            putAfter(entries, OBlocks.CUT_LEAD.get(), OBlocks.LEAD_BRICKS);
            putAfter(entries, OBlocks.LEAD_PILLAR.get(), OBlocks.CUT_LEAD);
            putAfter(entries, OBlocks.LEAD_BRICKS.get(), OBlocks.LEAD_PILLAR);
            putAfter(entries, Blocks.IRON_BARS, OBlocks.LEAD_BARS);

            putAfter(entries, Blocks.CUT_RED_SANDSTONE_SLAB, OBlocks.GROOVED_ICE);
            putAfter(entries, OBlocks.GROOVED_ICE.get(), OBlocks.GROOVED_PACKED_ICE);
            putAfter(entries, OBlocks.GROOVED_PACKED_ICE.get(), OBlocks.GROOVED_BLUE_ICE);
        }

        if (tab == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            putBefore(entries, Blocks.BARREL, OBlocks.LEAD_BOLT_CRATE);
        }

        if (tab == CreativeModeTabs.COLORED_BLOCKS) {
            OBlocks.CRYSTAL_GLASS.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry -> putBefore(entries, Items.GLASS_PANE, entry.getValue()));

            OBlocks.CRYSTAL_GLASS_PANES.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry -> putBefore(entries, Items.SHULKER_BOX, entry.getValue()));
        }

        if (tab == CreativeModeTabs.NATURAL_BLOCKS) {
            putAfter(entries, Items.DEEPSLATE_COPPER_ORE, OBlocks.LEAD_ORE);
            putAfter(entries, OBlocks.LEAD_ORE.get(), OBlocks.DEEPSLATE_LEAD_ORE);
            putAfter(entries, Items.DEEPSLATE_GOLD_ORE, OBlocks.SILVER_ORE);
            putAfter(entries, OBlocks.SILVER_ORE.get(), OBlocks.DEEPSLATE_SILVER_ORE);
            putAfter(entries, Items.RAW_COPPER_BLOCK, OBlocks.RAW_LEAD_BLOCK);
            putAfter(entries, Items.RAW_GOLD_BLOCK, OBlocks.RAW_SILVER_BLOCK);
        }

        if (tab == CreativeModeTabs.REDSTONE_BLOCKS || tab == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            putBefore(entries, Items.NOTE_BLOCK, OBlocks.GARGOYLE);
            putAfter(entries, Blocks.REDSTONE_LAMP, OBlocks.LEAD_BULB);
        }

        if (tab == CreativeModeTabs.REDSTONE_BLOCKS || tab == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            putAfter(entries, Items.TNT_MINECART, OItems.SHRAPNEL_BOMB_MINECART);
        }

        if (tab == CreativeModeTabs.REDSTONE_BLOCKS || tab == CreativeModeTabs.COMBAT) {
            putAfter(entries, Items.TNT, OBlocks.SHRAPNEL_BOMB);
        }

        if (tab == CreativeModeTabs.REDSTONE_BLOCKS || tab == CreativeModeTabs.BUILDING_BLOCKS) {
            putAfter(entries, Blocks.IRON_DOOR, OBlocks.LEAD_DOOR);
            putAfter(entries, Blocks.IRON_TRAPDOOR, OBlocks.LEAD_TRAPDOOR);
        }

        if (tab == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            putBefore(entries, Items.NETHERITE_SHOVEL, OItems.ELECTRUM_SHOVEL);
            putAfter(entries, OItems.ELECTRUM_SHOVEL.get(), OItems.ELECTRUM_PICKAXE);
            putAfter(entries, OItems.ELECTRUM_PICKAXE.get(), OItems.ELECTRUM_AXE);
            putBefore(entries, Items.MILK_BUCKET, OItems.MOLTEN_LEAD_BUCKET);
            putBefore(entries, Items.SPYGLASS, OItems.SILVER_MIRROR);
            putBefore(entries, OItems.SILVER_MIRROR.get(), OItems.THERMOMETER);
            putBefore(entries, OItems.THERMOMETER.get(), OItems.SPEEDOMETER);
            putBefore(entries, OItems.SPEEDOMETER.get(), OItems.UNKNOWN_DEVICE);
            putAfter(entries, Items.FLINT_AND_STEEL, OItems.FLINT_AND_PEWTER);
            putAfter(entries, Items.SHEARS, OItems.SCRIBE);
            putBefore(entries, Items.MUSIC_DISC_5, OItems.MUSIC_DISC_STRUCTURE);
        }

        if (tab == CreativeModeTabs.COMBAT) {
            putAfter(entries, OItems.ELECTRUM_AXE.get(), OItems.ELECTRUM_HOE);
            putBefore(entries, Items.NETHERITE_SWORD, OItems.ELECTRUM_SWORD);
            putBefore(entries, Items.NETHERITE_HELMET, OItems.ELECTRUM_HELMET);
            putAfter(entries, OItems.ELECTRUM_HELMET.get(), OItems.ELECTRUM_CHESTPLATE);
            putAfter(entries, OItems.ELECTRUM_CHESTPLATE.get(), OItems.ELECTRUM_LEGGINGS);
            putAfter(entries, OItems.ELECTRUM_LEGGINGS.get(), OItems.ELECTRUM_BOOTS);
            putBefore(entries, Items.ARROW, OItems.LEAD_BOLT);
        }

        if (tab == CreativeModeTabs.INGREDIENTS) {
            putAfter(entries, Items.RAW_COPPER, OItems.RAW_LEAD);
            putAfter(entries, Items.RAW_GOLD, OItems.RAW_SILVER);

            putAfter(entries, Items.IRON_NUGGET, OItems.LEAD_NUGGET);
            putAfter(entries, Items.GOLD_NUGGET, OItems.SILVER_NUGGET);
            putAfter(entries, OItems.SILVER_NUGGET.get(), OItems.ELECTRUM_NUGGET);

            putBefore(entries, Items.IRON_INGOT, OItems.NETHERITE_NUGGET);
            putAfter(entries, Items.COPPER_INGOT, OItems.LEAD_INGOT);
            putAfter(entries, Items.GOLD_INGOT, OItems.SILVER_INGOT);
            putBefore(entries, Items.NETHERITE_SCRAP, OItems.ELECTRUM_INGOT);

            putBefore(entries, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, OItems.ELECTRUM_UPGRADE_SMITHING_TEMPLATE);
        }


        if (tab == CreativeModeTabs.NATURAL_BLOCKS) {
            putAfter(entries, Items.LILY_OF_THE_VALLEY, OBlocks.PURPLE_DATURA);
            putAfter(entries, OBlocks.PURPLE_DATURA.get(), OBlocks.WHITE_DATURA);
        }

        if (ModList.get().isLoaded(FARMERS_DELIGHT_ID) && tab.location().equals(FD_TAB)) {
            putAfter(entries, ModItems.NETHERITE_KNIFE.get(), OItems.ELECTRUM_KNIFE);
        }
        if (ModList.get().isLoaded(SHIELD_EXPANSION_ID) && tab == CreativeModeTabs.COMBAT) {
            putAfter(entries, ItemsInit.NETHERITE_SHIELD.get(), OItems.ELECTRUM_SHIELD);
        }
        if (ModList.get().isLoaded(NETHERS_DELIGHT_ID) && (tab.location().equals(ND_TAB) || tab == CreativeModeTabs.TOOLS_AND_UTILITIES)) {
            putAfter(entries, NDItems.NETHERITE_MACHETE.get(), OItems.ELECTRUM_MACHETE);
        }
    }

    private static void putAfter(MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries, ItemLike after, Supplier<? extends ItemLike> supplier) {
        ItemLike key = supplier.get();
        if (!entries.contains(new ItemStack(after))) return;
        entries.putAfter(new ItemStack(after), new ItemStack(key), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    private static void putBefore(MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries, ItemLike before, Supplier<? extends ItemLike> supplier) {
        ItemLike key = supplier.get();
        if (!entries.contains(new ItemStack(before))) return;
        entries.putBefore(new ItemStack(before), new ItemStack(key), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

}
