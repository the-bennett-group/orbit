package the.bennett.group.orbit.world.planets.casud;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import the.bennett.group.orbit.Orbit;
import the.bennett.group.orbit.util.RegistryUtils;
import the.bennett.group.orbit.world.planets.casud.gen.CasudChunkGenerator;

public class Casud {
    public static final String NAME = "casud";

    public static final int SKY_COLOR = 3163680;
    public static final int FOG_COLOR = 3163680;

    public static final ResourceKey<Level> CASUD_KEY = RegistryUtils.makeKey(NAME, Registry.DIMENSION_REGISTRY);

    public static void initialize() {
        Registry.register(Registry.CHUNK_GENERATOR, Orbit.newId(NAME), CasudChunkGenerator.CODEC);
    }

}
