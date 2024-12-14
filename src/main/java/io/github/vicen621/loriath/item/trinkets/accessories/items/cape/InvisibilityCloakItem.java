package io.github.vicen621.loriath.item.trinkets.accessories.items.cape;

import io.github.vicen621.loriath.item.trinkets.accessories.AccessoryItem;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class InvisibilityCloakItem extends AccessoryItem {

    @Override
    public StatusEffectInstance getPermanentEffect() {
        return new StatusEffectInstance(StatusEffects.INVISIBILITY, -1, 0, true, false);
    }
}
