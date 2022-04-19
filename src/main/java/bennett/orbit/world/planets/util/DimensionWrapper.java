package bennett.orbit.world.planets.util;

import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;

public interface DimensionWrapper {
    String name();
    DimensionType type();
    BiomeSource biomeSource();
    ChunkGenerator chunkGenerator();
    LevelStem levelStem();
}
