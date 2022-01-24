package bennett.orbit.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record SaltPillarFeatureConfiguration(UniformInt centerHeight,
                                             UniformInt eastHeight,
                                             UniformInt westHeight,
                                             UniformInt northHeight,
                                             UniformInt southHeight) implements FeatureConfiguration {
    public static final Codec<SaltPillarFeatureConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            UniformInt.CODEC.fieldOf("centerHeight").forGetter(SaltPillarFeatureConfiguration::centerHeight),
            UniformInt.CODEC.fieldOf("eastHeight").forGetter(SaltPillarFeatureConfiguration::eastHeight),
            UniformInt.CODEC.fieldOf("westHeight").forGetter(SaltPillarFeatureConfiguration::westHeight),
            UniformInt.CODEC.fieldOf("northHeight").forGetter(SaltPillarFeatureConfiguration::northHeight),
            UniformInt.CODEC.fieldOf("southHeight").forGetter(SaltPillarFeatureConfiguration::southHeight)
    ).apply(instance, instance.stable(SaltPillarFeatureConfiguration::new)));
}