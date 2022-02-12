package bennett.orbit.planets.acidworld.dimension;

import bennett.orbit.Orbit;
import bennett.orbit.tags.OrbitTags;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.dimension.DimensionType;

import java.util.OptionalLong;

public class AcidWorldDimensionType {

    public static final DimensionType TYPE =
            DimensionType.create(
                    OptionalLong.empty(),
                    true,
                    false,
                    true,
                    true,
                    1.0,
                    false,
                    false,
                    true,
                    false,
                    false,
                    0,
                    256,
                    256,
                    Orbit.newId("infiniburn_acid_world"),
                    DimensionType.OVERWORLD_EFFECTS,
                    (float) 0.25);

    public static final ResourceKey<DimensionType> ACID_WORLD_LOCATION = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, Orbit.newId("acid_world"));
}
