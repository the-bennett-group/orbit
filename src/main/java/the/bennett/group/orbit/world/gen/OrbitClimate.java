package the.bennett.group.orbit.world.gen;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.QuartPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.levelgen.DensityFunction;

import java.util.List;

public class OrbitClimate extends Climate {
    protected static final int PARAMETER_COUNT = 8;

    //useful climate stuff
    public static final Climate.Parameter ZERO = Parameter.point(0.0F);
    public static final OrbitClimate.Parameter DEEP_OCEAN_CONTINENTALNESS = OrbitClimate.Parameter.span(-1.05f, -0.455f);
    public static final OrbitClimate.Parameter OCEAN_CONTINENTALNESS = OrbitClimate.Parameter.span(-0.455f, -0.19f);
    public static final OrbitClimate.Parameter COAST_CONTINENTALNESS = OrbitClimate.Parameter.span(-0.19f, -0.11f);
    public static final OrbitClimate.Parameter INLAND_CONTINENTALNESS = OrbitClimate.Parameter.span(-0.11f, 0.55f);
    public static final OrbitClimate.Parameter NEAR_INLAND_CONTINENTALNESS = OrbitClimate.Parameter.span(-0.11f, 0.03f);
    public static final OrbitClimate.Parameter MID_INLAND_CONTINENTALNESS = OrbitClimate.Parameter.span(0.03f, 0.3f);
    public static final OrbitClimate.Parameter FAR_INLAND_CONTINENTALNESS = OrbitClimate.Parameter.span(0.3f, 1.0f);
    public static final OrbitClimate.Parameter FULL_RANGE = OrbitClimate.Parameter.span(-1.0f, 1.0f);
    public static final OrbitClimate.Parameter[] temperatures = new OrbitClimate.Parameter[]{OrbitClimate.Parameter.span(-1.0f, -0.45f), OrbitClimate.Parameter.span(-0.45f, -0.15f), OrbitClimate.Parameter.span(-0.15f, 0.2f), OrbitClimate.Parameter.span(0.2f, 0.55f), OrbitClimate.Parameter.span(0.55f, 1.0f)};
    public static final OrbitClimate.Parameter[] humidities = new OrbitClimate.Parameter[]{OrbitClimate.Parameter.span(-1.0f, -0.35f), OrbitClimate.Parameter.span(-0.35f, -0.1f), OrbitClimate.Parameter.span(-0.1f, 0.1f), OrbitClimate.Parameter.span(0.1f, 0.3f), OrbitClimate.Parameter.span(0.3f, 1.0f)};
    public static final OrbitClimate.Parameter[] erosions = new OrbitClimate.Parameter[]{OrbitClimate.Parameter.span(-1.0f, -0.78f), OrbitClimate.Parameter.span(-0.78f, -0.375f), OrbitClimate.Parameter.span(-0.375f, -0.2225f), OrbitClimate.Parameter.span(-0.2225f, 0.05f), OrbitClimate.Parameter.span(0.05f, 0.45f), OrbitClimate.Parameter.span(0.45f, 0.55f), OrbitClimate.Parameter.span(0.55f, 1.0f)};

    public static OrbitClimate.Parameter range(OrbitClimate.Parameter lowerEnd, OrbitClimate.Parameter highEnd) {
        return OrbitClimate.Parameter.span(lowerEnd.min(), highEnd.max());
    }

    public static class TargetPoint extends Climate.TargetPoint{
        final long temperature;
        final long vegetation;
        final long continentalness;
        final long erosion;
        final long depth;
        final long alteration;
        final long weirdness;

        public TargetPoint(long temperature, long vegetation, long continentalness, long erosion, long depth, long weirdness) {
            this(temperature, vegetation, continentalness, erosion, depth, weirdness, 0L);
        }

