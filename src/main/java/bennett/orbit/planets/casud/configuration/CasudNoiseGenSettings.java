package bennett.orbit.planets.casud.configuration;

import bennett.orbit.blocks.OrbitBlocks;
import bennett.orbit.fluid.OrbitFluids;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.CubicSpline;
import net.minecraft.world.level.biome.TerrainShaper;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.SurfaceRules.RuleSource;
import net.minecraft.world.level.levelgen.synth.NormalNoise.NoiseParameters;

import static bennett.orbit.planets.OrbitWorldGenUtils.Y;
import static bennett.orbit.util.OrbitUtils.*;

public class CasudNoiseGenSettings {
    public static final NoiseSettings NOISE_SETTINGS = NoiseSettings.create(0, 256, new NoiseSamplingSettings(1.0d, 0.5d, 80d, 300d), new NoiseSlider(0.4, 3, 0), new NoiseSlider(-0.078125, 2, 8), 4, 4, new TerrainShaper(CubicSpline.constant(0.0f), CubicSpline.constant(0.5f), CubicSpline.constant(1.0f)));

    public static final float ORE_THICKNESS = 0.08F;
    public static final double VEININESS_FREQUENCY = 1.5D;
    public static final double NOODLE_SPACING_AND_STRAIGHTNESS = 1.5D;
    public static final double SURFACE_DENSITY_THRESHOLD = 1.5625D;
    public static final int MIN_Y_FOR_ORE_VEINS = 16;
    public static final int MAX_Y_FOR_ORE_VEINS = 128;


    public static final ResourceKey<DensityFunction> SHIFT_X = (ResourceKey<DensityFunction>) makeKey("casud/syhift_x", RegistryType.DENSITY_FUNCTION);
    public static final ResourceKey<DensityFunction> SHIFT_Z = (ResourceKey<DensityFunction>) makeKey("casud/shift_z", RegistryType.DENSITY_FUNCTION);
    public static final ResourceKey<DensityFunction> CONTINENTS = (ResourceKey<DensityFunction>) makeKey("casud/continents", RegistryType.DENSITY_FUNCTION);
    public static final ResourceKey<DensityFunction> EROSION = (ResourceKey<DensityFunction>) makeKey("casud/erosion", RegistryType.DENSITY_FUNCTION);
    public static final ResourceKey<DensityFunction> RIDGES = (ResourceKey<DensityFunction>) makeKey("casud/ridges", RegistryType.DENSITY_FUNCTION);
    public static final ResourceKey<DensityFunction> FACTOR = (ResourceKey<DensityFunction>) makeKey("casud/factor", RegistryType.DENSITY_FUNCTION);
    public static final ResourceKey<DensityFunction> DEPTH = (ResourceKey<DensityFunction>) makeKey("casud/depth", RegistryType.DENSITY_FUNCTION);
    public static final ResourceKey<DensityFunction> SLOPED_CHEESE = (ResourceKey<DensityFunction>) makeKey("casud/sloped_cheese", RegistryType.DENSITY_FUNCTION);
    public static final ResourceKey<DensityFunction> SPAGHETTI_ROUGHNESS_FUNCTION = (ResourceKey<DensityFunction>) makeKey("casud/spaghetti_roughness_function", RegistryType.DENSITY_FUNCTION);
    public static final ResourceKey<DensityFunction> ENTRANCES = (ResourceKey<DensityFunction>) makeKey("casud/entrances", RegistryType.DENSITY_FUNCTION);
    public static final ResourceKey<DensityFunction> NOODLE = (ResourceKey<DensityFunction>) makeKey("casud/noodle", RegistryType.DENSITY_FUNCTION);
    public static final ResourceKey<DensityFunction> PILLARS = (ResourceKey<DensityFunction>) makeKey("casud/pillars", RegistryType.DENSITY_FUNCTION);
    public static final ResourceKey<DensityFunction> SPAGHETTI_2D = (ResourceKey<DensityFunction>) makeKey("casud/spaghetti_2d", RegistryType.DENSITY_FUNCTION);
    public static final ResourceKey<DensityFunction> CAVE_LAYER = (ResourceKey<DensityFunction>) makeKey("casud/cave_layer", RegistryType.DENSITY_FUNCTION);
    public static final ResourceKey<DensityFunction> ORE_VEININESS = (ResourceKey<DensityFunction>) makeKey("casud/ore_veininess", RegistryType.DENSITY_FUNCTION);
    public static final ResourceKey<DensityFunction> ORE_VEIN_A = (ResourceKey<DensityFunction>) makeKey("casud/ore_vein_a", RegistryType.DENSITY_FUNCTION);
    public static final ResourceKey<DensityFunction> ORE_VEIN_B = (ResourceKey<DensityFunction>) makeKey("casud/ore_vein_b", RegistryType.DENSITY_FUNCTION);

