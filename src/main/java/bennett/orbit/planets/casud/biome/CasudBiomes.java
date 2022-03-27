package bennett.orbit.planets.casud.biome;

import bennett.orbit.Orbit;
import bennett.orbit.planets.OrbitWorldGenUtils;
import bennett.orbit.planets.casud.Casud;
import bennett.orbit.planets.casud.feature.OrbitFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Music;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.jetbrains.annotations.Nullable;

import static bennett.orbit.planets.OrbitWorldGenUtils.range;
import static bennett.orbit.util.OrbitUtils.RegistryType;
import static bennett.orbit.util.OrbitUtils.findHolder;
import static net.minecraft.world.entity.MobCategory.CREATURE;
import static net.minecraft.world.entity.MobCategory.MONSTER;

public class CasudBiomes {

    public static final ResourceKey<Biome> BLACKWOOD_FOREST_KEY = register("blackwood_forest");
    public static final ResourceKey<Biome> ACID_OCEAN_KEY = register("acid_ocean");
    public static final ResourceKey<Biome> DEEP_ACID_OCEAN_KEY = register("deep_acid_ocean");
    public static final ResourceKey<Biome> SALTY_FLATLANDS_KEY = register("salty_flatlands");
    public static final ResourceKey<Biome> SALTY_BEACH_KEY = register("salty_beach");

    public static Holder<Biome> BLACKWOOD_FOREST;
    public static Holder<Biome> ACID_OCEAN;
    public static Holder<Biome> DEEP_ACID_OCEAN;
    public static Holder<Biome> SALTY_FLATLANDS;
    public static Holder<Biome> SALTY_BEACH;

    public static final Climate.ParameterPoint BLACKWOOD_FOREST_PARAMETER;
    public static final Climate.ParameterPoint ACID_OCEAN_PARAMETER;
    public static final Climate.ParameterPoint DEEP_ACID_OCEAN_PARAMETER;
    public static final Climate.ParameterPoint SALTY_FLATLANDS_PARAMETER;
    public static final Climate.ParameterPoint SALTY_BEACH_PARAMETER;

    static {
        BLACKWOOD_FOREST_PARAMETER = Climate.parameters(range(OrbitWorldGenUtils.temperatures[0], OrbitWorldGenUtils.temperatures[3]), OrbitWorldGenUtils.humidities[1], OrbitWorldGenUtils.MID_INLAND_CONTINENTALNESS, OrbitWorldGenUtils.erosions[2], OrbitWorldGenUtils.FULL_RANGE, OrbitWorldGenUtils.FULL_RANGE, 0.0f);
        ACID_OCEAN_PARAMETER = Climate.parameters(OrbitWorldGenUtils.FULL_RANGE, OrbitWorldGenUtils.FULL_RANGE, OrbitWorldGenUtils.OCEAN_CONTINENTALNESS, OrbitWorldGenUtils.FULL_RANGE, OrbitWorldGenUtils.FULL_RANGE, OrbitWorldGenUtils.FULL_RANGE, 0.0f);
        DEEP_ACID_OCEAN_PARAMETER = Climate.parameters(OrbitWorldGenUtils.FULL_RANGE, OrbitWorldGenUtils.FULL_RANGE, OrbitWorldGenUtils.DEEP_OCEAN_CONTINENTALNESS, OrbitWorldGenUtils.FULL_RANGE, OrbitWorldGenUtils.FULL_RANGE, OrbitWorldGenUtils.FULL_RANGE, 0.0f);
        SALTY_FLATLANDS_PARAMETER = Climate.parameters(range(OrbitWorldGenUtils.temperatures[4], OrbitWorldGenUtils.temperatures[5]), OrbitWorldGenUtils.humidities[1], OrbitWorldGenUtils.INLAND_CONTINENTALNESS, OrbitWorldGenUtils.erosions[4], OrbitWorldGenUtils.FULL_RANGE, OrbitWorldGenUtils.FULL_RANGE, 0.0f);
        SALTY_BEACH_PARAMETER = Climate.parameters(OrbitWorldGenUtils.FULL_RANGE, OrbitWorldGenUtils.FULL_RANGE, OrbitWorldGenUtils.COAST_CONTINENTALNESS, OrbitWorldGenUtils.FULL_RANGE, OrbitWorldGenUtils.FULL_RANGE, OrbitWorldGenUtils.FULL_RANGE, 0.0f);
    }

