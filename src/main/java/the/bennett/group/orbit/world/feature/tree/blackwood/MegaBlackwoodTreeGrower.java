package the.bennett.group.orbit.world.feature.tree.blackwood;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import org.jetbrains.annotations.Nullable;
import the.bennett.group.orbit.blocks.OrbitBlocks;
import the.bennett.group.orbit.world.feature.OrbitFeatures;

import java.util.Iterator;

public class MegaBlackwoodTreeGrower extends AbstractMegaTreeGrower {
    public static TreeConfiguration TALLER_CONFIGURATION = new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(OrbitBlocks.BLACKWOOD_LOG), new GiantTrunkPlacer(13, 2, 14), BlockStateProvider.simple(OrbitBlocks.BLACKWOOD_LEAVES), new BlobFoliagePlacer(UniformInt.of(1,3), ConstantInt.of(0), 5), new TwoLayersFeatureSize(2, 3, 2)).dirt(BlockStateProvider.simple(OrbitBlocks.SALT_BLOCK)).build();
    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean bees) {
        return OrbitFeatures.BLACKWOOD;
    }

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource random) {
        return OrbitFeatures.BLACKWOOD_MEGA;
    }

    @Override
    public boolean growTree(ServerLevel world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, RandomSource random) {
        //blackwood has a small chance of generating acid somewhere nearby on the same y-level.
        Iterator<BlockPos> threeByThreeBlockIterator = BlockPos.MutableBlockPos.betweenClosed(pos.below().north(3).west(3), pos.south(2).east(2)).iterator();

        if(!super.growTree(world, chunkGenerator, pos, state, random)) {return false;}

        while(threeByThreeBlockIterator.hasNext()) {
            if(random.nextInt(16) <= 6) {
                world.setBlockAndUpdate(threeByThreeBlockIterator.next(), OrbitBlocks.ACID.defaultBlockState());
                break;
            }
        }
        return true;
    }
}
