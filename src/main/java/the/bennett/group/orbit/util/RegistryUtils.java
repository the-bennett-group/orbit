package the.bennett.group.orbit.util;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import the.bennett.group.orbit.Orbit;

public class RegistryUtils {

    public static RotatedPillarBlock newLogBlock() {
        return new RotatedPillarBlock(QuiltBlockSettings.copyOf(Blocks.OAK_LOG).strength(2.0F).sound(SoundType.WOOD));
    }

    public static <R extends Registry<T>, T> Holder<T> findHolder(T object, R registry) {
        return registry.getOrCreateHolderOrThrow(registry.getResourceKey(object).orElseThrow());
    }

    public static <R extends Registry<T>, T> Holder<T> findHolder(ResourceKey<T> key, R registry) {
        return registry.getOrCreateHolderOrThrow(key);
    }

    public static <T> ResourceKey<T> makeKey(String name, ResourceKey<Registry<T>> registry) {
        return ResourceKey.create(registry, Orbit.newId(name));
    }

    public static <T, R extends Registry<T>> ResourceKey<T> makeKey(String name, R registry) {
        return ResourceKey.create(registry.key(), Orbit.newId(name));
    }

    public static <T> ResourceKey<T> makeKeyAndRegisterToo(String name, T object, ResourceKey<Registry<T>> registryKey) {
        ResourceKey<T> key = makeKey(name, registryKey);
        Registry<T> registry = RegistryAccess.builtinCopy().ownedWritableRegistryOrThrow(registryKey);
        BuiltinRegistries.register(registry, key, object);
        return key;
    }

    public static <T> ResourceKey<T> makeKeyAndRegisterToo(String name, T object, Registry<T> registry) {
        ResourceKey<T> key = makeKey(name, registry);
        Registry.register(registry, key, object);
        return key;
    }

    public static void initialize() {

    }

}
