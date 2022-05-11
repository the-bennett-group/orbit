package the.bennett.group.orbit.world;

import com.mojang.serialization.Lifecycle;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.DensityFunction;
import the.bennett.group.orbit.util.RegistryUtils;

import java.util.OptionalLong;
import java.util.function.Function;

public class OrbitPlanetGenUtils {
    public static final Climate.Parameter DEEP_OCEAN_CONTINENTALNESS = Climate.Parameter.span(-1.05f, -0.455f);
    public static final Climate.Parameter OCEAN_CONTINENTALNESS = Climate.Parameter.span(-0.455f, -0.19f);
    public static final Climate.Parameter COAST_CONTINENTALNESS = Climate.Parameter.span(-0.19f, -0.11f);
    public static final Climate.Parameter INLAND_CONTINENTALNESS = Climate.Parameter.span(-0.11f, 0.55f);
    public static final Climate.Parameter NEAR_INLAND_CONTINENTALNESS = Climate.Parameter.span(-0.11f, 0.03f);
    public static final Climate.Parameter MID_INLAND_CONTINENTALNESS = Climate.Parameter.span(0.03f, 0.3f);
    public static final Climate.Parameter FAR_INLAND_CONTINENTALNESS = Climate.Parameter.span(0.3f, 1.0f);
    public static final Climate.Parameter FULL_RANGE = Climate.Parameter.span(-1.0f, 1.0f);
    public static final Climate.Parameter[] temperatures = new Climate.Parameter[]{Climate.Parameter.span(-1.0f, -0.45f), Climate.Parameter.span(-0.45f, -0.15f), Climate.Parameter.span(-0.15f, 0.2f), Climate.Parameter.span(0.2f, 0.55f), Climate.Parameter.span(0.55f, 1.0f)};
    public static final Climate.Parameter[] humidities = new Climate.Parameter[]{Climate.Parameter.span(-1.0f, -0.35f), Climate.Parameter.span(-0.35f, -0.1f), Climate.Parameter.span(-0.1f, 0.1f), Climate.Parameter.span(0.1f, 0.3f), Climate.Parameter.span(0.3f, 1.0f)};
    public static final Climate.Parameter[] erosions = new Climate.Parameter[]{Climate.Parameter.span(-1.0f, -0.78f), Climate.Parameter.span(-0.78f, -0.375f), Climate.Parameter.span(-0.375f, -0.2225f), Climate.Parameter.span(-0.2225f, 0.05f), Climate.Parameter.span(0.05f, 0.45f), Climate.Parameter.span(0.45f, 0.55f), Climate.Parameter.span(0.55f, 1.0f)};

    public static final WritableRegistry<DimensionType> dimensionTypeRegistry = RegistryAccess.builtinCopy().ownedWritableRegistryOrThrow(Registry.DIMENSION_TYPE_REGISTRY);
    public static final WritableRegistry<LevelStem> levelStemRegistry = new MappedRegistry(Registry.LEVEL_STEM_REGISTRY, Lifecycle.experimental(), (Function)null);

    public static Climate.Parameter range(Climate.Parameter lowerEnd, Climate.Parameter highEnd) {
        return Climate.Parameter.span(lowerEnd.min(), highEnd.max());
    }


    public static final ResourceKey<DensityFunction> ZERO_KEY = (ResourceKey<DensityFunction>) RegistryUtils.makeKey("common/zero", Registry.DENSITY_FUNCTION_REGISTRY);
    //public static final ResourceKey<DensityFunction> BASE_3D_NOISE = (ResourceKey<DensityFunction>) RegistryUtils.makeKey("common/base_3d_noise", Registry.DENSITY_FUNCTION_REGISTRY);
    public static final ResourceKey<DensityFunction> Y_KEY = (ResourceKey<DensityFunction>) RegistryUtils.makeKey("common/y", Registry.DENSITY_FUNCTION_REGISTRY);
    public static final DensityFunction ZERO = RegistryUtils.findHolder(ZERO_KEY, BuiltinRegistries.DENSITY_FUNCTION).value();

    private static OptionalLong SEED;
    public static Registry<Biome> biomeRegistry = RegistryAccess.builtinCopy().registryOrThrow(Registry.BIOME_REGISTRY);

    public static void initialize() {

    }

    public static void setSeed(long seed) {
        SEED = OptionalLong.of(seed);
    }

    public static OptionalLong seed() {
        return SEED;
    }



    public static void nullifySeed() {
        SEED = OptionalLong.empty();
    }
}
