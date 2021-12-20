package rbt.orbit.tags;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.Block;
import rbt.orbit.Orbit;

public class OrbitTags {
    public static final Tag<Block> CORRODIBLE_BLOCKS = TagFactory.BLOCK.create(new ResourceLocation("orbit", "corrodible_blocks"));

    public static void initialize() {

    }


}