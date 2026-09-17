package galena.oreganized.gothic.index;

import galena.oreganized.register.TagHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class GothicTags {

    public static final class Items {
        private static final TagHelper<Item> HELPER = new TagHelper<>(Registries.ITEM);

        public static final TagKey<Item> GARGOYLE_SNACK = HELPER.modTag("gargoyle_snack");

        public static final TagKey<Item> CRYSTAL_GLASS = HELPER.copy(Blocks.CRYSTAL_GLASS);
        public static final TagKey<Item> CRYSTAL_GLASS_PANES = HELPER.copy(Blocks.CRYSTAL_GLASS_PANES);
    }

    public static final class Blocks {
        private static final TagHelper<Block> HELPER = new TagHelper<>(Registries.BLOCK);

        public static final TagKey<Block> CRYSTAL_GLASS = HELPER.modTag("crystal_glass");
        public static final TagKey<Block> CRYSTAL_GLASS_PANES = HELPER.modTag("crystal_glass_panes");
    }

    public static final class Entities {
        private static final TagHelper<EntityType<?>> HELPER = new TagHelper<>(Registries.ENTITY_TYPE);

        public static final TagKey<EntityType<?>> SCARED_OF_GARGOYLE = HELPER.modTag("scared_of_gargoyle");
    }

}
