package the.bennett.group.orbit.world.planets.casud.biomes;

import net.minecraft.world.level.biome.Climate;
import the.bennett.group.orbit.world.gen.OrbitClimate;

import java.util.List;

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

    public static final List<OrbitClimate.ParameterPoint> SPAWN_TARGET =  List.of(
            new OrbitClimate.ParameterPoint(
                    OrbitClimate.FULL_RANGE, //temperature
                    OrbitClimate.FULL_RANGE, //vegetation
                    Climate.Parameter.span(OrbitClimate.INLAND_CONTINENTALNESS, OrbitClimate.FULL_RANGE),
                    OrbitClimate.FULL_RANGE, //erosion
                    OrbitClimate.ZERO, //depth
                    OrbitClimate.FULL_RANGE, //weirdness
                    OrbitClimate.FULL_RANGE, //alteration
                    0L),
            new OrbitClimate.ParameterPoint(
                    OrbitClimate.FULL_RANGE,
                    OrbitClimate.FULL_RANGE,
                    Climate.Parameter.span(OrbitClimate.INLAND_CONTINENTALNESS, OrbitClimate.FULL_RANGE),
                    OrbitClimate.FULL_RANGE,
                    OrbitClimate.ZERO,
                    OrbitClimate.FULL_RANGE,
                    OrbitClimate.FULL_RANGE,
                    0L));

}
