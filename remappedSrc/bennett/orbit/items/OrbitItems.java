package bennett.orbit.items;

import bennett.orbit.Orbit;
import bennett.orbit.blocks.OrbitBlocks;
import bennett.orbit.fluid.OrbitFluids;
import bennett.orbit.items.tabs.OrbitTabs;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

public final class OrbitItems {

	public static final Item ACID_BUCKET = register(new BucketItem(OrbitFluids.SOURCE_ACID, new Item.Properties()
			.craftRemainder(Items.BUCKET).stacksTo(1).tab(OrbitTabs.ORBIT_TAB)), "acid_bucket");

	public static final Item SALT_BLOCK = register(OrbitBlocks.SALT_BLOCK, "salt_block");
	public static final Item BLACKWOOD_LOG = register(OrbitBlocks.BLACKWOOD_LOG, "blackwood_log");
	public static final Item STRIPPED_BLACKWOOD_LOG = register(OrbitBlocks.STRIPPED_BLACKWOOD_LOG, "stripped_blackwood_log");
	public static final Item BLACKWOOD_PLANKS = register(OrbitBlocks.BLACKWOOD_PLANKS, "blackwood_planks");
	public static final Item BLACKWOOD_LEAVES = register(OrbitBlocks.BLACKWOOD_LEAVES, "blackwood_leaves");
	public static final Item BLACKWOOD_FENCE = register(OrbitBlocks.BLACKWOOD_FENCE, "blackwood_fence");
	public static final Item BLACKWOOD_FENCE_GATE = register(OrbitBlocks.BLACKWOOD_FENCE_GATE, "blackwood_fence_gate");
	public static final Item BLACKWOOD_SAPLING = register(OrbitBlocks.BLACKWOOD_SAPLING, "blackwood_sapling");
	
	public static void initialize() {

	}
	
	private static Item register(Item item, String name) {
		return Registry.register(Registry.ITEM, Orbit.newId(name), item);
    }

	private static Item register(Block block, String name) {
		return register(new BlockItem(block, new Item.Properties().tab(OrbitTabs.ORBIT_TAB)), name);
	}

}