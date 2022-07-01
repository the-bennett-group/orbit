package the.bennett.group.orbit.world.gen.surface;

import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.SurfaceRules;

import java.util.List;

public record RandomBlockRuleSource(List<BlockState> blockStates) implements SurfaceRules.RuleSource {
    private static int id = 0;
    public static final KeyDispatchDataCodec<RandomBlockRuleSource> CODEC = KeyDispatchDataCodec.of(BlockState.CODEC.listOf().xmap(RandomBlockRuleSource::new, RandomBlockRuleSource::blockStates).fieldOf("blockstates"));

    public RandomBlockRuleSource(List<BlockState> blockStates) {
        this.blockStates = blockStates;
        id++;
    }

    @Override
    public KeyDispatchDataCodec<? extends SurfaceRules.RuleSource> codec() {
        return CODEC;
    }

    @Override
    public SurfaceRules.SurfaceRule apply(SurfaceRules.Context context) {
        return new RandomBlockRule(this.blockStates, context, id);
    }
}
