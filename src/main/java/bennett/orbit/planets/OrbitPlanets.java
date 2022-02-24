package bennett.orbit.planets;

import bennett.orbit.planets.casud.Casud;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.WritableRegistry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;

public class OrbitPlanets {
    public static final RegistryAccess registryAccess = RegistryAccess.builtin();
    public static final MappedRegistry<LevelStem> levelStemMappedRegistry = new MappedRegistry<>(Registry.LEVEL_STEM_REGISTRY, Lifecycle.experimental());
    public static final Registry<Biome> biomeRegistry = OrbitPlanets.registryAccess.registryOrThrow(Registry.BIOME_REGISTRY);
    public static final WritableRegistry<DimensionType> dimensionTypeWritableRegistry = registryAccess.ownedRegistryOrThrow(Registry.DIMENSION_TYPE_REGISTRY);

    public static void initialize() {
        Casud.initialize();
        //levelStemMappedRegistry.register(Casud.CASUD_STEM_KEY, Casud.STEM, Lifecycle.experimental());
        dimensionTypeWritableRegistry.register(Casud.CASUD_LOCATION, Casud.TYPE, Lifecycle.stable());
    }
}
