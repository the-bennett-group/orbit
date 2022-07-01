package the.bennett.group.orbit.world.gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import the.bennett.group.orbit.Orbit;
import the.bennett.group.orbit.util.RegistryUtils;

import javax.annotation.Nullable;
import java.util.Optional;


public class BaseOrbitChunkGenerator extends NoiseBasedChunkGenerator {
    public static final Codec<BaseOrbitChunkGenerator> CODEC = RecordCodecBuilder.create((instance) -> {
        return commonCodec(instance).and(instance.group(RegistryOps.retrieveRegistry(Registry.NOISE_REGISTRY).forGetter((generator) -> {
            return generator.noises;
        }), BiomeSource.CODEC.fieldOf("biome_source").forGetter((generator) -> {
            return generator.biomeSource;
        }), NoiseGeneratorSettings.CODEC.fieldOf("settings").forGetter((generator) -> {
            return generator.settings;
        }))).apply(instance, instance.stable(BaseOrbitChunkGenerator::new));
    });

    public Registry<NormalNoise.NoiseParameters> noises;

    public BaseOrbitChunkGenerator(Registry<StructureSet> registry, Registry<NormalNoise.NoiseParameters> registry2, BiomeSource biomeSource, Holder<NoiseGeneratorSettings> holder) {
        super(registry, registry2, biomeSource, holder);
    }

    public static void initialize() {
        Registry.register(Registry.CHUNK_GENERATOR, Orbit.newId("generator"), CODEC);
        Registry.register(Registry.CHUNK_GENERATOR, Orbit.newId("casud"), CasudChunkGenerator.CODEC);
    }

    @Nullable
    protected static DensityFunction retrieveDF(String id) {
        ResourceKey<DensityFunction> dfKey = RegistryUtils.makeKey(id, Registry.DENSITY_FUNCTION_REGISTRY);
        Optional<Holder<DensityFunction>> dfMaybe = BuiltinRegistries.DENSITY_FUNCTION.getOrCreateHolder(dfKey).result();
        if(dfMaybe.isPresent()) {
            if(!dfMaybe.get().isBound()) {Orbit.log("Unbound : " + id);}
            return dfMaybe.get().isBound() ? dfMaybe.get().value() : null;
        } else {
            return null;
        }
    }

}
