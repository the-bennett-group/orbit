package the.bennett.group.orbit.world.gen.surface;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.SurfaceRules;
import the.bennett.group.orbit.Orbit;

import java.util.HashMap;

public class OrbitRuleSources {
    protected static HashMap<KeyDispatchDataCodec<? extends SurfaceRules.RuleSource>, String> SOURCES = new HashMap<>();
    static {
        SOURCES.put(RandomBlockRuleSource.CODEC, "random_block");
    }

    public static void initialize() {
        SOURCES.forEach(OrbitRuleSources::register);
    }

    public static <T extends SurfaceRules.RuleSource> Codec<T> register(KeyDispatchDataCodec<T> keyCodec, String name) {
        return Registry.register(Registry.RULE, Orbit.newId(name), keyCodec.codec());
    }
}
