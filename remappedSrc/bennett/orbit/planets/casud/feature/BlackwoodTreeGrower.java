package bennett.orbit.planets.casud.feature;

import org.jetbrains.annotations.Nullable;

import java.util.Random;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public class BlackwoodTreeGrower extends AbstractTreeGrower {
    private final ConfiguredFeature<TreeConfiguration, ?> feature;

    public BlackwoodTreeGrower(ConfiguredFeature<?, ?> feature) {
        this.feature = (ConfiguredFeature<TreeConfiguration, ?>)  feature;
    }
    @Nullable
    @Override
    protected ConfiguredFeature<?, ?> getTreeFeature(Random random, boolean largeHive) {
        return feature;
    }
}
