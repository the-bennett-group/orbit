package bennett.orbit.planets;

import bennett.orbit.planets.casud.Casud;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.WritableRegistry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class OrbitPlanets {
    public static final RegistryAccess registryAccess = RegistryAccess.builtinCopy();
    //public static final MappedRegistry<ResourceKey<LevelStem>> levelStemMappedRegistry = new MappedRegistry<>(Registry.LEVEL_STEM_REGISTRY, Lifecycle.experimental());
    //public static final Registry<Biome> biomeRegistry = OrbitPlanets.registryAccess.registryOrThrow(Registry.BIOME_REGISTRY);
    public static final WritableRegistry<DimensionType> dimensionTypeWritableRegistry = (WritableRegistry<DimensionType>) registryAccess.ownedRegistryOrThrow(Registry.DIMENSION_TYPE_REGISTRY);
    public static final WritableRegistry<Level> dimensionWritableRegistry = (WritableRegistry<Level>) registryAccess.ownedRegistryOrThrow(Registry.DIMENSION_REGISTRY);



    public static void initialize() {
        Casud.initialize();
        //levelStemMappedRegistry.register(Casud.CASUD_STEM_KEY, Casud.STEM, Lifecycle.experimental());
        //dimensionTypeWritableRegistry.register(Casud.CASUD_TYPE_KEY, Casud.TYPE, Lifecycle.stable());
        dimensionWritableRegistry.register(Casud.CASUD_LEVEL_KEY, getLevel(Casud.CASUD_LEVEL_KEY, dimensionWritableRegistry), Lifecycle.stable());
    }

    public static <T extends Level> T getLevel(ResourceKey<T> levelResourceKey, WritableRegistry<T> writableRegistry) {
        return writableRegistry.getHolderOrThrow(levelResourceKey).value();
    }
}
