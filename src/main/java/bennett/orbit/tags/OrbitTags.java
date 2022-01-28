package bennett.orbit.tags;

import bennett.orbit.Orbit;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.Block;

public class OrbitTags {
    public static final Tag<Block> CORRODIBLE_BLOCKS = TagFactory.BLOCK.create(Orbit.newId("corrodible_blocks"));

    public static void initialize() {

    }


}