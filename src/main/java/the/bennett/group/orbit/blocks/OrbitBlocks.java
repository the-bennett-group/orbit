package the.bennett.group.orbit.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import the.bennett.group.orbit.Orbit;
import the.bennett.group.orbit.blocks.fluid.OrbitFluids;
import the.bennett.group.orbit.util.ContentCounter;
import the.bennett.group.orbit.world.gen.feature.OrbitFeatures;
import the.bennett.group.orbit.world.gen.feature.tree.blackwood.BlackwoodTreeGrower;

import java.util.HashMap;
import java.util.Map;

public final class OrbitBlocks {
	public static final Map<Block, String> ITEMABLE_BLOCKS = new HashMap<>();
	public static final BlockBehaviour.StatePredicate NEVER = (blockState, blockGetter, blockPos) -> false;
	public static final BlockBehaviour.StatePredicate ALWAYS = (blockState, blockGetter, blockPos) -> true;

	public static final BlockBehaviour.StateArgumentPredicate<EntityType<?>> ENTITY_PREDICATE_NEVER  = (BlockState state, BlockGetter world, BlockPos pos, EntityType<?> type) -> false;

	// Casud associated blocks
	public static final Block ACID = new LiquidBlock(OrbitFluids.SOURCE_ACID, QuiltBlockSettings.copyOf(Blocks.LAVA).luminance(0).emissiveRendering(ALWAYS));
	public static final Block SALT_BLOCK = new Block(BlockBehaviour.Properties.of(Material.SAND).strength(0.3f).sound(SoundType.GRAVEL));
	public static final Block BLACKWOOD_LOG = newLogBlock();
	public static final Block STRIPPED_BLACKWOOD_LOG = newLogBlock();
	public static final Block BLACKWOOD_PLANKS = newLogBlock();
	public static final Block BLACKWOOD_LEAVES = new Block(BlockBehaviour.Properties.of(Material.GRASS).strength(4.0f).sound(SoundType.GRASS).noOcclusion().randomTicks().isViewBlocking(((blockState, blockGetter, blockPos) -> false)));
	public static final Block BLACKWOOD_FENCE = new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, BLACKWOOD_LOG.defaultMaterialColor()).emissiveRendering((blockState, blockGetter, blockPos) -> true).strength(2.0f, 3.0f).sound(SoundType.WOOD));
	public static final Block BLACKWOOD_FENCE_GATE = new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, BLACKWOOD_LOG.defaultMaterialColor()).strength(2.0f, 3.0f).sound(SoundType.WOOD));
	public static final Block BLACKWOOD_BUTTON = new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.75F).sound(SoundType.WOOD));
	public static final Block BLACKWOOD_TRAPDOOR = new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(3.33F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ENTITY_PREDICATE_NEVER));
	public static final Block BLACKWOOD_SLAB = new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(3.33F, 6.0F).sound(SoundType.WOOD));
	public static final Block BLACKWOOD_DOOR = new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(3.33F, 6.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ENTITY_PREDICATE_NEVER));
	public static final BlackwoodSaplingBlock BLACKWOOD_SAPLING = new BlackwoodSaplingBlock(new BlackwoodTreeGrower(OrbitFeatures.BLACKWOOD_TREE_CONFIGURED), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().noOcclusion().sound(SoundType.GRASS));

	static {
		ITEMABLE_BLOCKS.put(ACID, "acid");
		ITEMABLE_BLOCKS.put(SALT_BLOCK, "salt_block");
		ITEMABLE_BLOCKS.put(BLACKWOOD_LOG, "blackwood_log");
		ITEMABLE_BLOCKS.put(STRIPPED_BLACKWOOD_LOG, "stripped_blackwood_log");
		ITEMABLE_BLOCKS.put(BLACKWOOD_PLANKS, "blackwood_planks");
		ITEMABLE_BLOCKS.put(BLACKWOOD_LEAVES, "blackwood_leaves");
		ITEMABLE_BLOCKS.put(BLACKWOOD_FENCE, "blackwood_fence");
		ITEMABLE_BLOCKS.put(BLACKWOOD_FENCE_GATE, "blackwood_fence_gate");
		ITEMABLE_BLOCKS.put(BLACKWOOD_SAPLING, "blackwood_sapling");
		ITEMABLE_BLOCKS.put(BLACKWOOD_BUTTON, "blackwood_button");
		ITEMABLE_BLOCKS.put(BLACKWOOD_TRAPDOOR, "blackwood_trapdoor");
		ITEMABLE_BLOCKS.put(BLACKWOOD_SLAB, "blackwood_slab");
		ITEMABLE_BLOCKS.put(BLACKWOOD_DOOR, "blackwood_door");
	}

	public static void initialize() {
		ITEMABLE_BLOCKS.forEach(OrbitBlocks::register);
	}

	public static <T extends Block> T register(T block, String name) {
		ContentCounter.countBlock();
		return Registry.register(Registry.BLOCK, Orbit.newId(name), block);
	}

	private static LeavesBlock leaves(SoundType soundType) {
		return new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(soundType).noOcclusion().isSuffocating(NEVER).isViewBlocking(NEVER));
	}

	public static RotatedPillarBlock newLogBlock() {
		return new RotatedPillarBlock(QuiltBlockSettings.copyOf(Blocks.OAK_LOG).strength(2.0F).sound(SoundType.WOOD));
	}
}