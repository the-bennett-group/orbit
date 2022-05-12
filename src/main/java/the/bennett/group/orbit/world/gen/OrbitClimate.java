package the.bennett.group.orbit.world.gen;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Climate;

import java.util.List;

public class OrbitClimate extends Climate {
    protected static final int PARAMETER_COUNT = 8;

    public static class TargetPoint extends Climate.TargetPoint{
        final long temperature;
        final long humidity;
        final long continentalness;
        final long erosion;
        final long depth;
        final long alteration;
        final long weirdness;

        public TargetPoint(long temperature, long humidity, long continentalness, long erosion, long depth, long weirdness) {
            this(temperature, humidity, continentalness, erosion, depth, weirdness, 0L);
        }

        public TargetPoint(long temperature, long humidity, long continentalness, long erosion, long depth, long weirdness, long alteration) {
            super(temperature, humidity, continentalness, erosion, depth, weirdness);
            this.temperature = temperature;
            this.humidity = humidity;
            this.continentalness = continentalness;
            this.erosion = erosion;
            this.depth = depth;
            this.weirdness = weirdness;
            this.alteration = alteration;
        }

        @VisibleForTesting
        protected long[] toParameterArray() {
            return new long[]{this.temperature, this.humidity, this.continentalness, this.erosion, this.depth, this.weirdness, 0L};
        }

        public long alteration() {
            return this.alteration;
        }
    }

    public static class ParameterPoint extends Climate.ParameterPoint {
        public static final Codec<OrbitClimate.ParameterPoint> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Parameter.CODEC.fieldOf("temperature").forGetter((parameterPoint) -> parameterPoint.temperature),
            Parameter.CODEC.fieldOf("humidity").forGetter((parameterPoint) -> parameterPoint.humidity),
            Parameter.CODEC.fieldOf("continentalness").forGetter((parameterPoint) -> parameterPoint.continentalness),
            Parameter.CODEC.fieldOf("erosion").forGetter((parameterPoint) -> parameterPoint.erosion),
            Parameter.CODEC.fieldOf("depth").forGetter((parameterPoint) -> parameterPoint.depth),
            Parameter.CODEC.fieldOf("weirdness").forGetter((parameterPoint) -> parameterPoint.weirdness),
            Parameter.CODEC.fieldOf("alteration").forGetter((parameterPoint) -> parameterPoint.alteration),
            Codec.floatRange(0.0F, 1.0F).fieldOf("offset").xmap(Climate::quantizeCoord, Climate::unquantizeCoord).forGetter((parameterPoint) -> parameterPoint.offset))
            .apply(instance, ParameterPoint::new));
        public Parameter temperature;
        private final Parameter humidity;
        private final Parameter continentalness;
        private final Parameter erosion;
        private final Parameter depth;
        private final Parameter weirdness;
        private final Parameter alteration;
        private final long offset;

        public ParameterPoint(Parameter temperature, Parameter humidity, Parameter continentalness, Parameter erosion, Parameter depth, Parameter weirdness, long offset) {
            this(temperature, humidity, continentalness, erosion, depth, weirdness, new Parameter(0L, 0L), 0);
        }

        public ParameterPoint(Parameter temperature, Parameter humidity, Parameter continentalness, Parameter erosion, Parameter depth, Parameter weirdness, Parameter alteration, long offset) {
            super(temperature, humidity, continentalness, erosion, depth, weirdness, offset);
            this.temperature = temperature;
            this.humidity = humidity;
            this.continentalness = continentalness;
            this.erosion = erosion;
            this.depth = depth;
            this.weirdness = weirdness;
            this.offset = offset;
            this.alteration = alteration;
        }

        public long fitness(OrbitClimate.TargetPoint point) {
            return Mth.square(this.temperature.distance(point.temperature)) + Mth.square(this.humidity.distance(point.humidity)) + Mth.square(this.continentalness.distance(point.continentalness)) + Mth.square(this.erosion.distance(point.erosion)) + Mth.square(this.depth.distance(point.depth)) + Mth.square(this.weirdness.distance(point.weirdness)) + Mth.square(this.alteration.distance(point.alteration)) + Mth.square(this.offset);
        }

        protected List<Parameter> parameterSpace() {
            return ImmutableList.of(this.temperature, this.humidity, this.continentalness, this.erosion, this.depth, this.weirdness, this.alteration, new Parameter(this.offset, this.offset));
        }

        public Parameter alteration() {
            return this.alteration;
        }
    }

}
