package io.github.vicen621.loriath.item.trinkets.accessories.items.extra;

import io.github.vicen621.loriath.item.trinkets.accessories.AccessoryItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;

public class AdhesiveBandageItem extends AccessoryItem {

    @Override
    public void tick(ItemStack stack, LivingEntity entity) {
        if (entity.hasStatusEffect(StatusEffects.WITHER) && entity.getStatusEffect(StatusEffects.WITHER).getAmplifier() == 0) {
            entity.removeStatusEffect(StatusEffects.WITHER);
        }
    }
}
