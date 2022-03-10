package bennett.orbit.planets.casud.generator;

import bennett.orbit.blocks.OrbitBlocks;
import bennett.orbit.fluid.OrbitFluids;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.util.CubicSpline;
import net.minecraft.world.level.biome.TerrainShaper;
import net.minecraft.world.level.levelgen.*;

public class CasudNoiseGenSettings {
    public static final NoiseGeneratorSettings SETTINGS = new NoiseGeneratorSettings(
            new StructureSettings(false),
            NoiseSettings.create(0, 256, new NoiseSamplingSettings(1.0d, 0.5d, 80d, 300d), new NoiseSlider(0.4, 3, 0), new NoiseSlider(-0.078125, 2, 8), 4, 4, false, false, false, new TerrainShaper(CubicSpline.constant(0.0f), CubicSpline.constant(0.5f), CubicSpline.constant(1.0f))),
            OrbitBlocks.SALT_BLOCK.defaultBlockState(),
            OrbitFluids.SOURCE_ACID.defaultFluidState().createLegacyBlock(),
            SurfaceRuleData.overworldLike(false, true, true),
            64,
            false,
            true,
            true,
            false,
            true,
            false);
}
