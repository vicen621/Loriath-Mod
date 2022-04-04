package io.github.vicen621.loriath.mixin.effects;

import io.github.vicen621.loriath.common.item.accessories.AccessoryItem;
import io.github.vicen621.loriath.utils.TrinketsHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Shadow
	public abstract boolean addStatusEffect(StatusEffectInstance effect);

	@Shadow
	public abstract boolean addStatusEffect(StatusEffectInstance effect, @Nullable Entity source);

	/**
	 * Applies permanent status effects added by trinkets every 15 ticks
	 */
	@Inject(method = "tick", at = @At("TAIL"))
	private void applyPermanentEffects(CallbackInfo info) {
		if (!this.world.isClient && this.age % 15 == 0) {

			TrinketsHelper.getEquippedAccessories((LivingEntity) (Object) this).forEach(stack -> {
				StatusEffectInstance effect = ((AccessoryItem) stack.getItem()).getPermanentEffect();

				if (effect != null) {
					this.addStatusEffect(effect);
				}
			});
		}
	}
}
