package the.bennett.group.orbit.world.util;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import the.bennett.group.orbit.util.RegistryUtils;

public class OrbitWorldUtils {
    public static final ResourceKey<Level> CASUD = RegistryUtils.makeKey("casud", Registry.DIMENSION_REGISTRY);
    public static final float CASUD_GRAVITY_FACTOR = 2.0F;

    public static float getGravityModifer(ResourceKey<Level> dimension) {
        if(dimension == CASUD) {
            return CASUD_GRAVITY_FACTOR;
        }
        return 1;
    }
}
