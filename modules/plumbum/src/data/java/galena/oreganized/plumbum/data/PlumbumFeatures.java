package galena.oreganized.plumbum.data;

import static galena.oreganized.data.extensions.ORegistryBootstrapExtensions.getConfigured;
import static net.minecraft.tags.BlockTags.DEEPSLATE_ORE_REPLACEABLES;
import static net.minecraft.tags.BlockTags.STONE_ORE_REPLACEABLES;

import com.google.common.collect.ImmutableList;
import galena.oreganized.OConstants;
import galena.oreganized.data.ODatagen;
import galena.oreganized.plumbum.index.PlumbumBlocks;
import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class PlumbumFeatures {

    private static final ResourceLocation LEAD_ORE = OConstants.modLoc("lead_ore");
    private static final ResourceLocation LEAD_ORE_EXTRA = OConstants.modLoc("lead_ore_extra");
    private static final ResourceLocation DATURA = OConstants.modLoc("datura");
    private static final ResourceLocation PURPLE_DATURA = OConstants.modLoc("purple_datura");
    private static final ResourceLocation SPARSE_DATURA = OConstants.modLoc("sparse_datura");
    private static final ResourceLocation SPARSE_PURPLE_DATURA = OConstants.modLoc("sparse_purple_datura");

    public PlumbumFeatures() {
        ODatagen.addDataRegistryEntries(Registries.PLACED_FEATURE, this::placed);
        ODatagen.addDataRegistryEntries(Registries.CONFIGURED_FEATURE, this::configured);
    }

    private void placed(BootstrapContext<PlacedFeature> context) {
        context.register(ResourceKey.create(Registries.PLACED_FEATURE, LEAD_ORE), new PlacedFeature(getConfigured(context, LEAD_ORE), OrePlacements.commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.absolute(-40), VerticalAnchor.absolute(-20)))));
        context.register(ResourceKey.create(Registries.PLACED_FEATURE, LEAD_ORE_EXTRA), new PlacedFeature(getConfigured(context, LEAD_ORE), OrePlacements.commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.absolute(50), VerticalAnchor.absolute(80)))));
        context.register(ResourceKey.create(Registries.PLACED_FEATURE, DATURA), new PlacedFeature(getConfigured(context, DATURA), rareOrePlacement(10, HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES))));
        context.register(ResourceKey.create(Registries.PLACED_FEATURE, PURPLE_DATURA), new PlacedFeature(getConfigured(context, PURPLE_DATURA), rareOrePlacement(25, HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES))));
        context.register(ResourceKey.create(Registries.PLACED_FEATURE, SPARSE_DATURA), new PlacedFeature(getConfigured(context, SPARSE_DATURA), rareOrePlacement(30, HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES))));
        context.register(ResourceKey.create(Registries.PLACED_FEATURE, SPARSE_PURPLE_DATURA), new PlacedFeature(getConfigured(context, SPARSE_PURPLE_DATURA), rareOrePlacement(75, HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES))));
    }

    private void configured(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        context.register(ResourceKey.create(Registries.CONFIGURED_FEATURE, LEAD_ORE), new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(new TagMatchTest(STONE_ORE_REPLACEABLES), PlumbumBlocks.LEAD_ORE.get().defaultBlockState()), OreConfiguration.target(new TagMatchTest(DEEPSLATE_ORE_REPLACEABLES), PlumbumBlocks.DEEPSLATE_LEAD_ORE.get().defaultBlockState())), 8)));
        context.register(ResourceKey.create(Registries.CONFIGURED_FEATURE, LEAD_ORE_EXTRA), new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(new TagMatchTest(STONE_ORE_REPLACEABLES), PlumbumBlocks.LEAD_ORE.get().defaultBlockState()), OreConfiguration.target(new TagMatchTest(DEEPSLATE_ORE_REPLACEABLES), PlumbumBlocks.DEEPSLATE_LEAD_ORE.get().defaultBlockState())), 8)));
        context.register(ResourceKey.create(Registries.CONFIGURED_FEATURE, DATURA), new ConfiguredFeature<>(Feature.RANDOM_PATCH, daturaPatch(false, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(PlumbumBlocks.WHITE_DATURA.get()))))));
        context.register(ResourceKey.create(Registries.CONFIGURED_FEATURE, PURPLE_DATURA), new ConfiguredFeature<>(Feature.RANDOM_PATCH, daturaPatch(false, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(PlumbumBlocks.PURPLE_DATURA.get()))))));
        context.register(ResourceKey.create(Registries.CONFIGURED_FEATURE, SPARSE_DATURA), new ConfiguredFeature<>(Feature.RANDOM_PATCH, daturaPatch(true, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(PlumbumBlocks.WHITE_DATURA.get()))))));
        context.register(ResourceKey.create(Registries.CONFIGURED_FEATURE, SPARSE_PURPLE_DATURA), new ConfiguredFeature<>(Feature.RANDOM_PATCH, daturaPatch(true, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(PlumbumBlocks.PURPLE_DATURA.get()))))));
    }

    private static RandomPatchConfiguration daturaPatch(boolean sparse, Holder<PlacedFeature> feature) {
        return new RandomPatchConfiguration(64, sparse ? 2 : 4, 3, feature);
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier pCountPlacement, PlacementModifier pHeightRange) {
        return List.of(pCountPlacement, InSquarePlacement.spread(), pHeightRange, BiomeFilter.biome());
    }

    private static List<PlacementModifier> rareOrePlacement(int pChance, PlacementModifier pHeightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pHeightRange);
    }

}
