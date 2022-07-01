package the.bennett.group.orbit.world.gen;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.SurfaceRules;
import the.bennett.group.orbit.Orbit;

public class OrbitRuleSources {
    public static void initialize() {
        register(RandomBlockRuleSource.CODEC, "random_block");
    }

    public static <T extends SurfaceRules.RuleSource> Codec<T> register(KeyDispatchDataCodec<T> keyCodec, String name) {
        return Registry.register(Registry.RULE, Orbit.newId(name), keyCodec.codec());
    }
}
