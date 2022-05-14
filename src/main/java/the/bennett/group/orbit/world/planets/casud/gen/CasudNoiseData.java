package the.bennett.group.orbit.world.planets.casud.gen;

import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunctions;
import the.bennett.group.orbit.util.RegistryUtils;
import the.bennett.group.orbit.world.planets.casud.CasudDimensionData;

import static net.minecraft.world.level.levelgen.DensityFunctions.TerrainShaperSpline;
import static net.minecraft.world.level.levelgen.NoiseRouterData.SHIFT_X;
import static net.minecraft.world.level.levelgen.NoiseRouterData.SHIFT_Z;
import static net.minecraft.world.level.levelgen.synth.NormalNoise.NoiseParameters;

public class CasudNoiseData {
    //initialize noises
    private static final ResourceKey<NoiseParameters> CONTINENTALNESS_NOISE_KEY = makeNoiseKey("continentalness");
    private static final ResourceKey<NoiseParameters> EROSION_NOISE_KEY = makeNoiseKey("erosion");
    private static final ResourceKey<NoiseParameters> RIDGES_NOISE_KEY = makeNoiseKey("ridge");
    private static final ResourceKey<NoiseParameters> JAGGED_NOISE_KEY = makeNoiseKey("jagged");
    private static final ResourceKey<NoiseParameters> OFFSET_NOISE_KEY = makeNoiseKey("offset");
    private static final ResourceKey<NoiseParameters> TEMPERATURE_NOISE_KEY = makeNoiseKey("temperature");
    private static final ResourceKey<NoiseParameters> VEGETATION_NOISE_KEY = makeNoiseKey("vegetation");
    private static final ResourceKey<NoiseParameters> ALTERATION_NOISE_KEY = makeNoiseKey("alteration");

    //initialize all the density function keys
    public static final ResourceKey<DensityFunction> CONTINENTS_KEY = makeDFKey("continentalness");
    public static final ResourceKey<DensityFunction> EROSION_KEY = makeDFKey("erosion");
    public static final ResourceKey<DensityFunction> RIDGES_KEY = makeDFKey("ridges");
    public static final ResourceKey<DensityFunction> DEPTH_KEY = makeDFKey("depth");
    public static final ResourceKey<DensityFunction> OFFSET_KEY = makeDFKey("offset");
    public static final ResourceKey<DensityFunction> FACTOR_KEY = makeDFKey("factor");
    public static final ResourceKey<DensityFunction> TEMPERATURE_KEY = makeDFKey("temperature");
    public static final ResourceKey<DensityFunction> VEGETATION_KEY = makeDFKey("vegetation");
    public static final ResourceKey<DensityFunction> ALTERATION_KEY = makeDFKey("alteration");
    public static final ResourceKey<DensityFunction> BEFORE_JAGGEDNESS_KEY = makeDFKey("before_jaggedness");
    public static final ResourceKey<DensityFunction> FINAL_DENSITY_KEY = makeDFKey("final_density");

