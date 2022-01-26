package bennett.orbit.util;

import bennett.orbit.Orbit;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;

public class OrbitUtils {

    public static RotatedPillarBlock newLogBlock() {
        return new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).strength(2.0F).sounds(SoundType.WOOD));
    }
}
