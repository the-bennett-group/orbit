package bennett.orbit.planets.casud.feature;

import bennett.orbit.Orbit;
import bennett.orbit.blocks.OrbitBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

@SuppressWarnings("Deprecated")
public class OrbitFeatures {
    public static ConfiguredFeature<LakeFeature.Configuration, ?> ACID_LAKE_CONFIGURED;
    public static PlacedFeature ACID_LAKE_PLACED;
    public static PlacedFeature ACID_LAKE_PLACED_FREQUENT;

    public static Feature<PillarFeatureConfiguration> PILLAR_FEATURE;
    public static ConfiguredFeature<PillarFeatureConfiguration, ?> SALT_PILLAR_CONFIGURED;
    public static PlacedFeature SALT_PILLAR_PLACED;

    public static ConfiguredFeature<?, ?> BLACKWOOD_TREE_CONFIGURED;
    public static ConfiguredFeature<?, ?> BLACKWOOD_TREE_TALL_CONFIGURED;
    public static PlacedFeature BLACKWOOD_TREE_PLACED;
    public static PlacedFeature BLACKWOOD_TREE_TALL_PLACED;

    public static void initialize() {
        ACID_LAKE_CONFIGURED = register(Feature.LAKE.configured(
                        new LakeFeature.Configuration(BlockStateProvider.simple(OrbitBlocks.ACID.defaultBlockState()),
                        BlockStateProvider.simple(OrbitBlocks.SALT_BLOCK.defaultBlockState()))), "acid_lake");
        ACID_LAKE_PLACED = register(OrbitFeatures.ACID_LAKE_CONFIGURED.placed(
                        RarityFilter.onAverageOnceEvery(5),
                        InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()), "acid_lake_surface");
        ACID_LAKE_PLACED_FREQUENT = register(OrbitFeatures.ACID_LAKE_CONFIGURED.placed(
                RarityFilter.onAverageOnceEvery(2),
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()), "acid_lake_surface_frequent");

        PILLAR_FEATURE = register(new PillarFeature(PillarFeatureConfiguration.CODEC), "salt_pillar");
        SALT_PILLAR_CONFIGURED = register(PILLAR_FEATURE.configured(
                new PillarFeatureConfiguration(
                        UniformInt.of(5, 15), UniformInt.of(3, 10), UniformInt.of(3, 10), UniformInt.of(3, 10), UniformInt.of(3, 10), 
                        BlockStateProvider.simple(OrbitBlocks.SALT_BLOCK))),
                "salt_pillar_configured");
        SALT_PILLAR_PLACED = register(OrbitFeatures.SALT_PILLAR_CONFIGURED.placed(
                RarityFilter.onAverageOnceEvery(2),
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()), "salt_pillar_surface");

        BLACKWOOD_TREE_CONFIGURED = register(Feature.TREE
                .configured(new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(OrbitBlocks.BLACKWOOD_LOG), new StraightTrunkPlacer(8, 3, 0), BlockStateProvider.simple(OrbitBlocks.BLACKWOOD_LEAVES), new BlobFoliagePlacer(UniformInt.of(1, 3), ConstantInt.of(0), 4), new TwoLayersFeatureSize(2, 3, 2)).build()), "blackwood_tree_configured");
                                                                                                                                                                                                            //radius, offset from trunk, height
        BLACKWOOD_TREE_TALL_CONFIGURED = register(Feature.TREE
                .configured(new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(OrbitBlocks.BLACKWOOD_LOG), new StraightTrunkPlacer(10, 3, 6), BlockStateProvider.simple(OrbitBlocks.BLACKWOOD_LEAVES), new BlobFoliagePlacer(UniformInt.of(1, 4), ConstantInt.of(1), 6), new TwoLayersFeatureSize(2, 3, 2)).build()), "blackwood_tree_tall_configured");
        BLACKWOOD_TREE_PLACED = register(BLACKWOOD_TREE_CONFIGURED.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()), "blackwood_tree_placed");
        BLACKWOOD_TREE_TALL_PLACED = register(BLACKWOOD_TREE_TALL_CONFIGURED.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()), "blackwood_tree_tall_placed");
    }

    public static <FC extends FeatureConfiguration> Feature<FC> register(Feature<FC> feature, String name) {
        return Registry.register(Registry.FEATURE, Orbit.newId(name), feature);
    }

    public static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(ConfiguredFeature<FC, ?> configuredFeature, String name) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, Orbit.newId(name), configuredFeature);
    }

    public static PlacedFeature register(PlacedFeature feature, String name) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, Orbit.newId(name), feature);
    }

}