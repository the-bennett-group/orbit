package the.bennett.group.orbit.world.gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.text.DecimalFormat;
import java.util.List;

public class CasudChunkGenerator extends BaseOrbitChunkGenerator {
    public static final Codec<CasudChunkGenerator> CODEC = RecordCodecBuilder.create((instance) -> {
        return commonCodec(instance).and(instance.group(RegistryOps.retrieveRegistry(Registry.NOISE_REGISTRY).forGetter((generator) -> {
            return generator.noises;
        }), BiomeSource.CODEC.fieldOf("biome_source").forGetter((generator) -> {
            return generator.biomeSource;
        }), NoiseGeneratorSettings.CODEC.fieldOf("settings").forGetter((generator) -> {
            return generator.settings;
        }))).apply(instance, instance.stable(CasudChunkGenerator::new));
    });

    public CasudChunkGenerator(Registry<StructureSet> registry, Registry<NormalNoise.NoiseParameters> registry2, BiomeSource biomeSource, Holder<NoiseGeneratorSettings> holder) {
        super(registry, registry2, biomeSource, holder);
    }

    @Override
    public void addDebugScreenInfo(List<String> list, RandomState randomState, BlockPos blockPos) {
        DecimalFormat decimalFormat = new DecimalFormat("0.000");
        NoiseRouter noiseRouter = randomState.router();
        DensityFunction.SinglePointContext currentPos = new DensityFunction.SinglePointContext(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        list.add("CasudChunkGenerator -"
                + " Base Terrain: " + getDebugOrNullify("squished_base_height", decimalFormat, currentPos)
                + " Terrain Detail - " + getDebugOrNullify("terrain_detail", decimalFormat, currentPos) + " (Guard - " + getDebugOrNullify("terrain_detail_guard", decimalFormat, currentPos) + " Detail - " + getDebugOrNullify("terrain_detail_noise", decimalFormat, currentPos) + ")"
                + " Squishing " + getDebugOrNullify("squisher", decimalFormat, currentPos));
    }

    String getDebugOrNullify(String id, DecimalFormat format, DensityFunction.SinglePointContext pos) {
        if(retrieveDF(id) != null) {
            return format.format(retrieveDF(id).compute(pos));
        }
        return "Not Bound";
    }

    protected static DensityFunction retrieveDF(String id) {
        return BaseOrbitChunkGenerator.retrieveDF("casud/" + id);
    }
}