    public static final ResourceKey<NoiseParameters> AQUIFER_BARRIER = (ResourceKey<NoiseParameters>) makeKey("casud/aquifer_barrier", RegistryType.NOISE_PARAMETERS);
    public static final ResourceKey<NoiseParameters> AQUIFER_FLUID_LEVEL_FLOODEDNESS = (ResourceKey<NoiseParameters>) makeKey("casud/aquifer_fluid_level_floodedness", RegistryType.NOISE_PARAMETERS);
    public static final ResourceKey<NoiseParameters> AQUIFER_FLUID_LEVEL_SPREAD = (ResourceKey<NoiseParameters>) makeKey("casud/aquifer_fluid_level_spread", RegistryType.NOISE_PARAMETERS);
    public static final ResourceKey<NoiseParameters> AQUIFER_ACID = (ResourceKey<NoiseParameters>) makeKey("casud/aquifer_acid", RegistryType.NOISE_PARAMETERS);
    public static final ResourceKey<NoiseParameters> CAVE_CHEESE = (ResourceKey<NoiseParameters>) makeKey("casud/cave_cheese", RegistryType.NOISE_PARAMETERS);
    //public static final ResourceKey<NoiseParameters> JAGGED = (ResourceKey<NoiseParameters>) makeKey("casud/jagged", RegistryType.NOISE_PARAMETERS);
    public static final ResourceKey<NoiseParameters> TEMPERATURE = (ResourceKey<NoiseParameters>) makeKey("casud/temperature", RegistryType.NOISE_PARAMETERS);
    public static final ResourceKey<NoiseParameters> VEGETATION = (ResourceKey<NoiseParameters>) makeKey("casud/vegetation", RegistryType.NOISE_PARAMETERS);
    public static final ResourceKey<NoiseParameters> ORE_GAP = (ResourceKey<NoiseParameters>) makeKey("casud/ore_gap", RegistryType.NOISE_PARAMETERS);

