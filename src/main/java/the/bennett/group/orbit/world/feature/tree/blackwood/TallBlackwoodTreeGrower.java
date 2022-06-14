package the.bennett.group.orbit.world.feature.tree.blackwood;

import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import org.jetbrains.annotations.Nullable;
import the.bennett.group.orbit.blocks.OrbitBlocks;
import the.bennett.group.orbit.world.feature.OrbitFeatures;

public class TallBlackwoodTreeGrower extends AbstractTreeGrower {
    public static final TreeConfiguration TALL_CONFIGURATION = new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(OrbitBlocks.BLACKWOOD_LOG), new StraightTrunkPlacer(10, 3, 6), BlockStateProvider.simple(OrbitBlocks.BLACKWOOD_LEAVES), new BlobFoliagePlacer(UniformInt.of(1, 2), ConstantInt.of(0), 6), new TwoLayersFeatureSize(2, 3, 2)).dirt(BlockStateProvider.simple(OrbitBlocks.SALT_BLOCK)).build();
    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean bees) {
        return OrbitFeatures.BLACKWOOD_TALL;
    }
}
