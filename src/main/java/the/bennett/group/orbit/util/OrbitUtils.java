package the.bennett.group.orbit.util;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import the.bennett.group.orbit.Orbit;

public class OrbitUtils {

    public static RotatedPillarBlock newLogBlock() {
        return new RotatedPillarBlock(QuiltBlockSettings.copyOf(Blocks.OAK_LOG).strength(2.0F).sound(SoundType.WOOD));
    }

    public static <R extends Registry<O>, O> Holder<O> findHolder(O object, R registry) {
        return registry.getHolderOrThrow(registry.createIntrusiveHolder(object).key());
    }

    public static <T> ResourceKey<T> makeKey(String name, ResourceKey<Registry<T>> registry) {
        return ResourceKey.create(registry, Orbit.newId(name));
    }

    public static void initialize() {

    }
}
