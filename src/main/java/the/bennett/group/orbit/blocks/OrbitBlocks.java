package the.bennett.group.orbit.blocks;

import net.minecraft.core.Registry;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import the.bennett.group.orbit.Orbit;
import the.bennett.group.orbit.fluid.OrbitFluids;
import the.bennett.group.orbit.util.ContentCounter;
import the.bennett.group.orbit.util.RegistryUtils;
import the.bennett.group.orbit.world.feature.OrbitFeatures;
import the.bennett.group.orbit.world.feature.tree.blackwood.BlackwoodTreeGrower;

public final class OrbitBlocks {
	public static final Block ACID = register(new LiquidBlock(OrbitFluids.SOURCE_ACID, QuiltBlockSettings.copyOf(Blocks.LAVA).luminance(0).emissiveRendering((blockState, blockGetter, blockPos) -> true)), "acid");
	public static final Block SALT_BLOCK = register(new GlassBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(0.3f).sound(SoundType.SAND)), "salt_block");
	public static final Block BLACKWOOD_LOG = register(RegistryUtils.newLogBlock(), "blackwood_log");
	public static final Block STRIPPED_BLACKWOOD_LOG = register(RegistryUtils.newLogBlock(), "stripped_blackwood_log");
	public static final Block BLACKWOOD_PLANKS = register(RegistryUtils.newLogBlock(), "blackwood_planks");
	public static final Block BLACKWOOD_LEAVES = register(new Block(BlockBehaviour.Properties.of(Material.GRASS).strength(1.0f).sound(SoundType.GRASS).noOcclusion().isViewBlocking(((blockState, blockGetter, blockPos) -> false))), "blackwood_leaves");
	public static final Block BLACKWOOD_FENCE = register(new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, BLACKWOOD_LOG.defaultMaterialColor()).strength(2.0f, 3.0f).sound(SoundType.WOOD)), "blackwood_fence");
	public static final Block BLACKWOOD_FENCE_GATE = register(
			new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, BLACKWOOD_LOG.defaultMaterialColor()).strength(2.0f, 3.0f).sound(SoundType.WOOD)),
			"blackwood_fence_gate");
	public static final BlackwoodSaplingBlock BLACKWOOD_SAPLING = register(new BlackwoodSaplingBlock(new BlackwoodTreeGrower(OrbitFeatures.BLACKWOOD_TREE_CONFIGURED), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().noOcclusion().sound(SoundType.GRASS)), "blackwood_sapling");

	public static void initialize() {
	}

	public static <T extends Block> T register(T block, String name) {
		ContentCounter.countBlock();
		return Registry.register(Registry.BLOCK, Orbit.newId(name), block);
	}
}