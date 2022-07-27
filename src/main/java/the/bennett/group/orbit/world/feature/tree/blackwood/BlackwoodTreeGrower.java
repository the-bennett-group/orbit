package the.bennett.group.orbit.world.feature.tree.blackwood;

import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import the.bennett.group.orbit.blocks.OrbitBlocks;
import the.bennett.group.orbit.world.feature.OrbitFeatures;

public class BlackwoodTreeGrower extends BaseBlackwoodTreeGrower {
    public static final TreeConfiguration CONFIGURATION = new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(OrbitBlocks.BLACKWOOD_LOG), new StraightTrunkPlacer(7, 2, 2), BlockStateProvider.simple(OrbitBlocks.BLACKWOOD_LEAVES), new BushFoliagePlacer(UniformInt.of(0, 3), ConstantInt.of(0), 4), new TwoLayersFeatureSize(1, 0, 2)).dirt(BlockStateProvider.simple(OrbitBlocks.SALT_BLOCK)).build();

    public BlackwoodTreeGrower(ConfiguredFeature<TreeConfiguration, ?> feature) {

    }
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean bl) {
        return OrbitFeatures.BLACKWOOD_HOLDER;
    }
}
