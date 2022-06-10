package the.bennett.group.orbit.world.feature.tree.blackwood;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import the.bennett.group.orbit.blocks.OrbitBlocks;
import the.bennett.group.orbit.world.feature.OrbitFeatures;

import java.util.Iterator;
import java.util.Random;

public class BlackwoodTreeGrower extends BaseBlackwoodTreeGrower{
    public static final TreeConfiguration CONFIGURATION = new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(OrbitBlocks.BLACKWOOD_LOG), new StraightTrunkPlacer(8, 3, 0), BlockStateProvider.simple(OrbitBlocks.BLACKWOOD_LEAVES), new BlobFoliagePlacer(UniformInt.of(1, 3), ConstantInt.of(0), 4), new TwoLayersFeatureSize(2, 3, 2)).dirt(BlockStateProvider.simple(OrbitBlocks.SALT_BLOCK)).build();

    public BlackwoodTreeGrower(ConfiguredFeature<TreeConfiguration, ?> feature) {

    }
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random random, boolean bl) {
        return OrbitFeatures.BLACKWOOD;
    }

    public boolean hasFlowers(LevelAccessor world, BlockPos pos) {
        Iterator<BlockPos> twoBytwoBlockIterator = BlockPos.MutableBlockPos.betweenClosed(pos.below().north(2).west(2), pos.above().south(2).east(2)).iterator();

        BlockPos blockPos;
        do {
            if (!twoBytwoBlockIterator.hasNext()) {
                return false;
            }

            blockPos = twoBytwoBlockIterator.next();
        } while(!world.getBlockState(blockPos).is(BlockTags.FLOWERS));
        return true;
    }
}
