package galena.oreganized.index;

import galena.oreganized.register.TagHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class CoreTags {

    public static final class Items {
        private static final TagHelper<Item> HELPER = new TagHelper<>(Registries.ITEM);

        public static final TagKey<Item> HIDES_HAT_LAYER = HELPER.modTag("hides/hat_layer");
    }

    public static final class Blocks {
        private static final TagHelper<Block> HELPER = new TagHelper<>(Registries.BLOCK);

        public static final TagKey<Block> AMETHYST_CLUSTERS = HELPER.commonTag("clusters/amethyst");
        public static final TagKey<Block> QUARTZITE_CLUSTERS = HELPER.commonTag("clusters/quartzite");
    }

}
