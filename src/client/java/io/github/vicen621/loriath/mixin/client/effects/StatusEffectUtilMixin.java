package io.github.vicen621.loriath.mixin.client.effects;

import io.github.vicen621.loriath.item.trinkets.accessories.AccessoryItem;
import io.github.vicen621.loriath.utils.TrinketsHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StatusEffectUtil.class)
public abstract class StatusEffectUtilMixin {

    @Inject(method = "getDurationText", at = @At("HEAD"), cancellable = true)
    private static void setFromArtifactString(StatusEffectInstance effect, float multiplier, CallbackInfoReturnable<Text> info) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        if (player != null && effect.isInfinite()) {
            TrinketsHelper.getEquippedAccessories(player).forEach(stack -> {
                StatusEffectInstance trinketEffect = ((AccessoryItem) stack.getItem()).getPermanentEffect();

                if (trinketEffect != null && trinketEffect.getEffectType() == effect.getEffectType()) {
                    info.setReturnValue(stack.getName());
                }
            });
        }
    }
}
