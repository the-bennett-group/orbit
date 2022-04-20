package the.bennett.group.orbit.mixin;

import com.mojang.datafixers.DataFixer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.DataPackConfig;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import the.bennett.group.orbit.world.OrbitPlanetGenUtils;

import java.io.File;

@Mixin(LevelStorageSource.class)
public class LevelStorageSourceMixin {

    @Inject(method = "getDataPacks(Ljava/io/File;Lcom/mojang/datafixers/DataFixer;)Lnet/minecraft/world/level/DataPackConfig;",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/nbt/CompoundTag;getCompound(Ljava/lang/String;)Lnet/minecraft/nbt/CompoundTag;", ordinal = 0),
            locals = LocalCapture.CAPTURE_FAILHARD)
    private static void getSeed(File file, DataFixer dataFixer,
                                                         CallbackInfoReturnable<DataPackConfig> cir,
                                                         Tag fileCompoundTag, CompoundTag dataCompoundTag) {
        if(dataCompoundTag.contains("WorldGenSettings") && dataCompoundTag.getCompound("WorldGenSettings").contains("seed")) {
            OrbitPlanetGenUtils.setSeed(dataCompoundTag.getCompound("WorldGenSettings").getLong("seed"));
        }
    }
}