package bennett.orbit.planets.casud.feature;

import bennett.orbit.Orbit;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Random;

public class BlackwoodTreeGrower extends AbstractTreeGrower {
    private final Holder<ConfiguredFeature<?, ?>> feature;

    public BlackwoodTreeGrower(ConfiguredFeature<?, ?> feature) {
        this.feature = BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_FEATURE, Orbit.newId("blackwood"), feature);
    }
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random random, boolean largeHive) {
        return feature;
    }
}
