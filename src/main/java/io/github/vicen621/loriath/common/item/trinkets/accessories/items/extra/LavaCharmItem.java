package io.github.vicen621.loriath.common.item.trinkets.accessories.items.extra;

import io.github.vicen621.loriath.common.events.LivingEvent;
import io.github.vicen621.loriath.common.init.ModItems;
import io.github.vicen621.loriath.common.item.trinkets.accessories.AccessoryItem;
import io.github.vicen621.loriath.utils.TrinketsHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.NotNull;

public class LavaCharmItem extends AccessoryItem {

    int timer = 0;

    public LavaCharmItem() {
        LivingEvent.LivingEntityHurtCallback.EVENT.register(this::onHurt);
    }

    private float onHurt(@NotNull LivingEntity wearer, DamageSource source, float amount) {
        if (!wearer.getWorld().isClient && amount >= 1 && source == wearer.getWorld().getDamageSources().lava() &&
                wearer instanceof PlayerEntity player && TrinketsHelper.isEquipped(ModItems.LAVA_CHARM, wearer) &&
                !player.getItemCooldownManager().isCoolingDown(ModItems.LAVA_CHARM)) {
            wearer.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 140, 0, false, true));
            player.getItemCooldownManager().set(ModItems.LAVA_CHARM, 240);
            return 0;
        }
        return amount;
    }
}
