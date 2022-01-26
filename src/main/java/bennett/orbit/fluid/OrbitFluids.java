package bennett.orbit.fluid;

import bennett.orbit.Orbit;
import bennett.orbit.util.OrbitUtils;
import net.minecraft.core.Registry;
import net.minecraft.world.level.material.FlowingFluid;


public final class OrbitFluids {
	public static final FlowingFluid SOURCE_ACID = register(new AcidFluid.Source(), "acid");
	public static final FlowingFluid FLOWING_ACID = register(new AcidFluid.Flowing(), "flowing_acid");
	   
	
	public static void initialize() {

    }
	
	
	public static FlowingFluid register(FlowingFluid fluid, String name) {
		return Registry.register(Registry.FLUID, Orbit.newId(name), fluid);
		
	}
	
	
}