package io.github.vicen621.loriath.common.item.trinkets.accessories.items.extra;

import io.github.vicen621.loriath.common.item.trinkets.accessories.AccessoryItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;

public class FastClockItem extends AccessoryItem {

    @Override
    public void tick(ItemStack stack, LivingEntity entity) {
        if (entity.hasStatusEffect(StatusEffects.SLOWNESS) && entity.getStatusEffect(StatusEffects.SLOWNESS).getAmplifier() == 0) {
            entity.removeStatusEffect(StatusEffects.SLOWNESS);
        }
    }
}
