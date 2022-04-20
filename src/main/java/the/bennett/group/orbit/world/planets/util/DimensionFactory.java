package the.bennett.group.orbit.world.planets.util;

import com.mojang.serialization.Lifecycle;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.WritableRegistry;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import the.bennett.group.orbit.util.OrbitUtils;

public class DimensionFactory {
    private static final WritableRegistry<DimensionType> dimensionTypeRegistry = RegistryAccess.builtinCopy().ownedWritableRegistryOrThrow(Registry.DIMENSION_TYPE_REGISTRY);
    private static final WritableRegistry<LevelStem> levelStemRegistry = RegistryAccess.builtinCopy().ownedWritableRegistryOrThrow(Registry.LEVEL_STEM_REGISTRY);

    public static <T extends DimensionWrapper> void createAndInitializePlanet(T planet) {
        dimensionTypeRegistry.register(OrbitUtils.makeKey(planet.name(), Registry.DIMENSION_TYPE_REGISTRY), planet.type(), Lifecycle.stable());
        LevelStem levelStem = new LevelStem(OrbitUtils.findHolder(planet.type(), dimensionTypeRegistry), planet.chunkGenerator());
        levelStemRegistry.register(OrbitUtils.makeKey(planet.name(), Registry.LEVEL_STEM_REGISTRY), levelStem, Lifecycle.stable());
    }
}
