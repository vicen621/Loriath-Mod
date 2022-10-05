package io.github.vicen621.loriath.mixin.events;

import io.github.vicen621.loriath.common.events.LivingEvent;
import io.github.vicen621.loriath.common.init.ModItems;
import io.github.vicen621.loriath.common.item.trinkets.accessories.items.hand.DiggingClawsItem;
import io.github.vicen621.loriath.utils.TrinketsHelper;
import net.fabricmc.fabric.api.mininglevel.v1.MiningLevelManager;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract PlayerInventory getInventory();

    @Unique
    private PlayerEntity get() {
        return (PlayerEntity) (Object) this;
    }

    @ModifyVariable(method = "applyDamage", at = @At(value = "LOAD", ordinal = 0), argsOnly = true)
    private float fireLivingEntityHurtEvent(float amount, DamageSource source) {
        return LivingEvent.LivingEntityHurtCallback.EVENT.invoker().hurt(get(), source, amount);
    }

    @ModifyVariable(method = "applyDamage", at = @At(value = "LOAD", ordinal = 6), argsOnly = true)
    private float fireLivingEntityDamageEvent(float amount, DamageSource source) {
        return LivingEvent.LivingEntityDamageCallback.EVENT.invoker().damage(get(), source, amount);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void playerTick(CallbackInfo info) {
        LivingEvent.LivingEntityUpdateCallback.EVENT.invoker().update(get());
    }

    @Inject(method = "canHarvest", at = @At("HEAD"), cancellable = true)
    private void increaseBaseMiningLevel(BlockState state, CallbackInfoReturnable<Boolean> info) {
        if (TrinketsHelper.isEquipped(ModItems.DIGGING_CLAWS, this) &&
                DiggingClawsItem.NEW_BASE_MINING_LEVEL >= MiningLevelManager.getRequiredMiningLevel(state) &&
                (!state.isToolRequired() || get().getInventory().getMainHandStack().isSuitableFor(state))) {
            info.setReturnValue(true);
        }
    }

    @ModifyVariable(method = "getBlockBreakingSpeed", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffectUtil;hasHaste(Lnet/minecraft/entity/LivingEntity;)Z"))
    private float increaseMiningSpeed(float f) {
        return TrinketsHelper.isEquipped(ModItems.DIGGING_CLAWS, this) ?
                f + DiggingClawsItem.MINING_SPEED_INCREASE : f;
    }
}
