package bennett.orbit.items;

import bennett.orbit.Orbit;
import bennett.orbit.blocks.OrbitBlocks;
import bennett.orbit.items.tabs.OrbitTabs;
import bennett.orbit.util.OrbitUtils;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.world.item.*;
import bennett.orbit.fluid.OrbitFluids;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public final class OrbitItems {

	public static final Item ACID_BUCKET = register(new BucketItem(OrbitFluids.SOURCE_ACID, new Item.Properties()
			.craftRemainder(Items.BUCKET).stacksTo(1).tab(OrbitTabs.ORBIT_TAB)), "acid_bucket");

	public static final Item BLACKWOOD_FENCE_GATE = register(OrbitBlocks.BLACKWOOD_FENCE_GATE, "blackwood_fence_gate");
	
	public static void initialize() {
		register(new BlockItem(OrbitBlocks.SALT_BLOCK, new FabricItemSettings().group(OrbitTabs.ORBIT_TAB)), "salt_block");
		register(new BlockItem(OrbitBlocks.BLACKWOOD_LOG, new FabricItemSettings().group(OrbitTabs.ORBIT_TAB)), "blackwood_log");
		register(new BlockItem(OrbitBlocks.STRIPPED_BLACKWOOD_LOG, new FabricItemSettings().group(OrbitTabs.ORBIT_TAB)), "stripped_blackwood_log");
		register(new BlockItem(OrbitBlocks.BLACKWOOD_PLANKS, new FabricItemSettings().group(OrbitTabs.ORBIT_TAB)), "blackwood_planks");
	}
	
	private static Item register(Item item, String name) {
		return Registry.register(Registry.ITEM, Orbit.newId(name), item);
    }

	private static Item register(Block block, String name) {
		return register(new BlockItem(block, new Item.Properties().tab(OrbitTabs.ORBIT_TAB)), name);
	}

}