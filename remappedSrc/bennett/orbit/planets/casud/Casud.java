package bennett.orbit.planets.casud;

import bennett.orbit.Orbit;
import bennett.orbit.planets.OrbitPlanets;
import bennett.orbit.planets.casud.configuration.CasudNoiseGenSettings;
import java.util.OptionalLong;
import java.util.function.Supplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class Casud {
    public static final ResourceKey<DimensionType> CASUD_LOCATION = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, Orbit.newId("casud"));

    public static final DimensionType TYPE = DimensionType.create(OptionalLong.empty(), true, false, true, true, 1.0, false, false, true, false, false, 0, 256, 192, Orbit.newId("infiniburn_casud"), DimensionType.OVERWORLD_EFFECTS, 0.25f);
    public static final int CASUD_SKY_COLOR = 3163680;
    public static final int CASUD_WATER_COLOR = 3163680;
    public static final int CASUD_FOG_COLOR = 3163680;
    public static final int CASUD_WATER_FOG_COLOR = 10541966;

    public static Supplier<DimensionType> TYPE_SUPPLIER = () -> TYPE;
    public static Supplier<NoiseGeneratorSettings> NOISE_GEN_SETTINGS_SUPPLIER = () -> CasudNoiseGenSettings.SETTINGS;


    public static final  Registry<NormalNoise.NoiseParameters> noiseRegistry = OrbitPlanets.registryAccess.registryOrThrow(Registry.NOISE_REGISTRY);

                                                                                                                                                                                                                                                    //Temp, Humid, Cont, Eros, Depth, Weird, Offset
    //public static final MultiNoiseBiomeSource.Preset CASUD_BIOME_SOURCE_PRESET = new MultiNoiseBiomeSource.Preset(Orbit.newId("casud"), registry -> new Climate.ParameterList<Supplier<Biome>>(ImmutableList.of(Pair.of(Climate.parameters(OrbitUtils.FULL_RANGE, OrbitUtils.humidities[1], OrbitUtils.midInlandContinentalness, OrbitUtils.erosions[2], OrbitUtils.FULL_RANGE, OrbitUtils.FULL_RANGE, 0.0f), (Supplier<Biome>) () -> biomeRegistry.getOrThrow(CasudBiomes.BLACKWOOD_FOREST_KEY)), Pair.of(Climate.parameters(OrbitUtils.FULL_RANGE, OrbitUtils.FULL_RANGE, OrbitUtils.FULL_RANGE, OrbitUtils.oceanContinentalness, OrbitUtils.FULL_RANGE, OrbitUtils.FULL_RANGE, 0.0f), (Supplier<Biome>) () -> biomeRegistry.getOrThrow(CasudBiomes.ACID_OCEAN_KEY)), Pair.of(Climate.parameters(OrbitUtils.FULL_RANGE, OrbitUtils.FULL_RANGE, OrbitUtils.deepOceanContinentalness, OrbitUtils.FULL_RANGE, OrbitUtils.FULL_RANGE, OrbitUtils.FULL_RANGE, 0.0f), (Supplier<Biome>)() -> biomeRegistry.getOrThrow(CasudBiomes.DEEP_ACID_OCEAN_KEY)), Pair.of(Climate.parameters(OrbitUtils.temperatures[3], OrbitUtils.humidities[1], OrbitUtils.inlandContinentalness, OrbitUtils.erosions[4], OrbitUtils.FULL_RANGE, OrbitUtils.FULL_RANGE, 0.0f), (Supplier<Biome>)() -> biomeRegistry.getOrThrow(CasudBiomes.SALTY_FLATLANDS_KEY)))));
    //public static final BiomeSource CASUD_BIOME_SOURCE= CASUD_BIOME_SOURCE_PRESET.biomeSource(biomeRegistry);

    //public static final NoiseBasedChunkGenerator CASUD_CHUNK_GENERATOR = new NoiseBasedChunkGenerator(noiseRegistry, CASUD_BIOME_SOURCE, Orbit.SEED.getAsLong(),  NOISE_GEN_SETTINGS_SUPPLIER);
    //public static final ResourceKey<LevelStem> CASUD_STEM_KEY = ResourceKey.create(Registry.LEVEL_STEM_REGISTRY, Orbit.newId("casud"));
    //public static final LevelStem STEM = new LevelStem(TYPE_SUPPLIER, CASUD_CHUNK_GENERATOR);


    public static void initialize() {
        //CasudBiomes.initialize();
    }
}
