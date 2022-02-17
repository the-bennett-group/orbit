package bennett.orbit.planets.casud.biome;

import bennett.orbit.planets.casud.Casud;
import bennett.orbit.planets.casud.feature.OrbitFeatures;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.sounds.Music;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.entity.MobCategory.CREATURE;
import static net.minecraft.world.entity.MobCategory.MONSTER;
import static net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;

public class CasudBiomes {
    public static Biome blackwoodForest() {
        MobSpawnSettings.Builder mobSpawnSettingsBuilder = new MobSpawnSettings.Builder().addSpawn(MONSTER, new SpawnerData(EntityType.ZOMBIE, 80, 1, 4));
        BiomeGenerationSettings.Builder biomeGenBuilder = addCarvers(new BiomeGenerationSettings.Builder());
        biomeGenBuilder.addFeature(GenerationStep.Decoration.LAKES, OrbitFeatures.ACID_LAKE_PLACED).addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationFeatures.TREES_PLAINS.placed());
        BiomeDefaultFeatures.addDefaultGrass(biomeGenBuilder);

        return Biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.PLAINS, 1.0f, 0.5f, mobSpawnSettingsBuilder, biomeGenBuilder,  null);
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
        MobSpawnSettings.Builder mobSpawnSettingsBuilder = new MobSpawnSettings.Builder().addSpawn(MONSTER, new SpawnerData(EntityType.HUSK, 80, 1, 4)).addSpawn(CREATURE, new SpawnerData((EntityType.CHICKEN, 120, 1, 3));
        BiomeGenerationSettings.Builder biomeGenBuilder = addCarvers(new BiomeGenerationSettings.Builder())
                .addFeature(GenerationStep.Decoration.LAKES, OrbitFeatures.SALT_PILLAR_PLACED)
                .addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, OrbitFeatures.ACID_LAKE_PLACED_FREQUENT);
    }

    public static Biome Biome(Biome.Precipitation precipitation, Biome.BiomeCategory biomeCategory, float temperature, float downfall, MobSpawnSettings.Builder mobSpawnBuilder, BiomeGenerationSettings.Builder biomeGenBuilder, @Nullable Music music) {
        return new Biome.BiomeBuilder().precipitation(precipitation).biomeCategory(biomeCategory).temperature(temperature).downfall(downfall).specialEffects(new BiomeSpecialEffects.Builder().waterColor(Casud.CASUD_WATER_COLOR).waterFogColor(Casud.CASUD_WATER_FOG_COLOR).fogColor(Casud.CASUD_FOG_COLOR).skyColor(Casud.CASUD_SKY_COLOR).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(biomeGenBuilder.build()).build();
    }

    public static BiomeGenerationSettings.Builder addCarvers(BiomeGenerationSettings.Builder biomeGenBuilder) {
        return biomeGenBuilder.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE).addCarver(GenerationStep.Carving.LIQUID, Carvers.CAVE)
                .addCarver(GenerationStep.Carving.AIR, Carvers.CANYON).addCarver(GenerationStep.Carving.LIQUID, Carvers.CANYON)
                .addCarver(GenerationStep.Carving.AIR, Carvers.CAVE_EXTRA_UNDERGROUND).addCarver(GenerationStep.Carving.LIQUID, Carvers.CAVE_EXTRA_UNDERGROUND);
    }