    public static final DensityFunction AQUIFER_BARRIER_FUNCTION = DensityFunctions.noise((Holder<NoiseParameters>) findHolder(AQUIFER_BARRIER, RegistryType.NOISE_PARAMETERS));
    public static final DensityFunction AQUIFER_FLUID_LEVEL_FLOODEDNESS_FUNCTION = DensityFunctions.noise((Holder<NoiseParameters>) findHolder(AQUIFER_FLUID_LEVEL_FLOODEDNESS, RegistryType.NOISE_PARAMETERS));
    public static final DensityFunction AQUIFER_FLUID_LEVEL_SPREAD_FUNCTION = DensityFunctions.noise((Holder<NoiseParameters>) findHolder(AQUIFER_FLUID_LEVEL_SPREAD, RegistryType.NOISE_PARAMETERS));
    public static final DensityFunction AQUIFER_ACID_FUNCTION = DensityFunctions.noise((Holder<NoiseParameters>) findHolder(AQUIFER_ACID, RegistryType.NOISE_PARAMETERS));
    public static final DensityFunction SHIFT_X_FUNCTION = DensityFunctions.noise((Holder<NoiseParameters>) findHolder(SHIFT_X,  RegistryType.NOISE_PARAMETERS));
    public static final DensityFunction SHIFT_Z_FUNCTION = DensityFunctions.noise((Holder<NoiseParameters>) findHolder(SHIFT_Z,  RegistryType.NOISE_PARAMETERS));
    public static final DensityFunction TEMPERATURE_FUNCTION = DensityFunctions.shiftedNoise2d(SHIFT_X_FUNCTION, SHIFT_Z_FUNCTION, 0.25d, (Holder<NoiseParameters>) findHolder(TEMPERATURE, RegistryType.NOISE_PARAMETERS));
    public static final DensityFunction VEGETATION_FUNCTION = DensityFunctions.shiftedNoise2d(SHIFT_X_FUNCTION, SHIFT_Z_FUNCTION, 0.25d, (Holder<NoiseParameters>) findHolder(VEGETATION, RegistryType.NOISE_PARAMETERS));
    public static final DensityFunction DEPTH_AND_FACTOR_2D = noiseGradientDensity(DensityFunctions.cache2d(unwrapDensity( DEPTH)), unwrapDensity(DEPTH));
    public static final DensityFunction ENTRANCES_MODIFIED = DensityFunctions.min(unwrapDensity(ENTRANCES), DensityFunctions.mul(DensityFunctions.constant(5.0D), unwrapDensity(ENTRANCES)));
    public static final DensityFunction CAVES_AND_STUFF = DensityFunctions.rangeChoice(unwrapDensity(SLOPED_CHEESE), -1000000.0D, 1.5625D, ENTRANCES_MODIFIED, cavesAndStuff(SLOPED_CHEESE));
    public static final DensityFunction CAVES_AND_STUFF_PLUS_NOODLES = DensityFunctions.min(postProcess(NOISE_SETTINGS, CAVES_AND_STUFF), unwrapDensity(NOODLE));
    public static final DensityFunction INTERPOLATED_VEININESS = yLimitedInterpolatable(unwrapDensity(Y), DensityFunctions.noise((Holder<NoiseParameters>) findHolder(ORE_VEININESS, RegistryType.DENSITY_FUNCTION), 1.5D, 1.5D), MIN_Y_FOR_ORE_VEINS, MAX_Y_FOR_ORE_VEINS, 0);
    public static final DensityFunction ORE_VEIN_A_FUNCTION = yLimitedInterpolatable(unwrapDensity(Y), DensityFunctions.noise((Holder<NoiseParameters>) findHolder(ORE_VEIN_A, RegistryType.DENSITY_FUNCTION), 4.0D, 4.0D), MIN_Y_FOR_ORE_VEINS, MAX_Y_FOR_ORE_VEINS, 0).abs();
    public static final DensityFunction ORE_VEIN_B_FUNCTION = yLimitedInterpolatable(unwrapDensity(Y), DensityFunctions.noise((Holder<NoiseParameters>) findHolder(ORE_VEIN_B, RegistryType.DENSITY_FUNCTION), 4.0D, 4.0D), MIN_Y_FOR_ORE_VEINS, MAX_Y_FOR_ORE_VEINS, 0).abs();
    public static final DensityFunction ORE_VEIN_FUNCTION_FINAL = DensityFunctions.add(DensityFunctions.constant(-0.07999999821186066D), DensityFunctions.max(ORE_VEIN_A_FUNCTION, ORE_VEIN_B_FUNCTION));
    public static final DensityFunction ORE_GAP_FUNCTION = DensityFunctions.noise((Holder<NoiseParameters>) findHolder(ORE_GAP, RegistryType.DENSITY_FUNCTION));

    public static final NoiseRouterWithOnlyNoises ROUTER;
    public static final RuleSource RULE_SOURCE = CasudNoiseGenSettings.ruleSource();

    public static final NoiseGeneratorSettings SETTINGS;

    static {

        ROUTER = new NoiseRouterWithOnlyNoises(
                AQUIFER_BARRIER_FUNCTION,
                AQUIFER_FLUID_LEVEL_FLOODEDNESS_FUNCTION,
                AQUIFER_FLUID_LEVEL_SPREAD_FUNCTION,
                AQUIFER_ACID_FUNCTION,
                TEMPERATURE_FUNCTION,
                VEGETATION_FUNCTION,
                unwrapDensity(CONTINENTS),
                unwrapDensity(EROSION),
                unwrapDensity(DEPTH),
                unwrapDensity(RIDGES),
                DEPTH_AND_FACTOR_2D,
                CAVES_AND_STUFF_PLUS_NOODLES,
                INTERPOLATED_VEININESS,
                ORE_VEIN_FUNCTION_FINAL,
                ORE_GAP_FUNCTION
        );


        SETTINGS = new NoiseGeneratorSettings(
                NOISE_SETTINGS,
                OrbitBlocks.SALT_BLOCK.defaultBlockState(),
                OrbitFluids.SOURCE_ACID.defaultFluidState().createLegacyBlock(),
                ROUTER,
                RULE_SOURCE,
                64,
                false,
                true,
                true,
                false);
        }



