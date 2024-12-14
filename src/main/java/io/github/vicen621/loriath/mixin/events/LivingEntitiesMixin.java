package io.github.vicen621.loriath.mixin.events;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.vicen621.loriath.events.LivingEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class LivingEntitiesMixin extends Entity {

    public LivingEntitiesMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow
    public abstract void setMovementSpeed(float movementSpeed);

    @Unique
    private LivingEntity get() {
        return (LivingEntity) (Object) this;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void onEntityTick(CallbackInfo info) {
        LivingEvent.LivingEntityUpdateCallback.EVENT.invoker().update(get());
    }

    @ModifyVariable(method = "applyDamage", at = @At(value = "LOAD", ordinal = 0), argsOnly = true)
    private float fireLivingEntityHurtEvent(float amount, DamageSource source) {
        return LivingEvent.LivingEntityHurtCallback.EVENT.invoker().hurt(get(), source, amount);
    }

    @ModifyVariable(method = "applyDamage", at = @At(value = "LOAD", ordinal = 6), argsOnly = true)
    private float fireLivingEntityDamageEvent(float amount, DamageSource source) {
        return LivingEvent.LivingEntityDamageCallback.EVENT.invoker().damage(get(), source, amount);
    }

    @Inject(method = "handleFallDamage", at = @At("HEAD"), cancellable = true)
    private void causeFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if (!LivingEvent.LivingEntityFallCallback.EVENT.invoker().fall(get(), fallDistance, damageMultiplier))
            cir.setReturnValue(false);
    }

    @Inject(method = "jump", at = @At("TAIL"))
    private void onJump(CallbackInfo info) {
        LivingEvent.LivingEntityJumpCallback.EVENT.invoker().jump(get());
    }

    @Inject(method = "getEquipmentChanges", at = @At(value = "INVOKE",
            target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;")
    )
    private void onItemEquip(CallbackInfoReturnable<Map<EquipmentSlot, ItemStack>> cir, @Local EquipmentSlot equipmentSlot, @Local(ordinal = 0) ItemStack itemStack, @Local(ordinal = 1) ItemStack itemStack2) {
        LivingEvent.LivingEntityEquipmentChangeCallback.EVENT.invoker().changeEquipment(get(), equipmentSlot, itemStack, itemStack2);
    }
}
