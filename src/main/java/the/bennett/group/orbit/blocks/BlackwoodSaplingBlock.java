package the.bennett.group.orbit.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import the.bennett.group.orbit.tags.OrbitTags;

public class BlackwoodSaplingBlock extends SaplingBlock {
    public BlackwoodSaplingBlock(AbstractTreeGrower grower, Properties properties) {
        super(grower, properties);
    }

    @Override
    public boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.is(OrbitTags.BLACKWOOD_PLACEABLE);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockPos blockPos = pos.below();
        return this.mayPlaceOn(world.getBlockState(blockPos), world, blockPos);
    }
}
