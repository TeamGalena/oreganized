package galena.oreganized.argentum.index;

import galena.oreganized.register.TagHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class ArgentumTags {

    public static final class Items {
        private static final TagHelper<Item> HELPER = new TagHelper<>(Registries.ITEM);

        public static final TagKey<Item> NO_ANVIL_ENCHANT_COST = HELPER.modTag("no_anvil_enchant_cost");
        public static final TagKey<Item> ENCHANTED_BOOK_LIKE = HELPER.modTag("enchanted_book_like");
    }

    public static final class Blocks {
        private static final TagHelper<Block> HELPER = new TagHelper<>(Registries.BLOCK);

        public static final TagKey<Block> MINEABLE_WITH_SCRIBE = HELPER.modTag("mineable/scribe");
        public static final TagKey<Block> SILKTOUCH_WITH_SCRIBE = HELPER.modTag("silktouch_using_scribe");
        public static final TagKey<Block> SILKTOUCH_WITH_SCRIBE_BLACKLIST = HELPER.modTag("no_silktouch_using_scribe");

        public static final TagKey<Block> INCORRECT_FOR_SILVER_TOOL = HELPER.modTag("incorrect_for_silver_tool");

        public static final TagKey<Block> FAN_PROCESSING_CATALYST_TARNISHING = HELPER.modTag("fan_processing_catalysts/tarnishing");
    }

    public static final class Entities {
        private static final TagHelper<EntityType<?>> HELPER = new TagHelper<>(Registries.ENTITY_TYPE);

        public static final TagKey<EntityType<?>> TARNISHES = HELPER.modTag("tarnishes");
        public static final TagKey<EntityType<?>> TARNISHABLE = HELPER.modTag("tarnishable");
    }

    public static final class Fluids {
        private static final TagHelper<Fluid> HELPER = new TagHelper<>(Registries.FLUID);

        public static final TagKey<Fluid> FAN_PROCESSING_CATALYST_TARNISHING = HELPER.modTag("fan_processing_catalysts/tarnishing");
    }

}
