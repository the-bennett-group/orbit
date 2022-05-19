package the.bennett.group.orbit.mixin.world;


import net.minecraft.core.Registry;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import org.quiltmc.loader.api.QuiltLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import the.bennett.group.orbit.Orbit;
import the.bennett.group.orbit.util.SeedHolder;

import java.util.Optional;

@Mixin(WorldGenSettings.class)
public class WorldGenSettingsMixin {

    /**
     * World seed for worldgen when not specified by JSON by Haven King
     * <a href="https://github.com/Hephaestus-Dev/seedy-behavior/blob/master/src/main/java/dev/hephaestus/seedy/mixin/world/gen/GeneratorOptionsMixin.java">https://github.com/Hephaestus-Dev/seedy-behavior/blob/master/src/main/java/dev/hephaestus/seedy/mixin/world/gen/GeneratorOptionsMixin.java</a>
     */
    @Inject(method = "<init>(JZZLnet/minecraft/core/Registry;Ljava/util/Optional;)V",
            at = @At(value = "RETURN"))
    private void worldblender_giveUsTrueWorldSeed(long seed, boolean generateStructures, boolean bonusChest, Registry<LevelStem> mappedRegistry, Optional<String> legacyCustomOptions, CallbackInfo ci) {
        SeedHolder.setSeed(seed);
        if(QuiltLoader.isDevelopmentEnvironment()) {
            Orbit.log("Seed acquired: " + seed);
        }
    }
}