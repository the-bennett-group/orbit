package bennett.orbit.blocks;

import bennett.orbit.util.OrbitUtils;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import bennett.orbit.Orbit;
import bennett.orbit.fluid.OrbitFluids;
import net.minecraft.world.level.material.Material;

import static bennett.orbit.util.OrbitUtils.newLogBlock;


public final class OrbitBlocks {
	public static final Block ACID = register(new LiquidBlock(OrbitFluids.SOURCE_ACID, FabricBlockSettings.copyOf(Blocks.WATER).luminance(9)){}, "acid");
	public static final Block SALT_BLOCK = register(new GlassBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(0.3f).sound(SoundType.GLASS)), "salt_block");
	public static final Block BLACKWOOD_LOG = register(newLogBlock(), "blackwood_log");
	public static final Block STRIPPED_BLACKWOOD_LOG = register(newLogBlock(), "stripped_blackwood_log");
	public static final Block BLACKWOOD_PLANKS = register(newLogBlock(), "blackwood_planks");
	public static final Block BLACKWOOD_FENCE_GATE = register(
			new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, BLACKWOOD_LOG.defaultMaterialColor()).strength(2.0f, 3.0f).sound(SoundType.WOOD)),
			"blackwood_fence_gate");

	public static void initialize() {
	}

	public static Block register(Block block, String name) {
		return Registry.register(Registry.BLOCK, Orbit.newId(name), block);
	}

}