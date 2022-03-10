package bennett.orbit.planets.casud.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class PillarFeature extends Feature<PillarFeatureConfiguration> {
    public PillarFeature(Codec<PillarFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<PillarFeatureConfiguration> context) {
        BlockPos origin = context.origin();
        BlockStateProvider blockStateProvider = context.config().block();

        BlockPos currentPos = origin;
        Direction UP = Direction.UP;
        PillarFeatureConfiguration config = context.config();

        int centerHeight = config.centerHeight().get(context.random());
        int eastHeight = config.eastHeight().get(context.random());
        int westHeight = config.westHeight().get(context.random());
        int northHeight = config.northHeight().get(context.random());
        int southHeight = config.southHeight().get(context.random());

        // generate center pillar
        for (int i = 0; i <= centerHeight; i++) {
            context.level().setBlock(currentPos, blockStateProvider.getState(context.random(), currentPos), 1);
            currentPos = currentPos.above();

        }

        // generate east pillar
        currentPos = origin.east();
        for (int i = 0; i <= eastHeight; i++) {
            context.level().setBlock(currentPos, blockStateProvider.getState(context.random(), currentPos), 1);
            currentPos = currentPos.above();
        }

        // generate west pillar
        currentPos = origin.west();
        for (int i = 0; i <= westHeight; i++) {
            context.level().setBlock(currentPos, blockStateProvider.getState(context.random(), currentPos), 1);
            currentPos = currentPos.above();
        }

        // generate north pillar
        currentPos = origin.north();
        for (int i = 0; i <= northHeight; i++) {
            context.level().setBlock(currentPos, blockStateProvider.getState(context.random(), currentPos), 1);
            currentPos = currentPos.above();
        }

        // generate south pillar
        currentPos = origin.south();
        for (int i = 0; i <= southHeight; i++) {
            context.level().setBlock(currentPos, blockStateProvider.getState(context.random(), currentPos), 1);
            currentPos = currentPos.above();
        }

        return true;
    }
}