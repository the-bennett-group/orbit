package rbt.orbit.items;

import net.minecraft.core.Registry;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import rbt.orbit.Orbit;
import rbt.orbit.fluid.OrbitFluids;
import rbt.orbit.items.tabs.OrbitTabs;

public final class OrbitItems {

	//public static Item OIL_BUCKET = new BucketItem(OrbitFluids.STILL_OIL, new Item.Properties()
	// .craftRemainder(Items.BUCKET).stacksTo(1).tab(OrbitItemGroups.ORBIT_TAB));
	public static Item ACID_BUCKET = new BucketItem(OrbitFluids.SOURCE_ACID, new Item.Properties()
			.craftRemainder(Items.BUCKET).stacksTo(1).tab(OrbitTabs.ORBIT_TAB));
	
	public static void initialize() {
		//register(OIL_BUCKET, "oil_bucket");
		register(ACID_BUCKET, "acid_bucket");
	}
	
	private static void register(Item item, String name) {
         Registry.register(Registry.ITEM, Orbit.newId(name), item);
    }

}