package galena.oreganized.argentum.data;

import static net.minecraft.resources.ResourceLocation.fromNamespaceAndPath;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import galena.oreganized.ModCompat;
import galena.oreganized.OConstants;
import galena.oreganized.argentum.index.ArgentumBlocks;
import galena.oreganized.argentum.index.ArgentumItems;
import galena.oreganized.data.ODatagen;
import galena.oreganized.index.OTags;
import java.util.function.Function;
import java.util.stream.Stream;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredBlock;

@Mod(OConstants.MOD_ID)
public class ArgentumTags {

    public ArgentumTags() {
        ODatagen.addItemTagProvider(this::items);
        ODatagen.addBlockTagProvider(this::blocks);
        ODatagen.addEntityTagProvider(this::entities);
    }

    private void items(RegistrateItemTagsProvider provider) {
        provider.addTag(OTags.Items.RAW_MATERIALS_SILVER).add(ArgentumItems.RAW_SILVER.getKey());
        provider.addTag(OTags.Items.INGOTS_SILVER).add(ArgentumItems.SILVER_INGOT.getKey());
        provider.addTag(OTags.Items.NUGGETS_SILVER).add(ArgentumItems.SILVER_NUGGET.getKey());

        provider.addTag(ItemTags.PIGLIN_LOVED).add(ArgentumItems.SILVER_MIRROR.getKey());

        provider.addTag(ItemTags.BEACON_PAYMENT_ITEMS).add(ArgentumItems.SILVER_INGOT.getKey());

        provider.addTag(ItemTags.HEAD_ARMOR).add(ArgentumItems.SILVER_HELMET.getKey());
        provider.addTag(ItemTags.CHEST_ARMOR).add(ArgentumItems.SILVER_CHESTPLATE.getKey());
        provider.addTag(ItemTags.LEG_ARMOR).add(ArgentumItems.SILVER_LEGGINGS.getKey());
        provider.addTag(ItemTags.FOOT_ARMOR).add(ArgentumItems.SILVER_BOOTS.getKey());

        provider.copy(OTags.Blocks.ORES_SILVER, OTags.Items.ORES_SILVER);
        provider.copy(OTags.Blocks.STORAGE_BLOCKS_SILVER, OTags.Items.STORAGE_BLOCKS_SILVER);
        provider.copy(OTags.Blocks.STORAGE_BLOCKS_RAW_SILVER, OTags.Items.STORAGE_BLOCKS_RAW_SILVER);

        provider.addTag(ItemTags.TRIM_MATERIALS).add(ArgentumItems.SILVER_INGOT.getKey());

        provider.addTag(Tags.Items.MELEE_WEAPON_TOOLS)
                .add(ArgentumItems.SILVER_AXE.getKey())
                .add(ArgentumItems.SILVER_SWORD.getKey());

        provider.addTag(ItemTags.AXES).add(ArgentumItems.SILVER_AXE.getKey());
        provider.addTag(ItemTags.PICKAXES).add(ArgentumItems.SILVER_PICKAXE.getKey());
        provider.addTag(ItemTags.SWORDS).add(ArgentumItems.SILVER_SWORD.getKey());
        provider.addTag(ItemTags.SHOVELS).add(ArgentumItems.SILVER_SHOVEL.getKey());
        provider.addTag(ItemTags.HOES).add(ArgentumItems.SILVER_HOE.getKey());

        var noAnvilCost = provider.addTag(OTags.Items.NO_ANVIL_ENCHANT_COST);
        ArgentumSets.silverTools().forEach(it -> noAnvilCost.add(it.getKey()));

        provider.addTag(OTags.Items.PREVENTS_LEAD_CLOUD).add(ArgentumItems.SCRIBE.getKey());

        provider.addTag(OTags.Items.HIDES_HAT_LAYER).add(ArgentumItems.SILVER_HELMET.value());

        provider.addTag(OTags.Items.SLICER_TOOLS).add(Items.BRUSH);
    }

