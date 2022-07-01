package the.bennett.group.orbit.world.gen.df;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.DensityFunction;
import the.bennett.group.orbit.Orbit;

import java.util.HashMap;

public class OrbitDensityFunctions {
    protected static HashMap<KeyDispatchDataCodec<? extends  DensityFunction>, String> FUNCTIONS_MAP = new HashMap<>();
    static {
        FUNCTIONS_MAP.put(ScaledNoiseFunction.CODEC, "scaled_noise");
    }

    public static void initialize() {
        FUNCTIONS_MAP.forEach(OrbitDensityFunctions::register);
    }

    public static <T extends DensityFunction> Codec<T> register(KeyDispatchDataCodec<T> keyCodec, String name) {
        return Registry.register(Registry.DENSITY_FUNCTION_TYPES, Orbit.newId(name), keyCodec.codec());
    }
}
