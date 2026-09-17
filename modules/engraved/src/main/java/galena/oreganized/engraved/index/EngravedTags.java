package galena.oreganized.engraved.index;

import galena.oreganized.register.TagHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class EngravedTags {

    public static final class Items {
        private static final TagHelper<Item> HELPER = new TagHelper<>(Registries.ITEM);

        public static final TagKey<Item> TOOLS_BUSH_HAMMER = HELPER.commonTag("tools/bush_hammer");
    }

    public static final class Blocks {
        private static final TagHelper<Block> HELPER = new TagHelper<>(Registries.BLOCK);

        public static final TagKey<Block> MINEABLE_WITH_BUSH_HAMMER = HELPER.modTag("mineable/bush_hammer");
    }

}
