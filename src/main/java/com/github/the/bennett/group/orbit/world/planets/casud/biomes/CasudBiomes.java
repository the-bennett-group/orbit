package com.github.the.bennett.group.orbit.world.planets.casud.biomes;

import com.github.the.bennett.group.orbit.util.OrbitUtils;
import com.github.the.bennett.group.orbit.world.feature.OrbitFeatures;
import com.github.the.bennett.group.orbit.world.planets.casud.dimension.CasudDimensionWrapper;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.sounds.Music;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.entity.MobCategory.CREATURE;
import static net.minecraft.world.entity.MobCategory.MONSTER;
import static net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;

public class CasudBiomes {
    public static final Biome BLACKWOOD_FOREST = blackwoodForest();
    public static final Biome ACID_OCEAN = acidOcean();
    public static final Biome DEEP_ACID_OCEAN = deepAcidOcean();
    public static final Biome SALTY_FLATLANDS = saltyFlatlands();
    public static final Biome SALTY_BEACH = saltyBeach();

    public static void initialize() {
    }

    public static Biome blackwoodForest() {
        MobSpawnSettings.Builder mobSpawnSettingsBuilder = new MobSpawnSettings.Builder().addSpawn(MONSTER, new SpawnerData(EntityType.ZOMBIE, 80, 1, 4));
        BiomeGenerationSettings.Builder biomeGenBuilder = addCarvers(new BiomeGenerationSettings.Builder());
        biomeGenBuilder.addFeature(GenerationStep.Decoration.LAKES, OrbitUtils.findHolder(OrbitFeatures.ACID_LAKE_PLACED, BuiltinRegistries.PLACED_FEATURE));
        BiomeDefaultFeatures.addDefaultGrass(biomeGenBuilder);

        return Biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.PLAINS, 1.0f, 0.5f, mobSpawnSettingsBuilder, biomeGenBuilder, null);
    }

    public static Biome acidOcean() {
        MobSpawnSettings.Builder mobSpawnSettingsBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeGenBuilder = addCarvers(new BiomeGenerationSettings.Builder());

        return Biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.OCEAN, 0.5f, 1.0f, mobSpawnSettingsBuilder, biomeGenBuilder, null);
    }

    public static Biome deepAcidOcean() {
        return acidOcean();
    }

    public static Biome saltyFlatlands() {
        MobSpawnSettings.Builder mobSpawnSettingsBuilder = new MobSpawnSettings.Builder().addSpawn(MONSTER, new SpawnerData(EntityType.HUSK, 80, 1, 4)).addSpawn(CREATURE, new SpawnerData(EntityType.CHICKEN, 120, 1, 3));
        BiomeGenerationSettings.Builder biomeGenBuilder = addCarvers(new BiomeGenerationSettings.Builder())
                .addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, OrbitUtils.findHolder(OrbitFeatures.SALT_PILLAR_PLACED, BuiltinRegistries.PLACED_FEATURE))
                .addFeature(GenerationStep.Decoration.LAKES, OrbitUtils.findHolder(OrbitFeatures.ACID_LAKE_PLACED_FREQUENT, BuiltinRegistries.PLACED_FEATURE));
        return Biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.PLAINS, 0.0f, 0.5f, mobSpawnSettingsBuilder, biomeGenBuilder, null);
    }

    public static Biome saltyBeach() {
        MobSpawnSettings.Builder mobSpawnSettingsBuilder = new MobSpawnSettings.Builder().addSpawn(MONSTER, new SpawnerData(EntityType.HUSK, 80, 1, 4)).addSpawn(CREATURE, new SpawnerData(EntityType.CHICKEN, 120, 1, 3));
        BiomeGenerationSettings.Builder biomeGenBuilder = addCarvers(new BiomeGenerationSettings.Builder())
                .addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, OrbitUtils.findHolder(OrbitFeatures.SALT_PILLAR_PLACED, BuiltinRegistries.PLACED_FEATURE))
                .addFeature(GenerationStep.Decoration.LAKES, OrbitUtils.findHolder(OrbitFeatures.ACID_LAKE_PLACED_FREQUENT, BuiltinRegistries.PLACED_FEATURE));
        return Biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.PLAINS, 0.0f, 0.5f, mobSpawnSettingsBuilder, biomeGenBuilder, null);
    }

    public static Biome Biome(Biome.Precipitation precipitation, Biome.BiomeCategory biomeCategory, float temperature, float downfall, MobSpawnSettings.Builder mobSpawnBuilder, BiomeGenerationSettings.Builder biomeGenBuilder, @Nullable Music music) {
        return new Biome.BiomeBuilder().precipitation(precipitation).biomeCategory(biomeCategory).temperature(temperature).downfall(downfall).specialEffects(new BiomeSpecialEffects.Builder().waterFogColor(CasudDimensionWrapper.FOG_COLOR).fogColor(CasudDimensionWrapper.FOG_COLOR).skyColor(CasudDimensionWrapper.SKY_COLOR).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(biomeGenBuilder.build()).build();
    }

    public static BiomeGenerationSettings.Builder addCarvers(BiomeGenerationSettings.Builder biomeGenBuilder) {
        return biomeGenBuilder.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE).addCarver(GenerationStep.Carving.LIQUID, Carvers.CAVE)
                .addCarver(GenerationStep.Carving.AIR, Carvers.CANYON).addCarver(GenerationStep.Carving.LIQUID, Carvers.CANYON)
                .addCarver(GenerationStep.Carving.AIR, Carvers.CAVE_EXTRA_UNDERGROUND).addCarver(GenerationStep.Carving.LIQUID, Carvers.CAVE_EXTRA_UNDERGROUND);
    }
}