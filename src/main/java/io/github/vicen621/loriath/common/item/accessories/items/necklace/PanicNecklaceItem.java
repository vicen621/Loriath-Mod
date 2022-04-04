package io.github.vicen621.loriath.common.item.accessories.items.necklace;

import io.github.vicen621.loriath.common.item.accessories.AccessoryItem;
import io.github.vicen621.loriath.common.events.LivingEntityHurtCallback;
import io.github.vicen621.loriath.common.init.ModItems;
import io.github.vicen621.loriath.utils.TrinketsHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class PanicNecklaceItem extends AccessoryItem {

	public PanicNecklaceItem() {
		LivingEntityHurtCallback.EVENT.register(PanicNecklaceItem::applyEffects);
	}

	private static void applyEffects(LivingEntity user, DamageSource source, float amount) {
		if (!user.world.isClient && amount >= 1 && TrinketsHelper.isEquipped(ModItems.PANIC_NECKLACE, user)) {
			user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 160, 0, false, false));
		}
	}
}