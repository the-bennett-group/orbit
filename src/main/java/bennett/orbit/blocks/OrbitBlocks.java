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
	public static Block BLACKWOOD_LOG;
	public static Block STRIPPED_BLACKWOOD_LOG;
	public static Block BLACKWOOD_PLANKS;

	public static void initialize() {
		ACID = register(new LiquidBlock(OrbitFluids.SOURCE_ACID, FabricBlockSettings.copyOf(Blocks.WATER).luminance(9)){}, "acid");
		SALT_BLOCK = register(new GlassBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(0.3f).sound(SoundType.GLASS)), "salt_block");
		BLACKWOOD_LOG = register(new Block(FabricBlockSettings.of(Material.WOOD).strength(2.0f)), "blackwood_log");
		STRIPPED_BLACKWOOD_LOG = register(new Block(FabricBlockSettings.of(Material.WOOD).strength(2.0f)), "stripped_blackwood_log");
		BLACKWOOD_PLANKS = register(new Block(FabricBlockSettings.of(Material.WOOD).strength(2.0f)), "blackwood_planks");
	}

	public static Block register(Block block, String name) {
		return Registry.register(Registry.BLOCK, Orbit.newId(name), block);
	}

}