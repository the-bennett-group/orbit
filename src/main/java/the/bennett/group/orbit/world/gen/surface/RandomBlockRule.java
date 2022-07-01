package the.bennett.group.orbit.world.gen.surface;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.jetbrains.annotations.Nullable;
import the.bennett.group.orbit.Orbit;

import java.util.List;

public class RandomBlockRule implements SurfaceRules.SurfaceRule {
    private final List<BlockState> blockstates;
    private final SurfaceRules.Context context;
    private final int id;
    @Nullable
    @Override
    public BlockState tryApply(int i, int j, int k) {
        RandomSource random = this.context.randomState.getOrCreateRandomFactory(Orbit.newId("randomblockrule" + this.id)).at(i, j, k);
        return this.blockstates.get(random.nextInt(this.blockstates.size()));
    }

    public RandomBlockRule(List<BlockState> blockStates, SurfaceRules.Context context, int id) {
        this.blockstates = blockStates;
        this.context = context;
        this.id = id;
    }
}
