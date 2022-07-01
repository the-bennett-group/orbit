package the.bennett.group.orbit.blocks;

import net.minecraft.core.Registry;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import the.bennett.group.orbit.Orbit;
import the.bennett.group.orbit.blocks.fluid.OrbitFluids;
import the.bennett.group.orbit.util.ContentCounter;
import the.bennett.group.orbit.world.feature.OrbitFeatures;
import the.bennett.group.orbit.world.feature.tree.blackwood.BlackwoodTreeGrower;

public final class OrbitBlocks {
	public static final BlockBehaviour.StatePredicate never = (blockState, blockGetter, blockPos) -> false;
	public static final Block ACID = register(new LiquidBlock(OrbitFluids.SOURCE_ACID, QuiltBlockSettings.copyOf(Blocks.LAVA).luminance(0).emissiveRendering((blockState, blockGetter, blockPos) -> true)), "acid");
	public static final Block SALT_BLOCK = register(new Block(BlockBehaviour.Properties.of(Material.SAND).strength(0.3f).sound(SoundType.GRAVEL)), "salt_block");
	public static final Block BLACKWOOD_LOG = register(newLogBlock(), "blackwood_log");
	public static final Block STRIPPED_BLACKWOOD_LOG = register(newLogBlock(), "stripped_blackwood_log");
	public static final Block BLACKWOOD_PLANKS = register(newLogBlock(), "blackwood_planks");
	public static final Block BLACKWOOD_LEAVES = register(new Block(BlockBehaviour.Properties.of(Material.GRASS).strength(4.0f).sound(SoundType.GRASS).noOcclusion().randomTicks().isViewBlocking(((blockState, blockGetter, blockPos) -> false))), "blackwood_leaves");
	public static final Block BLACKWOOD_FENCE = register(new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, BLACKWOOD_LOG.defaultMaterialColor()).emissiveRendering((blockState, blockGetter, blockPos) -> true).strength(2.0f, 3.0f).sound(SoundType.WOOD)), "blackwood_fence");
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

	private static LeavesBlock leaves(SoundType soundType) {
		return new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(soundType).noOcclusion().isSuffocating(never).isViewBlocking(never));
	}

	public static RotatedPillarBlock newLogBlock() {
		return new RotatedPillarBlock(QuiltBlockSettings.copyOf(Blocks.OAK_LOG).strength(2.0F).sound(SoundType.WOOD));
	}
}