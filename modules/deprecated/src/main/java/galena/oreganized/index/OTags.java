package galena.oreganized.index;

import galena.oreganized.argentum.index.ArgentumTags;
import galena.oreganized.armament.index.ArmamentTags;
import galena.oreganized.electrum.index.ElectrumTags;
import galena.oreganized.glance.index.GlanceTags;
import galena.oreganized.gothic.index.GothicTags;
import galena.oreganized.plumbum.index.PlumbumTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.Tags;

public class OTags {

    public static class Items {

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> LIGHTER_THAN_LEAD = PlumbumTags.Items.LIGHTER_THAN_LEAD;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> CRYSTAL_GLASS = GothicTags.Items.CRYSTAL_GLASS;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> CRYSTAL_GLASS_PANES = GothicTags.Items.CRYSTAL_GLASS_PANES;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> STONE_TYPES_GLANCE = GlanceTags.Items.STONE_TYPES_GLANCE;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> RAW_MATERIALS_SILVER = CoreTags.Items.RAW_MATERIALS_SILVER;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> RAW_MATERIALS_LEAD = CoreTags.Items.RAW_MATERIALS_LEAD;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> INGOTS_GOLD = Tags.Items.INGOTS_GOLD;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> INGOTS_SILVER = CoreTags.Items.INGOTS_SILVER;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> INGOTS_LEAD = CoreTags.Items.INGOTS_LEAD;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> INGOTS_ELECTRUM = CoreTags.Items.INGOTS_ELECTRUM;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> NUGGETS_SILVER = CoreTags.Items.NUGGETS_SILVER;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> NUGGETS_LEAD = CoreTags.Items.NUGGETS_LEAD;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> NUGGETS_ELECTRUM = CoreTags.Items.NUGGETS_ELECTRUM;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> NUGGETS_NETHERITE = CoreTags.Items.NUGGETS_NETHERITE;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> ORES_SILVER = CoreTags.Items.ORES_SILVER;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> ORES_LEAD = CoreTags.Items.ORES_LEAD;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> STORAGE_BLOCKS_SILVER = CoreTags.Items.STORAGE_BLOCKS_SILVER;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> STORAGE_BLOCKS_LEAD = CoreTags.Items.STORAGE_BLOCKS_LEAD;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> STORAGE_BLOCKS_ELECTRUM = CoreTags.Items.STORAGE_BLOCKS_ELECTRUM;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_SILVER = CoreTags.Items.STORAGE_BLOCKS_RAW_SILVER;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_LEAD = CoreTags.Items.STORAGE_BLOCKS_RAW_LEAD;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> HAS_KINETIC_DAMAGE = ElectrumTags.Items.HAS_KINETIC_DAMAGE;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> GARGOYLE_SNACK = GothicTags.Items.GARGOYLE_SNACK;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> PREVENTS_LEAD_CLOUD = PlumbumTags.Items.PREVENTS_LEAD_CLOUD;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> PROTECTIVE_HELMET = PlumbumTags.Items.PROTECTIVE_HELMET;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> PROTECTIVE_ARMOR_PART = PlumbumTags.Items.PROTECTIVE_ARMOR_PART;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> NO_ANVIL_ENCHANT_COST = ArgentumTags.Items.NO_ANVIL_ENCHANT_COST;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Item> ENCHANTED_BOOK_LIKE = ArgentumTags.Items.ENCHANTED_BOOK_LIKE;

    }

    public static class Blocks {

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Block> MINEABLE_WITH_SCRIBE = ArgentumTags.Blocks.MINEABLE_WITH_SCRIBE;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Block> SILKTOUCH_WITH_SCRIBE = ArgentumTags.Blocks.SILKTOUCH_WITH_SCRIBE;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Block> SILKTOUCH_WITH_SCRIBE_BLACKLIST = ArgentumTags.Blocks.SILKTOUCH_WITH_SCRIBE_BLACKLIST;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Block> CRYSTAL_GLASS = GothicTags.Blocks.CRYSTAL_GLASS;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Block> CRYSTAL_GLASS_PANES = GothicTags.Blocks.CRYSTAL_GLASS_PANES;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Block> STONE_TYPES_GLANCE = GlanceTags.Blocks.STONE_TYPES_GLANCE;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Block> ORES_SILVER = CoreTags.Blocks.ORES_SILVER;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Block> ORES_LEAD = CoreTags.Blocks.ORES_LEAD;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Block> STORAGE_BLOCKS_SILVER = CoreTags.Blocks.STORAGE_BLOCKS_SILVER;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Block> STORAGE_BLOCKS_LEAD = CoreTags.Blocks.STORAGE_BLOCKS_LEAD;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Block> STORAGE_BLOCKS_ELECTRUM = CoreTags.Blocks.STORAGE_BLOCKS_ELECTRUM;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Block> STORAGE_BLOCKS_RAW_SILVER = CoreTags.Blocks.STORAGE_BLOCKS_RAW_SILVER;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Block> STORAGE_BLOCKS_RAW_LEAD = CoreTags.Blocks.STORAGE_BLOCKS_RAW_LEAD;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Block> MELTS_LEAD = PlumbumTags.Blocks.MELTS_LEAD;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Block> PREVENTS_LEAD_CLOUD = PlumbumTags.Blocks.PREVENTS_LEAD_CLOUD;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Block> BLOWS_LEAD_CLOUD = PlumbumTags.Blocks.BLOWS_LEAD_CLOUD;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Block> CREATES_LEAD_CLOUD = PlumbumTags.Blocks.CREATES_LEAD_CLOUD;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Block> FIRE_HEAT_LEVEL = PlumbumTags.Blocks.FIRE_HEAT_LEVEL;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Block> LAVA_HEAT_LEVEL = PlumbumTags.Blocks.LAVA_HEAT_LEVEL;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Block> AMETHYST_CLUSTERS = CoreTags.Blocks.AMETHYST_CLUSTERS;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Block> QUARTZITE_CLUSTERS = CoreTags.Blocks.QUARTZITE_CLUSTERS;

    }

    public static class Entities {

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<EntityType<?>> LIGHTER_THAN_LEAD = PlumbumTags.Entities.LIGHTER_THAN_LEAD;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<EntityType<?>> BOLT_SUSCEPTIBLE = ArmamentTags.Entities.BOLT_SUSCEPTIBLE;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<EntityType<?>> BOLT_RESISTANT = ArmamentTags.Entities.BOLT_RESISTANT;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<EntityType<?>> SCARED_OF_GARGOYLE = GothicTags.Entities.SCARED_OF_GARGOYLE;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<EntityType<?>> TARNISHABLE = ArgentumTags.Entities.TARNISHABLE;

    }

    public static class Fluids {

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Fluid> MOLTEN_LEAD = PlumbumTags.Fluids.MOLTEN_LEAD;

    }

    public static class Biomes {

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Biome> HAS_BOULDER = GlanceTags.Biomes.HAS_BOULDER;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Biome> RICH_IN_LEAD_ORE = PlumbumTags.Biomes.RICH_IN_LEAD_ORE;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Biome> HAS_DATURA = PlumbumTags.Biomes.HAS_DATURA;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Biome> HAS_SPARSE_DATURA = PlumbumTags.Biomes.HAS_SPARSE_DATURA;

    }

    public static class Enchantments {

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Enchantment> PREVENTS_LEAD_CLOUD = PlumbumTags.Enchantments.PREVENTS_LEAD_CLOUD;

        @Deprecated(forRemoval = true, since = "5.3.0")
        public static final TagKey<Enchantment> HEAT_IMMUNITY = PlumbumTags.Enchantments.HEAT_IMMUNITY;

    }
}
