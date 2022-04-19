package bennett.orbit.world.gen;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;

import java.util.stream.Stream;

public abstract class BaseOrbitBiomeSource extends BiomeSource {
    public BaseOrbitBiomeSource(Stream<Holder<Biome>> stream) {
        super(stream);
    }
    /*public static Codec<bennett.orbit.world.planets.casud.CasudBiomeSource> CODEC =  RecordCodecBuilder.create((instance) -> {
        return instance.group(RegistryFixedCodec.create(Registry.BIOME_REGISTRY),
                        Codec.LONG.fieldOf("seed").stable().forGetter((source) -> source.seed))
                .apply(instance, instance.stable(BaseOrbitBiomeSource::new));
    });

    private final long seed;

    public BaseOrbitBiomeSource(Registry<Biome> biomeRegistry, long seed) {
        super();
        this.seed = seed;
    }*/

    @Override
    protected Codec<? extends BiomeSource> codec() {
        return CODEC;
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
