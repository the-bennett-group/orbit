package com.github.the.bennett.group.orbit.tags;

import com.github.the.bennett.group.orbit.Orbit;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class OrbitTags {
    public static final TagKey<Block> CORRODIBLE_BLOCKS = TagKey.create(Registry.BLOCK_REGISTRY, Orbit.newId("corrodible_blocks"));
    public static final TagKey<Block> INFINIBURN_CASUD = TagKey.create(Registry.BLOCK_REGISTRY, Orbit.newId("infiniburn_casud"));

    public static void initialize() {

    }


}