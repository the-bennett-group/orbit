package com.github.the.bennett.group.orbit.items.tabs;

import com.github.the.bennett.group.orbit.Orbit;
import com.github.the.bennett.group.orbit.blocks.OrbitBlocks;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;


public final class OrbitTabs {
	public static final CreativeModeTab ORBIT_TAB = newGroup("orbit_group", OrbitBlocks.BLACKWOOD_LOG);

	public static void initialize() {
	}
	public static CreativeModeTab newGroup(String name, Block icon) {
		return FabricItemGroupBuilder.create(Orbit.newId(name))
				.icon(() -> new ItemStack(icon)).build();	}
}

