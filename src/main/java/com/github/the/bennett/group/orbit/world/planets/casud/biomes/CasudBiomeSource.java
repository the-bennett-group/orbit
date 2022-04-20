package com.github.the.bennett.group.orbit.world.planets.casud.biomes;

public abstract class CasudBiomeSource {
    /*public static Codec<EcotonesBiomeSource> CODEC =  RecordCodecBuilder.create((instance) -> {
        return instance.group(RegistryLookupCodec.of(Registry.BIOME_KEY).forGetter((source) -> source.biomeRegistry),
                        Codec.LONG.fieldOf("seed").stable().forGetter((source) -> source.seed))
                .apply(instance, instance.stable(EcotonesBiomeSource::new));
    });
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
    }*/
}
