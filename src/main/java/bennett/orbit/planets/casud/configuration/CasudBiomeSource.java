package bennett.orbit.planets.casud.configuration;

import bennett.orbit.Orbit;
import bennett.orbit.planets.OrbitWorldGenUtils;
import bennett.orbit.planets.casud.biome.CasudBiomes;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;

import java.util.Optional;
import java.util.function.Supplier;

public class CasudBiomeSource {

   // public static final MultiNoiseBiomeSource SOURCE;
    private static long SEED = OrbitWorldGenUtils.seed();

    public static Climate.ParameterList PARAMETER_LIST;

    public static MultiNoiseBiomeSource.Preset preset;

    public static MultiNoiseBiomeSource SOURCE;


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
        SOURCE = new MultiNoiseBiomeSource(createParameterList(OrbitWorldGenUtils.biomeRegistry), Optional.of(new MultiNoiseBiomeSource.PresetInstance(preset, OrbitWorldGenUtils.biomeRegistry)));
    }


}