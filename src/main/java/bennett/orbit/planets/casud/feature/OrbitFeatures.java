package bennett.orbit.planets.casud.feature;

import bennett.orbit.Orbit;
import bennett.orbit.blocks.OrbitBlocks;
import bennett.orbit.util.OrbitUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
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

import java.util.List;

@SuppressWarnings("Deprecated")
public class OrbitFeatures {
    public static ConfiguredFeature<LakeFeature.Configuration, ?> ACID_LAKE_CONFIGURED;
    public static PlacedFeature ACID_LAKE_PLACED;
    public static ResourceKey<PlacedFeature> ACID_LAKE_KEY;
    public static PlacedFeature ACID_LAKE_PLACED_FREQUENT;

    public static Feature<PillarFeatureConfiguration> PILLAR_FEATURE;
    public static ConfiguredFeature<PillarFeatureConfiguration, ?> SALT_PILLAR_CONFIGURED;
    public static PlacedFeature SALT_PILLAR_PLACED;
    public static ResourceKey<PlacedFeature> SALT_PILLAR_KEY;

    public static ConfiguredFeature<?, ?> BLACKWOOD_TREE_CONFIGURED;
    public static ConfiguredFeature<?, ?> BLACKWOOD_TREE_TALL_CONFIGURED;
    public static PlacedFeature BLACKWOOD_TREE_PLACED;
    public static PlacedFeature BLACKWOOD_TREE_TALL_PLACED;
    public static ResourceKey<PlacedFeature> BLACKWOOD_TREE_KEY;
    public static ResourceKey<PlacedFeature> BLACKWOOD_TREE_TALL_KEY;

    public static void initialize() {
        ACID_LAKE_CONFIGURED = register(new ConfiguredFeature<>(Feature.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(OrbitBlocks.ACID.defaultBlockState()), BlockStateProvider.simple(OrbitBlocks.SALT_BLOCK.defaultBlockState()))), "acid_lake");
        ACID_LAKE_PLACED = register(new PlacedFeature(Holder.direct(ACID_LAKE_CONFIGURED), List.of(InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(5), BiomeFilter.biome())), "acid_lake_surface");
        ACID_LAKE_KEY = OrbitUtils.makeKey("acid_lake_surface");
        ACID_LAKE_PLACED_FREQUENT = register(new PlacedFeature(Holder.direct(ACID_LAKE_CONFIGURED), List.of(RarityFilter.onAverageOnceEvery(2),InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())), "acid_lake_surface_frequent");

        PILLAR_FEATURE = register(new PillarFeature(PillarFeatureConfiguration.CODEC), "salt_pillar");
        SALT_PILLAR_CONFIGURED = register(new ConfiguredFeature<>(PILLAR_FEATURE, new PillarFeatureConfiguration(UniformInt.of(5, 15), UniformInt.of(3, 10), UniformInt.of(3, 10), UniformInt.of(3, 10), UniformInt.of(3, 10), BlockStateProvider.simple(OrbitBlocks.SALT_BLOCK))), "salt_pillar_configured");
        SALT_PILLAR_PLACED = register(new PlacedFeature(Holder.direct(SALT_PILLAR_CONFIGURED), List.of(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())), "salt_pillar_surface");
        SALT_PILLAR_KEY = OrbitUtils.makeKey("salt_pillar_surface");

        BLACKWOOD_TREE_CONFIGURED = register(new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(OrbitBlocks.BLACKWOOD_LOG), new StraightTrunkPlacer(8, 3, 0), BlockStateProvider.simple(OrbitBlocks.BLACKWOOD_LEAVES), new BlobFoliagePlacer(UniformInt.of(1, 3), ConstantInt.of(0), 4), new TwoLayersFeatureSize(2, 3, 2)).build()), "blackwood_tree_configured");
                                                                                                                                                                                                            //radius, offset from trunk, height
        BLACKWOOD_TREE_TALL_CONFIGURED = register(new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(OrbitBlocks.BLACKWOOD_LOG), new StraightTrunkPlacer(10, 3, 6), BlockStateProvider.simple(OrbitBlocks.BLACKWOOD_LEAVES), new BlobFoliagePlacer(UniformInt.of(1, 4), ConstantInt.of(1), 6), new TwoLayersFeatureSize(2, 3, 2)).build()), "blackwood_tree_tall_configured");
        BLACKWOOD_TREE_PLACED = register(new PlacedFeature(Holder.direct(BLACKWOOD_TREE_CONFIGURED), List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())), "blackwood_tree_placed");
        BLACKWOOD_TREE_TALL_PLACED = register(new PlacedFeature(Holder.direct(BLACKWOOD_TREE_TALL_CONFIGURED), List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())), "blackwood_tree_tall_placed");
        BLACKWOOD_TREE_KEY = OrbitUtils.makeKey("blackwood_tree_placed");
        BLACKWOOD_TREE_TALL_KEY = OrbitUtils.makeKey("blackwood_tree_tall_placed");
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