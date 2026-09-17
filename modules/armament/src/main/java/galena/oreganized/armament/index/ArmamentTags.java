package galena.oreganized.armament.index;

import galena.oreganized.register.TagHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ArmamentTags {

    public static final class Items {
        private static final TagHelper<Item> HELPER = new TagHelper<>(Registries.ITEM);


    }

    public static final class Blocks {
        private static final TagHelper<Block> HELPER = new TagHelper<>(Registries.BLOCK);

    }

    public static final class Entities {
        private static final TagHelper<EntityType<?>> HELPER = new TagHelper<>(Registries.ENTITY_TYPE);

        public static final TagKey<EntityType<?>> BOLT_SUSCEPTIBLE = HELPER.modTag("bolt_susceptible");
        public static final TagKey<EntityType<?>> BOLT_RESISTANT = HELPER.modTag("bolt_resistant");
    }

}
