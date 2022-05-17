package io.github.vicen621.loriath.mixin.events;

import io.github.vicen621.loriath.common.events.LivingEntityHurtCallback;
import io.github.vicen621.loriath.common.events.LivingEntityUpdateCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "applyDamage", at = @At("HEAD"), cancellable = true)
    private void onEntityHurt(DamageSource source, float amount, CallbackInfo info) {
        if (!this.isInvulnerableTo(source) && amount > 0.0F) {
            if (!LivingEntityHurtCallback.EVENT.invoker().hurt(this, source, amount))
                info.cancel();
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void onEntityTick(CallbackInfo info) {
        LivingEntityUpdateCallback.EVENT.invoker().update(this);
    }
}