    public static DensityFunction cavesAndStuff(ResourceKey<DensityFunction> densityFunctionKey) {
        DensityFunction spaghetti2d = unwrapDensity(SPAGHETTI_2D);
        DensityFunction spaghettiRoughness = unwrapDensity(SPAGHETTI_ROUGHNESS_FUNCTION);
        DensityFunction caveLayerFunction = DensityFunctions.noise((Holder<NoiseParameters>) findHolder(CAVE_LAYER, RegistryType.NOISE_PARAMETERS), 8.0D);
        DensityFunction caveLayerFunctionModified = DensityFunctions.mul(DensityFunctions.constant(4.0D), caveLayerFunction.square());
        DensityFunction caveLayerFunctionModifiedAgain = DensityFunctions.noise((Holder<NoiseParameters>) findHolder(CAVE_CHEESE, RegistryType.DENSITY_FUNCTION), 0.6666666666666666D);
        DensityFunction caveLayerFunctionModifiedAgainThenClamped = DensityFunctions.add(DensityFunctions.add(DensityFunctions.constant(0.27D), caveLayerFunctionModifiedAgain).clamp(-1.0D, 1.0D), DensityFunctions.add(DensityFunctions.constant(1.5D), DensityFunctions.mul(DensityFunctions.constant(-0.64D), unwrapDensity(densityFunctionKey))).clamp(0.0D, 0.5D));
        DensityFunction caveLayerFunctionModifiedAndClampedCaveLayerFunction = DensityFunctions.add(caveLayerFunctionModified, caveLayerFunctionModifiedAgainThenClamped);
        DensityFunction allTheCaveLayersTogether = DensityFunctions.min(DensityFunctions.min(caveLayerFunctionModifiedAndClampedCaveLayerFunction, unwrapDensity(ENTRANCES)), DensityFunctions.add(spaghetti2d, spaghettiRoughness));
        DensityFunction pillarFunction = unwrapDensity(PILLARS);
        DensityFunction pillarFunctionModified = DensityFunctions.rangeChoice(pillarFunction, -1000000.0D, 0.03D, DensityFunctions.constant(-1000000.0D), pillarFunction);
        return DensityFunctions.max(allTheCaveLayersTogether, pillarFunctionModified);

    }

    public static RuleSource ruleSource() {
        return SurfaceRules.state(OrbitBlocks.SALT_BLOCK.defaultBlockState());
    }

    public static void initialize() {

    }

    public static DensityFunction unwrapDensity(ResourceKey<DensityFunction> densityFunctionKey) {
        return ((Holder<DensityFunction>) findHolder(densityFunctionKey, RegistryType.DENSITY_FUNCTION)).value();
    }


     private static DensityFunction noiseGradientDensity(DensityFunction densityFunction, DensityFunction densityFunction2) {
            DensityFunction densityFunction3 = DensityFunctions.mul(densityFunction2, densityFunction);
            return DensityFunctions.mul(DensityFunctions.constant(4.0D), densityFunction3.quarterNegative());
     }

     public static DensityFunction postProcess(NoiseSettings noiseSettings, DensityFunction densityFunction) {
        DensityFunction densityFunctionWithSlide = DensityFunctions.slide(noiseSettings, densityFunction);
        DensityFunction blendedDensityFunction = DensityFunctions.blendDensity(densityFunctionWithSlide);
        return DensityFunctions.mul(DensityFunctions.interpolated(blendedDensityFunction), DensityFunctions.constant(0.64D)).squeeze();
    }

    //todo: figure out what the heck is going on here
    public static DensityFunction yLimitedInterpolatable(DensityFunction densityFunction, DensityFunction densityFunction2, int i, int j, int k) {
        return DensityFunctions.interpolated(DensityFunctions.rangeChoice(densityFunction, (double)i, (double)(j + 1), densityFunction2, DensityFunctions.constant((double)k)));
    }



}
