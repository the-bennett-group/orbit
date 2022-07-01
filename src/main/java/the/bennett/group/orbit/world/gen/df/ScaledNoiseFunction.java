package the.bennett.group.orbit.world.gen.df;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunctions;

public record ScaledNoiseFunction(DensityFunction shiftX, DensityFunction shiftY, DensityFunction shiftZ, DensityFunction xzScale, DensityFunction yScale, DensityFunction.NoiseHolder noise) implements DensityFunction {
    private static final MapCodec<ScaledNoiseFunction> DATA_CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return instance.group(DensityFunction.HOLDER_HELPER_CODEC.fieldOf("shift_x").orElse(DensityFunctions.constant(0.0)).forGetter(ScaledNoiseFunction::shiftX), DensityFunction.HOLDER_HELPER_CODEC.fieldOf("shift_y").orElse(DensityFunctions.constant(0.0)).forGetter(ScaledNoiseFunction::shiftY), DensityFunction.HOLDER_HELPER_CODEC.fieldOf("shift_z").orElse(DensityFunctions.constant(0.0)).forGetter(ScaledNoiseFunction::shiftZ), DensityFunction.HOLDER_HELPER_CODEC.fieldOf("xz_scale").forGetter(ScaledNoiseFunction::xzScale), DensityFunction.HOLDER_HELPER_CODEC.fieldOf("y_scale").forGetter(ScaledNoiseFunction::yScale), NoiseHolder.CODEC.fieldOf("noise").forGetter(ScaledNoiseFunction::noise)).apply(instance, ScaledNoiseFunction::new);
    });
    public static final KeyDispatchDataCodec<ScaledNoiseFunction> CODEC;

    @Override
    public double compute(FunctionContext context) {
        double computedXZ = this.xzScale.compute(context);
        double computedY = this.yScale.compute(context);
        double d = context.blockX() * computedXZ + this.shiftX.compute(context);
        double e = context.blockY() * computedY + this.shiftY.compute(context);
        double f = context.blockZ() * computedXZ + this.shiftZ.compute(context);
        return this.noise.getValue(d, e, f);
    }

    @Override
    public void fillArray(double[] array, ContextProvider context) {
        context.fillAllDirectly(array, this);
    }

    @Override
    public DensityFunction mapAll(Visitor visitor) {
        return visitor.apply(new ScaledNoiseFunction(this.shiftX.mapAll(visitor), this.shiftY.mapAll(visitor), this.shiftZ.mapAll(visitor), this.xzScale.mapAll(visitor), this.yScale.mapAll(visitor), visitor.visitNoise(this.noise)));
    }

    @Override
    public double minValue() {
        return -this.maxValue();
    }

    @Override
    public double maxValue() {
        return this.noise.maxValue();
    }

    @Override
    public KeyDispatchDataCodec<? extends DensityFunction> codec() {
        return CODEC;
    }

    static {
        CODEC = KeyDispatchDataCodec.of(DATA_CODEC);
    }
}
