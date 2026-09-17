package galena.oreganized.plumbum.index;

import galena.oreganized.register.TagHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class PlumbumTags {

    public static final class Items {
        private static final TagHelper<Item> HELPER = new TagHelper<>(Registries.ITEM);

        public static final TagKey<Item> LIGHTER_THAN_LEAD = HELPER.modTag("lighter_than_lead");

        public static final TagKey<Item> PREVENTS_LEAD_CLOUD = HELPER.modTag("tools/prevents_lead_cloud");

        public static final TagKey<Item> PROTECTIVE_HELMET = HELPER.modTag("lead_protection/helmet_standalone");
        public static final TagKey<Item> PROTECTIVE_ARMOR_PART = HELPER.modTag("lead_protection/armor_set");
    }

    public static final class Blocks {
        private static final TagHelper<Block> HELPER = new TagHelper<>(Registries.BLOCK);

        public static final TagKey<Block> MELTS_LEAD = HELPER.modTag("melts_lead");
        public static final TagKey<Block> PREVENTS_LEAD_CLOUD = HELPER.modTag("prevents_lead_cloud");
        public static final TagKey<Block> BLOWS_LEAD_CLOUD = HELPER.modTag("blows_lead_cloud");
        public static final TagKey<Block> CREATES_LEAD_CLOUD = HELPER.modTag("creates_lead_cloud");

        public static final TagKey<Block> FIRE_HEAT_LEVEL = HELPER.modTag("heat_level/fire");
        public static final TagKey<Block> LAVA_HEAT_LEVEL = HELPER.modTag("heat_level/lava");

        public static final TagKey<Block> INCORRECT_FOR_LEAD_TOOL = HELPER.modTag("incorrect_for_lead_tool");
    }

    public static final class Entities {
        private static final TagHelper<EntityType<?>> HELPER = new TagHelper<>(Registries.ENTITY_TYPE);

        public static final TagKey<EntityType<?>> LIGHTER_THAN_LEAD = HELPER.modTag("lighter_than_lead");
    }

    public static final class Fluids {
        private static final TagHelper<Fluid> HELPER = new TagHelper<>(Registries.FLUID);

        public static final TagKey<Fluid> MOLTEN_LEAD = HELPER.commonTag("molten_lead");
    }

    public static final class Biomes {
        private static final TagHelper<Biome> HELPER = new TagHelper<>(Registries.BIOME);

        public static final TagKey<Biome> RICH_IN_LEAD_ORE = HELPER.modTag("rich_in_lead_ore");
        public static final TagKey<Biome> HAS_DATURA = HELPER.modTag("has_feature/datura");
        public static final TagKey<Biome> HAS_SPARSE_DATURA = HELPER.modTag("has_feature/sparse_datura");
    }

    public static final class Enchantments {
        private static final TagHelper<Enchantment> HELPER = new TagHelper<>(Registries.ENCHANTMENT);

        public static final TagKey<Enchantment> PREVENTS_LEAD_CLOUD = HELPER.modTag("prevents_lead_cloud");
        public static final TagKey<Enchantment> HEAT_IMMUNITY = HELPER.modTag("heat_immunity");
    }

}
