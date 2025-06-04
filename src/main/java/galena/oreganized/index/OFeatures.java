package galena.oreganized.index;

import static net.minecraft.tags.BlockTags.DEEPSLATE_ORE_REPLACEABLES;
import static net.minecraft.tags.BlockTags.STONE_ORE_REPLACEABLES;

import com.google.common.collect.ImmutableList;
import galena.oreganized.Oreganized;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Oreganized.MOD_ID);

    public static final RegistryObject<Feature<OreConfiguration>> SILVER_ORE = FEATURES.register("silver_ore", () -> new OreFeature(OreConfiguration.CODEC));
    public static final RegistryObject<Feature<OreConfiguration>> SILVER_ORE_EXTRA = FEATURES.register("silver_ore_extra", () -> new OreFeature(OreConfiguration.CODEC));
    public static final RegistryObject<Feature<OreConfiguration>> LEAD_ORE = FEATURES.register("lead_ore", () -> new OreFeature(OreConfiguration.CODEC));
    public static final RegistryObject<Feature<OreConfiguration>> LEAD_ORE_EXTRA = FEATURES.register("lead_ore_extra", () -> new OreFeature(OreConfiguration.CODEC));

    public static final class Configured {


        public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_ORE = create("silver_ore");
        public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_ORE_EXTRA = create("silver_ore_extra");
        public static final ResourceKey<ConfiguredFeature<?, ?>> LEAD_ORE = create("lead_ore");
        public static final ResourceKey<ConfiguredFeature<?, ?>> LEAD_ORE_EXTRA = create("lead_ore_extra");

        public static ResourceKey<ConfiguredFeature<?, ?>> create(String name) {
            return ResourceKey.create(Registries.CONFIGURED_FEATURE, Oreganized.modLoc(name));
        }

        public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
            context.register(SILVER_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(new TagMatchTest(STONE_ORE_REPLACEABLES), OBlocks.SILVER_ORE.get().defaultBlockState()), OreConfiguration.target(new TagMatchTest(DEEPSLATE_ORE_REPLACEABLES), OBlocks.DEEPSLATE_SILVER_ORE.get().defaultBlockState())), 3, 0.8F)));
            context.register(SILVER_ORE_EXTRA, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(new TagMatchTest(STONE_ORE_REPLACEABLES), OBlocks.SILVER_ORE.get().defaultBlockState()), OreConfiguration.target(new TagMatchTest(DEEPSLATE_ORE_REPLACEABLES), OBlocks.DEEPSLATE_SILVER_ORE.get().defaultBlockState())), 2, 1F)));
            context.register(LEAD_ORE, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(new TagMatchTest(STONE_ORE_REPLACEABLES), OBlocks.LEAD_ORE.get().defaultBlockState()), OreConfiguration.target(new TagMatchTest(DEEPSLATE_ORE_REPLACEABLES), OBlocks.DEEPSLATE_LEAD_ORE.get().defaultBlockState())), 8)));
            context.register(LEAD_ORE_EXTRA, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(new TagMatchTest(STONE_ORE_REPLACEABLES), OBlocks.LEAD_ORE.get().defaultBlockState()), OreConfiguration.target(new TagMatchTest(DEEPSLATE_ORE_REPLACEABLES), OBlocks.DEEPSLATE_LEAD_ORE.get().defaultBlockState())), 8)));
        }
    }

    public static final class Placed {


        public static final ResourceKey<PlacedFeature> SILVER_ORE = create("silver_ore");
        public static final ResourceKey<PlacedFeature> SILVER_ORE_EXTRA = create("silver_ore_extra");
        public static final ResourceKey<PlacedFeature> LEAD_ORE = create("lead_ore");
        public static final ResourceKey<PlacedFeature> LEAD_ORE_EXTRA = create("lead_ore_extra");

        public static ResourceKey<PlacedFeature> create(String name) {
            return ResourceKey.create(Registries.PLACED_FEATURE, Oreganized.modLoc(name));
        }
        public static void bootstrap(BootstapContext<PlacedFeature> context) {
            HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);

            context.register(SILVER_ORE, new PlacedFeature(features.getOrThrow(Configured.SILVER_ORE), OrePlacements.commonOrePlacement(5, HeightRangePlacement.uniform(VerticalAnchor.absolute(-15), VerticalAnchor.absolute(5)))));
            context.register(SILVER_ORE_EXTRA, new PlacedFeature(features.getOrThrow(Configured.SILVER_ORE), OrePlacements.commonOrePlacement(5, HeightRangePlacement.uniform(VerticalAnchor.absolute(140), VerticalAnchor.absolute(160)))));
            context.register(LEAD_ORE, new PlacedFeature(features.getOrThrow(Configured.LEAD_ORE), OrePlacements.commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.absolute(-40), VerticalAnchor.absolute(-20)))));
            context.register(LEAD_ORE_EXTRA, new PlacedFeature(features.getOrThrow(Configured.LEAD_ORE), OrePlacements.commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.absolute(50), VerticalAnchor.absolute(80)))));
        }
    }
}
