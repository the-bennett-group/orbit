package the.bennett.group.orbit.world.planets.casud.gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.TerrainShaper;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import org.quiltmc.loader.api.QuiltLoader;
import the.bennett.group.orbit.Orbit;
import the.bennett.group.orbit.blocks.OrbitBlocks;
import the.bennett.group.orbit.fluid.OrbitFluids;
import the.bennett.group.orbit.util.SeedHolder;
import the.bennett.group.orbit.world.gen.BaseOrbitChunkGenerator;
import the.bennett.group.orbit.world.gen.OrbitClimate;
import the.bennett.group.orbit.world.gen.OrbitNoiseRouter;
import the.bennett.group.orbit.world.planets.casud.CasudDimensionData;
import the.bennett.group.orbit.world.planets.casud.biomes.CasudBiomeSource;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.world.level.levelgen.DensityFunctions.zero;

public class CasudChunkGenerator extends BaseOrbitChunkGenerator {
    private long seed;
    public final BiomeSource biomeSource;
    private final WorldgenRandom worldgenRandom;
    private final BlockState defaultBlock;
    private final BlockState defaultFluid;
    private final int seaLevel;
    //private final NoiseGeneratorSettings noiseGeneratorSettings;
    private final SurfaceSystem surfaceSystem;
    private final LegacyRandomSource randomSource;
    private final Aquifer.FluidPicker aquiferFluidPicker;
    private final OrbitNoiseRouter router;

    public static final Codec<CasudChunkGenerator> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                            BiomeSource.CODEC.fieldOf("biome_source").forGetter((generator) -> generator.biomeSource),
                            Codec.LONG.fieldOf("seed").stable().orElseGet(SeedHolder::seed).forGetter((generator) -> generator.seed))
                    .apply(instance, instance.stable(CasudChunkGenerator::new)));

    private OrbitNoiseRouter buildRouter() {
        PositionalRandomFactory baseRandomFactory = this.worldgenRandom.forkPositional();
        PositionalRandomFactory aquiferRandomFactory = baseRandomFactory.fromHashOf(Orbit.newId("casud/aquifer")).forkPositional();
        PositionalRandomFactory oreRandomFactory = baseRandomFactory.fromHashOf(Orbit.newId("casud/ore")).forkPositional();
        return new OrbitNoiseRouter(
                zero(), //aquifer barrier noise
                zero(), //aquifer floodedness noise
                zero(), //aquifer fluid spread noise
                zero(), //lava aquifer noise
                aquiferRandomFactory,
                oreRandomFactory, //ore vein random factory (i think)
                CasudNoiseData.TEMPERATURE,
                CasudNoiseData.VEGETATION,
                CasudNoiseData.CONTINENTS,
                CasudNoiseData.EROSION, //used to make terrain flatter
                CasudNoiseData.DEPTH, //used for cave biome placement
                CasudNoiseData.RIDGES, //weirdness... i really don't know why they used both names
                CasudNoiseData.ALTERATION, //this is special to Orbit! this is used as a guard noise for more niche terrain features. TODO: explain this when its done
                CasudNoiseData.BEFORE_JAGGEDNESS, //inital density before jaggedness
                CasudNoiseData.BEFORE_JAGGEDNESS, //final density
                zero(), //ore vein toggle noise
                zero(), //ore vein ridge noise
                zero(), //ore vein gap noise
                CasudBiomeSource.SPAWN_TARGET);
    }

    public CasudChunkGenerator(BiomeSource biomeSource, long seed) {
        super(BuiltinRegistries.STRUCTURE_SETS, Optional.empty(), biomeSource);
        this.biomeSource = biomeSource;
        this.defaultBlock = OrbitBlocks.SALT_BLOCK.defaultBlockState();
        this.defaultFluid = OrbitFluids.SOURCE_ACID.defaultFluidState().createLegacyBlock();
        this.worldgenRandom = new WorldgenRandom(new LegacyRandomSource(seed));
        this.seaLevel = CasudDimensionData.SEA_LEVEL;
        this.randomSource = new LegacyRandomSource(SeedHolder.seed()); //TODO: figure out if this should actually be the world seed: for now I think its fine.
        this.surfaceSystem = new SurfaceSystem(BuiltinRegistries.NOISE, this.defaultBlock, this.seaLevel, SeedHolder.seed(), WorldgenRandom.Algorithm.LEGACY);
        Aquifer.FluidStatus acidFluidStatus = new Aquifer.FluidStatus(CasudDimensionData.MAX_HEIGHT, OrbitBlocks.ACID.defaultBlockState());
        this.aquiferFluidPicker = (int1, int2, int3) -> acidFluidStatus; // i TRULY don't know what those integers are doing, all I know is that acid will always be returned.
        this.router = buildRouter();
    }


    @Override
    public BlockState getDefaultBlock() {
        return this.defaultBlock;
    }

    @Override
    public BlockState getDefaultFluid() {
        return this.defaultFluid;
    }

    @Override
    public Climate.Sampler climateSampler() {
        return new OrbitClimate.Sampler(
                this.router.temperature(),
                this.router.humidity(),
                this.router.continents(),
                this.router.erosion(),
                this.router.depth(),
                this.router.ridges(),
                this.router.alteration(),
                this.router.spawnTarget()
        );
    }


    @Override
    public void applyCarvers(WorldGenRegion worldGenRegion, long l, BiomeManager biomeManager, StructureFeatureManager structureFeatureManager, ChunkAccess chunkAccess, GenerationStep.Carving carving) {

    }

    public CompletableFuture<ChunkAccess> postProcess() {
        return new CompletableFuture<>();
    }

    @Override
    public void buildSurface(WorldGenRegion region, StructureFeatureManager structures, ChunkAccess chunk) {
        WorldGenerationContext worldGenerationContext = new WorldGenerationContext(this, region);
        this.buildBedrock(chunk);

    }

    protected void buildBedrock(ChunkAccess chunk) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        int chunkStartX = chunk.getPos().getMinBlockX();
        int chunkStartZ = chunk.getPos().getMinBlockZ();

        for (BlockPos pos : BlockPos.betweenClosed(chunkStartX, 0, chunkStartZ, chunkStartX + 15, 0, chunkStartZ + 15)) {
            for (int y = 3; y >= 0; --y) {
                if (y <= this.worldgenRandom.nextInt(3)) {
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
        DecimalFormat decimalFormat = new DecimalFormat("0.000");
        DensityFunction.SinglePointContext singlePointContext = new DensityFunction.SinglePointContext(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        list.add(
                "OrbitNoiseRouter T: " + decimalFormat.format(this.router.temperature().compute(singlePointContext))
                        + " H: " + decimalFormat.format(this.router.humidity().compute(singlePointContext))
                        + " C: " + decimalFormat.format(this.router.continents().compute(singlePointContext))
                        + " E: " + decimalFormat.format(this.router.erosion().compute(singlePointContext))
                        + " D: \n" + decimalFormat.format(this.router.depth().compute(singlePointContext))
                        + " Alt: " + decimalFormat.format(this.router.alteration().compute(singlePointContext))
                        + " PV: " + decimalFormat.format(TerrainShaper.peaksAndValleys((float)this.router.ridges().compute(singlePointContext)))
                        + " w/oJ: " + decimalFormat.format(this.router.initialDensityWithoutJaggedness().compute(singlePointContext))
                        + " N: " + decimalFormat.format(this.router.finalDensity().compute(singlePointContext)));
    }


}
