package rbt.orbit.items.tabs;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.CreativeModeTab;
import rbt.orbit.Orbit;
import rbt.orbit.items.OrbitItems;


public final class OrbitTabs {
	public static final CreativeModeTab ORBIT_TAB = FabricItemGroupBuilder.create(
			Orbit.newId("orbit_group"))
			.icon(() -> new ItemStack(Blocks.MOSS_BLOCK))
			.build();
	

	public static void initialize() {
		
	}
}

