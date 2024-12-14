package io.github.vicen621.loriath.item.trinkets.accessories.items.head;

import io.github.vicen621.loriath.init.ModItems;
import io.github.vicen621.loriath.item.trinkets.accessories.AccessoryItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class DivingGearItem extends AccessoryItem {

    @Override
    public void tick(ItemStack stack, LivingEntity entity) {
        if (entity.getAir() < 100 && entity.isInsideWaterOrBubbleColumn() &&
                entity instanceof PlayerEntity player &&
                !player.getItemCooldownManager().isCoolingDown(ModItems.DIVING_GEAR)) {
            entity.setAir(entity.getAir() + 30);
            player.getItemCooldownManager().set(ModItems.DIVING_GEAR, 60); // 3 seconds
        }
    }
}
