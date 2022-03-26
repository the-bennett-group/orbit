package bennett.orbit.planets.casud.feature;

import bennett.orbit.blocks.OrbitBlocks;
import net.minecraft.core.Holder;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.Random;

public class BlackwoodTreeGrower extends AbstractTreeGrower {
    public static final TreeConfiguration CONFIGURATION;

    static {
        CONFIGURATION = new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(OrbitBlocks.BLACKWOOD_LOG), new StraightTrunkPlacer(8, 3, 0), BlockStateProvider.simple(OrbitBlocks.BLACKWOOD_LEAVES), new BlobFoliagePlacer(UniformInt.of(1, 3), ConstantInt.of(0), 4), new TwoLayersFeatureSize(2, 3, 2)).build();
    }
    public BlackwoodTreeGrower(ConfiguredFeature<TreeConfiguration, ?> feature) {

    }
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random random, boolean bl) {
        return OrbitFeatures.BLACKWOOD;
    }
}
