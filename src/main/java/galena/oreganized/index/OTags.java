package galena.oreganized.index;

import static galena.oreganized.ModCompat.NETHERS_DELIGHT_ID;
import static galena.oreganized.ModCompat.SHIELD_EXPANSION_ID;

import galena.oreganized.Oreganized;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class OTags {

    public static class Items {

        public static final TagKey<Item> CRYSTAL_GLASS = tag("crystal_glass");
        public static final TagKey<Item> CRYSTAL_GLASS_PANES = tag("crystal_glass_panes");
        public static final TagKey<Item> LIGHTER_THAN_LEAD = tag("lighter_than_lead");
        public static final TagKey<Item> STONE_TYPES_GLANCE = tag("stone_types/glance");

        public static final TagKey<Item> RAW_MATERIALS_SILVER = forgeTag("raw_materials/silver");
        public static final TagKey<Item> RAW_MATERIALS_LEAD = forgeTag("raw_materials/lead");

        public static final TagKey<Item> INGOTS_GOLD = forgeTag("ingots/gold");
        public static final TagKey<Item> INGOTS_SILVER = forgeTag("ingots/silver");
        public static final TagKey<Item> INGOTS_LEAD = forgeTag("ingots/lead");
        public static final TagKey<Item> INGOTS_ELECTRUM = forgeTag("ingots/electrum");

        public static final TagKey<Item> NUGGETS_SILVER = forgeTag("nuggets/silver");
        public static final TagKey<Item> NUGGETS_LEAD = forgeTag("nuggets/lead");
        public static final TagKey<Item> NUGGETS_ELECTRUM = forgeTag("nuggets/electrum");
        public static final TagKey<Item> NUGGETS_NETHERITE = forgeTag("nuggets/netherite");

        public static final TagKey<Item> ORES_SILVER = forgeTag("ores/silver");
        public static final TagKey<Item> ORES_LEAD = forgeTag("ores/lead");

        public static final TagKey<Item> STORAGE_BLOCKS_SILVER = forgeTag("storage_blocks/silver");
        public static final TagKey<Item> STORAGE_BLOCKS_LEAD = forgeTag("storage_blocks/lead");
        public static final TagKey<Item> STORAGE_BLOCKS_ELECTRUM = forgeTag("storage_blocks/electrum");

        public static final TagKey<Item> STORAGE_BLOCKS_RAW_SILVER = forgeTag("storage_blocks/raw_silver");
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_LEAD = forgeTag("storage_blocks/raw_lead");
        public static final TagKey<Item> BUCKETS_MOLTEN_LEAD = forgeTag("buckets/molten_lead");
        public static final TagKey<Item> TOOLS_BUSH_HAMMER = forgeTag("tools/bush_hammer");

        public static final TagKey<Item> TOOLS_KNIVES = forgeTag("tools/knife");
        public static final TagKey<Item> SHIELDS = forgeTag("shields");
        public static final TagKey<Item> SHIELDS_SE = ItemTags.create(ResourceLocation.fromNamespaceAndPath(SHIELD_EXPANSION_ID, "shields"));
        public static final TagKey<Item> MACHETES = ItemTags.create(ResourceLocation.fromNamespaceAndPath(NETHERS_DELIGHT_ID, "tools/machete"));

        public static final TagKey<Item> HAS_KINETIC_DAMAGE = tag("has_kinetic_damage");

        public static final TagKey<Item> GARGOYLE_SNACK = tag("gargoyle_snack");

        public static final TagKey<Item> PROTECTIVE_HELMET = tag("lead_protection/helmet_standalone");
        public static final TagKey<Item> PROTECTIVE_ARMOR_PART = tag("lead_protection/armor_set");

        public static final TagKey<Item> NO_ANVIL_ENCHANT_COST = tag("no_anvil_enchant_cost");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(Oreganized.modLoc(name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }

    public static class Blocks {

        public static final TagKey<Block> MINEABLE_WITH_BUSH_HAMMER = tag("mineable/bush_hammer");
        public static final TagKey<Block> MINEABLE_WITH_SCRIBE = tag("mineable/scribe");
        public static final TagKey<Block> SILKTOUCH_WITH_SCRIBE = tag("silktouch_using_scribe");
        public static final TagKey<Block> SILKTOUCH_WITH_SCRIBE_BLACKLIST = tag("no_silktouch_using_scribe");
        public static final TagKey<Block> ENGRAVABLE = tag("engravable");
        public static final TagKey<Block> ENGRAVABLE_NEEDS_PLATE = tag("engravable/needs_plate");
        public static final TagKey<Block> FIRE_SOURCE = tag("fire_source");
        public static final TagKey<Block> CRYSTAL_GLASS = tag("crystal_glass");
        public static final TagKey<Block> CRYSTAL_GLASS_PANES = tag("crystal_glass_panes");
        public static final TagKey<Block> STONE_TYPES_GLANCE = tag("stone_types/glance");

        public static final TagKey<Block> ORES_SILVER = forgeTag("ores/silver");
        public static final TagKey<Block> ORES_LEAD = forgeTag("ores/lead");

        public static final TagKey<Block> STORAGE_BLOCKS_SILVER = forgeTag("storage_blocks/silver");
        public static final TagKey<Block> STORAGE_BLOCKS_LEAD = forgeTag("storage_blocks/lead");
        public static final TagKey<Block> STORAGE_BLOCKS_ELECTRUM = forgeTag("storage_blocks/electrum");

        public static final TagKey<Block> STORAGE_BLOCKS_RAW_SILVER = forgeTag("storage_blocks/raw_silver");

        public static final TagKey<Block> STORAGE_BLOCKS_RAW_LEAD = forgeTag("storage_blocks/raw_lead");

        public static final TagKey<Block> MELTS_LEAD = tag("melts_lead");
        public static final TagKey<Block> PREVENTS_LEAD_CLOUD = tag("prevents_lead_cloud");
        public static final TagKey<Block> BLOWS_LEAD_CLOUD = tag("blows_lead_cloud");
        public static final TagKey<Block> CREATES_LEAD_CLOUD = tag("creates_lead_cloud");

        public static final TagKey<Block> FIRE_HEAT_LEVEL = tag("heat_level/fire");
        public static final TagKey<Block> LAVA_HEAT_LEVEL = tag("heat_level/lava");

        public static final TagKey<Block> CARRY_ON_BLACKLIST = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("carryon", "block_blacklist"));
        public static final TagKey<Block> BOMB_BREAKABLE = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("supplementaries", "bomb_breakable"));
        public static final TagKey<Block> CANNON_TNTS = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("supplementaries", "cannon_tnts"));

        public static final TagKey<Block> AMETHYST_CLUSTERS = forgeTag("clusters/amethyst");
        public static final TagKey<Block> QUARTZITE_CLUSTERS = forgeTag("clusters/quartzite");

        public static final TagKey<Block> INCORRECT_FOR_SILVER_TOOL = tag("incorrect_for_silver_tool");
        public static final TagKey<Block> INCORRECT_FOR_ELECTRUM_TOOL = tag("incorrect_for_electrum_tool");
        public static final TagKey<Block> INCORRECT_FOR_LEAD_TOOL = tag("incorrect_for_lead_tool");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, Oreganized.modLoc(name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }

    public static class Entities {

        public static final TagKey<EntityType<?>> LIGHTER_THAN_LEAD = tag("lighter_than_lead");
        public static final TagKey<EntityType<?>> BOLT_SUSCEPTIBLE = tag("bolt_susceptible");
        public static final TagKey<EntityType<?>> BOLT_RESISTANT = tag("bolt_resistant");
        public static final TagKey<EntityType<?>> SCARED_OF_GARGOYLE = tag("scared_of_gargoyle");
        public static final TagKey<EntityType<?>> TARNISHABLE = tag("tarnishable");

        private static TagKey<EntityType<?>> tag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, Oreganized.modLoc(name));
        }
    }

    public static class Fluids {

        public static final TagKey<Fluid> MOLTEN_LEAD = forgeTag("molten_lead");

        private static TagKey<Fluid> tag(String name) {
            return TagKey.create(Registries.FLUID, Oreganized.modLoc(name));
        }

        private static TagKey<Fluid> forgeTag(String name) {
            return TagKey.create(Registries.FLUID, ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }

    public static class Biomes {

        public static final TagKey<Biome> HAS_BOULDER = tag("has_structure/boulder");
        public static final TagKey<Biome> RICH_IN_LEAD_ORE = tag("rich_in_lead_ore");
        public static final TagKey<Biome> HAS_DATURA = tag("has_feature/datura");
        public static final TagKey<Biome> HAS_SPARSE_DATURA = tag("has_feature/sparse_datura");

        private static TagKey<Biome> tag(String name) {
            return TagKey.create(Registries.BIOME, Oreganized.modLoc(name));
        }
    }

    public static class Enchantments {

        public static final TagKey<Enchantment> PREVENTS_LEAD_CLOUD = tag("prevents_lead_cloud");
        public static final TagKey<Enchantment> HEAD_IMMUNITY = tag("heat_immunity");

        private static TagKey<Enchantment> tag(String name) {
            return TagKey.create(Registries.ENCHANTMENT, Oreganized.modLoc(name));
        }
    }
}
