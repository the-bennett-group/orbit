package bennett.orbit.world.planets;

import bennett.orbit.world.planets.casud.dimension.CasudDimensionWrapper;
import bennett.orbit.world.planets.util.DimensionFactory;

public class OrbitPlanets {
    public static void initialize(){
        DimensionFactory.createAndInitializePlanet(new CasudDimensionWrapper());
    }
}
