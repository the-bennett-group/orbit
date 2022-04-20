package com.github.the.bennett.group.orbit.world.planets.casud.dimension;

import com.github.the.bennett.group.orbit.tags.OrbitTags;
import com.github.the.bennett.group.orbit.util.OrbitUtils;
import com.github.the.bennett.group.orbit.world.OrbitPlanetGenUtils;
import com.github.the.bennett.group.orbit.world.planets.casud.biomes.CasudBiomes;
import com.github.the.bennett.group.orbit.world.planets.casud.gen.CasudChunkGenerator;
import com.github.the.bennett.group.orbit.world.planets.util.DimensionWrapper;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.DimensionType;

import java.util.OptionalLong;

public class CasudDimensionWrapper implements DimensionWrapper {
    private String name;
    private DimensionType type;
    private BiomeSource biomeSource;
    private ChunkGenerator chunkGenerator;
    public static final int SKY_COLOR = 3163680;
    public static final int FOG_COLOR = 3163680;

    public CasudDimensionWrapper() {
        name = "casud";
        //TODO: maybe none of this should be created in dimension wrapper? just constants?
        type = DimensionType.create(OptionalLong.empty(), true, false, false, true, 1.0d, false, false, true, false, false, 0, 304, 224, OrbitTags.INFINIBURN_CASUD, DimensionType.OVERWORLD_EFFECTS, 0.25f);
        biomeSource = new FixedBiomeSource(OrbitUtils.findHolder(CasudBiomes.BLACKWOOD_FOREST, BuiltinRegistries.BIOME));
        chunkGenerator = new CasudChunkGenerator(biomeSource, OrbitPlanetGenUtils.seed());
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
}