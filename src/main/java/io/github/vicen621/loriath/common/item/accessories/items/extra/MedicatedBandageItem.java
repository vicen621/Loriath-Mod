package io.github.vicen621.loriath.common.item.accessories.items.extra;

import io.github.vicen621.loriath.common.item.accessories.AccessoryItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;

public class MedicatedBandageItem extends AccessoryItem {

    @Override
    public void tick(ItemStack stack, LivingEntity entity) {
        if (entity.hasStatusEffect(StatusEffects.WITHER) && entity.getStatusEffect(StatusEffects.WITHER).getAmplifier() == 0) {
            entity.removeStatusEffect(StatusEffects.WITHER);
        }
        if (entity.hasStatusEffect(StatusEffects.POISON) && entity.getStatusEffect(StatusEffects.POISON).getAmplifier() == 0) {
            entity.removeStatusEffect(StatusEffects.POISON);
        }
    }
}
