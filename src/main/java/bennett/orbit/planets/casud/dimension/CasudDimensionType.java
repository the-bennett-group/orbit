package bennett.orbit.planets.casud.dimension;

import bennett.orbit.Orbit;
import bennett.orbit.tags.OrbitTags;
import net.minecraft.world.level.dimension.DimensionType;

import java.util.OptionalLong;
import java.util.function.Supplier;

public class CasudDimensionType {

    public static final DimensionType TYPE = DimensionType.create(OptionalLong.empty(), true, false, true, true, 1.0, false, false, true, false, false, 0, 256, 256, OrbitTags.INFINIBURN_CASUD, Orbit.newId("infiniburn_acid_world"), (float) 0.25);

    public static Supplier<DimensionType> SUPPLIER = new Supplier<DimensionType>() {
        @Override
        public DimensionType get() {
            return TYPE;
        }
    };


}
