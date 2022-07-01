package the.bennett.group.orbit.world.gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

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

}
