package the.bennett.group.orbit.client;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class AcidStatePredicate implements BlockBehaviour.StatePredicate {

    @Override
    public boolean test(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }
}
