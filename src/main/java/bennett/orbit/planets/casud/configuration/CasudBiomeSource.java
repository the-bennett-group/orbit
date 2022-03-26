package bennett.orbit.planets.casud.configuration;

import bennett.orbit.Orbit;
import bennett.orbit.planets.OrbitWorldGenUtils;
import bennett.orbit.planets.casud.biome.CasudBiomes;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;

import java.util.Optional;
import java.util.function.Supplier;

public class CasudBiomeSource extends MultiNoiseBiomeSource {
    public static final Codec<CasudBiomeSource> CODEC =
            RecordCodecBuilder.create((instance) -> instance.group(
                            RegistryOps.retrieveRegistry(Registry.BIOME_REGISTRY).forGetter((CasudBiomeSource) -> CasudBiomeSource.biomeRegistry))
                    .apply(instance, instance.stable(CasudBiomeSource::new)));

   // public static final MultiNoiseBiomeSource SOURCE;
    private static long SEED = OrbitWorldGenUtils.seed();

    public static Climate.ParameterList PARAMETER_LIST;

    public static MultiNoiseBiomeSource.Preset preset;
    private final Registry<Biome> biomeRegistry;

    public CasudBiomeSource(Registry<Biome> registry) {
        super(createParameterList(registry), Optional.of(new PresetInstance(createBiomeSourcePreset(), registry)));

        biomeRegistry = registry;


    }

    private static Climate.ParameterList createParameterList(Registry<Biome> biomeRegistry) {
        PARAMETER_LIST = new Climate.ParameterList(ImmutableList.of(
//                  Temp, Humid, Cont, Eros, Depth, Weird, Offset
                Pair.of(CasudBiomes.BLACKWOOD_FOREST_PARAMETER, (Supplier<Biome>) () -> biomeRegistry.getOrThrow(CasudBiomes.BLACKWOOD_FOREST_KEY)),
                Pair.of(CasudBiomes.ACID_OCEAN_PARAMETER, (Supplier<Biome>) () -> biomeRegistry.getOrThrow(CasudBiomes.ACID_OCEAN_KEY)),
                Pair.of(CasudBiomes.DEEP_ACID_OCEAN_PARAMETER, (Supplier<Biome>) () -> biomeRegistry.getOrThrow(CasudBiomes.DEEP_ACID_OCEAN_KEY)),
                Pair.of(CasudBiomes.SALTY_FLATLANDS_PARAMETER, (Supplier<Biome>) () -> biomeRegistry.getOrThrow(CasudBiomes.SALTY_FLATLANDS_KEY)),
                Pair.of(CasudBiomes.SALTY_BEACH_PARAMETER, (Supplier<Biome>) () -> biomeRegistry.getOrThrow(CasudBiomes.SALTY_BEACH_KEY))));

        return PARAMETER_LIST;
    }


    private static MultiNoiseBiomeSource.Preset createBiomeSourcePreset() {
        preset = new MultiNoiseBiomeSource.Preset(Orbit.newId("casud"), (registry) -> PARAMETER_LIST);
        return preset;
    }


    public static void initialize() {

    }


    @Override
    protected Codec<? extends BiomeSource> codec() {
        return CODEC;
    }

    @Override
    public BiomeSource withSeed(long seed) {
        //jokes on you, i already have the seed!
        return this;
    }

}