    private void blocks(RegistrateTagsProvider.IntrinsicImpl<Block> provider) {
        provider.addTag(OTags.Blocks.ORES_SILVER).add(ArgentumBlocks.SILVER_ORE.getKey(), ArgentumBlocks.DEEPSLATE_SILVER_ORE.getKey());
        provider.addTag(OTags.Blocks.STORAGE_BLOCKS_SILVER).add(ArgentumBlocks.SILVER_BLOCKS.base().getKey());
        provider.addTag(OTags.Blocks.STORAGE_BLOCKS_RAW_SILVER).add(ArgentumBlocks.RAW_SILVER_BLOCK.getKey());

        provider.addTag(Tags.Blocks.ORES_IN_GROUND_STONE).add(ArgentumBlocks.SILVER_ORE.getKey());
        provider.addTag(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE).add(ArgentumBlocks.DEEPSLATE_SILVER_ORE.getKey());

        Stream<DeferredBlock<?>> silverBlocks = Stream.of(
                Stream.of(
                        ArgentumBlocks.SILVER_ORE,
                        ArgentumBlocks.DEEPSLATE_SILVER_ORE,
                        ArgentumBlocks.RAW_SILVER_BLOCK
                ),
                ArgentumBlocks.SILVER_BLOCKS.all(),
                ArgentumBlocks.SILVER_BARS.all(),
                ArgentumBlocks.SILVER_BULBS.all(),
                ArgentumBlocks.SILVER_PILLARS.all(),
                ArgentumBlocks.CHISELED_SILVER.all(),
                ArgentumBlocks.CUT_SILVER_SLABS.all(),
                ArgentumBlocks.CUT_SILVER_STAIRS.all(),
                ArgentumBlocks.SILVER_DOORS.all(),
                ArgentumBlocks.SILVER_TRAPDOORS.all()
        ).flatMap(Function.identity());

        silverBlocks.map(Holder::value).forEach(block -> {
            provider.addTag(BlockTags.NEEDS_IRON_TOOL).add(block);
            provider.addTag(BlockTags.MINEABLE_WITH_PICKAXE).add(block);
        });

        provider.addTag(BlockTags.BEACON_BASE_BLOCKS).addTag(OTags.Blocks.STORAGE_BLOCKS_SILVER);

        ArgentumBlocks.CUT_SILVER_STAIRS.all().forEach(it -> provider.addTag(BlockTags.STAIRS).add(it.getKey()));
        ArgentumBlocks.CUT_SILVER_SLABS.all().forEach(it -> provider.addTag(BlockTags.SLABS).add(it.getKey()));

        ArgentumBlocks.SILVER_DOORS.all().forEach(it -> provider.addTag(BlockTags.MOB_INTERACTABLE_DOORS).add(it.getKey()));
        ArgentumBlocks.SILVER_DOORS.all().forEach(it -> provider.addTag(BlockTags.DOORS).add(it.getKey()));
        ArgentumBlocks.SILVER_TRAPDOORS.all().forEach(it -> provider.addTag(BlockTags.TRAPDOORS).add(it.getKey()));

        provider.addTag(BlockTags.ICE)
                .add(ArgentumBlocks.GROOVED_ICE.getKey())
                .add(ArgentumBlocks.GROOVED_PACKED_ICE.getKey())
                .add(ArgentumBlocks.GROOVED_BLUE_ICE.getKey());

        provider.addTag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ArgentumBlocks.GROOVED_ICE.getKey())
                .add(ArgentumBlocks.GROOVED_PACKED_ICE.getKey())
                .add(ArgentumBlocks.GROOVED_BLUE_ICE.getKey());

        provider.addTag(OTags.Blocks.AMETHYST_CLUSTERS)
                .add(Blocks.AMETHYST_CLUSTER)
                .add(Blocks.LARGE_AMETHYST_BUD)
                .add(Blocks.MEDIUM_AMETHYST_BUD)
                .add(Blocks.SMALL_AMETHYST_BUD);

        provider.addTag(OTags.Blocks.QUARTZITE_CLUSTERS)
                .addOptional(fromNamespaceAndPath(ModCompat.NO_MANS_LAND, "quartzite_cluster"))
                .addOptional(fromNamespaceAndPath(ModCompat.NO_MANS_LAND, "large_quartzite_bud"))
                .addOptional(fromNamespaceAndPath(ModCompat.NO_MANS_LAND, "medium_quartzite_bud"))
                .addOptional(fromNamespaceAndPath(ModCompat.NO_MANS_LAND, "small_quartzite_bud"));