    public static Biome blackwoodForest() {
        MobSpawnSettings.Builder mobSpawnSettingsBuilder = new MobSpawnSettings.Builder().addSpawn(MONSTER, new SpawnerData(EntityType.ZOMBIE, 80, 1, 4));
        BiomeGenerationSettings.Builder biomeGenBuilder = addCarvers(new BiomeGenerationSettings.Builder());
        biomeGenBuilder.addFeature(GenerationStep.Decoration.LAKES, (Holder<PlacedFeature>) findHolder(OrbitFeatures.ACID_LAKE_KEY, RegistryType.PLACED_FEATURE)).addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, (Holder<PlacedFeature>) findHolder(OrbitFeatures.BLACKWOOD_TREE_KEY, RegistryType.PLACED_FEATURE)).addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, (Holder<PlacedFeature>) findHolder(OrbitFeatures.BLACKWOOD_TALL_KEY, RegistryType.PLACED_FEATURE)).addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, (Holder<PlacedFeature>) findHolder(OrbitFeatures.BLACKWOOD_MEGA_KEY, RegistryType.PLACED_FEATURE));
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
        MobSpawnSettings.Builder mobSpawnSettingsBuilder = new MobSpawnSettings.Builder().addSpawn(MONSTER, new SpawnerData(EntityType.HUSK, 80, 1, 4)).addSpawn(CREATURE, new SpawnerData(EntityType.CHICKEN, 120, 1, 3));
        BiomeGenerationSettings.Builder biomeGenBuilder = addCarvers(new BiomeGenerationSettings.Builder())
                .addFeature(GenerationStep.Decoration.LAKES, (Holder<PlacedFeature>) findHolder(OrbitFeatures.ACID_LAKE_FREQUENT_KEY, RegistryType.PLACED_FEATURE))
                .addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, (Holder<PlacedFeature>) findHolder(OrbitFeatures.SALT_PILLAR_KEY, RegistryType.PLACED_FEATURE));
        return Biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.PLAINS, 1.0f, 0.3f, mobSpawnSettingsBuilder, biomeGenBuilder, null);
    }

    public static Biome saltyBeach() {
        MobSpawnSettings.Builder mobSpawnSettingsBuilder = new MobSpawnSettings.Builder().addSpawn(MONSTER, new SpawnerData(EntityType.HUSK, 80, 1, 4)).addSpawn(CREATURE, new SpawnerData(EntityType.CHICKEN, 120, 1, 3));
        BiomeGenerationSettings.Builder biomeGenBuilder = addCarvers(new BiomeGenerationSettings.Builder());
        return Biome(Biome.Precipitation.RAIN, Biome.BiomeCategory.PLAINS, 1.0f, 0.3f, mobSpawnSettingsBuilder, biomeGenBuilder, null);
    }

    public static Biome Biome(Biome.Precipitation precipitation, Biome.BiomeCategory biomeCategory, float temperature, float downfall, MobSpawnSettings.Builder mobSpawnBuilder, BiomeGenerationSettings.Builder biomeGenBuilder, @Nullable Music music) {
        return new Biome.BiomeBuilder().precipitation(precipitation).biomeCategory(biomeCategory).temperature(temperature).downfall(downfall).specialEffects(new BiomeSpecialEffects.Builder().waterColor(Casud.CASUD_WATER_COLOR).waterFogColor(Casud.CASUD_WATER_FOG_COLOR).fogColor(Casud.CASUD_FOG_COLOR).skyColor(Casud.CASUD_SKY_COLOR).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(biomeGenBuilder.build()).build();
    }

    public static BiomeGenerationSettings.Builder addCarvers(BiomeGenerationSettings.Builder biomeGenBuilder) {
        return biomeGenBuilder.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE).addCarver(GenerationStep.Carving.LIQUID, Carvers.CAVE)
                .addCarver(GenerationStep.Carving.AIR, Carvers.CANYON).addCarver(GenerationStep.Carving.LIQUID, Carvers.CANYON)
                .addCarver(GenerationStep.Carving.AIR, Carvers.CAVE_EXTRA_UNDERGROUND).addCarver(GenerationStep.Carving.LIQUID, Carvers.CAVE_EXTRA_UNDERGROUND);
    }

    public static void initialize() {
        BLACKWOOD_FOREST = register(BLACKWOOD_FOREST_KEY, blackwoodForest());
        ACID_OCEAN = register(ACID_OCEAN_KEY, acidOcean());
        DEEP_ACID_OCEAN = register(DEEP_ACID_OCEAN_KEY, deepAcidOcean());
        SALTY_FLATLANDS = register(SALTY_FLATLANDS_KEY, saltyFlatlands());
        SALTY_BEACH = register(SALTY_BEACH_KEY, saltyBeach());
    }
    public static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registry.BIOME_REGISTRY, Orbit.newId(name));
    }
    public static Holder<Biome> register(ResourceKey<Biome> key, Biome biome) {
        return BuiltinRegistries.register(BuiltinRegistries.BIOME, key, biome);
    }
}