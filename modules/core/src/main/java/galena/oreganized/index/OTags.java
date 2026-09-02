package galena.oreganized.index;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

import com.teamabnormals.blueprint.core.util.TagUtil;
import galena.oreganized.ModCompat;
import galena.oreganized.OConstants;
import net.minecraft.core.registries.Registries;
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

        public static final TagKey<Item> RAW_MATERIALS_SILVER = commonTag("raw_materials/silver");
        public static final TagKey<Item> RAW_MATERIALS_LEAD = commonTag("raw_materials/lead");

        public static final TagKey<Item> INGOTS_GOLD = commonTag("ingots/gold");
        public static final TagKey<Item> INGOTS_SILVER = commonTag("ingots/silver");
        public static final TagKey<Item> INGOTS_LEAD = commonTag("ingots/lead");
        public static final TagKey<Item> INGOTS_ELECTRUM = commonTag("ingots/electrum");

        public static final TagKey<Item> NUGGETS_SILVER = commonTag("nuggets/silver");
        public static final TagKey<Item> NUGGETS_LEAD = commonTag("nuggets/lead");
        public static final TagKey<Item> NUGGETS_ELECTRUM = commonTag("nuggets/electrum");
        public static final TagKey<Item> NUGGETS_NETHERITE = commonTag("nuggets/netherite");

        public static final TagKey<Item> ORES_SILVER = commonTag("ores/silver");
        public static final TagKey<Item> ORES_LEAD = commonTag("ores/lead");

        public static final TagKey<Item> STORAGE_BLOCKS_SILVER = commonTag("storage_blocks/silver");
        public static final TagKey<Item> STORAGE_BLOCKS_LEAD = commonTag("storage_blocks/lead");
        public static final TagKey<Item> STORAGE_BLOCKS_ELECTRUM = commonTag("storage_blocks/electrum");

        public static final TagKey<Item> STORAGE_BLOCKS_RAW_SILVER = commonTag("storage_blocks/raw_silver");
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_LEAD = commonTag("storage_blocks/raw_lead");
        public static final TagKey<Item> TOOLS_BUSH_HAMMER = commonTag("tools/bush_hammer");

        public static final TagKey<Item> TOOLS_KNIVES = TagUtil.itemTag(ModCompat.FARMERS_DELIGHT_ID, "tools/knives");
        public static final TagKey<Item> FORGE_TOOLS_KNIVES = commonTag("tools/knife");
        public static final TagKey<Item> SHIELDS = commonTag("shields");
        public static final TagKey<Item> SHIELDS_SE = TagUtil.itemTag(ModCompat.SHIELD_EXPANSION_ID, "shields");
        public static final TagKey<Item> MACHETES = TagUtil.itemTag(ModCompat.NETHERS_DELIGHT_ID, "tools/machete");

        public static final TagKey<Item> HAS_KINETIC_DAMAGE = tag("has_kinetic_damage");

        public static final TagKey<Item> GARGOYLE_SNACK = tag("gargoyle_snack");

        public static final TagKey<Item> PROTECTIVE_HELMET = tag("lead_protection/helmet_standalone");
        public static final TagKey<Item> PROTECTIVE_ARMOR_PART = tag("lead_protection/armor_set");

        public static final TagKey<Item> NO_ANVIL_ENCHANT_COST = tag("no_anvil_enchant_cost");
        public static final TagKey<Item> ENCHANTED_BOOK_LIKE = tag("enchanted_book_like");

        public static final TagKey<Item> SLICER_TOOLS = TagUtil.itemTag("sliceanddice", "allowed_tools");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(OConstants.modLoc(name));
        }

        private static TagKey<Item> commonTag(String name) {
            return TagUtil.itemTag("c", name);
        }
    }

    public static class Blocks {

        public static final TagKey<Block> MINEABLE_WITH_BUSH_HAMMER = tag("mineable/bush_hammer");
        public static final TagKey<Block> MINEABLE_WITH_SCRIBE = tag("mineable/scribe");
        public static final TagKey<Block> SILKTOUCH_WITH_SCRIBE = tag("silktouch_using_scribe");
        public static final TagKey<Block> SILKTOUCH_WITH_SCRIBE_BLACKLIST = tag("no_silktouch_using_scribe");
        public static final TagKey<Block> ENGRAVABLE = tag("engravable");
        public static final TagKey<Block> ENGRAVABLE_NEEDS_PLATE = tag("engravable/needs_plate");
        public static final TagKey<Block> CRYSTAL_GLASS = tag("crystal_glass");
        public static final TagKey<Block> CRYSTAL_GLASS_PANES = tag("crystal_glass_panes");
        public static final TagKey<Block> STONE_TYPES_GLANCE = tag("stone_types/glance");

        public static final TagKey<Block> ORES_SILVER = commonTag("ores/silver");
        public static final TagKey<Block> ORES_LEAD = commonTag("ores/lead");

        public static final TagKey<Block> STORAGE_BLOCKS_SILVER = commonTag("storage_blocks/silver");
        public static final TagKey<Block> STORAGE_BLOCKS_LEAD = commonTag("storage_blocks/lead");
        public static final TagKey<Block> STORAGE_BLOCKS_ELECTRUM = commonTag("storage_blocks/electrum");

        public static final TagKey<Block> STORAGE_BLOCKS_RAW_SILVER = commonTag("storage_blocks/raw_silver");

        public static final TagKey<Block> STORAGE_BLOCKS_RAW_LEAD = commonTag("storage_blocks/raw_lead");

        public static final TagKey<Block> MELTS_LEAD = tag("melts_lead");
        public static final TagKey<Block> PREVENTS_LEAD_CLOUD = tag("prevents_lead_cloud");
        public static final TagKey<Block> BLOWS_LEAD_CLOUD = tag("blows_lead_cloud");
        public static final TagKey<Block> CREATES_LEAD_CLOUD = tag("creates_lead_cloud");

        public static final TagKey<Block> FIRE_HEAT_LEVEL = tag("heat_level/fire");
        public static final TagKey<Block> LAVA_HEAT_LEVEL = tag("heat_level/lava");

        public static final TagKey<Block> CARRY_ON_BLACKLIST = TagUtil.blockTag("carryon", "block_blacklist");
        public static final TagKey<Block> BOMB_BREAKABLE = TagUtil.blockTag("supplementaries", "bomb_breakable");
        public static final TagKey<Block> CANNON_TNTS = TagUtil.blockTag("supplementaries", "cannon_tnts");

        public static final TagKey<Block> AMETHYST_CLUSTERS = commonTag("clusters/amethyst");
        public static final TagKey<Block> QUARTZITE_CLUSTERS = commonTag("clusters/quartzite");

        public static final TagKey<Block> INCORRECT_FOR_SILVER_TOOL = tag("incorrect_for_silver_tool");
        public static final TagKey<Block> INCORRECT_FOR_ELECTRUM_TOOL = tag("incorrect_for_electrum_tool");
        public static final TagKey<Block> INCORRECT_FOR_LEAD_TOOL = tag("incorrect_for_lead_tool");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, OConstants.modLoc(name));
        }

        private static TagKey<Block> commonTag(String name) {
            return TagKey.create(Registries.BLOCK, fromNamespaceAndPath("c", name));
        }
    }

    public static class Entities {

        public static final TagKey<EntityType<?>> LIGHTER_THAN_LEAD = tag("lighter_than_lead");
        public static final TagKey<EntityType<?>> BOLT_SUSCEPTIBLE = tag("bolt_susceptible");
        public static final TagKey<EntityType<?>> BOLT_RESISTANT = tag("bolt_resistant");
        public static final TagKey<EntityType<?>> SCARED_OF_GARGOYLE = tag("scared_of_gargoyle");
        public static final TagKey<EntityType<?>> TARNISHABLE = tag("tarnishable");

        private static TagKey<EntityType<?>> tag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, OConstants.modLoc(name));
        }
    }

    public static class Fluids {

        public static final TagKey<Fluid> MOLTEN_LEAD = commonTag("molten_lead");

        private static TagKey<Fluid> tag(String name) {
            return TagKey.create(Registries.FLUID, OConstants.modLoc(name));
        }

        private static TagKey<Fluid> commonTag(String name) {
            return TagKey.create(Registries.FLUID, fromNamespaceAndPath("c", name));
        }
    }

    public static class Biomes {

        public static final TagKey<Biome> HAS_BOULDER = tag("has_structure/boulder");
        public static final TagKey<Biome> RICH_IN_LEAD_ORE = tag("rich_in_lead_ore");
        public static final TagKey<Biome> HAS_DATURA = tag("has_feature/datura");
        public static final TagKey<Biome> HAS_SPARSE_DATURA = tag("has_feature/sparse_datura");

        private static TagKey<Biome> tag(String name) {
            return TagKey.create(Registries.BIOME, OConstants.modLoc(name));
        }
    }

    public static class Enchantments {

        public static final TagKey<Enchantment> PREVENTS_LEAD_CLOUD = tag("prevents_lead_cloud");
        public static final TagKey<Enchantment> HEAT_IMMUNITY = tag("heat_immunity");

        private static TagKey<Enchantment> tag(String name) {
            return TagKey.create(Registries.ENCHANTMENT, OConstants.modLoc(name));
        }
    }
}
