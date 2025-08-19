package galena.oreganized.index;

import static galena.oreganized.ModCompat.FARMERS_DELIGHT_ID;
import static galena.oreganized.ModCompat.NETHERS_DELIGHT_ID;
import static galena.oreganized.ModCompat.SHIELD_EXPANSION_ID;

import galena.oreganized.Oreganized;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.versions.forge.ForgeVersion;

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

        public static final TagKey<Item> TOOLS_KNIVES = forgeTag("tools/knives");
        public static final TagKey<Item> TOOLS_KNIVES_FD = ItemTags.create(new ResourceLocation(FARMERS_DELIGHT_ID, "tools/knives"));
        public static final TagKey<Item> SHIELDS = forgeTag("shields");
        public static final TagKey<Item> SHIELDS_SE = ItemTags.create(new ResourceLocation(SHIELD_EXPANSION_ID, "shields"));
        public static final TagKey<Item> MACHETES = ItemTags.create(new ResourceLocation(NETHERS_DELIGHT_ID, "tools/machetes"));

        public static final TagKey<Item> HAS_KINETIC_DAMAGE = tag( "has_kinetic_damage");

        public static final TagKey<Item> GARGOYLE_SNACK = tag("gargoyle_snack");

        public static final TagKey<Item> PROTECTIVE_HELMET = tag("lead_protection/helmet_standalone");
        public static final TagKey<Item> PROTECTIVE_ARMOR_PART = tag("lead_protection/armor_set");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(Oreganized.modLoc(name));
        }
        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation(ForgeVersion.MOD_ID, name));
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

        public static final TagKey<Block> MELTS_LEAD = forgeTag("melts_lead");
        public static final TagKey<Block> PREVENTS_LEAD_CLOUD = tag("prevents_lead_cloud");
        public static final TagKey<Block> BLOWS_LEAD_CLOUD = tag("blows_lead_cloud");
        public static final TagKey<Block> CREATES_LEAD_CLOUD = tag("creates_lead_cloud");

        public static final TagKey<Block> FIRE_HEAT_LEVEL = tag("heat_level/fire");
        public static final TagKey<Block> LAVA_HEAT_LEVEL = tag("heat_level/lava");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(Oreganized.modLoc(name));
        }
        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation(ForgeVersion.MOD_ID, name));
        }
    }

    public static class Entities {

        public static final TagKey<EntityType<?>> LIGHTER_THAN_LEAD = tag("lighter_than_lead");
        public static final TagKey<EntityType<?>> BOLT_RESISTANT = tag("bolt_resistant");

        private static TagKey<EntityType<?>> tag(String name) {
            return EntityTypeTags.create(Oreganized.modLoc(name).toString());
        }
    }

    public static class Fluids {

        public static final TagKey<Fluid> MOLTEN_LEAD = forgeTag("molten_lead");

        private static TagKey<Fluid> tag(String name) {
            return FluidTags.create(Oreganized.modLoc(name));
        }

        private static TagKey<Fluid> forgeTag(String name) {
            return FluidTags.create(new ResourceLocation(ForgeVersion.MOD_ID, name));
        }
    }

    public static class Biomes {

        public static final TagKey<Biome> HAS_BOULDER = tag("has_structure/boulder");
        public static final TagKey<Biome> RICH_IN_LEAD_ORE = tag("rich_in_lead_ore");
        public static final TagKey<Biome> HAS_DATURA = tag("has_feature/datura");
        public static final TagKey<Biome> HAS_SPARSE_DATURA = tag("has_feature/sparse_datura");

        private static TagKey<Biome> tag(String name) {
            return BiomeTags.create(Oreganized.modLoc(name).toString());
        }
    }
}
