package the.bennett.group.orbit.world.planets.casud.gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.server.level.WorldGenRegion;
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
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import org.quiltmc.loader.api.QuiltLoader;
import the.bennett.group.orbit.blocks.OrbitBlocks;
import the.bennett.group.orbit.fluid.OrbitFluids;
import the.bennett.group.orbit.util.SeedHolder;
import the.bennett.group.orbit.world.planets.casud.Casud;
import the.bennett.group.orbit.world.planets.casud.biomes.CasudBiomes;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class CasudChunkGenerator extends ChunkGenerator {
    public static final Codec<CasudChunkGenerator> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                            BiomeSource.CODEC.fieldOf("biome_source").forGetter((generator) -> generator.biomeSource),
                            Codec.LONG.fieldOf("seed").stable().orElseGet(SeedHolder::seed).forGetter((generator) -> generator.seed))
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
    private final int seaLevel;
    private final NoiseGeneratorSettings noiseGeneratorSettings;
    private final SurfaceSystem surfaceSystem;
    private final LegacyRandomSource randomSource;
    private final Aquifer.FluidPicker aquiferFluidPicker;

    //TODO: move init functionality into BaseOrbitChunkGenerator
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
        this.seaLevel = Casud.SEA_LEVEL;
        this.randomSource = new LegacyRandomSource(SeedHolder.seed()); //TODO: figure out if this should actually be the world seed: for now I think its fine.
        this.surfaceSystem = new SurfaceSystem(BuiltinRegistries.NOISE, this.defaultBlock, this.seaLevel, SeedHolder.seed(), WorldgenRandom.Algorithm.LEGACY);
        Aquifer.FluidStatus acidFluidStatus = new Aquifer.FluidStatus(Casud.MAX_HEIGHT, OrbitBlocks.ACID.defaultBlockState());
        this.aquiferFluidPicker = (int1, int2, int3) -> acidFluidStatus; // I TRULY don't know what those integers are doing, all I know is that acid will always be returned.
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
    public void buildSurface(WorldGenRegion region, StructureFeatureManager structures, ChunkAccess chunk) {
        WorldGenerationContext worldGenerationContext = new WorldGenerationContext(this, region);
        NoiseChunk noiseChunk = chunk.getOrCreateNoiseChunk(this.router, () -> {
            return new Beardifier(structureFeatureManager, chunkAccess);
        }, noiseGeneratorSettings, this.globalFluidPicker, Blender.of(worldGenRegion));

        this.surfaceSystem.buildSurface(region.getBiomeManager(), region.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY), true, worldGenerationContext, noiseChunk, CasudBiomes.RULE_SOURCE);
        this.buildBedrock(chunk, random);

    }

    protected void buildBedrock(ChunkAccess chunk, WorldgenRandom random) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        int chunkStartX = chunk.getPos().getMinBlockX();
        int chunkStartZ = chunk.getPos().getMinBlockZ();

        for (BlockPos pos : BlockPos.betweenClosed(chunkStartX, 0, chunkStartZ, chunkStartX + 15, 0, chunkStartZ + 15)) {
            for (int y = 3; y >= 0; --y) {
                if (y <= random.nextInt(3)) {
                    chunk.setBlockState(mutable.set(pos.getX(), y, pos.getZ()), Blocks.BEDROCK.defaultBlockState(), false);
                }
            }
        }
    }

    @Override
    public void spawnOriginalMobs(WorldGenRegion worldGenRegion) {

    }

    @Override
    public int getGenDepth() {
        return 0;
    }

    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Executor executor, Blender blender, StructureFeatureManager structureFeatureManager, ChunkAccess chunk) {
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        Heightmap heightmap = chunk.getOrCreateHeightmapUnprimed(Heightmap.Types.OCEAN_FLOOR_WG);
        Heightmap heightmap2 = chunk.getOrCreateHeightmapUnprimed(Heightmap.Types.WORLD_SURFACE_WG);

        for(int i = 0; i <= 64; ++i) {
            BlockState blockState = defaultBlock;
            if (blockState != null) {
                int j = chunk.getMinBuildHeight() + i;
                for(int k = 0; k < 16; ++k) {
                    for(int l = 0; l < 16; ++l) {
                        chunk.setBlockState(mutableBlockPos.set(k, j, l), blockState, false);
                        heightmap.update(k, j, l, blockState);
                        heightmap2.update(k, j, l, blockState);
                    }
                }
            }
        }

        return CompletableFuture.completedFuture(chunk);
    }

    @Override
    public void createStructures(RegistryAccess registryAccess, StructureFeatureManager structureFeatureManager, ChunkAccess chunkAccess, StructureManager structureManager, long l) {
        //TODO fix structures
    }

    protected BlockState getBlockState(double density, int y) {
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
        return this.seaLevel;
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
        if(QuiltLoader.isDevelopmentEnvironment()) {
            list.add("Seed " + SeedHolder.seed());
        }
    }

    void addBedrock()
}
