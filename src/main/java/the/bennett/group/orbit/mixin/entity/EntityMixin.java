package the.bennett.group.orbit.mixin.entity;

import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.Fluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import the.bennett.group.orbit.tags.OrbitTags;
import the.bennett.group.orbit.world.damagesource.OrbitDamageSources;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    public boolean firstTick;

    @Shadow
    public Object2DoubleMap<TagKey<Fluid>> fluidHeight;

    public boolean wasEyeInOrbitAcid = false;


    @Shadow public abstract boolean updateFluidHeightAndDoFluidPushing(TagKey<Fluid> key, double d);

    @Shadow public float fallDistance;

    @Shadow public abstract boolean hurt(DamageSource source, float amount);

    @Shadow public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Shadow protected RandomSource random;

    public boolean isInOrbitAcid() {
        return !this.firstTick && this.fluidHeight.getDouble(OrbitTags.ACID) > 0.0;
    }

    @Inject(method = "updateInWaterStateAndDoFluidPushing()Z",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;updateFluidHeightAndDoFluidPushing(Lnet/minecraft/tags/TagKey;D)Z", shift = At.Shift.AFTER))
    public void updateAndDoAcidPushingToo(CallbackInfoReturnable<Boolean> cir) {
        if (isInOrbitAcid()) {
            cir.setReturnValue(this.updateFluidHeightAndDoFluidPushing(OrbitTags.ACID, 0.0023333333333333335));
        }
    }

    @Inject(method= "canSpawnSprintParticle()Z",
            at = @At(value = "RETURN"))
    public void canSpawnSprintParticleWithRegardsToAcid(CallbackInfoReturnable<Boolean> cir) {
        if(isInOrbitAcid()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "baseTick()V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;updateSwimming()V", shift = At.Shift.AFTER))
    public void updateAcid(CallbackInfo ci) {
        if(this.isInOrbitAcid()) {
            this.hurt(OrbitDamageSources.ACID, 4.0F);
            this.fallDistance  = 0F;
        }
    }

    public void acidHurt() {
        if (!this.acidImmune()) {
            if (this.hurt(OrbitDamageSources.ACID, 4.0F)) {
                this.playSound(SoundEvents.GENERIC_BURN, 0.4F, 2.0F + this.random.nextFloat() * 0.4F);
            }
        }
    }

    public boolean acidImmune() {
        return false;
    }

}
