package the.bennett.group.orbit.blocks.fluid;

import net.minecraft.core.Registry;
import net.minecraft.world.level.material.FlowingFluid;
import the.bennett.group.orbit.Orbit;


public final class OrbitFluids {
	public static final FlowingFluid SOURCE_ACID = register(new AcidFluid.Source(), "acid");
	public static final FlowingFluid FLOWING_ACID = register(new AcidFluid.Flowing(), "flowing_acid");
	   
	
	public static void initialize() {

    }

	public static FlowingFluid register(FlowingFluid fluid, String name) {
		return Registry.register(Registry.FLUID, Orbit.newId(name), fluid);
		
	}
	
	
}