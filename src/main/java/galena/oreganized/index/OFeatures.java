package galena.oreganized.index;

import static net.minecraft.tags.BlockTags.DEEPSLATE_ORE_REPLACEABLES;
import static net.minecraft.tags.BlockTags.STONE_ORE_REPLACEABLES;

import com.google.common.collect.ImmutableList;
import galena.oreganized.Oreganized;
import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.HeightmapPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Oreganized.MOD_ID);

    public static final RegistryObject<Feature<OreConfiguration>> SILVER_ORE = FEATURES.register("silver_ore", () -> new OreFeature(OreConfiguration.CODEC));
    public static final RegistryObject<Feature<OreConfiguration>> SILVER_ORE_EXTRA = FEATURES.register("silver_ore_extra", () -> new OreFeature(OreConfiguration.CODEC));
    public static final RegistryObject<Feature<OreConfiguration>> LEAD_ORE = FEATURES.register("lead_ore", () -> new OreFeature(OreConfiguration.CODEC));
    public static final RegistryObject<Feature<OreConfiguration>> LEAD_ORE_EXTRA = FEATURES.register("lead_ore_extra", () -> new OreFeature(OreConfiguration.CODEC));
    public static final RegistryObject<Feature<RandomPatchConfiguration>> DATURA = FEATURES.register("datura", () -> new RandomPatchFeature(RandomPatchConfiguration.CODEC));
    public static final RegistryObject<Feature<RandomPatchConfiguration>> PURPLE_DATURA = FEATURES.register("purple_datura", () -> new RandomPatchFeature(RandomPatchConfiguration.CODEC));

    public static final class Configured {


        public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_ORE = create("silver_ore");
        public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_ORE_EXTRA = create("silver_ore_extra");
        public static final ResourceKey<ConfiguredFeature<?, ?>> LEAD_ORE = create("lead_ore");
        public static final ResourceKey<ConfiguredFeature<?, ?>> LEAD_ORE_EXTRA = create("lead_ore_extra");
        public static final ResourceKey<ConfiguredFeature<?, ?>> DATURA = create("datura");
        public static final ResourceKey<ConfiguredFeature<?, ?>> PURPLE_DATURA = create("purple_datura");
        public static final ResourceKey<ConfiguredFeature<?, ?>> SPARSE_DATURA = create("sparse_datura");
        public static final ResourceKey<ConfiguredFeature<?, ?>> SPARSE_PURPLE_DATURA = create("sparse_purple_datura");

        public static ResourceKey<ConfiguredFeature<?, ?>> create(String name) {
            return ResourceKey.create(Registries.CONFIGURED_FEATURE, Oreganized.modLoc(name));
        }

        public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
            context.register(SILVER_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(new TagMatchTest(STONE_ORE_REPLACEABLES), OBlocks.SILVER_ORE.get().defaultBlockState()), OreConfiguration.target(new TagMatchTest(DEEPSLATE_ORE_REPLACEABLES), OBlocks.DEEPSLATE_SILVER_ORE.get().defaultBlockState())), 3, 0.8F)));
            context.register(SILVER_ORE_EXTRA, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(new TagMatchTest(STONE_ORE_REPLACEABLES), OBlocks.SILVER_ORE.get().defaultBlockState()), OreConfiguration.target(new TagMatchTest(DEEPSLATE_ORE_REPLACEABLES), OBlocks.DEEPSLATE_SILVER_ORE.get().defaultBlockState())), 2, 1F)));
            context.register(LEAD_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(new TagMatchTest(STONE_ORE_REPLACEABLES), OBlocks.LEAD_ORE.get().defaultBlockState()), OreConfiguration.target(new TagMatchTest(DEEPSLATE_ORE_REPLACEABLES), OBlocks.DEEPSLATE_LEAD_ORE.get().defaultBlockState())), 8)));
            context.register(LEAD_ORE_EXTRA, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(new TagMatchTest(STONE_ORE_REPLACEABLES), OBlocks.LEAD_ORE.get().defaultBlockState()), OreConfiguration.target(new TagMatchTest(DEEPSLATE_ORE_REPLACEABLES), OBlocks.DEEPSLATE_LEAD_ORE.get().defaultBlockState())), 8)));
            context.register(DATURA, new ConfiguredFeature<>(Feature.RANDOM_PATCH, daturaPatch(false, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(OBlocks.WHITE_DATURA.get()))))));
            context.register(PURPLE_DATURA, new ConfiguredFeature<>(Feature.RANDOM_PATCH, daturaPatch(false, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(OBlocks.PURPLE_DATURA.get()))))));
            context.register(SPARSE_DATURA, new ConfiguredFeature<>(Feature.RANDOM_PATCH, daturaPatch(true, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(OBlocks.WHITE_DATURA.get()))))));
            context.register(SPARSE_PURPLE_DATURA, new ConfiguredFeature<>(Feature.RANDOM_PATCH, daturaPatch(true, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(OBlocks.PURPLE_DATURA.get()))))));
        }

        private static RandomPatchConfiguration daturaPatch(boolean sparse, Holder<PlacedFeature> feature) {
            return new RandomPatchConfiguration(64, sparse ? 2 : 4, 3, feature);
        }
    }

    public static final class Placed {

        public static final ResourceKey<PlacedFeature> SILVER_ORE = create("silver_ore");
        public static final ResourceKey<PlacedFeature> SILVER_ORE_EXTRA = create("silver_ore_extra");
        public static final ResourceKey<PlacedFeature> LEAD_ORE = create("lead_ore");
        public static final ResourceKey<PlacedFeature> LEAD_ORE_EXTRA = create("lead_ore_extra");
        public static final ResourceKey<PlacedFeature> DATURA = create("datura");
        public static final ResourceKey<PlacedFeature> PURPLE_DATURA = create("purple_datura");
        public static final ResourceKey<PlacedFeature> SPARSE_DATURA = create("sparse_datura");
        public static final ResourceKey<PlacedFeature> SPARSE_PURPLE_DATURA = create("sparse_purple_datura");

        public static ResourceKey<PlacedFeature> create(String name) {
            return ResourceKey.create(Registries.PLACED_FEATURE, Oreganized.modLoc(name));
        }

        public static void bootstrap(BootstapContext<PlacedFeature> context) {
            HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);

            context.register(SILVER_ORE, new PlacedFeature(features.getOrThrow(Configured.SILVER_ORE), OrePlacements.commonOrePlacement(5, HeightRangePlacement.uniform(VerticalAnchor.absolute(-15), VerticalAnchor.absolute(5)))));
            context.register(SILVER_ORE_EXTRA, new PlacedFeature(features.getOrThrow(Configured.SILVER_ORE), OrePlacements.commonOrePlacement(5, HeightRangePlacement.uniform(VerticalAnchor.absolute(140), VerticalAnchor.absolute(160)))));
            context.register(LEAD_ORE, new PlacedFeature(features.getOrThrow(Configured.LEAD_ORE), OrePlacements.commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.absolute(-40), VerticalAnchor.absolute(-20)))));
            context.register(LEAD_ORE_EXTRA, new PlacedFeature(features.getOrThrow(Configured.LEAD_ORE), OrePlacements.commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.absolute(50), VerticalAnchor.absolute(80)))));
            context.register(DATURA, new PlacedFeature(features.getOrThrow(Configured.DATURA), rareOrePlacement(10, HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES))));
            context.register(PURPLE_DATURA, new PlacedFeature(features.getOrThrow(Configured.PURPLE_DATURA), rareOrePlacement(25, HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES))));
            context.register(SPARSE_DATURA, new PlacedFeature(features.getOrThrow(Configured.SPARSE_DATURA), rareOrePlacement(30, HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES))));
            context.register(SPARSE_PURPLE_DATURA, new PlacedFeature(features.getOrThrow(Configured.SPARSE_PURPLE_DATURA), rareOrePlacement(75, HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES))));
        }

        private static List<PlacementModifier> rareOrePlacement(int pChance, PlacementModifier pHeightRange) {
            return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pHeightRange);
        }

        private static List<PlacementModifier> orePlacement(PlacementModifier pCountPlacement, PlacementModifier pHeightRange) {
            return List.of(pCountPlacement, InSquarePlacement.spread(), pHeightRange, BiomeFilter.biome());
        }

    }
}
