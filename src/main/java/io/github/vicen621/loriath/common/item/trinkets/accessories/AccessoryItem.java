package io.github.vicen621.loriath.common.item.trinkets.accessories;

import io.github.vicen621.loriath.common.item.trinkets.TrinketItem;
import net.minecraft.entity.effect.StatusEffectInstance;

public class AccessoryItem extends TrinketItem {

    /**
     * Used to give a Trinket a permanent status effect while wearing it.
     * The StatusEffectInstance is applied every 15 ticks so a duration greater than that is required.
     *
     * @return The {@link StatusEffectInstance} to be applied while wearing this artifact
     */
    public StatusEffectInstance getPermanentEffect() {
        return null;
    }
}
