package the.bennett.group.orbit.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import the.bennett.group.orbit.blocks.OrbitBlocks;

import java.util.Random;

public class BlackwoodTreeGrower extends AbstractTreeGrower {
    public static final TreeConfiguration DEFAULT_CONFIGURATION;
    public static final TreeConfiguration TALL_CONFIGURATION;
    public static final TreeConfiguration MEGA_CONFIGURATION;

    static {
        DEFAULT_CONFIGURATION = new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(OrbitBlocks.BLACKWOOD_LOG), new StraightTrunkPlacer(8, 3, 0), BlockStateProvider.simple(OrbitBlocks.BLACKWOOD_LEAVES), new BlobFoliagePlacer(UniformInt.of(1, 3), ConstantInt.of(0), 4), new TwoLayersFeatureSize(2, 3, 2)).dirt(BlockStateProvider.simple(OrbitBlocks.SALT_BLOCK)).build();
        TALL_CONFIGURATION = new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(OrbitBlocks.BLACKWOOD_LOG), new StraightTrunkPlacer(10, 3, 6), BlockStateProvider.simple(OrbitBlocks.BLACKWOOD_LEAVES), new BlobFoliagePlacer(UniformInt.of(1, 4), ConstantInt.of(1), 6), new TwoLayersFeatureSize(2, 3, 2)).dirt(BlockStateProvider.simple(OrbitBlocks.SALT_BLOCK)).build();
        MEGA_CONFIGURATION = new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(OrbitBlocks.BLACKWOOD_LOG), new GiantTrunkPlacer(13, 2, 14), BlockStateProvider.simple(OrbitBlocks.BLACKWOOD_LEAVES), new MegaPineFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), UniformInt.of(13, 17)), new TwoLayersFeatureSize(1, 1, 2)).dirt(BlockStateProvider.simple(OrbitBlocks.SALT_BLOCK)).build();
    }
    public BlackwoodTreeGrower(ConfiguredFeature<TreeConfiguration, ?> feature) {

    }
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random random, boolean bl) {
        return OrbitFeatures.BLACKWOOD;
    }
}
