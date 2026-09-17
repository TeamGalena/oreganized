package galena.oreganized.electrum.index;

import galena.oreganized.register.TagHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ElectrumTags {

    public static final class Items {
        private static final TagHelper<Item> HELPER = new TagHelper<>(Registries.ITEM);

        public static final TagKey<Item> HAS_KINETIC_DAMAGE = HELPER.modTag("has_kinetic_damage");

    }

    public static final class Blocks {
        private static final TagHelper<Block> HELPER = new TagHelper<>(Registries.BLOCK);

        public static final TagKey<Block> INCORRECT_FOR_ELECTRUM_TOOL = HELPER.modTag("incorrect_for_electrum_tool");
    }

}
