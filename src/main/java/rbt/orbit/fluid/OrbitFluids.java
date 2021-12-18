package rbt.orbit.fluid;

import net.minecraft.core.Registry;
import net.minecraft.world.level.material.FlowingFluid;
import rbt.orbit.Orbit;


public final class OrbitFluids {
	// TODO: create oil
	// public static FlowingFluid STILL_OIL;
	// public static FlowingFluid FLOWING_OIL;

	public static FlowingFluid SOURCE_ACID;
	public static FlowingFluid FLOWING_ACID;
	   
	
	public static void initialize() {
		// TODO: create oil
		// //oil
		// STILL_OIL = register(new OilFluid.StillOil(), "oil");
		// FLOWING_OIL = register(new OilFluid.FlowingOil(), "flowing_oil");

		//acid
		SOURCE_ACID = register(new AcidFluid.Source(), "acid");
		FLOWING_ACID = register(new AcidFluid.Flowing(), "flowing_acid");
    }
	
	
	public static FlowingFluid register(FlowingFluid fluid, String name) {
		return Registry.register(Registry.FLUID, Orbit.newId(name), fluid);
		
	}
	
	
}