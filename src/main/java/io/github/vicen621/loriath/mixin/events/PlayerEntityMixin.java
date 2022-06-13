package io.github.vicen621.loriath.mixin.events;

import io.github.vicen621.loriath.common.events.LivingEvent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Unique
    private PlayerEntity get() {
        return (PlayerEntity) (Object) this;
    }

    @ModifyVariable(method = "applyDamage", at = @At(value = "LOAD", ordinal = 0), argsOnly = true)
    private float fireLivingEntityHurtEvent(float amount, DamageSource source) {
        return LivingEvent.LivingEntityHurtCallback.EVENT.invoker().hurt(get(), source, amount);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void playerTick(CallbackInfo info) {
        LivingEvent.LivingEntityUpdateCallback.EVENT.invoker().update(get());
    }
}
