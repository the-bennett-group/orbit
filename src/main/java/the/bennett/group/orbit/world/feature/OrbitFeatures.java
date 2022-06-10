package the.bennett.group.orbit.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import the.bennett.group.orbit.Orbit;
import the.bennett.group.orbit.blocks.OrbitBlocks;
import the.bennett.group.orbit.util.ContentCounter;
import the.bennett.group.orbit.util.RegistryUtils;
import the.bennett.group.orbit.world.feature.tree.blackwood.BlackwoodTreeGrower;
import the.bennett.group.orbit.world.feature.tree.blackwood.MegaBlackwoodTreeGrower;
import the.bennett.group.orbit.world.feature.tree.blackwood.TallBlackwoodTreeGrower;

import java.util.List;

@SuppressWarnings("Deprecated")
public class OrbitFeatures {
    //Casud's features
    public static ConfiguredFeature<LakeFeature.Configuration, ?> ACID_LAKE_CONFIGURED;
    public static PlacedFeature ACID_LAKE_PLACED;
    public static PlacedFeature ACID_LAKE_PLACED_FREQUENT;
    public static ResourceKey<PlacedFeature> ACID_LAKE_KEY;
    public static ResourceKey<PlacedFeature> ACID_LAKE_FREQUENT_KEY;

    public static Feature<PillarFeatureConfiguration> PILLAR_FEATURE;
    public static ConfiguredFeature<PillarFeatureConfiguration, ?> SALT_PILLAR_CONFIGURED;
    public static PlacedFeature SALT_PILLAR_PLACED;
    public static ResourceKey<PlacedFeature> SALT_PILLAR_KEY;

    public static Holder<ConfiguredFeature<?, ?>> BLACKWOOD;
    public static Holder<ConfiguredFeature<?, ?>> BLACKWOOD_TALL;
    public static Holder<ConfiguredFeature<?, ?>> BLACKWOOD_MEGA;
    public static ConfiguredFeature<TreeConfiguration, ?> BLACKWOOD_TREE_CONFIGURED;
    public static ConfiguredFeature<TreeConfiguration, ?> BLACKWOOD_TALL_CONFIGURED;
    public static ConfiguredFeature<TreeConfiguration, ?> BLACKWOOD_MEGA_CONFIGURED;


    public static void initialize() {
        //Casud's features
        ACID_LAKE_CONFIGURED = register(new ConfiguredFeature<>(Feature.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(OrbitBlocks.ACID.defaultBlockState()), BlockStateProvider.simple(OrbitBlocks.SALT_BLOCK.defaultBlockState()))), "acid_lake");
        ACID_LAKE_PLACED = register(new PlacedFeature(Holder.direct(ACID_LAKE_CONFIGURED), List.of(InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(5), BiomeFilter.biome())), "acid_lake_surface");
        ACID_LAKE_KEY = RegistryUtils.makeKey("acid_lake_surface", Registry.PLACED_FEATURE_REGISTRY);
        ACID_LAKE_PLACED_FREQUENT = register(new PlacedFeature(Holder.direct(ACID_LAKE_CONFIGURED), List.of(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), BiomeFilter.biome())), "acid_lake_surface_frequent");
        ACID_LAKE_FREQUENT_KEY = RegistryUtils.makeKey("acid_lake_surface_frequent", Registry.PLACED_FEATURE_REGISTRY);

        PILLAR_FEATURE = register(new PillarFeature(PillarFeatureConfiguration.CODEC), "salt_pillar");
        SALT_PILLAR_CONFIGURED = register(new ConfiguredFeature<>(PILLAR_FEATURE, new PillarFeatureConfiguration(UniformInt.of(5, 15), UniformInt.of(3, 10), UniformInt.of(3, 10), UniformInt.of(3, 10), UniformInt.of(3, 10), BlockStateProvider.simple(OrbitBlocks.SALT_BLOCK))), "salt_pillar_configured");
        SALT_PILLAR_PLACED = register(new PlacedFeature(Holder.direct(SALT_PILLAR_CONFIGURED), List.of(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), BiomeFilter.biome())), "salt_pillar_surface");
        SALT_PILLAR_KEY = RegistryUtils.makeKey("salt_pillar_surface", Registry.PLACED_FEATURE_REGISTRY);

        BLACKWOOD_TREE_CONFIGURED = new ConfiguredFeature<>(Feature.TREE, BlackwoodTreeGrower.CONFIGURATION);
        BLACKWOOD_TALL_CONFIGURED = new ConfiguredFeature<>(Feature.TREE, TallBlackwoodTreeGrower.TALL_CONFIGURATION);
        BLACKWOOD_MEGA_CONFIGURED = new ConfiguredFeature<>(Feature.TREE, MegaBlackwoodTreeGrower.TALLER_CONFIGURATION);

        BLACKWOOD = makeHolder("blackwood", BLACKWOOD_TREE_CONFIGURED);
        BLACKWOOD_TALL = makeHolder("blackwood_tall", BLACKWOOD_TALL_CONFIGURED);
        BLACKWOOD_MEGA = makeHolder("blackwood_mega", BLACKWOOD_MEGA_CONFIGURED);

    }


    public static <FC extends FeatureConfiguration> Feature<FC> register(Feature<FC> feature, String name) {
        return Registry.register(Registry.FEATURE, Orbit.newId(name), feature);
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> ConfiguredFeature<FC, ?> register(ConfiguredFeature<FC, F> configuredFeature, String name) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, Orbit.newId(name), configuredFeature);
    }

    public static PlacedFeature register(PlacedFeature feature, String name) {
        ContentCounter.countFeature();
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, Orbit.newId(name), feature);
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<?, ?>> makeHolder(String name, ConfiguredFeature<FC, ?> feature) {
        return BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_FEATURE, Orbit.newId(name), feature);
    }


}