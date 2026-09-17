package galena.oreganized.index;

import galena.oreganized.register.TagHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class CoreTags {

    public static final class Items {
        private static final TagHelper<Item> HELPER = new TagHelper<>(Registries.ITEM);

        public static final TagKey<Item> RAW_MATERIALS_SILVER = HELPER.commonTag("raw_materials/silver");
        public static final TagKey<Item> RAW_MATERIALS_LEAD = HELPER.commonTag("raw_materials/lead");

        public static final TagKey<Item> INGOTS_SILVER = HELPER.commonTag("ingots/silver");
        public static final TagKey<Item> INGOTS_LEAD = HELPER.commonTag("ingots/lead");
        public static final TagKey<Item> INGOTS_ELECTRUM = HELPER.commonTag("ingots/electrum");

        public static final TagKey<Item> NUGGETS_SILVER = HELPER.commonTag("nuggets/silver");
        public static final TagKey<Item> NUGGETS_LEAD = HELPER.commonTag("nuggets/lead");
        public static final TagKey<Item> NUGGETS_ELECTRUM = HELPER.commonTag("nuggets/electrum");
        public static final TagKey<Item> NUGGETS_NETHERITE = HELPER.commonTag("nuggets/netherite");

        public static final TagKey<Item> ORES_SILVER = HELPER.copy(Blocks.ORES_SILVER);
        public static final TagKey<Item> ORES_LEAD = HELPER.copy(Blocks.ORES_LEAD);

        public static final TagKey<Item> STORAGE_BLOCKS_SILVER = HELPER.copy(Blocks.STORAGE_BLOCKS_SILVER);
        public static final TagKey<Item> STORAGE_BLOCKS_LEAD = HELPER.copy(Blocks.STORAGE_BLOCKS_LEAD);
        public static final TagKey<Item> STORAGE_BLOCKS_ELECTRUM = HELPER.copy(Blocks.STORAGE_BLOCKS_ELECTRUM);

        public static final TagKey<Item> STORAGE_BLOCKS_RAW_SILVER = HELPER.copy(Blocks.STORAGE_BLOCKS_RAW_SILVER);
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_LEAD = HELPER.copy(Blocks.STORAGE_BLOCKS_RAW_LEAD);

        public static final TagKey<Item> HIDES_HAT_LAYER = HELPER.modTag("hides/hat_layer");
    }

    public static final class Blocks {
        private static final TagHelper<Block> HELPER = new TagHelper<>(Registries.BLOCK);

        public static final TagKey<Block> ORES_SILVER = HELPER.commonTag("ores/silver");
        public static final TagKey<Block> ORES_LEAD = HELPER.commonTag("ores/lead");

        public static final TagKey<Block> STORAGE_BLOCKS_SILVER = HELPER.commonTag("storage_blocks/silver");
        public static final TagKey<Block> STORAGE_BLOCKS_LEAD = HELPER.commonTag("storage_blocks/lead");
        public static final TagKey<Block> STORAGE_BLOCKS_ELECTRUM = HELPER.commonTag("storage_blocks/electrum");

        public static final TagKey<Block> STORAGE_BLOCKS_RAW_SILVER = HELPER.commonTag("storage_blocks/raw_silver");
        public static final TagKey<Block> STORAGE_BLOCKS_RAW_LEAD = HELPER.commonTag("storage_blocks/raw_lead");

        public static final TagKey<Block> AMETHYST_CLUSTERS = HELPER.commonTag("clusters/amethyst");
        public static final TagKey<Block> QUARTZITE_CLUSTERS = HELPER.commonTag("clusters/quartzite");
    }

}
