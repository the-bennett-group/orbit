package bennett.orbit.util;

import bennett.orbit.Orbit;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
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

    public enum RegistryType {
        CONFIGURED_FEATURE,
        PLACED_FEATURE,
        BIOME,
        NOISE_PARAMETERS,
        DENSITY_FUNCTION,
    }

    public static Holder<?> findHolder(ResourceKey key, RegistryType type) {
        switch (type) {
            case CONFIGURED_FEATURE -> {
                return BuiltinRegistries.CONFIGURED_FEATURE.getHolderOrThrow(key);
            }
            case PLACED_FEATURE -> {
                return BuiltinRegistries.PLACED_FEATURE.getHolderOrThrow(key);
            }
            case BIOME -> {
                return BuiltinRegistries.BIOME.getHolderOrThrow(key);
            }
            case NOISE_PARAMETERS -> {
                return BuiltinRegistries.NOISE.getHolderOrThrow(key);
            }
            case DENSITY_FUNCTION -> {
                return BuiltinRegistries.DENSITY_FUNCTION.getHolderOrThrow(key);
            }
        }
        return null;
    }


    public static ResourceKey<?> makeKey(String name, RegistryType type) {
        switch (type) {
            case CONFIGURED_FEATURE -> {
                return makeKey(name, Registry.CONFIGURED_FEATURE_REGISTRY);
            }
            case PLACED_FEATURE -> {
                return makeKey(name, Registry.PLACED_FEATURE_REGISTRY);
            }
            case BIOME -> {
                return makeKey(name, Registry.BIOME_REGISTRY);
            }
            case NOISE_PARAMETERS -> {
                return makeKey(name, Registry.NOISE_REGISTRY);
            }
            case DENSITY_FUNCTION -> {
                return makeKey(name, Registry.DENSITY_FUNCTION_REGISTRY);
            }
        }
        return null;
    }

    public static <T> ResourceKey<T> makeKey(String name, ResourceKey<Registry<T>> registry) {
        return ResourceKey.create(registry, Orbit.newId(name));
    }

    public static void initialize() {

    }
}
