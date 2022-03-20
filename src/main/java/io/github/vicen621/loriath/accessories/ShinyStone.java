package io.github.vicen621.loriath.accessories;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import io.github.vicen621.loriath.item.CustomItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class ShinyStone extends TrinketItem {

    public ShinyStone() {
        super(new FabricItemSettings().group(CustomItems.ITEM_GROUP).maxCount(1));
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity.getEntityWorld().isClient) return;

        NbtCompound tag = stack.getOrCreateNbt();

        int ticks = tag.getInt("shinyStoneTicks");

        if (entity.getVelocity().x == 0 && entity.getVelocity().z == 0 && entity.isOnGround()) {
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
