package bennett.orbit.world.planets.casud.dimension;

import bennett.orbit.tags.OrbitTags;
import bennett.orbit.world.planets.util.DimensionWrapper;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;

import java.util.OptionalLong;

public class CasudDimensionWrapper implements DimensionWrapper {
    private String name;
    private DimensionType type;
    private BiomeSource biomeSource;
    private ChunkGenerator chunkGenerator;
    private LevelStem levelStem;

    public CasudDimensionWrapper() {
        name = "casud";
        type = DimensionType.create(OptionalLong.empty(), true, false, false, true, 1.0d, false, false, true, false, false, 0, 304, 224, OrbitTags.INFINIBURN_CASUD, DimensionType.OVERWORLD_EFFECTS, 0.25f);
        //biomeSource = new CasudBiomeSource();
    }

    public String name() {
        return this.name;
    }

    public DimensionType type() {
        return this.type;
    }

    public BiomeSource biomeSource() {
        return this.biomeSource;
    }

    public ChunkGenerator chunkGenerator() {
        return this.chunkGenerator;
    }

    public LevelStem levelStem() {
        return this.levelStem;
    }
}