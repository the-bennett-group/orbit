package the.bennett.group.orbit.world.planets;

import the.bennett.group.orbit.world.planets.casud.dimension.CasudDimensionWrapper;
import the.bennett.group.orbit.world.planets.util.DimensionFactory;

public class OrbitPlanets {
    public static void initialize(long seed){
        DimensionFactory.createAndInitializePlanet(new CasudDimensionWrapper(seed));
    }
}
