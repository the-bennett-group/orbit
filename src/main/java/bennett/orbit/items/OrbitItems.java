package bennett.orbit.items;

import bennett.orbit.Orbit;
import bennett.orbit.blocks.OrbitBlocks;
import bennett.orbit.items.tabs.OrbitTabs;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import bennett.orbit.fluid.OrbitFluids;
import net.minecraft.world.level.block.Block;
import org.lwjgl.system.CallbackI;

public final class OrbitItems {

	public static Item ACID_BUCKET = new BucketItem(OrbitFluids.SOURCE_ACID, new Item.Properties()
			.craftRemainder(Items.BUCKET).stacksTo(1).tab(OrbitTabs.ORBIT_TAB));
	
	public static void initialize() {
		register(ACID_BUCKET, "acid_bucket");
		register(new BlockItem(OrbitBlocks.SALT_BLOCK, new FabricItemSettings().group(OrbitTabs.ORBIT_TAB)), "salt_block");
		register(new BlockItem(OrbitBlocks.BLACKWOOD_LOG, new FabricItemSettings().group(OrbitTabs.ORBIT_TAB)), "blackwood_log");
		register(new BlockItem(OrbitBlocks.STRIPPED_BLACKWOOD_LOG, new FabricItemSettings().group(OrbitTabs.ORBIT_TAB)), "stripped_blackwood_log");
		register(new BlockItem(OrbitBlocks.BLACKWOOD_PLANKS, new FabricItemSettings().group(OrbitTabs.ORBIT_TAB)), "blackwood_planks");
	}
	
	private static void register(Item item, String name) {
         Registry.register(Registry.ITEM, Orbit.newId(name), item);
    }

}