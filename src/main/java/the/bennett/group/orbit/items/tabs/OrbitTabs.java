package the.bennett.group.orbit.items.tabs;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.quiltmc.qsl.item.group.api.QuiltItemGroup;
import the.bennett.group.orbit.Orbit;
import the.bennett.group.orbit.blocks.OrbitBlocks;


public final class OrbitTabs {
	public static final CreativeModeTab ORBIT_TAB = newGroup("orbit_group", OrbitBlocks.BLACKWOOD_LOG);

	public static CreativeModeTab newGroup(String name, Block icon) {
		return QuiltItemGroup.createWithIcon(Orbit.newId(name), () -> new ItemStack(icon));
	}

	public static void initialize() {
	}
}

