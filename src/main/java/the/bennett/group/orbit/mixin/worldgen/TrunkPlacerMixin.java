package the.bennett.group.orbit.mixin.worldgen;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import the.bennett.group.orbit.blocks.OrbitBlocks;

import java.util.function.BiConsumer;

@Mixin(TrunkPlacer.class)
public class TrunkPlacerMixin {
    @Inject(method="setDirtAt", at = @At(value = "HEAD"), cancellable = true)
    private static void preventSaltGeneration(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, BlockPos pos, TreeConfiguration config, CallbackInfo ci) {
        if (config.dirtProvider.getState(random, pos) == OrbitBlocks.SALT_BLOCK.defaultBlockState()) {
            ci.cancel();
        }
    }
}
