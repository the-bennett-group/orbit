package bennett.orbit.blocks;

import bennett.orbit.fluid.AcidFluid;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import bennett.orbit.Orbit;
import bennett.orbit.fluid.OrbitFluids;
import net.minecraft.world.level.material.Material;


public final class OrbitBlocks {
	public static Block ACID;
	public static Block SALT_BLOCK;

	public static void initialize() {
		ACID = register(new LiquidBlock(OrbitFluids.SOURCE_ACID, FabricBlockSettings.copyOf(Blocks.WATER).luminance(9)){}, "acid");
		SALT_BLOCK = register(new GlassBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(0.3f).sound(SoundType.GLASS)), "salt_block");
	}

	public static Block register(Block block, String name) {
		return Registry.register(Registry.BLOCK, Orbit.newId(name), block);
	}

}