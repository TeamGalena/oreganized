package galena.oreganized.electrum.index;

import galena.oreganized.index.CoreTags;
import galena.oreganized.register.TagHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ElectrumTags {

    public static final class Items {
        private static final TagHelper<Item> HELPER = new TagHelper<>(Registries.ITEM);

        public static final TagKey<Item> INGOTS_ELECTRUM = HELPER.commonTag("ingots/electrum");
        public static final TagKey<Item> NUGGETS_ELECTRUM = HELPER.commonTag("nuggets/electrum");

        public static final TagKey<Item> STORAGE_BLOCKS_ELECTRUM = HELPER.copy(CoreTags.Blocks.STORAGE_BLOCKS_ELECTRUM);

        public static final TagKey<Item> HAS_KINETIC_DAMAGE = HELPER.modTag("has_kinetic_damage");

    }

    public static final class Blocks {
        private static final TagHelper<Block> HELPER = new TagHelper<>(Registries.BLOCK);

        public static final TagKey<Block> STORAGE_BLOCKS_ELECTRUM = HELPER.commonTag("storage_blocks/electrum");

        public static final TagKey<Block> INCORRECT_FOR_ELECTRUM_TOOL = HELPER.modTag("incorrect_for_electrum_tool");
    }

}
