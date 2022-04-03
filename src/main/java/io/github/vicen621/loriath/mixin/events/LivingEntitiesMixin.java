package io.github.vicen621.loriath.mixin.events;

import io.github.vicen621.loriath.events.LivingEntityHurtCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {LivingEntity.class, PlayerEntity.class})
public abstract class LivingEntitiesMixin extends Entity {

    public LivingEntitiesMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "applyDamage", at = @At("HEAD"))
    private void onEntityHurt(DamageSource source, float amount, CallbackInfo info) {
        if (!this.isInvulnerableTo(source)) {
            LivingEntityHurtCallback.EVENT.invoker().hurt((LivingEntity) (Object) this, source, amount);
        }
    }
}
