package the.bennett.group.orbit.items;

import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import the.bennett.group.orbit.Orbit;
import the.bennett.group.orbit.blocks.OrbitBlocks;
import the.bennett.group.orbit.blocks.fluid.OrbitFluids;
import the.bennett.group.orbit.items.tabs.OrbitTabs;

public final class OrbitItems {

	public static final Item ACID_BUCKET = new BucketItem(OrbitFluids.SOURCE_ACID, new Item.Properties()
			.craftRemainder(Items.BUCKET).stacksTo(1).tab(OrbitTabs.ORBIT_TAB));

	public static final Item SALT_BLOCK = newBlockItem(OrbitBlocks.SALT_BLOCK);
	public static final Item BLACKWOOD_LOG = newBlockItem(OrbitBlocks.BLACKWOOD_LOG);
	public static final Item STRIPPED_BLACKWOOD_LOG = newBlockItem(OrbitBlocks.STRIPPED_BLACKWOOD_LOG);
	public static final Item BLACKWOOD_PLANKS = newBlockItem(OrbitBlocks.BLACKWOOD_PLANKS);
	public static final Item BLACKWOOD_LEAVES = newBlockItem(OrbitBlocks.BLACKWOOD_LEAVES);
	public static final Item BLACKWOOD_FENCE = newBlockItem(OrbitBlocks.BLACKWOOD_FENCE);
	public static final Item BLACKWOOD_FENCE_GATE = newBlockItem(OrbitBlocks.BLACKWOOD_FENCE_GATE);
	public static final Item BLACKWOOD_SAPLING = newBlockItem(OrbitBlocks.BLACKWOOD_SAPLING);
	public static final Item BLACKWOOD_TRAPDOOR = newBlockItem(OrbitBlocks.BLACKWOOD_TRAPDOOR);
	public static final Item BLACKWOOD_BUTTON = newBlockItem(OrbitBlocks.BLACKWOOD_BUTTON);

	public static void initialize() {
		register(ACID_BUCKET, "acid_bucket");
		register(SALT_BLOCK, "salt_block");
		register(BLACKWOOD_LOG, "blackwood_log");
		register(STRIPPED_BLACKWOOD_LOG, "stripped_blackwood_log");
		register(BLACKWOOD_PLANKS, "blackwood_planks");
		register(BLACKWOOD_LEAVES, "blackwood_leaves");
		register(BLACKWOOD_FENCE, "blackwood_fence");
		register(BLACKWOOD_FENCE_GATE, "blackwood_fence_gate");
		register(BLACKWOOD_SAPLING, "blackwood_sapling");
		register(BLACKWOOD_TRAPDOOR, "blackwood_trapdoor");
		register(BLACKWOOD_BUTTON, "blackwood_button");
	}
	
	private static void register(Item item, String name) {
		Registry.register(Registry.ITEM, Orbit.newId(name), item);
	}

	private static Item newBlockItem(Block block) {
		return new BlockItem(block, new Item.Properties().tab(OrbitTabs.ORBIT_TAB));
	}

}