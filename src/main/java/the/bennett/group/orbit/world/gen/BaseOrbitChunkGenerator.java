package the.bennett.group.orbit.world.gen;

import bennett.orbit.Orbit;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.TerrainShaper;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import org.quiltmc.loader.api.QuiltLoader;
import the.bennett.group.orbit.util.SeedHolder;

import java.text.DecimalFormat;
import java.util.List;


public class BaseOrbitChunkGenerator extends NoiseBasedChunkGenerator {
    public static final Codec<BaseOrbitChunkGenerator> CODEC = RecordCodecBuilder.create((instance) -> {
        return commonCodec(instance).and(instance.group(RegistryOps.retrieveRegistry(Registry.NOISE_REGISTRY).forGetter((generator) -> {
            return generator.noises;
        }), BiomeSource.CODEC.fieldOf("biome_source").forGetter((generator) -> {
            return generator.biomeSource;
        }), Codec.LONG.fieldOf("seed").stable().orElseGet(SeedHolder::seed).forGetter((generator) -> {
            return generator.seed;
        }), NoiseGeneratorSettings.CODEC.fieldOf("settings").forGetter((generator) -> {
            return generator.settings;
        }))).apply(instance, instance.stable(BaseOrbitChunkGenerator::new));
    });

    public Registry<NormalNoise.NoiseParameters> noises;
    public Long seed;

    public BaseOrbitChunkGenerator(Registry<StructureSet> registry, Registry<NormalNoise.NoiseParameters> registry2, BiomeSource biomeSource, long l, Holder<NoiseGeneratorSettings> holder) {
        super(registry, registry2, biomeSource, l, holder);
    }

    public static void initialize() {
        Registry.register(Registry.CHUNK_GENERATOR, Orbit.newId("generator"), CODEC);
    }

    @Override
    public void addDebugScreenInfo(List<String> list, BlockPos blockPos) {
        if (QuiltLoader.isDevelopmentEnvironment()) {
            //i like how this looks better.
            list.add("Seed " + SeedHolder.seed());
            DecimalFormat decimalFormat = new DecimalFormat("0.000");
            DensityFunction.SinglePointContext singlePointContext = new DensityFunction.SinglePointContext(blockPos.getX(), blockPos.getY(), blockPos.getZ());
            double d = this.router().ridges().compute(singlePointContext);
            String var10001 = decimalFormat.format(this.router().temperature().compute(singlePointContext));
            list.add(
                    "NoiseRouter T: " + var10001
                            + " H: " + decimalFormat.format(this.router().humidity().compute(singlePointContext))
                            + " C: " + decimalFormat.format(this.router().continents().compute(singlePointContext))
                            + " E: " + decimalFormat.format(this.router().erosion().compute(singlePointContext))
                            + " D: " + decimalFormat.format(this.router().depth().compute(singlePointContext)));
            list.add(
                    " W: " + decimalFormat.format(d)
                            + " PV: " + decimalFormat.format(TerrainShaper.peaksAndValleys((float) d))
                            + " w/oJ: " + decimalFormat.format(this.router().initialDensityWithoutJaggedness().compute(singlePointContext))
                            + " F: " + decimalFormat.format(this.router().finalDensity().compute(singlePointContext)));
            return;
        }
        super.addDebugScreenInfo(list, blockPos);
    }

}
