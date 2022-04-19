package bennett.orbit.util;

import bennett.orbit.Orbit;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;

public class OrbitUtils {

    public static RotatedPillarBlock newLogBlock() {
        return new RotatedPillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG).strength(2.0F).sound(SoundType.WOOD));
    }

    public static Holder<Block> findHolder(Block block) {
        return Registry.BLOCK.getHolderOrThrow(block.builtInRegistryHolder().key());
    }

    public static <T> ResourceKey<T> makeKey(String name, ResourceKey<Registry<T>> registry) {
        return ResourceKey.create(registry, Orbit.newId(name));
    }

    public static void initialize() {

    }
}
