package bennett.orbit.world.feature;

import bennett.orbit.blocks.OrbitBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class SaltPillarFeature extends Feature<SaltPillarFeatureConfiguration> {
    public SaltPillarFeature(Codec<SaltPillarFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<SaltPillarFeatureConfiguration> context) {
        BlockPos origin = context.origin();
        BlockPos currentPos = origin;
        Direction UP = Direction.UP;
        SaltPillarFeatureConfiguration config = context.config();

        int centerHeight = config.centerHeight().sample(context.random());
        int eastHeight = config.eastHeight().sample(context.random());
        int westHeight = config.westHeight().sample(context.random());
        int northHeight = config.northHeight().sample(context.random());
        int southHeight = config.southHeight().sample(context.random());

        // generate center pillar
        for (int i = 0; i <= centerHeight; i++) {
            context.level().setBlock(currentPos, OrbitBlocks.SALT_BLOCK.defaultBlockState(), 1);
            currentPos = currentPos.above();

        }

        // generate east pillar
        currentPos = origin.east();
        for (int i = 0; i <= eastHeight; i++) {
            context.level().setBlock(currentPos, OrbitBlocks.SALT_BLOCK.defaultBlockState(), 1);
            currentPos = currentPos.above();
        }

        // generate west pillar
        currentPos = origin.west();
        for (int i = 0; i <= westHeight; i++) {
            context.level().setBlock(currentPos, OrbitBlocks.SALT_BLOCK.defaultBlockState(), 1);
            currentPos = currentPos.above();
        }

        // generate north pillar
        currentPos = origin.north();
        for (int i = 0; i <= northHeight; i++) {
            context.level().setBlock(currentPos, OrbitBlocks.SALT_BLOCK.defaultBlockState(), 1);
            currentPos = currentPos.above();
        }

        // generate south pillar
        currentPos = origin.south();
        for (int i = 0; i <= southHeight; i++) {
            context.level().setBlock(currentPos, OrbitBlocks.SALT_BLOCK.defaultBlockState(), 1);
            currentPos = currentPos.above();
        }

        return true;
    }
}