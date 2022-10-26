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

	public static void initialize() {
		OrbitBlocks.ITEMABLE_BLOCKS.forEach((Block block, String name) -> register(newBlockItem(block), name));
		register(ACID_BUCKET, "acid_bucket");
	}
	
	private static void register(Item item, String name) {
		Registry.register(Registry.ITEM, Orbit.newId(name), item);
	}

	private static Item newBlockItem(Block block) {
		return new BlockItem(block, new Item.Properties().tab(OrbitTabs.ORBIT_TAB));
	}

}