package the.bennett.group.orbit.tags;

import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.material.Fluid;
import the.bennett.group.orbit.Orbit;

public class OrbitTags {
    public static final TagKey<Block> CORRODIBLE_BLOCKS = newBlocktag("corrodible_blocks");
    public static final TagKey<Fluid> ACID = newFluidTag("acid");
    public static final TagKey<Block> INFINIBURN_CASUD = newBlocktag("infiniburn_casud");
    public static final TagKey<Block> BLACKWOOD_REPLACEABLE = newBlocktag("blackwood_replaceable");

    public static final TagKey<Biome> CASUD_BIOMES = newBiomeTag("casud");

    public static final TagKey<PlacedFeature> ACID_LAKES = newPlacedFeatureTag("acid_lakes");
    public static final TagKey<PlacedFeature> BLACKWOOD_TREES = newPlacedFeatureTag("blackwood_trees");

    public static final TagKey<Item> CREATES_SALT_FROM_ACID = newItemTag("creates_salt_from_acid");
    public static void initialize() {

    }

    public static TagKey<Block> newBlocktag(String name) {
        return TagKey.create(Registry.BLOCK_REGISTRY, Orbit.newId(name));
    }

    public static TagKey<Fluid> newFluidTag(String name) {
        return TagKey.create(Registry.FLUID_REGISTRY, Orbit.newId(name));
    }

    public static TagKey<Biome> newBiomeTag(String name) {
        return TagKey.create(Registry.BIOME_REGISTRY, Orbit.newId(name));
    }

    public static TagKey<PlacedFeature> newPlacedFeatureTag(String name) {
        return TagKey.create(Registry.PLACED_FEATURE_REGISTRY, Orbit.newId(name));
    }

    public static TagKey<Item> newItemTag(String name) {
        return TagKey.create(Registry.ITEM_REGISTRY, Orbit.newId(name));
    }
}