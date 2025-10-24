package galena.oreganized.data;

import galena.oreganized.Oreganized;
import galena.oreganized.index.DyeColors;
import galena.oreganized.index.OBlocks;
import galena.oreganized.index.OTags;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class OBlockTags extends IntrinsicHolderTagsProvider<Block> {

    public OBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> future, @Nullable ExistingFileHelper helper) {
        super(output, Registries.BLOCK, future, block -> block.builtInRegistryHolder().key(), Oreganized.MOD_ID, helper);
    }

    @Override
    public @NotNull String getName() {
        return "Oreganized Block Tags";
    }

    private static TagKey<Block> dyedTag(@Nullable DyeColor color) {
        var id = new ResourceLocation("forge", "dyed");
        if (color != null) id = id.withSuffix("/" + color.getSerializedName());
        return TagKey.create(Registries.BLOCK, id);
    }

    private void tagDyed(Map<DyeColor, ? extends Supplier<? extends Block>> values, TagKey<Block>... keys) {
        values.entrySet().stream().sorted(Map.Entry.comparingByKey(DyeColors.comparator())).forEach(entry -> {
            var block = entry.getValue().get();
            var id = BuiltInRegistries.BLOCK.getKey(block);
            for (var key : keys) {
                tag(key).addOptional(id);
            }
            tag(dyedTag(null)).addOptional(id);
            tag(dyedTag(entry.getKey())).addOptional(id);
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Oreganized
        tagDyed(OBlocks.CRYSTAL_GLASS, OTags.Blocks.CRYSTAL_GLASS);
        tagDyed(OBlocks.CRYSTAL_GLASS_PANES, OTags.Blocks.CRYSTAL_GLASS_PANES);

        tag(OTags.Blocks.FIRE_SOURCE).addTag(BlockTags.FIRE).addTag(BlockTags.CAMPFIRES);
        tag(OTags.Blocks.STONE_TYPES_GLANCE).add(
                OBlocks.POLISHED_GLANCE.get(), OBlocks.GLANCE_BRICKS.get(), OBlocks.CHISELED_GLANCE.get(),
                OBlocks.GLANCE_BRICK_STAIRS.get(), OBlocks.GLANCE_BRICK_WALL.get()
        );

        // Oreganized Forge
        tag(OTags.Blocks.ORES_SILVER).add(OBlocks.SILVER_ORE.get(), OBlocks.DEEPSLATE_SILVER_ORE.get());
        tag(OTags.Blocks.ORES_LEAD).add(OBlocks.LEAD_ORE.get(), OBlocks.DEEPSLATE_LEAD_ORE.get());

        tag(OTags.Blocks.STORAGE_BLOCKS_SILVER).add(OBlocks.SILVER_BLOCK.get());
        tag(OTags.Blocks.STORAGE_BLOCKS_LEAD).add(OBlocks.LEAD_BLOCK.get());
        tag(OTags.Blocks.STORAGE_BLOCKS_ELECTRUM).add(OBlocks.ELECTRUM_BLOCK.get());

        tag(OTags.Blocks.STORAGE_BLOCKS_RAW_SILVER).add(OBlocks.RAW_SILVER_BLOCK.get());
        tag(OTags.Blocks.STORAGE_BLOCKS_RAW_LEAD).add(OBlocks.RAW_LEAD_BLOCK.get());

        // Vanilla
        tag(BlockTags.WALLS).add(OBlocks.GLANCE_WALL.get(), OBlocks.GLANCE_BRICK_WALL.get());
        tag(BlockTags.STAIRS).add(OBlocks.GLANCE_STAIRS.get(), OBlocks.POLISHED_GLANCE_STAIRS.get(), OBlocks.GLANCE_BRICK_STAIRS.get());
        tag(BlockTags.SLABS).add(OBlocks.GLANCE_SLAB.get(), OBlocks.POLISHED_GLANCE_SLAB.get(), OBlocks.GLANCE_BRICK_SLAB.get());
        tag(BlockTags.BEACON_BASE_BLOCKS)
                .addTag(OTags.Blocks.STORAGE_BLOCKS_SILVER)
                .addTag(OTags.Blocks.STORAGE_BLOCKS_ELECTRUM);
        tag(BlockTags.IMPERMEABLE).addTag(OTags.Blocks.CRYSTAL_GLASS);
        tag(BlockTags.CAULDRONS).add(OBlocks.MOLTEN_LEAD_CAULDRON.get());
        tag(BlockTags.DOORS).add(OBlocks.LEAD_DOOR.get());
        tag(BlockTags.TRAPDOORS).add(OBlocks.LEAD_TRAPDOOR.get());
        tag(BlockTags.SMALL_FLOWERS).add(OBlocks.WHITE_DATURA.get());
        tag(BlockTags.SMALL_FLOWERS).add(OBlocks.PURPLE_DATURA.get());
        // Forge
        tag(Tags.Blocks.ORES).addTags(OTags.Blocks.ORES_SILVER, OTags.Blocks.ORES_LEAD);
        tag(Tags.Blocks.ORE_RATES_SINGULAR).addTags(OTags.Blocks.ORES_SILVER, OTags.Blocks.ORES_LEAD);
        tag(Tags.Blocks.STORAGE_BLOCKS)
                .addTags(
                        OTags.Blocks.STORAGE_BLOCKS_SILVER,
                        OTags.Blocks.STORAGE_BLOCKS_LEAD,
                        OTags.Blocks.STORAGE_BLOCKS_ELECTRUM,
                        OTags.Blocks.STORAGE_BLOCKS_RAW_SILVER,
                        OTags.Blocks.STORAGE_BLOCKS_RAW_LEAD
                )
                .add(OBlocks.LEAD_BOLT_CRATE.get());
        tag(Tags.Blocks.GLASS).addTag(OTags.Blocks.CRYSTAL_GLASS);
        tag(Tags.Blocks.GLASS_PANES).addTag(OTags.Blocks.CRYSTAL_GLASS_PANES);
        tag(Tags.Blocks.ORES_IN_GROUND_STONE).add(OBlocks.LEAD_ORE.get(), OBlocks.SILVER_ORE.get());
        tag(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE).add(OBlocks.DEEPSLATE_LEAD_ORE.get(), OBlocks.DEEPSLATE_SILVER_ORE.get());

        Stream.of(
                OBlocks.LEAD_ORE,
                OBlocks.DEEPSLATE_LEAD_ORE,
                OBlocks.RAW_LEAD_BLOCK,

                OBlocks.LEAD_BLOCK,
                OBlocks.LEAD_BRICKS,
                OBlocks.LEAD_PILLAR,
                OBlocks.LEAD_BULB,
                OBlocks.CUT_LEAD,

                OBlocks.ELECTRUM_BLOCK,

                OBlocks.LEAD_DOOR,
                OBlocks.LEAD_TRAPDOOR,
                OBlocks.LEAD_BARS
        ).map(RegistryObject::get).forEach(block -> {
            tag(BlockTags.NEEDS_STONE_TOOL).add(block);
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block);
        });

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                OBlocks.SILVER_ORE.get(),
                OBlocks.DEEPSLATE_SILVER_ORE.get(),
                OBlocks.RAW_SILVER_BLOCK.get(),

                OBlocks.SILVER_BLOCK.get(),

                OBlocks.GLANCE.get(),
                OBlocks.GLANCE_STAIRS.get(),
                OBlocks.GLANCE_SLAB.get(),
                OBlocks.POLISHED_GLANCE.get(),
                OBlocks.POLISHED_GLANCE_STAIRS.get(),
                OBlocks.POLISHED_GLANCE_SLAB.get(),
                OBlocks.GLANCE_WALL.get(),
                OBlocks.GLANCE_BRICKS.get(),
                OBlocks.GLANCE_BRICK_STAIRS.get(),
                OBlocks.GLANCE_BRICK_SLAB.get(),
                OBlocks.GLANCE_BRICK_WALL.get(),
                OBlocks.CHISELED_GLANCE.get(),
                OBlocks.SPOTTED_GLANCE.get(),
                OBlocks.WAXED_SPOTTED_GLANCE.get(),

                OBlocks.GARGOYLE.get(),

                OBlocks.MOLTEN_LEAD_CAULDRON.get(),

                OBlocks.GROOVED_ICE.get(),
                OBlocks.GROOVED_PACKED_ICE.get(),
                OBlocks.GROOVED_BLUE_ICE.get()
        );

        tagDyed(OBlocks.WAXED_CONCRETE_POWDER, BlockTags.MINEABLE_WITH_SHOVEL);

        tag(BlockTags.NEEDS_IRON_TOOL).add(
                OBlocks.SILVER_ORE.get(),
                OBlocks.DEEPSLATE_SILVER_ORE.get(),
                OBlocks.SILVER_BLOCK.get(),
                OBlocks.RAW_SILVER_BLOCK.get()
        );

        tag(BlockTags.MINEABLE_WITH_AXE).add(OBlocks.LEAD_BOLT_CRATE.get());

        tag(OTags.Blocks.MELTS_LEAD)
                .add(Blocks.LAVA)
                .add(Blocks.MAGMA_BLOCK)
                .addTags(BlockTags.CAMPFIRES)
                .addTags(BlockTags.FIRE);

        tag(BlockTags.ICE)
                .add(OBlocks.GROOVED_ICE.get())
                .add(OBlocks.GROOVED_PACKED_ICE.get())
                .add(OBlocks.GROOVED_BLUE_ICE.get());

        tag(OTags.Blocks.AMETHYST_CLUSTERS)
                .add(Blocks.AMETHYST_CLUSTER)
                .add(Blocks.LARGE_AMETHYST_BUD)
                .add(Blocks.MEDIUM_AMETHYST_BUD)
                .add(Blocks.SMALL_AMETHYST_BUD);

        var scribeMineable = tag(OTags.Blocks.MINEABLE_WITH_SCRIBE)
                .addTags(Tags.Blocks.GLASS)
                .addTags(Tags.Blocks.GLASS_PANES)
                .addTags(Tags.Blocks.OBSIDIAN)
                .addTags(OTags.Blocks.AMETHYST_CLUSTERS)
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

        scribeMineable.addOptionalTag(new ResourceLocation("botania", "quartz_blocks"));

        Stream.of("%s", "waxed_%s", "%s_cluster", "%s_pane").forEach(pattern -> {
            scribeMineable
                    .addOptional(new ResourceLocation("quark", pattern.formatted("red_corundum")))
                    .addOptional(new ResourceLocation("quark", pattern.formatted("orange_corundum")))
                    .addOptional(new ResourceLocation("quark", pattern.formatted("yellow_corundum")))
                    .addOptional(new ResourceLocation("quark", pattern.formatted("green_corundum")))
                    .addOptional(new ResourceLocation("quark", pattern.formatted("blue_corundum")))
                    .addOptional(new ResourceLocation("quark", pattern.formatted("indigo_corundum")))
                    .addOptional(new ResourceLocation("quark", pattern.formatted("violet_corundum")))
                    .addOptional(new ResourceLocation("quark", pattern.formatted("white_corundum")))
                    .addOptional(new ResourceLocation("quark", pattern.formatted("black_corundum")))
            ;
        });

        scribeMineable
                .addOptional(new ResourceLocation("ae2", "quartz_cluster"))
                .addOptional(new ResourceLocation("ae2", "flawless_budding_quartz"))
                .addOptional(new ResourceLocation("ae2", "flawed_budding_quartz"))
                .addOptional(new ResourceLocation("ae2", "damaged_budding_quartz"))
                .addOptional(new ResourceLocation("ae2", "chipped_budding_quartz"));

        tag(OTags.Blocks.SILKTOUCH_WITH_SCRIBE_BLACKLIST)
                .add(OBlocks.GROOVED_ICE.get())
                .add(OBlocks.GROOVED_PACKED_ICE.get())
                .add(OBlocks.GROOVED_BLUE_ICE.get());

        tag(BlockTags.SNOW_LAYER_CANNOT_SURVIVE_ON)
                .add(OBlocks.GROOVED_ICE.get())
                .add(OBlocks.GROOVED_PACKED_ICE.get());

        tag(OTags.Blocks.SILKTOUCH_WITH_SCRIBE)
                .addTags(OTags.Blocks.MINEABLE_WITH_SCRIBE)
                .addTags(BlockTags.MINEABLE_WITH_PICKAXE);

        tag(OTags.Blocks.PREVENTS_LEAD_CLOUD)
                .add(Blocks.WATER)
                .addOptional(new ResourceLocation("spelunkery", "spring_water"));

        tag(OTags.Blocks.CREATES_LEAD_CLOUD)
                .addTags(OTags.Blocks.ORES_LEAD)
                .addTags(OTags.Blocks.STORAGE_BLOCKS_RAW_LEAD);

        tag(OTags.Blocks.BLOWS_LEAD_CLOUD)
                .addTags(OTags.Blocks.CREATES_LEAD_CLOUD);

        tag(OTags.Blocks.FIRE_HEAT_LEVEL)
                .addTags(BlockTags.FIRE)
                .addTags(BlockTags.CAMPFIRES);

        tag(OTags.Blocks.LAVA_HEAT_LEVEL)
                .add(Blocks.MAGMA_BLOCK)
                .add(Blocks.LAVA)
                .add(OBlocks.MOLTEN_LEAD.get())
                .add(Blocks.LAVA_CAULDRON)
                .add(OBlocks.MOLTEN_LEAD_CAULDRON.get());

        tag(OTags.Blocks.CARRY_ON_BLACKLIST)
                .add(OBlocks.LEAD_DOOR.get())
                .add(OBlocks.LEAD_TRAPDOOR.get());

        tag(OTags.Blocks.BOMB_BREAKABLE).add(OBlocks.SHRAPNEL_BOMB.get());
        tag(OTags.Blocks.CANNON_TNTS).add(OBlocks.SHRAPNEL_BOMB.get());
    }
}
