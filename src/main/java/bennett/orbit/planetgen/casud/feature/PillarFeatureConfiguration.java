package bennett.orbit.planetgen.casud.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record PillarFeatureConfiguration(UniformInt centerHeight,
                                         UniformInt eastHeight,
                                         UniformInt westHeight,
                                         UniformInt northHeight,
                                         UniformInt southHeight,
                                         BlockStateProvider block) implements FeatureConfiguration {
    public static final Codec<PillarFeatureConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            UniformInt.CODEC.fieldOf("centerHeight").forGetter(PillarFeatureConfiguration::centerHeight),
            UniformInt.CODEC.fieldOf("eastHeight").forGetter(PillarFeatureConfiguration::eastHeight),
            UniformInt.CODEC.fieldOf("westHeight").forGetter(PillarFeatureConfiguration::westHeight),
            UniformInt.CODEC.fieldOf("northHeight").forGetter(PillarFeatureConfiguration::northHeight),
            UniformInt.CODEC.fieldOf("southHeight").forGetter(PillarFeatureConfiguration::southHeight),
            BlockStateProvider.CODEC.fieldOf("block").forGetter(PillarFeatureConfiguration::block)
    ).apply(instance, instance.stable(PillarFeatureConfiguration::new)));
}