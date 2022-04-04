package io.github.vicen621.loriath.common.item.accessories.items.extra;

import io.github.vicen621.loriath.common.item.accessories.AccessoryItem;
import io.github.vicen621.loriath.common.events.LivingEntityHurtCallback;
import io.github.vicen621.loriath.common.init.ModItems;
import io.github.vicen621.loriath.utils.TrinketsHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class LavaCharmItem extends AccessoryItem {

    int timer = 0;

    public LavaCharmItem() {
        LivingEntityHurtCallback.EVENT.register(this::onHurt);
    }

    private void onHurt(LivingEntity wearer, DamageSource source, float amount) {
        if (!wearer.world.isClient && amount >= 1 && source == DamageSource.LAVA &&
                wearer instanceof PlayerEntity player && TrinketsHelper.isEquipped(ModItems.LAVA_CHARM, wearer) &&
                !player.getItemCooldownManager().isCoolingDown(ModItems.LAVA_CHARM)) {
            wearer.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 140, 0, false, true));
            player.getItemCooldownManager().set(ModItems.LAVA_CHARM, 100);
        }
    }
}
