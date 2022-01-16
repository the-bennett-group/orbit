package bennett.orbit.world.feature;

import bennett.orbit.Orbit;
import bennett.orbit.blocks.OrbitBlocks;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.impl.biome.modification.BuiltInRegistryKeys;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.MiscOverworldFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
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
    public static ConfiguredFeature<LakeFeature.Configuration, ?> ACID_LAKE;
    public static  PlacedFeature ACID_LAKE_SURFACE;

    public static void initialize() {
        ACID_LAKE = register(Feature.LAKE.configured(
                        new LakeFeature.Configuration(BlockStateProvider.simple(OrbitBlocks.ACID.defaultBlockState()),
                        BlockStateProvider.simple(OrbitBlocks.SALT_BLOCK.defaultBlockState()))), "acid_lake");

        ACID_LAKE_SURFACE = register(OrbitFeatures.ACID_LAKE.placed(
                        RarityFilter.onAverageOnceEvery(13),
                        InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()), "acid_lake_surface");

        BiomeModifications.addFeature(BiomeSelectors.all(), GenerationStep.Decoration.UNDERGROUND_ORES, BuiltInRegistryKeys.get(ACID_LAKE_SURFACE));
    }

    public static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(ConfiguredFeature<FC, ?> configuredFeature, String name) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, Orbit.newId(name), configuredFeature);
    }

    public static PlacedFeature register(PlacedFeature feature, String name) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, Orbit.newId(name), feature);
    }


}