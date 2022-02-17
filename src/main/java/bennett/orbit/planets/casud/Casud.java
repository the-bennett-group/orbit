package bennett.orbit.planets.casud;

import bennett.orbit.Orbit;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;

import java.util.OptionalLong;
import java.util.function.Supplier;

public class Casud {
    public static final ResourceKey<DimensionType> CASUD_LOCATION = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, Orbit.newId("casud"));

    public static final DimensionType TYPE = DimensionType.create(OptionalLong.empty(), true, false, true, true, 1.0, false, false, true, false, false, 0, 256, 256, Orbit.newId("infiniburn_casud"), DimensionType.OVERWORLD_EFFECTS, 0.25f);
    public static Supplier<DimensionType> TYPE_SUPPLIER = new Supplier<DimensionType>() {
        @Override
        public DimensionType get() {
            return TYPE;
        }
    };

    public static final int CASUD_SKY_COLOR = 3163680;
    public static final int CASUD_WATER_COLOR = 3163680;
    public static final int CASUD_FOG_COLOR = 3163680;
    public static final int CASUD_WATER_FOG_COLOR = 10541966;

    public static final NoiseBasedChunkGenerator CasedChunkGenerator = new NoiseBasedChunkGenerator()s

    public static final ResourceKey<LevelStem> STEM_KEY = ResourceKey.create(Registry.LEVEL_STEM_REGISTRY, Orbit.newId("casud"));
    public static final LevelStem STEM = new LevelStem();
    public static void initialize() {

    }
}
