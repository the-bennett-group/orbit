package the.bennett.group.orbit.world.planets;

import the.bennett.group.orbit.world.planets.casud.CasudDimensionData;
import the.bennett.group.orbit.world.planets.casud.gen.CasudNoiseData;

public class OrbitPlanets {
    public static void initialize(){
        CasudDimensionData.initialize();
        CasudNoiseData.initialize();
    }
}
