package bennett.orbit.blocks;

import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import bennett.orbit.Orbit;
import bennett.orbit.fluid.OrbitFluids;


public final class OrbitBlocks {
	public static Block ACID;
	 
	public static void initialize() {
		/*
		OIL = register(OIL, "oil");
		OIL = register(OrbitFluids.STILL_OIL, "oil");
		*/
		ACID = register(OrbitFluids.SOURCE_ACID, "acid");

				
	}
	
	public static Block register(FlowingFluid fluid, String name) {
		return Registry.register(Registry.BLOCK, Orbit.newId(name),
				new LiquidBlock(fluid, FabricBlockSettings.copyOf(Blocks.WATER).luminance(5)) {});
	}
	

}