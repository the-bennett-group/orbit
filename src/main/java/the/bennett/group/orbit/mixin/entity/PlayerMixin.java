package the.bennett.group.orbit.mixin.entity;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import the.bennett.group.orbit.rules.OrbitRules;
import the.bennett.group.orbit.world.damagesource.OrbitDamageSources;

@Mixin(Player.class)
public abstract class PlayerMixin extends Entity {
    public PlayerMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }


    @Inject(method = "isInvulnerableTo(Lnet/minecraft/world/damagesource/DamageSource;)Z",
            at = @At(value= "TAIL"), cancellable = true)
    private void detectIfInvulnerableToAcid(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if(damageSource == OrbitDamageSources.ACID) {
            cir.setReturnValue(!this.level.getGameRules().getBoolean(OrbitRules.RULE_DO_ACID_DAMAGE));
        }
        cir.cancel();
    }
}
