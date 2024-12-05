package io.github.vicen621.loriath.common.item.trinkets.accessories.items.feet;

import io.github.vicen621.loriath.common.item.trinkets.accessories.AccessoryItem;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class FrogLegItem extends AccessoryItem {
    @Override
    public StatusEffectInstance getPermanentEffect() {
        return new StatusEffectInstance(StatusEffects.JUMP_BOOST, -1, 1, true, false);
    }
}
