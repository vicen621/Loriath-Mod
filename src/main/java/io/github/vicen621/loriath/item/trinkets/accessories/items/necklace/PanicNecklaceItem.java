package io.github.vicen621.loriath.item.trinkets.accessories.items.necklace;

import io.github.vicen621.loriath.events.LivingEvent;
import io.github.vicen621.loriath.init.ModItems;
import io.github.vicen621.loriath.item.trinkets.accessories.AccessoryItem;
import io.github.vicen621.loriath.utils.TrinketsHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class PanicNecklaceItem extends AccessoryItem {

    public PanicNecklaceItem() {
        LivingEvent.LivingEntityHurtCallback.EVENT.register(this::applyEffects);
    }

    private float applyEffects(LivingEntity user, DamageSource source, float amount) {
        if (!user.getWorld().isClient && amount >= 1 && TrinketsHelper.isEquipped(ModItems.PANIC_NECKLACE, user))
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 160, 0, false, false));
        return amount;
    }
}