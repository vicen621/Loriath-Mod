package io.github.vicen621.loriath.item.trinkets.accessories.items.extra;

import dev.emi.trinkets.api.SlotReference;
import io.github.vicen621.loriath.item.trinkets.accessories.AccessoryItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class ShinyStoneItem extends AccessoryItem {

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        stack.getOrCreateNbt().putInt("shinyStoneTicks", 0);
    }

    @Override
    public void tick(ItemStack stack, LivingEntity entity) {
        NbtCompound tag = stack.getOrCreateNbt();

        int ticks = tag.getInt("shinyStoneTicks");

        // FIXME: Este trinket no funciona, hay que ver una manera en la cual se pueda identificar que el jugador se esta moviendo
        if (entity.getMovementSpeed() > 0 && entity.isOnGround()) {
            ticks++;
            tag.putInt("shinyStoneTicks", ticks);
            if (ticks >= 60) //3 secs
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 80, 2)); //4 secs
            if (ticks >= 120) //6 secs
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 25, 1));
        } else {
            tag.putInt("shinyStoneTicks", 0);
        }
    }
}
