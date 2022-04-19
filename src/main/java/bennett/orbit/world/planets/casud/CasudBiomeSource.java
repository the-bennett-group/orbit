package bennett.orbit.world.planets.casud;

import bennett.orbit.world.gen.BaseOrbitBiomeSource;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;

import java.util.stream.Stream;

public abstract class CasudBiomeSource extends BaseOrbitBiomeSource {
    protected CasudBiomeSource(Stream<Holder<Biome>> stream) {
        super(stream);
    }
    /*public static Codec<CasudBiomeSource> CODEC =  RecordCodecBuilder.create((instance) -> {
        return instance.group(RegistryFixedCodec.create(Registry.BIOME_REGISTRY),
                        Codec.LONG.fieldOf("seed").stable().forGetter((source) -> source.seed))
                .apply(instance, instance.stable(CasudBiomeSource::new));
    });*/

    //private final long seed;

    // public CasudBiomeSource(Registry<Biome> biomeRegistry, long seed) {
    //     //super(BiomeGenData.LOOKUP.keySet().stream().map((k) -> () -> biomeRegistry.getOrThrow(k)));
    //     this.seed = seed;
    // }

    @Override
    protected Codec<? extends BiomeSource> codec() {
        return null;
    }

    @Override
    public BiomeSource withSeed(long l) {
        return null;
    }

    @Override
    public Holder<Biome> getNoiseBiome(int i, int j, int k, Climate.Sampler sampler) {
        return null;
    }
}
