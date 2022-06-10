package the.bennett.group.orbit.world.feature.tree.blackwood;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import the.bennett.group.orbit.blocks.OrbitBlocks;

import java.util.Iterator;
import java.util.Random;

public abstract class BaseBlackwoodTreeGrower extends AbstractTreeGrower {
    @Override
     public boolean growTree(ServerLevel world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, Random random) {
        //blackwood has a small chance of generating acid somewhere nearby on the same y-level.
        Iterator<BlockPos> threeByThreeBlockIterator = BlockPos.MutableBlockPos.betweenClosed(pos.below().north(3).west(3), pos.south(2).east(2)).iterator();

        while(threeByThreeBlockIterator.hasNext()) {
            if(random.nextInt(16) <= 6) {
                world.setBlockAndUpdate(threeByThreeBlockIterator.next(), OrbitBlocks.ACID.defaultBlockState());
                break;
            }
        }
        System.out.println("YEAH");
        return super.growTree(world, chunkGenerator, pos, state, random);
    }
}
