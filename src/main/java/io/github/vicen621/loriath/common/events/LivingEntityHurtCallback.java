package io.github.vicen621.loriath.common.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

/**
 * Callback for applying Trinket effects when the wearer was hurt in general, same injection point as Forge's LivingHurtEvent
 */
public interface LivingEntityHurtCallback {

	Event<LivingEntityHurtCallback> EVENT = EventFactory.createArrayBacked(LivingEntityHurtCallback.class,
			(listeners) -> (user, source, amount) -> {
				for (LivingEntityHurtCallback listener : listeners) {
					listener.hurt(user, source, amount);
				}
			});

	void hurt(LivingEntity user, DamageSource source, float amount);
}