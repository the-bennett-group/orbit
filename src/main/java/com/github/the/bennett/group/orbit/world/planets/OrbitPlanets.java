package com.github.the.bennett.group.orbit.world.planets;

import com.github.the.bennett.group.orbit.world.planets.casud.dimension.CasudDimensionWrapper;
import com.github.the.bennett.group.orbit.world.planets.util.DimensionFactory;

public class OrbitPlanets {
    public static void initialize(){
        DimensionFactory.createAndInitializePlanet(new CasudDimensionWrapper());
    }
}
