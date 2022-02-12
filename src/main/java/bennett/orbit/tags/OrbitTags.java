package bennett.orbit.tags;

import bennett.orbit.Orbit;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.fabricmc.fabric.impl.biome.modification.BuiltInRegistryKeys;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;

public class OrbitTags {
    public static ArrayList<Tag<?>> ORBIT_TAG_LIST = new ArrayList<>();
    public static final Tag<Block> CORRODIBLE_BLOCKS = TagFactory.BLOCK.create(Orbit.newId("corrodible_blocks"));
    public static final Tag<Block> INFINIBURN_ACID_WORLD = TagFactory.BLOCK.create(Orbit.newId("infiniburn_acid_world"));

    public static void initialize() {
        ORBIT_TAG_LIST.add(CORRODIBLE_BLOCKS);
        ORBIT_TAG_LIST.add(INFINIBURN_ACID_WORLD);
    }


}