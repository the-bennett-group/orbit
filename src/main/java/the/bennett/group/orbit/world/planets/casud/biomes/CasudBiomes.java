package the.bennett.group.orbit.world.planets.casud.biomes;

import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Music;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.jetbrains.annotations.Nullable;
import the.bennett.group.orbit.util.ContentCounter;
import the.bennett.group.orbit.util.RegistryUtils;
import the.bennett.group.orbit.world.feature.OrbitFeatures;
import the.bennett.group.orbit.world.planets.casud.Casud;

import static net.minecraft.world.entity.MobCategory.CREATURE;
import static net.minecraft.world.entity.MobCategory.MONSTER;
import static net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;

public class CasudBiomes {
    //TODO, move the resourcekeys to a central OrbitBiomes class
    public static final ResourceKey<Biome> BLACKWOOD_FOREST = RegistryUtils.makeKeyAndRegisterToo("blackwood_forest", blackwoodForest(), BuiltinRegistries.BIOME);
    public static final ResourceKey<Biome> ACID_OCEAN = RegistryUtils.makeKeyAndRegisterToo("acid_ocean", acidOcean(), BuiltinRegistries.BIOME);
    public static final ResourceKey<Biome> DEEP_ACID_OCEAN = RegistryUtils.makeKeyAndRegisterToo("deep_acid_ocean", deepAcidOcean(), BuiltinRegistries.BIOME);
    public static final ResourceKey<Biome> SALTY_FLATLANDS = RegistryUtils.makeKeyAndRegisterToo("salty_flatlands", saltyFlatlands(), BuiltinRegistries.BIOME);
    public static final ResourceKey<Biome> SALTY_BEACH = RegistryUtils.makeKeyAndRegisterToo("salty_beach", saltyBeach(), BuiltinRegistries.BIOME);

    public static void initialize() {
    }

    public static Biome blackwoodForest() {
        MobSpawnSettings.Builder mobSpawnSettingsBuilder = new MobSpawnSettings.Builder().addSpawn(MONSTER, new SpawnerData(EntityType.ZOMBIE, 80, 1, 4));
        BiomeGenerationSettings.Builder biomeGenBuilder = addCarvers(new BiomeGenerationSettings.Builder());
        biomeGenBuilder.addFeature(GenerationStep.Decoration.LAKES, RegistryUtils.findHolder(OrbitFeatures.ACID_LAKE_KEY, BuiltinRegistries.PLACED_FEATURE));
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
                .addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, RegistryUtils.findHolder(OrbitFeatures.SALT_PILLAR_KEY, BuiltinRegistries.PLACED_FEATURE))
                .addFeature(GenerationStep.Decoration.LAKES, RegistryUtils.findHolder(OrbitFeatures.ACID_LAKE_FREQUENT_KEY, BuiltinRegistries.PLACED_FEATURE));
        return Biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.PLAINS, 0.0f, 0.5f, mobSpawnSettingsBuilder, biomeGenBuilder, null);
    }

    public static Biome saltyBeach() {
        MobSpawnSettings.Builder mobSpawnSettingsBuilder = new MobSpawnSettings.Builder().addSpawn(MONSTER, new SpawnerData(EntityType.HUSK, 80, 1, 4)).addSpawn(CREATURE, new SpawnerData(EntityType.CHICKEN, 120, 1, 3));
        BiomeGenerationSettings.Builder biomeGenBuilder = addCarvers(new BiomeGenerationSettings.Builder())
                .addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, RegistryUtils.findHolder(OrbitFeatures.SALT_PILLAR_KEY, BuiltinRegistries.PLACED_FEATURE))
                .addFeature(GenerationStep.Decoration.LAKES, RegistryUtils.findHolder(OrbitFeatures.ACID_LAKE_FREQUENT_KEY, BuiltinRegistries.PLACED_FEATURE));
        return Biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.PLAINS, 0.0f, 0.5f, mobSpawnSettingsBuilder, biomeGenBuilder, null);
    }

    public static Biome Biome(Biome.Precipitation precipitation, Biome.BiomeCategory biomeCategory, float temperature, float downfall, MobSpawnSettings.Builder mobSpawnBuilder, BiomeGenerationSettings.Builder biomeGenBuilder, @Nullable Music music) {
        ContentCounter.countBiome();
        //TODO add water color and biome-level coloring?
        return new Biome.BiomeBuilder().precipitation(precipitation).biomeCategory(biomeCategory).temperature(temperature).downfall(downfall).specialEffects(new BiomeSpecialEffects.Builder().waterFogColor(Casud.FOG_COLOR).fogColor(Casud.FOG_COLOR).skyColor(Casud.SKY_COLOR).waterColor(Casud.SKY_COLOR).waterFogColor(Casud.FOG_COLOR).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(biomeGenBuilder.build()).build();
    }

    public static BiomeGenerationSettings.Builder addCarvers(BiomeGenerationSettings.Builder biomeGenBuilder) {
        return biomeGenBuilder.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE).addCarver(GenerationStep.Carving.LIQUID, Carvers.CAVE)
                .addCarver(GenerationStep.Carving.AIR, Carvers.CANYON).addCarver(GenerationStep.Carving.LIQUID, Carvers.CANYON)
                .addCarver(GenerationStep.Carving.AIR, Carvers.CAVE_EXTRA_UNDERGROUND).addCarver(GenerationStep.Carving.LIQUID, Carvers.CAVE_EXTRA_UNDERGROUND);
    }
}