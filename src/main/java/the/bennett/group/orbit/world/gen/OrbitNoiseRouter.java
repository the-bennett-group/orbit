package the.bennett.group.orbit.world.gen;

import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunctions;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;

import java.util.List;

public class OrbitNoiseRouter extends NoiseRouter{
    private final DensityFunction barrierNoise;
    private final DensityFunction fluidLevelFloodednessNoise;
    private final DensityFunction fluidLevelSpreadNoise;
    private final DensityFunction aquiferLavaNoise;
    private final PositionalRandomFactory aquiferPositionalRandomFactory;
    private final PositionalRandomFactory oreVeinsPositionalRandomFactory;
    private final DensityFunction temperature;
    private final DensityFunction humidity;
    private final DensityFunction continents;
    private final DensityFunction erosion;
    private final DensityFunction depth;
    private final DensityFunction ridges;
    private final DensityFunction alteration;
    private final DensityFunction initialDensityWithoutJaggedness;
    private final DensityFunction finalDensity;
    private final DensityFunction veinToggle;
    private final DensityFunction veinRidged;
    private final DensityFunction veinGap;
    private final List<OrbitClimate.ParameterPoint> spawnTarget;

    public <T extends Climate.ParameterPoint> OrbitNoiseRouter(DensityFunction barrierNoise, DensityFunction fluidLevelFloodednessnoise, DensityFunction fluidLevelSpreadNoise, DensityFunction aquiferLavaNoise, PositionalRandomFactory aquiferPositionalRandomFactory, PositionalRandomFactory oreVeinsPositionalRandomFactory, DensityFunction temperature, DensityFunction humidity, DensityFunction continents, DensityFunction erosion, DensityFunction depth, DensityFunction ridges, DensityFunction initialDensityWithoutJaggedness, DensityFunction finalDensity, DensityFunction veinToggle, DensityFunction veinRidged, DensityFunction veinGap, List<T> spawnTarget) {
        this(barrierNoise, fluidLevelFloodednessnoise, fluidLevelSpreadNoise, aquiferLavaNoise, aquiferPositionalRandomFactory, oreVeinsPositionalRandomFactory, temperature, humidity, continents, erosion, depth, ridges, initialDensityWithoutJaggedness, finalDensity, veinToggle, veinRidged, veinGap, DensityFunctions.zero(), (List<OrbitClimate.ParameterPoint>) spawnTarget);
    }

    public OrbitNoiseRouter(DensityFunction barrierNoise, DensityFunction fluidLevelFloodednessnoise, DensityFunction fluidLevelSpreadNoise, DensityFunction aquiferLavaNoise, PositionalRandomFactory aquiferPositionalRandomFactory, PositionalRandomFactory oreVeinsPositionalRandomFactory, DensityFunction temperature, DensityFunction humidity, DensityFunction continents, DensityFunction erosion, DensityFunction depth, DensityFunction ridges, DensityFunction alteration, DensityFunction initialDensityWithoutJaggedness, DensityFunction finalDensity, DensityFunction veinToggle, DensityFunction veinRidged, DensityFunction veinGap, List<OrbitClimate.ParameterPoint> spawnTarget) {
        super(barrierNoise, fluidLevelFloodednessnoise, fluidLevelSpreadNoise, aquiferLavaNoise, aquiferPositionalRandomFactory, oreVeinsPositionalRandomFactory, temperature, humidity, continents, erosion, depth, ridges, initialDensityWithoutJaggedness, finalDensity, veinToggle, veinRidged, veinGap, OrbitClimate.ParameterPoint.convertList(spawnTarget));
        this.barrierNoise = barrierNoise;
        this.fluidLevelFloodednessNoise = fluidLevelFloodednessnoise;
        this.fluidLevelSpreadNoise = fluidLevelSpreadNoise;
        this.aquiferLavaNoise = aquiferLavaNoise;
        this.aquiferPositionalRandomFactory = aquiferPositionalRandomFactory;
        this.oreVeinsPositionalRandomFactory = oreVeinsPositionalRandomFactory;
        this.temperature = temperature;
        this.humidity = humidity;
        this.continents = continents;
        this.erosion = erosion;
        this.depth = depth;
        this.ridges = ridges; //also known as weirdness
        this.alteration = alteration;
        this.initialDensityWithoutJaggedness = initialDensityWithoutJaggedness;
        this.finalDensity = finalDensity;
        this.veinToggle = veinToggle;
        this.veinRidged = veinRidged;
        this.veinGap = veinGap;
        this.spawnTarget = spawnTarget;
    }

    public DensityFunction alteration() {
        return this.alteration;
    }

    public List<OrbitClimate.ParameterPoint> spawnTargetWithAlteration() {
        return this.spawnTarget;
    }
}
