package the.bennett.group.orbit.world.planets.casud;

import net.minecraft.core.Registry;
import the.bennett.group.orbit.Orbit;
import the.bennett.group.orbit.world.planets.casud.gen.CasudChunkGenerator;

public class CasudDimensionData {
    public static final String NAME = "casud";
    public static final int SKY_COLOR = 3163680;
    public static final int FOG_COLOR = 3163680;
    public static final int SEA_LEVEL = 32;
    public static final int MIN_HEIGHT = 0;
    public static final int MAX_HEIGHT = 256;

    public static void initialize() {
        Registry.register(Registry.CHUNK_GENERATOR, Orbit.newId(NAME), CasudChunkGenerator.CODEC);
    }

}
