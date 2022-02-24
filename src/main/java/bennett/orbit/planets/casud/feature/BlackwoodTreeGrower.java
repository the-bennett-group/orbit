package bennett.orbit.planets.casud.feature;

import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class BlackwoodTreeGrower extends AbstractTreeGrower {
    private final ConfiguredFeature<TreeConfiguration, ?> feature;

    public BlackwoodTreeGrower(ConfiguredFeature<?, ?> feature) {
        this.feature = (ConfiguredFeature<TreeConfiguration, ?>)  feature;
    }
    @Nullable
    @Override
    protected ConfiguredFeature<?, ?> getConfiguredFeature(Random random, boolean largeHive) {
        return feature;
    }
}