    //initialize all the noises
    public static final NoiseParameters CONTINENTALNESS_NOISE = bindNoise(CONTINENTALNESS_NOISE_KEY, new NoiseParameters(-9, DoubleList.of(1.0, 1.0, 2.0, 2.0, 2.0, 1.0, 1.0, 1.0, 2.0)));
    public static final NoiseParameters EROSION_NOISE = bindNoise(EROSION_NOISE_KEY, new NoiseParameters(-9, DoubleList.of(1.0, 1.0, 0.0, 1.0, 1.0)));
    public static final NoiseParameters RIDGES_NOISE = bindNoise(RIDGES_NOISE_KEY, new NoiseParameters(-9, DoubleList.of(1.0, 2.0, 1.0, 0.0, 0.0, 0.0)));
    public static final NoiseParameters JAGGED_NOISE = bindNoise(JAGGED_NOISE_KEY, new NoiseParameters(-16, DoubleList.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0)));
    public static final NoiseParameters TEMPERATURE_NOISE = bindNoise(TEMPERATURE_NOISE_KEY, new NoiseParameters(-10, DoubleList.of( 1.5, 0.0, 1.0, 0.0, 0.0, 0.0)));
    public static final NoiseParameters VEGETATION_NOISE = bindNoise(VEGETATION_NOISE_KEY, new NoiseParameters(-8, DoubleList.of(1.0, 1.0, 0.0, 0.0, 0.0, 0.0)));
    public static final NoiseParameters ALTERATION_NOISE = bindNoise(ALTERATION_NOISE_KEY, new NoiseParameters(-9, DoubleList.of(2.0, 1.5, 1.5)));
    public static final NoiseParameters OFFSET_NOISE = bindNoise(OFFSET_NOISE_KEY, new NoiseParameters(-3, DoubleList.of(1.0, 1.0, 1.0, 0.0)));

    //initialize all the density functions
    public static final DensityFunction CONTINENTS = bindDF(CONTINENTS_KEY, shiftedNoise(CONTINENTALNESS_NOISE_KEY));
    public static final DensityFunction EROSION = bindDF(EROSION_KEY, shiftedNoise(EROSION_NOISE_KEY));
    public static final DensityFunction RIDGES = bindDF(RIDGES_KEY, shiftedNoise(RIDGES_NOISE_KEY));
    private static final DensityFunction BLENDING_FACTOR = DensityFunctions.constant(10.0);
    public static final DensityFunction OFFSET = bindDF(OFFSET_KEY, blendedSpline(-0.81, 2.5, TerrainShaperSpline.SplineType.OFFSET, DensityFunctions.blendOffset()));
    public static final DensityFunction FACTOR = bindDF(FACTOR_KEY, blendedSpline(0.0, 8.0, TerrainShaperSpline.SplineType.FACTOR, BLENDING_FACTOR));
    public static final DensityFunction DEPTH = bindDF(DEPTH_KEY, DensityFunctions.add(DensityFunctions.yClampedGradient(CasudDimensionData.MIN_HEIGHT, CasudDimensionData.MAX_HEIGHT, 1.5, -1.5), OFFSET));
    public static final DensityFunction TEMPERATURE = bindDF(TEMPERATURE_KEY, shiftedNoise(TEMPERATURE_NOISE_KEY));
    public static final DensityFunction VEGETATION = bindDF(VEGETATION_KEY, shiftedNoise(VEGETATION_NOISE_KEY));
    public static final DensityFunction ALTERATION = bindDF(ALTERATION_KEY, shiftedNoise(ALTERATION_NOISE_KEY));
    public static final DensityFunction BEFORE_JAGGEDNESS = bindDF(BEFORE_JAGGEDNESS_KEY, noiseGradientDensity(DensityFunctions.cache2d(FACTOR), DEPTH));
    //public static final DensityFunction FINAL_DENSITY = bind(FINAL_DENSITY_KEY)

    public static ResourceKey<DensityFunction> makeDFKey(String name) {
        return RegistryUtils.makeKey("casud/" + name, BuiltinRegistries.DENSITY_FUNCTION);
    }

    public static ResourceKey<NoiseParameters> makeNoiseKey(String name) {
        return RegistryUtils.makeKey("casud/" + name, BuiltinRegistries.NOISE);
    }

    private static NoiseParameters getNoise(ResourceKey<NoiseParameters> key) {
        return BuiltinRegistries.NOISE.getHolderOrThrow(key).value();
    }

    private static DensityFunction shiftedNoise(ResourceKey<NoiseParameters> key) {
        return DensityFunctions.shiftedNoise2d(getDF(SHIFT_X), getDF(SHIFT_Z), 0.25, noiseHolder(key));
    }

    private static DensityFunction getDF(ResourceKey<DensityFunction> key) {
        return BuiltinRegistries.DENSITY_FUNCTION.getHolderOrThrow(key).value();
    }

    private static DensityFunction bindDF(ResourceKey<DensityFunction> key, DensityFunction function) {
        return BuiltinRegistries.register(BuiltinRegistries.DENSITY_FUNCTION, key, function).value();
    }

    private static NoiseParameters bindNoise(ResourceKey<NoiseParameters> key, NoiseParameters function) {
        return BuiltinRegistries.register(BuiltinRegistries.NOISE, key, function).value();
    }

    private static Holder<NoiseParameters> noiseHolder(ResourceKey<NoiseParameters> noiseKey) {
        return RegistryUtils.findHolder(noiseKey, BuiltinRegistries.NOISE);
    }

    private static DensityFunction blendedSpline(double d, double e, TerrainShaperSpline.SplineType splineType, DensityFunction densityFunction) {
        DensityFunction baseTerrainFunction = DensityFunctions.terrainShaperSpline(CasudNoiseData.CONTINENTS, CasudNoiseData.EROSION, CasudNoiseData.RIDGES, splineType, d, e);
        DensityFunction blender = DensityFunctions.lerp(DensityFunctions.blendAlpha(), densityFunction, baseTerrainFunction);
        return DensityFunctions.flatCache(DensityFunctions.cache2d(blender));
    }

    private static DensityFunction noiseGradientDensity(DensityFunction densityFunction, DensityFunction densityFunction2) {
        DensityFunction densityFunction3 = DensityFunctions.mul(densityFunction2, densityFunction);
        return DensityFunctions.mul(DensityFunctions.constant(4.0), densityFunction3.quarterNegative());
    }

    public static void initialize() {

    }

}