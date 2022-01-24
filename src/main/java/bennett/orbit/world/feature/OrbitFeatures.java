package bennett.orbit.world.feature;

import bennett.orbit.Orbit;
import bennett.orbit.blocks.OrbitBlocks;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.impl.biome.modification.BuiltInRegistryKeys;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

@SuppressWarnings("Deprecated")
public class OrbitFeatures {
    public static ConfiguredFeature<LakeFeature.Configuration, ?> ACID_LAKE_CONFIGURED;
    public static PlacedFeature ACID_LAKE_PLACED;

    public static Feature<SaltPillarFeatureConfiguration> SALT_PILLAR;
    public static  ConfiguredFeature<SaltPillarFeatureConfiguration, ?> SALT_PILLAR_CONFIGURED;
    public static PlacedFeature SALT_PILLAR_PLACED;

    public static void initialize() {
        ACID_LAKE_CONFIGURED = register(Feature.LAKE.configured(
                        new LakeFeature.Configuration(BlockStateProvider.simple(OrbitBlocks.ACID.defaultBlockState()),
                        BlockStateProvider.simple(OrbitBlocks.SALT_BLOCK.defaultBlockState()))), "acid_lake");
        ACID_LAKE_PLACED = register(OrbitFeatures.ACID_LAKE_CONFIGURED.placed(
                        RarityFilter.onAverageOnceEvery(3),
                        InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()), "acid_lake_surface");

        SALT_PILLAR = register(new SaltPillarFeature(SaltPillarFeatureConfiguration.CODEC), "salt_pillar");
        SALT_PILLAR_CONFIGURED = register(SALT_PILLAR.configured(
                new SaltPillarFeatureConfiguration(
                        UniformInt.of(5, 15), UniformInt.of(3, 10), UniformInt.of(3, 10), UniformInt.of(3, 10), UniformInt.of(3, 10))),
                "salt_pillar_configured");
        SALT_PILLAR_PLACED = register(OrbitFeatures.SALT_PILLAR_CONFIGURED.placed(
                RarityFilter.onAverageOnceEvery(2),
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()), "salt_pillar_surface");
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