        var scribeMineable = provider.addTag(OTags.Blocks.MINEABLE_WITH_SCRIBE)
                .addTags(Tags.Blocks.GLASS_BLOCKS)
                .addTags(Tags.Blocks.GLASS_PANES)
                .addTags(Tags.Blocks.OBSIDIANS)
                .addTags(OTags.Blocks.AMETHYST_CLUSTERS)
                .addTags(OTags.Blocks.QUARTZITE_CLUSTERS)
                .addTags(BlockTags.ICE)
                .addTags(BlockTags.CRYSTAL_SOUND_BLOCKS)
                .add(Blocks.AMETHYST_BLOCK);

        scribeMineable
                .add(Blocks.QUARTZ_BRICKS)
                .add(Blocks.QUARTZ_PILLAR)
                .add(Blocks.QUARTZ_SLAB)
                .add(Blocks.QUARTZ_STAIRS)
                .add(Blocks.CHISELED_QUARTZ_BLOCK)
                .add(Blocks.SMOOTH_QUARTZ)
                .add(Blocks.SMOOTH_QUARTZ_SLAB)
                .add(Blocks.SMOOTH_QUARTZ_STAIRS);

        scribeMineable.addOptionalTag(fromNamespaceAndPath("botania", "quartz_blocks"));

        Stream.of("%s", "waxed_%s", "%s_cluster", "%s_pane").forEach(pattern -> {
            scribeMineable
                    .addOptional(fromNamespaceAndPath(ModCompat.QUARK, pattern.formatted("red_corundum")))
                    .addOptional(fromNamespaceAndPath(ModCompat.QUARK, pattern.formatted("orange_corundum")))
                    .addOptional(fromNamespaceAndPath(ModCompat.QUARK, pattern.formatted("yellow_corundum")))
                    .addOptional(fromNamespaceAndPath(ModCompat.QUARK, pattern.formatted("green_corundum")))
                    .addOptional(fromNamespaceAndPath(ModCompat.QUARK, pattern.formatted("blue_corundum")))
                    .addOptional(fromNamespaceAndPath(ModCompat.QUARK, pattern.formatted("indigo_corundum")))
                    .addOptional(fromNamespaceAndPath(ModCompat.QUARK, pattern.formatted("violet_corundum")))
                    .addOptional(fromNamespaceAndPath(ModCompat.QUARK, pattern.formatted("white_corundum")))
                    .addOptional(fromNamespaceAndPath(ModCompat.QUARK, pattern.formatted("black_corundum")))
            ;
        });

        scribeMineable
                .addOptional(ResourceLocation.fromNamespaceAndPath("ae2", "quartz_cluster"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("ae2", "flawless_budding_quartz"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("ae2", "flawed_budding_quartz"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("ae2", "damaged_budding_quartz"))
                .addOptional(ResourceLocation.fromNamespaceAndPath("ae2", "chipped_budding_quartz"));

        provider.addTag(OTags.Blocks.SILKTOUCH_WITH_SCRIBE_BLACKLIST)
                .add(ArgentumBlocks.GROOVED_ICE.getKey())
                .add(ArgentumBlocks.GROOVED_PACKED_ICE.getKey())
                .add(ArgentumBlocks.GROOVED_BLUE_ICE.getKey());

        provider.addTag(BlockTags.SNOW_LAYER_CANNOT_SURVIVE_ON)
                .add(ArgentumBlocks.GROOVED_ICE.getKey())
                .add(ArgentumBlocks.GROOVED_PACKED_ICE.getKey());

        provider.addTag(OTags.Blocks.SILKTOUCH_WITH_SCRIBE)
                .addTags(OTags.Blocks.MINEABLE_WITH_SCRIBE)
                .addTags(BlockTags.MINEABLE_WITH_PICKAXE);

        provider.addTag(OTags.Blocks.INCORRECT_FOR_SILVER_TOOL).addTag(BlockTags.INCORRECT_FOR_STONE_TOOL);
    }

    private void entities(RegistrateTagsProvider.IntrinsicImpl<EntityType<?>> provider) {
        provider.addTag(OTags.Entities.TARNISHABLE).addTags(EntityTypeTags.UNDEAD);
    }

}
