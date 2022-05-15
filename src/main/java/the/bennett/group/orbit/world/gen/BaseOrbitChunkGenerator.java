package the.bennett.group.orbit.world.gen;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public abstract class BaseOrbitChunkGenerator extends ChunkGenerator {
    private long seed;
    protected static final BlockState AIR = Blocks.AIR.defaultBlockState();
    protected final int verticalNoiseResolution = 8;
    protected final int horizontalNoiseResolution = 4;
    protected final int noiseSizeX = 16 / this.horizontalNoiseResolution;
    protected final int noiseSizeY = 256 / this.verticalNoiseResolution;
    protected final int noiseSizeZ = 16 / this.horizontalNoiseResolution;

    public BaseOrbitChunkGenerator(Registry<StructureSet> registry, Optional<HolderSet<StructureSet>> optional, BiomeSource biomeSource) {
        super(registry, optional, biomeSource);
    }

    @Override
    public ChunkGenerator withSeed(long l) {
        this.seed = l;
        return this;
    }

    public abstract BlockState getDefaultBlock();

    public abstract BlockState getDefaultFluid();

    @Override
    public Climate.Sampler climateSampler() {
        return null;
    }


    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Executor executor, Blender blender, StructureFeatureManager structureFeatureManager, ChunkAccess chunkAccess) {
        fillFromNoise(structureFeatureManager, chunkAccess);

        return CompletableFuture.completedFuture(chunkAccess);
    }

    private CompletableFuture<ChunkAccess> fillFromNoise(StructureFeatureManager structureFeatureManager, ChunkAccess chunk) {
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        Heightmap heightmap = chunk.getOrCreateHeightmapUnprimed(Heightmap.Types.OCEAN_FLOOR_WG);
        Heightmap heightmap2 = chunk.getOrCreateHeightmapUnprimed(Heightmap.Types.WORLD_SURFACE_WG);
        BlockState defaultBlock = getDefaultBlock();
        BlockState defaultFluid = getDefaultFluid();
        for(int i = 0; i <= 64; ++i) {
            if (defaultBlock != null) {
                int j = chunk.getMinBuildHeight() + i;
                for(int k = 0; k < 16; ++k) {
                    for(int l = 0; l < 16; ++l) {
                        chunk.setBlockState(mutableBlockPos.set(k, j, l), defaultBlock, false);
                        heightmap.update(k, j, l, defaultBlock);
                        heightmap2.update(k, j, l, defaultBlock);
                    }
                }
            }
        }
        postProcess();
        return CompletableFuture.completedFuture(chunk);
    }

    public abstract CompletableFuture<ChunkAccess> postProcess();

    @Override
    public abstract int getSeaLevel();

    @Override
    public abstract int getMinY();

    @Override
    public int getBaseHeight(int i, int j, Heightmap.Types types, LevelHeightAccessor levelHeightAccessor) {
        return 0;
    }

    @Override
    public abstract NoiseColumn getBaseColumn(int i, int j, LevelHeightAccessor levelHeightAccessor);

    @Override
    public abstract void addDebugScreenInfo(List<String> list, BlockPos blockPos);
}
