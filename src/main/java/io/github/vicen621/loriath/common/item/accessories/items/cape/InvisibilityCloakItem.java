package io.github.vicen621.loriath.common.item.accessories.items.cape;

import io.github.vicen621.loriath.common.item.accessories.AccessoryItem;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class InvisibilityCloakItem extends AccessoryItem {

    @Override
    public StatusEffectInstance getPermanentEffect() {
        return new StatusEffectInstance(StatusEffects.INVISIBILITY, 20, 0, true, false);
    }
}
