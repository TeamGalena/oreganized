package galena.oreganized.argentum.data;

import static galena.oreganized.data.extensions.ORegistryBootstrapExtensions.getConfigured;
import static net.minecraft.tags.BlockTags.DEEPSLATE_ORE_REPLACEABLES;
import static net.minecraft.tags.BlockTags.STONE_ORE_REPLACEABLES;

import com.google.common.collect.ImmutableList;
import galena.oreganized.OConstants;
import galena.oreganized.argentum.index.ArgentumBlocks;
import galena.oreganized.data.ODatagen;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.neoforged.fml.common.Mod;

@Mod(OConstants.MOD_ID)
public class ArgentumFeatures {

    private static final ResourceLocation SILVER_ORE = OConstants.modLoc("silver_ore");
    private static final ResourceLocation SILVER_ORE_EXTRA = OConstants.modLoc("silver_ore_extra");

    public ArgentumFeatures() {
        ODatagen.addDataRegistryEntries(Registries.PLACED_FEATURE, this::placed);
        ODatagen.addDataRegistryEntries(Registries.CONFIGURED_FEATURE, this::configured);
    }

    private void placed(BootstrapContext<PlacedFeature> context) {
        context.register(ResourceKey.create(Registries.PLACED_FEATURE, SILVER_ORE), new PlacedFeature(getConfigured(context, SILVER_ORE), OrePlacements.commonOrePlacement(5, HeightRangePlacement.uniform(VerticalAnchor.absolute(-15), VerticalAnchor.absolute(5)))));
        context.register(ResourceKey.create(Registries.PLACED_FEATURE, SILVER_ORE_EXTRA), new PlacedFeature(getConfigured(context, SILVER_ORE), OrePlacements.commonOrePlacement(5, HeightRangePlacement.uniform(VerticalAnchor.absolute(140), VerticalAnchor.absolute(160)))));
    }

    private void configured(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        context.register(ResourceKey.create(Registries.CONFIGURED_FEATURE, SILVER_ORE), new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(new TagMatchTest(STONE_ORE_REPLACEABLES), ArgentumBlocks.SILVER_ORE.get().defaultBlockState()), OreConfiguration.target(new TagMatchTest(DEEPSLATE_ORE_REPLACEABLES), ArgentumBlocks.DEEPSLATE_SILVER_ORE.get().defaultBlockState())), 3, 0.8F)));
        context.register(ResourceKey.create(Registries.CONFIGURED_FEATURE, SILVER_ORE_EXTRA), new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(new TagMatchTest(STONE_ORE_REPLACEABLES), ArgentumBlocks.SILVER_ORE.get().defaultBlockState()), OreConfiguration.target(new TagMatchTest(DEEPSLATE_ORE_REPLACEABLES), ArgentumBlocks.DEEPSLATE_SILVER_ORE.get().defaultBlockState())), 2, 1F)));
    }

}
