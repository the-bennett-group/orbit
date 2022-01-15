package bennett.orbit.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record OrbitIslandFeatureConfiguration(UniformInt width, UniformInt length, UniformInt depth, BlockStateProvider block) implements FeatureConfiguration {
    public static final Codec<OrbitIslandFeatureConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            UniformInt.CODEC.fieldOf("width").forGetter(OrbitIslandFeatureConfiguration::width),
            UniformInt.CODEC.fieldOf("length").forGetter(OrbitIslandFeatureConfiguration::length),
            UniformInt.CODEC.fieldOf("depth").forGetter(OrbitIslandFeatureConfiguration::depth),
            BlockStateProvider.CODEC.fieldOf("block").forGetter(OrbitIslandFeatureConfiguration::block)
    ).apply(instance, instance.stable(OrbitIslandFeatureConfiguration::new)));
}