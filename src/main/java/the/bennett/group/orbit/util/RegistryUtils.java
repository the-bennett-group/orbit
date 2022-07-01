package the.bennett.group.orbit.util;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import the.bennett.group.orbit.Orbit;

public class RegistryUtils {

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
