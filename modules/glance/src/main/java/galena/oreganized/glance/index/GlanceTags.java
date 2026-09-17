package galena.oreganized.glance.index;

import galena.oreganized.register.TagHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class GlanceTags {

    public static final class Items {
        private static final TagHelper<Item> HELPER = new TagHelper<>(Registries.ITEM);

        public static final TagKey<Item> STONE_TYPES_GLANCE = HELPER.copy(Blocks.STONE_TYPES_GLANCE);
    }

    public static final class Blocks {
        private static final TagHelper<Block> HELPER = new TagHelper<>(Registries.BLOCK);

        public static final TagKey<Block> STONE_TYPES_GLANCE = HELPER.modTag("stone_types/glance");
    }

    public static final class Biomes {
        private static final TagHelper<Biome> HELPER = new TagHelper<>(Registries.BIOME);

        public static final TagKey<Biome> HAS_BOULDER = HELPER.modTag("has_structure/boulder");
    }

}
