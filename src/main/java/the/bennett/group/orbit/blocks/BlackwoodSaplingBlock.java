package the.bennett.group.orbit.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;

public class BlackwoodSaplingBlock extends SaplingBlock {
    public BlackwoodSaplingBlock(AbstractTreeGrower grower, Properties properties) {
        super(grower, properties);
    }

    @Override
    public boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return super.mayPlaceOn(floor, world, pos)  || floor.is(OrbitBlocks.SALT_BLOCK) || floor.is(OrbitBlocks.ACID) || floor.is(Blocks.PURPLE_TERRACOTTA);
    }
}
