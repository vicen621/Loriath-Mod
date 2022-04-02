package io.github.vicen621.loriath.mixin.effects.client;

import io.github.vicen621.loriath.LoriathMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StatusEffectUtil.class)
public abstract class MobEffectUtilMixin {

	@Inject(method = "durationToString", at = @At("HEAD"), cancellable = true)
	private static void setFromArtifactString(StatusEffectInstance effect, float multiplier, CallbackInfoReturnable<String> info) {
		ClientPlayerEntity player = MinecraftClient.getInstance().player;

		if (player != null && effect.isPermanent()) {
			LoriathMod.getAllEquipped(player).forEach(stack -> {
				StatusEffectInstance trinketEffect = new StatusEffectInstance(StatusEffects.ABSORPTION); //TODO ((AccesoryItem) stack.getItem()).getPermanentEffect();

				if (trinketEffect != null && trinketEffect.getEffectType() == effect.getEffectType()) {
					info.setReturnValue(stack.getName().getString());
				}
			});
		}
	}
}
