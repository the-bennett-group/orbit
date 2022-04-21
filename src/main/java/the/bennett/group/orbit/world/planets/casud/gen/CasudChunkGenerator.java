package the.bennett.group.orbit.world.planets.casud.gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.chunk.ProtoChunk;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;
import the.bennett.group.orbit.blocks.OrbitBlocks;
import the.bennett.group.orbit.fluid.OrbitFluids;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class CasudChunkGenerator extends ChunkGenerator {
    public static final Codec<CasudChunkGenerator> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                            BiomeSource.CODEC.fieldOf("biome_source").forGetter((generator) -> generator.biomeSource),
                            Codec.LONG.fieldOf("seed").stable().forGetter((generator) -> generator.seed))
                    .apply(instance, instance.stable(CasudChunkGenerator::new)));

    private long seed;
    public final BiomeSource biomeSource;
    private final WorldgenRandom worldgenRandom;
    private final BlockState defaultBlock;
    private final BlockState defaultFluid;
    private static final BlockState AIR = Blocks.AIR.defaultBlockState();
    private final int verticalNoiseResolution;
    private final int horizontalNoiseResolution;
    private final WorldgenRandom random;
    private final int noiseSizeX;
    private final int noiseSizeY;
    private final int noiseSizeZ;

    public CasudChunkGenerator(BiomeSource biomeSource, long seed) {
        super(BuiltinRegistries.STRUCTURE_SETS, Optional.empty(), biomeSource);
        this.seed = seed;
        this.biomeSource = biomeSource;
        this.worldgenRandom = new WorldgenRandom(new XoroshiroRandomSource(RandomSupport.seedUniquifier()));
        this.defaultBlock = OrbitBlocks.SALT_BLOCK.defaultBlockState();
        this.defaultFluid = OrbitFluids.SOURCE_ACID.defaultFluidState().createLegacyBlock();

        this.verticalNoiseResolution = 8;
        this.horizontalNoiseResolution = 4;
        this.noiseSizeX = 16 / this.horizontalNoiseResolution;
        this.noiseSizeY = 256 / this.verticalNoiseResolution;
        this.noiseSizeZ = 16 / this.horizontalNoiseResolution;
        this.random = new WorldgenRandom(new LegacyRandomSource(seed));
    }


    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public ChunkGenerator withSeed(long l) {
        this.seed = l;
        return this;
    }

    @Override
    public Climate.Sampler climateSampler() {
        return Climate.empty();
    }


    @Override
    public void applyCarvers(WorldGenRegion worldGenRegion, long l, BiomeManager biomeManager, StructureFeatureManager structureFeatureManager, ChunkAccess chunkAccess, GenerationStep.Carving carving) {

    }

    @Override
    public void buildSurface(WorldGenRegion worldGenRegion, StructureFeatureManager structureFeatureManager, ChunkAccess chunkAccess) {

    }

    @Override
    public void spawnOriginalMobs(WorldGenRegion worldGenRegion) {

    }

    @Override
    public int getGenDepth() {
        return 0;
    }

    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Executor executor, Blender blender, StructureFeatureManager structureFeatureManager, ChunkAccess chunkAccess) {
        fillFromNoise(structureFeatureManager, chunkAccess);

        return CompletableFuture.completedFuture(chunkAccess);
    }

    void fillFromNoise(StructureFeatureManager structureFeatureManager, ChunkAccess chunk) {
        ProtoChunk protoChunk = (ProtoChunk)chunk;
        Heightmap oceanFloor = protoChunk.getOrCreateHeightmapUnprimed(Heightmap.Types.OCEAN_FLOOR_WG);
        Heightmap worldSurface = protoChunk.getOrCreateHeightmapUnprimed(Heightmap.Types.WORLD_SURFACE_WG);
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        ChunkPos pos = chunk.getPos();
        int chunkX = pos.x;
        int chunkZ = pos.z;
        int chunkStartX = chunkX << 4;
        int chunkStartZ = chunkZ << 4;

        for(int noiseX = 0; noiseX < this.noiseSizeX; ++noiseX) {
            // Initialize noise data on the x1 column
            int noiseZ;

            // [0, 4] -> z noise chunks
            for(noiseZ = 0; noiseZ < this.noiseSizeZ; ++noiseZ) {
                LevelChunkSection section = protoChunk.getSection(chunk.getSectionIndex(255));
                section.acquire();

                // [0, 32] -> y noise chunks
                for(int noiseY = this.noiseSizeY - 1; noiseY >= 0; --noiseY) {
                    // Lower samples

                    // Upper samples


                    // [0, 8) -> y noise pieces
                    for(int pieceY = this.verticalNoiseResolution - 1; pieceY >= 0; --pieceY) {
                        int realY = noiseY * this.verticalNoiseResolution + pieceY;
                        int localY = realY & 15;
                        int sectionY = chunk.getSectionIndex(realY);
                        // Get the chunk section
                        if (section.bottomBlockY() >> 4 != sectionY) {
                            section.release();
                            section = protoChunk.getSection(sectionY);
                            section.acquire();
                        }

                        // progress within loop
                        double yLerp = (double) pieceY / (double)this.verticalNoiseResolution;

                        // Interpolate noise data based on y progress


                        // [0, 4) -> x noise pieces
                        for(int pieceX = 0; pieceX < this.horizontalNoiseResolution; ++pieceX) {
                            int realX = chunkStartX + noiseX * this.horizontalNoiseResolution + pieceX;
                            int localX = realX & 15;

                            // Interpolate noise based on x progress

                            // [0, 4) -> z noise pieces
                            for(int pieceZ = 0; pieceZ < this.horizontalNoiseResolution; ++pieceZ) {
                                int realZ = chunkStartZ + noiseZ * this.horizontalNoiseResolution + pieceZ;
                                int localZ = realZ & 15;

                                // Get the blockstate based on the y and density
                                BlockState state = this.getBlockState(1, realY);

                                if (state != AIR) {
                                    // Add light source if the state has light
                                    if (state.getLightEmission() != 0) {
                                        mutable.set(realX, realY, realZ);
                                        protoChunk.addLight(mutable);
                                    }

                                    // Place the state at the position
                                    section.setBlockState(localX, localY, localZ, state, false);
                                    // Track heightmap data
                                    oceanFloor.update(localX, realY, localZ, state);
                                    worldSurface.update(localX, realY, localZ, state);
                                }
                            }
                        }
                    }
                }

                section.release();
            }

        }
    }

    public BlockState getBlockState(double density, int y) {
        if (density > 0.0D) {
            return this.defaultBlock;
        } else if (y < this.getSeaLevel()) {
            return this.defaultFluid;
        } else {
            return AIR;
        }
    }

    @Override
    public int getSeaLevel() {
        return 64;
    }

    @Override
    public int getMinY() {
        return 0;
    }

    @Override
    public int getBaseHeight(int i, int j, Heightmap.Types types, LevelHeightAccessor levelHeightAccessor) {
        return 64;
    }

    @Override
    public NoiseColumn getBaseColumn(int x, int z, LevelHeightAccessor levelHeightAccessor) {
        BlockState[] states = new BlockState[this.noiseSizeY * this.verticalNoiseResolution];
        return new NoiseColumn(0, states);
    }



    @Override
    public void addDebugScreenInfo(List<String> list, BlockPos blockPos) {

    }
}
