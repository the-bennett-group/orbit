package com.github.the.bennett.group.orbit.items.tabs;

import com.github.the.bennett.group.orbit.Orbit;
import com.github.the.bennett.group.orbit.blocks.OrbitBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.quiltmc.qsl.item.group.api.QuiltItemGroup;


public final class OrbitTabs {
	public static final CreativeModeTab ORBIT_TAB = newGroup("orbit_group", OrbitBlocks.BLACKWOOD_LOG);

	public static CreativeModeTab newGroup(String name, Block icon) {
		return QuiltItemGroup.createWithIcon(Orbit.newId(name), () -> new ItemStack(icon));
	}

	public static void initialize() {
	}
}