        public TargetPoint(long temperature, long vegetation, long continentalness, long erosion, long depth, long weirdness, long alteration) {
            super(temperature, vegetation, continentalness, erosion, depth, weirdness);
            this.temperature = temperature;
            this.vegetation = vegetation;
            this.continentalness = continentalness;
            this.erosion = erosion;
            this.depth = depth;
            this.weirdness = weirdness;
            this.alteration = alteration;
        }

        @VisibleForTesting
        protected long[] toParameterArray() {
            return new long[]{this.temperature, this.vegetation, this.continentalness, this.erosion, this.depth, this.weirdness, 0L};
        }

        public long alteration() {
            return this.alteration;
        }
    }

    public static class ParameterPoint extends Climate.ParameterPoint {
        public static final Codec<OrbitClimate.ParameterPoint> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Parameter.CODEC.fieldOf("temperature").forGetter((parameterPoint) -> parameterPoint.temperature),
            Parameter.CODEC.fieldOf("vegetation").forGetter((parameterPoint) -> parameterPoint.vegetation),
            Parameter.CODEC.fieldOf("continentalness").forGetter((parameterPoint) -> parameterPoint.continentalness),
            Parameter.CODEC.fieldOf("erosion").forGetter((parameterPoint) -> parameterPoint.erosion),
            Parameter.CODEC.fieldOf("depth").forGetter((parameterPoint) -> parameterPoint.depth),
            Parameter.CODEC.fieldOf("weirdness").forGetter((parameterPoint) -> parameterPoint.weirdness),
            Parameter.CODEC.fieldOf("alteration").forGetter((parameterPoint) -> parameterPoint.alteration),
            Codec.floatRange(0.0F, 1.0F).fieldOf("offset").xmap(Climate::quantizeCoord, Climate::unquantizeCoord).forGetter((parameterPoint) -> parameterPoint.offset))
            .apply(instance, ParameterPoint::new));
        public Parameter temperature;
        private final Parameter vegetation;
        private final Parameter continentalness;
        private final Parameter erosion;
        private final Parameter depth;
        private final Parameter weirdness;
        private final Parameter alteration;
        private final long offset;

        public ParameterPoint(Parameter temperature, Parameter vegetation, Parameter continentalness, Parameter erosion, Parameter depth, Parameter weirdness, long offset) {
            this(temperature, vegetation, continentalness, erosion, depth, weirdness, new Parameter(0L, 0L), 0);
        }

        public ParameterPoint(Parameter temperature, Parameter vegetation, Parameter continentalness, Parameter erosion, Parameter depth, Parameter weirdness, Parameter alteration, long offset) {
            super(temperature, vegetation, continentalness, erosion, depth, weirdness, offset);
            this.temperature = temperature;
            this.vegetation = vegetation;
            this.continentalness = continentalness;
            this.erosion = erosion;
            this.depth = depth;
            this.weirdness = weirdness;
            this.offset = offset;
            this.alteration = alteration;
        }

        public long fitness(OrbitClimate.TargetPoint point) {
            return Mth.square(this.temperature.distance(point.temperature)) + Mth.square(this.vegetation.distance(point.vegetation)) + Mth.square(this.continentalness.distance(point.continentalness)) + Mth.square(this.erosion.distance(point.erosion)) + Mth.square(this.depth.distance(point.depth)) + Mth.square(this.weirdness.distance(point.weirdness)) + Mth.square(this.alteration.distance(point.alteration)) + Mth.square(this.offset);
        }

        protected List<Parameter> parameterSpace() {
            return ImmutableList.of(this.temperature, this.vegetation, this.continentalness, this.erosion, this.depth, this.weirdness, this.alteration, new Parameter(this.offset, this.offset));
        }

        public Parameter alteration() {
            return this.alteration;
        }
    }

    public static class Sampler extends Climate.Sampler {
        private final DensityFunction temperature;
        private final DensityFunction humidity;
        private final DensityFunction continentalness;
        private final DensityFunction erosion;
        private final DensityFunction depth;
        private final DensityFunction weirdness;
        private final DensityFunction alteration;
        private final List<OrbitClimate.ParameterPoint> spawnTarget;
        public <T extends Climate.ParameterPoint> Sampler(DensityFunction temperature, DensityFunction humidity, DensityFunction continentalness, DensityFunction erosion, DensityFunction depth, DensityFunction weirdness, DensityFunction alteration, List<T> list) {
            super(temperature, humidity, continentalness, erosion, depth, weirdness, (List<Climate.ParameterPoint>) list);
            this.temperature = temperature;
            this.humidity = humidity;
            this.continentalness = continentalness;
            this.erosion = erosion;
            this.depth = depth;
            this.weirdness = weirdness;
            this.alteration = alteration;
            this.spawnTarget = (List<ParameterPoint>) list;
        }

        public OrbitClimate.TargetPoint sample(int x, int y, int z) {
            int i = QuartPos.toBlock(x);
            int j = QuartPos.toBlock(y);
            int k = QuartPos.toBlock(z);
            DensityFunction.SinglePointContext singlePointContext = new DensityFunction.SinglePointContext(i, j, k);
            return OrbitClimate.orbitTarget((float)this.temperature.compute(singlePointContext), (float)this.humidity.compute(singlePointContext), (float)this.continentalness.compute(singlePointContext), (float)this.erosion.compute(singlePointContext), (float)this.depth.compute(singlePointContext), (float)this.weirdness.compute(singlePointContext), (float)this.alteration.compute(singlePointContext));
        }

        public BlockPos findSpawnPosition() {
            return this.spawnTarget.isEmpty() ? BlockPos.ZERO : OrbitClimate.findSpawnPosition(this.spawnTarget, this);
        }

        public DensityFunction temperature() {
            return this.temperature;
        }

        public DensityFunction humidity() {
            return this.humidity;
        }

        public DensityFunction continentalness() {
            return this.continentalness;
        }

        public DensityFunction erosion() {
            return this.erosion;
        }

        public DensityFunction depth() {
            return this.depth;
        }

        public DensityFunction weirdness() {
            return this.weirdness;
        }

        public List<OrbitClimate.ParameterPoint> spawnOrbitClimateTarget() {
            return this.spawnTarget;
        }
    }

    public static TargetPoint orbitTarget(float temperatureNoise, float humidityNoise, float continentalnessNoise, float erosionNoise, float depth, float weirdnessNoise, float alterationNoise) {
        return new TargetPoint(quantizeCoord(temperatureNoise), quantizeCoord(humidityNoise), quantizeCoord(continentalnessNoise), quantizeCoord(erosionNoise), quantizeCoord(depth), quantizeCoord(weirdnessNoise), quantizeCoord(alterationNoise));
    }

    public static ParameterPoint orbitParameters(float temperature, float humidity, float continentalness, float erosion, float depth, float weirdness, float alteration, float offset) {
        return new ParameterPoint(OrbitClimate.Parameter.point(temperature), OrbitClimate.Parameter.point(humidity), OrbitClimate.Parameter.point(continentalness), OrbitClimate.Parameter.point(erosion), OrbitClimate.Parameter.point(depth), OrbitClimate.Parameter.point(weirdness), Climate.Parameter.point(alteration), quantizeCoord(offset));
    }

    public static ParameterPoint orbitParameters(Parameter temperature, Parameter humidity, Parameter continentalness, Parameter erosion, Parameter depth, Parameter weirdness, Parameter alteration, float offset) {
        return new ParameterPoint(temperature, humidity, continentalness, erosion, depth, weirdness, alteration, quantizeCoord(offset));
    }

    public static BlockPos findSpawnPosition(List<OrbitClimate.ParameterPoint> noises, OrbitClimate.Sampler sampler) {
        return (new SpawnFinder(noises, sampler)).result.location();
    }



}
