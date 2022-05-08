package io.github.vicen621.loriath.mixin.effects.client;

import io.github.vicen621.loriath.common.item.accessories.AccessoryItem;
import io.github.vicen621.loriath.utils.TrinketsHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    /**
     * Show the effect as permanent, which normally only happens if the duration is >= 32767
     * Doing it here makes sure it is set to permanent everytime the server sent an update packet
     */
    @Inject(method = "setStatusEffect", at = @At("HEAD"))
    private void showStatusEffectPermanent(StatusEffectInstance effect, Entity __, CallbackInfo info) {
        //noinspection ConstantConditions
        if ((Object) this instanceof LivingEntity entity) {

            TrinketsHelper.getEquippedAccessories(entity).forEach(stack -> {
                StatusEffectInstance trinketPermEffect = ((AccessoryItem) stack.getItem()).getPermanentEffect();

                if (trinketPermEffect != null && trinketPermEffect.getEffectType() == effect.getEffectType()) {
                    effect.setPermanent(true);
                }
            });
        }
    }
}
