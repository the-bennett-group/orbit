package bennett.orbit.planets.casud.dimension;

import bennett.orbit.Orbit;
import java.util.OptionalLong;
import java.util.function.Supplier;
import net.minecraft.world.level.dimension.DimensionType;

public class CasudDimensionType {

    public static final DimensionType TYPE = DimensionType.create(OptionalLong.empty(), true, false, true, true, 1.0, false, false, true, false, false, 0, 256, 256, Orbit.newId("infiniburn_acid_world"), DimensionType.OVERWORLD_EFFECTS, (float) 0.25);

    public static Supplier<DimensionType> SUPPLIER = new Supplier<DimensionType>() {
        @Override
        public DimensionType get() {
            return TYPE;
        }
    };


}
